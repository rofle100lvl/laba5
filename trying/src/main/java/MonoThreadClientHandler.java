import commandDescriptions.CommandDescription;
import utils.CommandManager;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;
    CommandManager commandManager;
    public MonoThreadClientHandler(Socket client, CommandManager commandManager) {
        MonoThreadClientHandler.clientDialog = client;
        this.commandManager = commandManager;
    }
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    @Override
    public void run() {

        try {
            // инициируем каналы общения в сокете, для сервера

            // канал записи в сокет следует инициализировать сначала канал чтения для избежания блокировки выполнения программы на ожидании заголовка в сокете
            ObjectOutputStream out = new ObjectOutputStream(clientDialog.getOutputStream());

// канал чтения из сокета
            ObjectInputStream in = new ObjectInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");

            System.out.println("DataOutputStream  created");
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // начинаем диалог с подключенным клиентом в цикле, пока сокет не
            // закрыт клиентом

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");

                // серверная нить ждёт в канале чтения (inputstream) получения
                // данных клиента после получения данных считывает их
                CommandDescription commandDescription;
                try {
                    commandDescription = (CommandDescription) in.readObject();
                    Callable<Response> task = () -> commandManager::launchCommand(commandDescriptionS);
                } catch (ClassNotFoundException ignored) {
                    continue;
                }
                executeIt.execute(new Thread(commandManager::launchCommand(commandDescription)));
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сокет общения с клиентом в нити моносервера
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}