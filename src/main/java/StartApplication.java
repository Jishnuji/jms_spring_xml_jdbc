import dao.ReceivedMessageDaoJDBCImpl;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class StartApplication {
    private static final Logger LOG = LoggerFactory.getLogger(StartApplication.class);
    public static void main(String[] args)  {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOG.error("Current thread was interrupted. " + e);
        }
    }
}
