import support.ServerLogger;
import utils.NIOServer;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            NIOServer nioServer = new NIOServer();
            nioServer.run();
        }catch (FileNotFoundException e){
            ServerLogger.logger.
                    info("Файл настроек сейчас не доступен, попробуйте " +
                            "создать новый server.properties и повторите попытку");
        }
        catch (JAXBException | IllegalAccessException e){
            ServerLogger.logger.
                    info("Файла коллекции не существует или выставлены неправильные" +
                         "права доступа. Укажите другой файл.");
        }
    }
}
