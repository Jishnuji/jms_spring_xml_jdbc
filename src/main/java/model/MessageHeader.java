package model;

import dao.ReceivedMessageDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import util.Util;

import javax.jms.JMSException;
import javax.jms.Message;

public class MessageHeader {
    private String messageId;
    private int deliveryMode;
    private String destination;
    private long messageTimestamp;
    private int priority;

    private static ReceivedMessageDao receivedMessageDao = new ReceivedMessageDao(new DriverManagerDataSource());

    public void getMessageHeaders(Message message) throws JMSException {
        this.messageId = message.getJMSMessageID();
        this.deliveryMode = message.getJMSDeliveryMode();
        this.destination = (message.getJMSDestination()).toString();
        this.messageTimestamp = message.getJMSTimestamp();
        this.priority = message.getJMSPriority();

        receivedMessageDao.saveMessageHeaderDao(deliveryMode, destination, messageTimestamp, messageId, priority);
    }
}
