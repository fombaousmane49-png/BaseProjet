package singleton;

import java.sql.*;

public class Singleton {
    private Connection connection;
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static volatile Singleton instance;

    private Singleton() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Singleton getConnection() throws SQLException {
       if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
