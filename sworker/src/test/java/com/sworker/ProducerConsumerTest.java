package com.sworker;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.jms.ProducerService;
import com.sworker.jms.entity.Email;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext-jms.xml")
public class ProducerConsumerTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("emailDestination")
    private Destination destination;
    
    @Autowired  
    @Qualifier("sessionAwareQueue")  
    private Destination sessionAwareQueue;  
    
    @Test
    public void testSend() {
        for (int i = 0; i < 2; i++) {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i + 1));
        }
    }

    @Test
    public void testSend2() {
        for (int i = 0; i < 2; i++) {
            Email email = new Email("lizc", "kobe", "message" + i);

            producerService.sendMessage(destination, email);
        }
    }
    
    @Test  
    public void testSessionAwareMessageListener() {  
        producerService.sendMessage(sessionAwareQueue, "测试SessionAwareMessageListener");  
    }  

}
