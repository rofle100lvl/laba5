package utils;
import startClasses.Flat;

import java.sql.*;
import java.util.LinkedList;

public class Database {
    private String url = "jdbc:postgresql://localhost:5432/FlatsTable";
    private String userName = "postgres";
    private String pass= "zju532";
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private Connection connection;

    public void openConnection() throws ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, userName, pass);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при подключении к базе данных!");
        } catch (ClassNotFoundException exception) {
            System.out.println("Драйвер управления базой дынных не найден!");
        }
    }

    public void closeConnection() {
        if (connection == null) return;
        try {
            connection.close();
            System.out.println("Соединение с базой данных разорвано.");
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при разрыве соединения с базой данных!");
        }
    }

    public void createIfExist() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS flats(" +
                "id SERIAL," +
                "flatname text NOT NULL, " +
                "Xcoordinate float NOT NULL ," +
                "Ycoordinate float NOT NULL, " +
                "area float NOT NULL, " +
                "numberOfRooms INTEGER NOT NULL," +
                "price INTEGER NOT NULL," +
                "balcony BOOLEAN NOT NULL," +
                "furnish text NOT NULL," +
                "houseName text NOT NULL," +
                "houseYear BIGINT NOT NULL," +
                "houseNumberOfFloors integer NOT NULL," +
                "houseNumberOfFlatsOnFloor integer NOT NULL," +
                "numberOfLifts integer NOT NULL," +
                "login text NOT NULL PRIMARY KEY)");
        statement.close();
    }

    
    public void unmarshal(LinkedList<Flat>flats) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = null;
        try{
            rs = statement.executeQuery("SELECT * FROM FlatsTable");
            while(rs.next()){

            }
        }
    }
}
