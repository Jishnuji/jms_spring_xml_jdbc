 package service;

 import model.MessageEntity;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 import javax.jms.JMSException;
 import javax.jms.Message;
 import javax.jms.MessageListener;
 import javax.jms.TextMessage;

public class MessageReceiver implements MessageListener{
    private MessageEntity messageEntity;
    private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
    private static final Logger LOG_RECEIVED_MESSAGE = LoggerFactory.getLogger("messageReceiver");

    public MessageReceiver(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
    }

    @Override
    public void onMessage(Message message) {
        try {
            LOG_RECEIVED_MESSAGE.info("Received text: " + ((TextMessage) message).getText());
            messageEntity.setHeadersAndBody(message, messageEntity);
        } catch (JMSException e) {
            LOG.error("There is some JMSException occurred while getting text from message. " + e);
        }
    }
}
