package com.sworker.dao;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwAttachmentEntity;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class AttachmentDaoTests {

	@Resource 
	private  IAttachmentDao attachmentdao;
	
	/**
	 * 附件存储测试
	 */
	@Test
	public void saveTest() throws Exception{
		
		SwAttachmentEntity att=new SwAttachmentEntity();
		
		att.setBusinessid(new Long(1));
		
		att.setFileid(new Long(12121));
		
		att.setFolder("C:\\test");
		
		att.setName("abc");
		att.setSuffix(".jpg");
		att.setType(0);
		att.setUrl("www.baidu.com");
	
		attachmentdao.saveAttachment(att);
		
		
	}
	
	/**
	 * 通过附件ID删除附件测试
	 */
	@Test
	public void deleteAttachmentByIdTests() throws Exception{
		
		Long bi=new Long(622592);
		
		attachmentdao.deleteAttachmentById(bi);
		
		
	}
	
	
	/**
	 * 通过业务ID删除附件测试
	 */
	@Test
	public void deleteAttachmentByBizIDTests() throws Exception{
		
		
		Long bi =new Long(1);
		
		attachmentdao.deleteAttachmentByBizID(bi);
		
		
	}
	
	/**
	 * 通过业务ID 连接查询 附件信息表和文件信息表，得到附件ID再通过附件ID查询附件实体
	 * @throws Exception
	 */
	
	@Test
	public void findAttachmentsByBizIDTests() throws Exception{
		
		
	attachmentdao.findAttachmentsByBizID(new Long(1));
		
	}
	
	
	
}
