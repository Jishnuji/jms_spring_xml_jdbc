package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MessageReceiver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Logger LOG = LoggerFactory.getLogger(Util.class);

    public Connection getConnect () {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception throwable) {
            LOG.error("There is some error occurred " + throwable);
        }
        return connection;
    }
}
