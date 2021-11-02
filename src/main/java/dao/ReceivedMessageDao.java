package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ReceivedMessageDao {
    private  Util util;
    private static Logger LOG = LoggerFactory.getLogger(ReceivedMessageDao.class);

    public ReceivedMessageDao(Util util) {
        this.util = util;
    }

    public void createMessageBodyTable() {
        String sql = "CREATE TABLE IF NOT EXISTS message_body (id bigserial NOT NULL," +
                "message_id text not NULL, body text NULL, CONSTRAINT message_body_pk PRIMARY KEY (id)," +
                "CONSTRAINT message_body_un UNIQUE (message_id)," +
                "CONSTRAINT message_body_fk FOREIGN KEY (message_id) REFERENCES message_header(message_id)";
        try (Connection connection = util.getConnect(); Statement statement = connection.createStatement()){
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement.execute(sql);
            connection.commit();
        } catch (SQLException throwable) {
            LOG.error("There is some error occurred " + throwable);
        }
    }

    public void createMessageHeaderTable() {
        String sql = "CREATE TABLE IF NOT EXISTS message_header (id bigserial NOT NULL," +
                "delivery_mode int NULL, destination text NULL, created bigint NULL, message_id text NULL," +
                "priority int NULL," +
                "CONSTRAINT message_header_pk PRIMARY KEY (id), CONSTRAINT message_header_un UNIQUE (message_id)";
        try (Connection connection = util.getConnect(); Statement statement = connection.createStatement()){
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement.execute(sql);
            connection.commit();
        } catch (SQLException throwable) {
            LOG.error("There is some error occurred " + throwable);
        }
    }

    public void saveMessageBodyDao (String messageID, String messageBody) {
        String sql = "INSERT INTO message_body(message_id, body) VALUES (?, ?)";
        try(Connection connection = util.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)){
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement.setString(1, messageID);
            statement.setString(2, messageBody);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException throwable) {
            LOG.error("There is some error occurred " + throwable);
        }
    }

    public void saveMessageHeaderDao (int deliveryMode, String destination, long messageTimestamp,
                                      String messageId, int priority) {
        String sql = "INSERT INTO message_header(delivery_mode, destination, created, message_id, priority) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = util.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)){
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement.setInt(1, deliveryMode);
            statement.setString(2, destination);
            statement.setLong(3, messageTimestamp);
            statement.setString(4, messageId);
            statement.setInt(5, priority);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException throwable) {
            LOG.error("There is some error occurred " + throwable);
        }
    }
}
