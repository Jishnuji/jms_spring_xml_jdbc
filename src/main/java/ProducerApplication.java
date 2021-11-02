import dao.ReceivedMessageDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import util.Util;

import java.sql.Connection;

public class ProducerApplication {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

//        ReceivedMessageDao receivedMessageDao = context.getBean(ReceivedMessageDao.class);
//        receivedMessageDao.createMessageHeaderTable();
//        receivedMessageDao.createMessageBodyTable();

        DriverManagerDataSource basicDataSource = context.getBean(DriverManagerDataSource.class);
        Connection connection = basicDataSource.getConnection("postgres", "root");
        System.out.println(connection);
//
//        Util util = new Util();
//        Connection connectionUtil = util.getConnect();
//        System.out.println(connectionUtil);

        Thread.sleep(2000);

    }
}
