package com.qiaoyansong;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/6/19 15:56
 * description：
 */

@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message != null && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收到的消息为" + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
