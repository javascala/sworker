package com.sworker.jms.listener;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.sworker.jms.entity.Email;


public class PhoneMessageListener extends MessageListenerBase implements MessageListener {

    public void execute(ObjectMessage objMessage) throws JMSException {

            Email email = (Email) messageConverter.fromMessage(objMessage);
            System.out.println("phone接收到一个ObjectMessage，包含Email对象。");
            System.out.println(email);

    }
}
