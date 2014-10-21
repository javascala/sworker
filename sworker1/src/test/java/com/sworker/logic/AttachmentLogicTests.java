package com.sworker.logic;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwAttachmentEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class AttachmentLogicTests {

	@Resource
	private IAttachmentLogic attachmentlogic;
	
	@Test
	public void createAttachmentTests() throws Exception{
		
		SwAttachmentEntity attachment=new SwAttachmentEntity();
		
		attachment.setBusinessid(new Long(1));
		attachment.setFileid(new Long(2));
		attachment.setFolder("c://text");
		attachment.setName("aaa");
		attachment.setSuffix(".jpg");		
		attachment.setType(0);
		attachment.setUrl("www.baidu.com");
	
		attachmentlogic.createAttachment(attachment);
		
		
	}
	
	
	
	
	
	
}
