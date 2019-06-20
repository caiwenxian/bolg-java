package controller.test.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSubscriber {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("active-topic-1");

            MessageConsumer messageConsumer = session.createConsumer(topic);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String msg = ((TextMessage)message).getText();
                        System.out.println("消费者1收到的消息:" + msg);
                    } catch (JMSException e) {

                    }
                }
            });

            MessageConsumer messageConsumer2 = session.createConsumer(topic);
            messageConsumer2.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String msg = ((TextMessage)message).getText();
                        System.out.println("消费者2收到的消息:" + msg);
                    } catch (JMSException e) {

                    }
                }
            });

            session.close();
            connection.close();
        } catch (JMSException e){

        }
    }
}
