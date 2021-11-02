 package service;

import dao.ReceivedMessageDao;
import model.MessageBody;
import model.MessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.jms.*;

public class MessageReceiver implements MessageListener{
    private MessageBody messageBody;
    private MessageHeader messageHeader;
    private static Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
    private static Logger LOG2 = LoggerFactory.getLogger("messageReceiver");

    public MessageReceiver(MessageBody messageBody, MessageHeader messageHeader) {
        this.messageBody = messageBody;
        this.messageHeader = messageHeader;
    }

    @Override
    public void onMessage(Message message) {
        try {
            LOG2.info("Received text: " + ((TextMessage) message).getText());
            messageHeader.getMessageHeaders(message);
            messageBody.getMessageBody(message);

        } catch (JMSException e) {
            LOG.error("There is some error occurred " + e);
        }
    }
}
