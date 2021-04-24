import exceptions.ArgumentsCountException;
import exceptions.UnknownCommandNameException;
import utils.Request;

import java.io.*;
import java.net.Socket;

public class App {
    private final BufferedReader reader;
    private final CommandDescriptionFactory commandDescriptionFactory;
    private final Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public App(Socket socket) {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandDescriptionFactory = new CommandDescriptionFactory();
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String argument = reader.readLine();
                String[] words = argument.trim().replaceAll(" +", " ").split(" ", 2);

                String args;
                String name = words[0];
                try {
                    args = words[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    args = "";
                }
                objectOutputStream.writeObject(commandDescriptionFactory.getCommandDescription(name, args));
                try {
                    Request request = (Request) objectInputStream.readObject();
                    request.printRequest();
                } catch (ClassNotFoundException e) {
                    System.out.println("Класса не существует");
                }


            } catch (IOException ignored) {
            } catch (UnknownCommandNameException | ArgumentsCountException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
