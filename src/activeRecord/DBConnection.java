package activeRecord;

import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static DBConnection instance;
    private static Connection connection;

    // variables a modifier en fonction de la base
    private String userName = "root";
    private String password = "";
    private String serverName = "localhost";
    // Attention, sous MAMP, le port est 8889
    private String portNumber = "3306";
    private String tableName = "serie.Serie";

    // iL faut une base nommee testSerie !
    private static String dbName = "testSerie";

    public DBConnection() throws SQLException {
        // creation de la connection
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        connection = DriverManager.getConnection(urlDB, connectionProps);

    }

    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null)
            instance = new DBConnection();
        return instance;
    }

    public static void setNomDB(String nomDB) throws SQLException {
        dbName = nomDB;
        instance = new DBConnection();
    }

    public static Connection getConnection() {
        return connection;
    }

}