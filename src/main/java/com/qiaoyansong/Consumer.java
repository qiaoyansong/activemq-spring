package com.qiaoyansong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/6/19 15:20
 * description：
 */
@Component
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 因为静态方法只能调用静态的属性，然而AutoWired是无法注入静态属性的，因此使用下面的方法
     */
    public static void main(String[] args) {
        runWithReceive();
    }

    /**
     * 使用Receive接受消息
     */
    private static void runWithReceive(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Consumer main = atx.getBean("consumer", Consumer.class);
        String o = (String)main.jmsTemplate.receiveAndConvert();
        System.out.println("接收到的消息为" + o);
    }

}
