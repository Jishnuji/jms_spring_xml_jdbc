package model;

import dao.ReceivedMessageDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import util.Util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageBody {

    private String textBody;
    private String messageID;
    private static ReceivedMessageDao receivedMessageDao;

    public void setReceivedMessageDao(ReceivedMessageDao receivedMessageDao) {
        MessageBody.receivedMessageDao = receivedMessageDao;
    }

    public void getMessageBody(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        this.textBody = textMessage.getText();
        this.messageID = message.getJMSMessageID();

        setReceivedMessageDao(new ReceivedMessageDao(new DriverManagerDataSource()));
        receivedMessageDao.saveMessageBodyDao(messageID, textBody);
    }
}
