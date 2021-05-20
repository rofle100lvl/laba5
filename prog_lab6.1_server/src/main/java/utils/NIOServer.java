package utils;

import commandDescriptions.CommandDescription;
import commandDescriptions.ExitDescription;
import support.CommandName;
import support.ServerLogger;


import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class NIOServer {

    private static final Map<SocketChannel, CommandDescription> sockets = new ConcurrentHashMap<>();
    private final CommandManager commandManager;
    private final CollectionManager collectionManager;
    private final ServerSocketChannel serverChannel;
    Selector selector;
    Properties properties;

    private int getPort() {
        return Integer.parseInt(properties.getProperty("PORT"));
    }
    private String  getCollectionFile(){
        return properties.getProperty("collectionFileUri");
    }

    public NIOServer() throws IOException, JAXBException, IllegalAccessException {
        properties = new Properties();
        properties.load(new FileInputStream("server.properties"));
        collectionManager = Parser.fromXmlToObject(getCollectionFile());
        commandManager = new CommandManager(collectionManager);
        collectionManager.setCommandManager(commandManager);
        serverChannel = ServerSocketChannel.open();
        serverChannel.socket().
                bind(new InetSocketAddress(getPort()));
        serverChannel.configureBlocking(false);
    }

    public void run() throws IOException {
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        log("Сервер начал свою работу");
        while (true) {
            selector.select();
            for (SelectionKey key : selector.selectedKeys()) {
                if (key.isValid()) {
                    try {
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = serverChannel.accept();
                            socketChannel.configureBlocking(false);
                            log("Connected " + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            try {
                                byte[] buffer = new byte[2048];
                                int bytesRead = socketChannel.read(ByteBuffer.wrap(buffer));
                                log("Reading from " + socketChannel.getRemoteAddress() + ", bytes read=" + bytesRead);
                                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream
                                        (buffer));
                                sockets.put(socketChannel, (CommandDescription) objectInputStream.readObject());
                                // Detecting end of the message
                                socketChannel.register(selector, SelectionKey.OP_WRITE);
                            }catch (IOException ignored){
                                sockets.remove(socketChannel);
                                log(String.format("Подключение с клиентом %s разорвано",  socketChannel.getRemoteAddress()));
                                commandManager.launchCommand(new ExitDescription());
                                socketChannel.close();
                            }

                        } else if (key.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            Response response = commandManager.launchCommand(sockets.get(socketChannel));

                            // делаем ответ
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
                            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);

                            outputStream.writeObject(response);
                            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());

                            int bytesWritten = socketChannel.write(byteBuffer);
                            log("Writing to " + socketChannel.getRemoteAddress() + ", bytes written=" + bytesWritten);

                            if (sockets.get(socketChannel).getName() == CommandName.EXIT){
                                socketChannel.close();
                                continue;
                            }
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        log(e.getMessage());
                    }
                }
            }

            selector.selectedKeys().clear();
        }
    }

    private static void log(String message) {
        ServerLogger.logger.info(message);
    }
}