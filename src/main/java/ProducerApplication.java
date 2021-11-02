import dao.ReceivedMessageDao;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ProducerApplication {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        ReceivedMessageDao receivedMessageDao = context.getBean(ReceivedMessageDao.class);
        receivedMessageDao.createMessageBodyTable();
        receivedMessageDao.createMessageHeaderTable();

        Thread.sleep(2000);

    }
}
