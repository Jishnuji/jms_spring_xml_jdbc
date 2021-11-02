package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class ScheduledMessageSender {
    int count = 1;
    private MessageSender messageSender;

    public ScheduledMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void startMessageSender() {
        messageSender.sendMessage("Hello! " + count);
        count++;
    }
}
