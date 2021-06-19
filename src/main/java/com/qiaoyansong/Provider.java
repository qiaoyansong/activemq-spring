package com.qiaoyansong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/6/19 15:20
 * description：
 */
@Component
public class Provider {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 因为静态方法只能调用静态的属性，然而AutoWired是无法注入静态属性的，因此使用下面的方法
     */
    public static void main(String[] args) {
        runWithReceive();
    }

    /**
     * 使用正常的生产者发送消息 消费者接收消息
     */
    public static void runWithReceive(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Provider main = atx.getBean("provider", Provider.class);
        main.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("spring-provider-textMessage");
            return textMessage;
        });
        System.out.println("消息发送完毕");
    }

    /**
     * 使用监听器 不需要启动消费者即只启动生产者 也可以接收消息
     */
    public static void runWithListener(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        Provider main = atx.getBean("provider", Provider.class);
        main.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("spring-provider-textMessage");
            return textMessage;
        });
        System.out.println("消息发送完毕");
    }
}
