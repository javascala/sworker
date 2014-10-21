package com.sworker.jms.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.sworker.jms.entity.Email;


public abstract class MessageListenerBase implements MessageListener {

	public MessageConverter messageConverter;
	
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			//这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换，或者直接把onMessage方法的参数改成Message的子类TextMessage
			TextMessage textMsg = (TextMessage) message;
			System.out.println("接收到一个纯文本消息。");
			try {
				System.out.println("消息内容是：" + textMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
		} else if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				/*Object obj = objMessage.getObject();
				Email email = (Email) obj;*/
			    execute(objMessage);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
	}

	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

    public abstract void execute(ObjectMessage objMessage) throws JMSException;
}
