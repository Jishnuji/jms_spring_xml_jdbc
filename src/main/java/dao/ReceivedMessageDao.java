package dao;

import model.MessageEntity;

public interface ReceivedMessageDao {
    void save(MessageEntity messageEntity);
}
