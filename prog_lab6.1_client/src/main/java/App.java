import exceptions.ArgumentsCountException;
import exceptions.LimitOfReconnectionsException;
import exceptions.UnknownCommandNameException;
import utils.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class App {
    private final BufferedReader reader;
    private final CommandDescriptionFactory commandDescriptionFactory;
    private final Connector connector;
    private Response serverResponse;

    public App() throws LimitOfReconnectionsException, UnknownHostException {
        connector = new Connector(11953);
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandDescriptionFactory = new CommandDescriptionFactory();
    }

    public void run() throws IOException, LimitOfReconnectionsException {
        while (true) {
            if(System.in.available() > 0){
                String s = reader.readLine();
                try {
                    String[] words = splitWords(s);
                    connector.send(commandDescriptionFactory.getCommandDescription(words[0], words[1]));
                } catch (UnknownCommandNameException e) {
                    e.printStackTrace();
                } catch (ArgumentsCountException e) {
                    e.printStackTrace();
                }
            }
            serverResponse = connector.receive();
            if(serverResponse != null){
                serverResponse.printResponse();
            }
        }
    }

    public String[] splitWords(String request){
        request = request.trim().replaceAll(" +", " ");
        String[] words = new String[2];
        String commandName = request.split(" ",1)[0];
        words[0] = commandName;
        try {
            String argument = request.split(" ",1)[1];
            words[1] = argument;
        }
        catch (ArrayIndexOutOfBoundsException e){
            words[1] = "";
        }
        return words;
    }
}
