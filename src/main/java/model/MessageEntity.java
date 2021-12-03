package model;

import dao.ReceivedMessageDao;
import dao.ReceivedMessageDaoJDBCImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageEntity {
    private static ReceivedMessageDao receivedMessageDao;
    private String messageId;
    private int deliveryMode;
    private String destination;
    private long messageTimestamp;
    private int priority;
    private String textBody;
    private static final Logger LOG = LoggerFactory.getLogger(MessageEntity.class);

    public void setReceivedMessageDao(ReceivedMessageDaoJDBCImpl receivedMessageDaoJDBCImpl) {
        receivedMessageDao = receivedMessageDaoJDBCImpl;
    }

    public void setHeadersAndBody(Message message, MessageEntity entity) {
        TextMessage textMessage = (TextMessage) message;

        try {
            this.textBody = textMessage.getText();
            this.messageId = message.getJMSMessageID();
            this.deliveryMode = message.getJMSDeliveryMode();
            this.destination = (message.getJMSDestination()).toString();
            this.messageTimestamp = message.getJMSTimestamp();
            this.priority = message.getJMSPriority();
        } catch (JMSException e) {
            LOG.error("There is some JMSException occurred while the header and body data was getting from the message. " + e);
        }

        receivedMessageDao.save(entity);
    }

    public String getMessageId() {
        return messageId;
    }

    public int getDeliveryMode() {
        return deliveryMode;
    }

    public String getDestination() {
        return destination;
    }

    public long getMessageTimestamp() {
        return messageTimestamp;
    }

    public int getPriority() {
        return priority;
    }

    public String getTextBody() {
        return textBody;
    }
}
