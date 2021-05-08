

import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.CommandManager;
import utils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NIOServer {

    private static final Map<SocketChannel, CommandDescription> sockets = new ConcurrentHashMap<>();
    private CommandManager commandManager;
    private CollectionManager collectionManager;

    public NIOServer() {
        collectionManager = new CollectionManager();
        commandManager = new CommandManager(collectionManager);
        collectionManager.setCommandManager(commandManager);
    }

    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(45001));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        log("Server started at port 45001. Waiting for connections...");

        while (true) {
            selector.select(); // Blocking call, but only one for everything
            for (SelectionKey key : selector.selectedKeys()) {
                if (key.isValid()) {
                    try {
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = serverChannel.accept(); // Non blocking, never null
                            socketChannel.configureBlocking(false);
                            log("Connected " + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            byte[] buffer = new byte[2048];
                            int bytesRead = socketChannel.read(ByteBuffer.wrap(buffer)); // Reading, non-blocking call
                            log("Reading from " + socketChannel.getRemoteAddress() + ", bytes read=" + bytesRead);

                            // Detecting connection closed from client side
                            if (bytesRead == -1) {
                                log("Connection closed " + socketChannel.getRemoteAddress());
                                sockets.remove(socketChannel);
                                socketChannel.close();
                            }

                            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream
                                    (buffer));
                             sockets.put(socketChannel, (CommandDescription) objectInputStream.readObject());
                            System.out.println(socketChannel);
                            System.out.println(sockets.get(socketChannel));
                            // Detecting end of the message
                            if (bytesRead > 0) {
                                socketChannel.register(selector, SelectionKey.OP_WRITE);
                            }
                        } else if (key.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            byte[] buffer = new byte[1024];
                            System.out.println(socketChannel);
                            System.out.println(sockets.get(socketChannel));
                            Response response = commandManager.launchCommand(sockets.get(socketChannel));

                            // Reading client message from buffer

                            // Building response
                            ByteArrayOutputStream b1 = new ByteArrayOutputStream(2048);
                            ObjectOutputStream outputStream = new ObjectOutputStream(b1);
                            outputStream.writeObject(response);
                            ByteBuffer byteBuffer = ByteBuffer.wrap(b1.toByteArray());
                            int bytesWritten = socketChannel.write(byteBuffer); // woun't always write anything
                            log("Writing to " + socketChannel.getRemoteAddress() + ", bytes writteb=" + bytesWritten);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        log("error " + e.getMessage());
                    }
                }
            }

            selector.selectedKeys().clear();
        }
    }

    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}