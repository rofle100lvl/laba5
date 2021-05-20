import exceptions.LimitOfReconnectionsException;
import utils.UserAsker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

public class ConnectorFabric {
    UserAsker userAsker;

    public ConnectorFabric(UserAsker userAsker) {
        this.userAsker = userAsker;
    }

    public Connector getInputConnector()  {
        while (true) {
            try {
                int PORT, reconnectionTimeout, maxReconnectionAttempts;
                while (true) {
                    System.out.println("Введите порт");
                    try {
                        PORT = Integer.parseInt(userAsker.getUserScanner().readLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы должны ввести число");
                    }
                }
                while (true) {
                    System.out.println("Введите время ожидания между переключениями");
                    try {
                        reconnectionTimeout = Integer.parseInt(userAsker.getUserScanner().readLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы должны ввести число");
                    }
                }
                while (true) {
                    System.out.println("Введите колличество переподключений");
                    try {
                        maxReconnectionAttempts = Integer.parseInt(userAsker.getUserScanner().readLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы должны ввести число");
                    }
                }

                Connector connector = new Connector(PORT, reconnectionTimeout, maxReconnectionAttempts);
                return connector;
            } catch (LimitOfReconnectionsException ignored) {
                continue;
            } catch (IOException e){
                System.out.println("Произошёл сбой, повтор ввода");
                userAsker.setUserScanner(new BufferedReader(new InputStreamReader(System.in)));
                continue;
            }

        }
    }

    private Connector getPropertiesConnector(Properties properties) throws NumberFormatException,
            LimitOfReconnectionsException, NullPointerException{
            int reconnectionTimeout = Integer.parseInt(properties.getProperty("reconnectionTimeout"));
            int maxReconnectionAttempts = Integer.parseInt(properties.getProperty("maxReconnectionAttempts"));
            int PORT = Integer.parseInt(properties.getProperty("PORT"));
            return new Connector(PORT, reconnectionTimeout, maxReconnectionAttempts);
    }


    private Properties loadProperties()  {
        try {
            Properties clientProperties = new Properties();
            String s;
            System.out.println("Введите путь к файлу");
            s = userAsker.getUserScanner().readLine();
            clientProperties.load(new FileInputStream(s));
            return clientProperties;
        } catch (IOException e) {
            return null;
        }
    }

    public Connector getConnector()  {
        System.out.println("Хотите взять данные из файла config.properties? (да/нет)");
        try {
            String answer =userAsker.getUserScanner().readLine().toLowerCase();
            while (true) {
                if (answer.equals("да")) {
                    try {
                        return getPropertiesConnector(loadProperties());
                    } catch (LimitOfReconnectionsException | NumberFormatException | NullPointerException e) {
                        System.out.println("Файл повреждён или недоступен, пожалуйста введите в ручную");
                        return getInputConnector();
                    }
                } else if (answer.equals("нет")){
                    return getInputConnector();
                }
                System.out.println("Ответьте да или нет");
                answer =userAsker.getUserScanner().readLine().toLowerCase();
            }
        } catch (IOException ignored) { }
        return null;
    }
}
