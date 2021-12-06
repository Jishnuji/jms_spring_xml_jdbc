package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageSender {
    private JmsTemplate jmsQueueTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    public void sendMessage(String message) {
        jmsQueueTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)  {
                TextMessage textMessage = null;
                try {
                    textMessage = session.createTextMessage(message);
                } catch (JMSException e) {
                    LOG.error("There is some JMSException occurred in process of message creating. " + e);
                }
                return textMessage;
            }
        });
    }
}