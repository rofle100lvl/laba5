package utils;

import com.sun.corba.se.pept.encoding.InputObject;
import com.sun.org.apache.xpath.internal.axes.OneStepIterator;
import commandDescriptions.CommandDescription;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    ServerSocket serverSocket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    CommandManager commandManager;
    CollectionManager collectionManager;

    private void openConnection() {
        while (true) {
            try {
                serverSocket = new ServerSocket(11953);
                break;
            } catch (IOException e) {
                System.out.println("Порт занят");
            }
        }
    }

    public App() {
        openConnection();
        collectionManager = Parser.fromXmlToObject("collection.xml");
        commandManager = new CommandManager(collectionManager);
        collectionManager.setCommandManager(commandManager);
        work();

    }

    public void work() {
        Socket socket = null;
        while (true) {
            try {
                System.out.println("Ждёт ответ от клиента");
                socket = serverSocket.accept();
                System.out.println("Connection произошёл");
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                CommandDescription commandDescription = (CommandDescription) objectInputStream.readObject();
                objectOutputStream.writeObject(commandManager.launchCommand(commandDescription));
                commandManager.launchCommand(commandDescription).printResponse();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
