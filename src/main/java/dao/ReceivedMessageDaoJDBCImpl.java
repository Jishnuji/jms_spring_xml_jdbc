package dao;

import model.MessageEntity;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReceivedMessageDaoJDBCImpl implements ReceivedMessageDao {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_BODY = "INSERT INTO message_body(message_id, body) VALUES (?, ?)";
    private static final String INSERT_HEADER = "INSERT INTO message_header(delivery_mode, destination, created, " +
            "message_id, priority) VALUES (?, ?, ?, ?, ?)";

    public ReceivedMessageDaoJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(MessageEntity entity) {
        jdbcTemplate.update(INSERT_HEADER, entity.getDeliveryMode(), entity.getDestination(), entity.getMessageTimestamp(),
                entity.getMessageId(), entity.getPriority());
        jdbcTemplate.update(INSERT_BODY, entity.getMessageId(), entity.getTextBody());
    }
}