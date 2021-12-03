package service;

public class ScheduledMessageSender {
    private int count = 1;
    private MessageSender messageSender;

    public ScheduledMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void startMessageSender() {
        messageSender.sendMessage("Hello! " + count);
        count++;
    }
}
