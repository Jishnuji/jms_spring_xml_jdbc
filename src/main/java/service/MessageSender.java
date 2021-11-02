package service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageSender {
    private JmsTemplate jmsQueueTemplate;

    public MessageSender(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    public void sendMessage(String message) {
        jmsQueueTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
    }
}