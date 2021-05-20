import support.ServerLogger;
import utils.Database;
import utils.NIOServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Database database = new Database();
        database.openConnection();
        database.createIfExist();
    }
}
