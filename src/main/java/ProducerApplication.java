import dao.ReceivedMessageDao;
import org.apache.activemq.broker.BrokerService;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class ProducerApplication {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:6616");
        broker.setBrokerName("broker");
        broker.setPersistent(false);
        broker.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ReceivedMessageDao receivedMessageDao = context.getBean(ReceivedMessageDao.class);
//        receivedMessageDao.createMessageHeaderTable();
//        receivedMessageDao.createMessageBodyTable();
        Thread.sleep(2000);

    }
}
