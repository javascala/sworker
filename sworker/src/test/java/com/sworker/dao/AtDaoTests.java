package com.sworker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwAtInfoEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class AtDaoTests {

	
	@Resource
	private IAtDao atdao;
	
	
	/**
	 * 插入测试
	 * 
	 * @throws Exception
	 */
	
	@Test
	public void insertTest() throws Exception{
		
		SwAtInfoEntity at1=new SwAtInfoEntity();
		
		at1.setApptypeid("aaa");
		at1.setAssociateid(new Long(1));
		at1.setAtobjid(new Long(2));
		at1.setAtobjtype("xxxx");

		
		SwAtInfoEntity at2=new SwAtInfoEntity();
		at2.setApptypeid("bbb");
		at2.setAssociateid(new Long(3));
		at2.setAtobjid(new Long(4));
		at2.setAtobjtype("yyyy");
		
		List<SwAtInfoEntity> AtObjs=new ArrayList<SwAtInfoEntity>();
		
		AtObjs.add(at1);
		AtObjs.add(at2);
		atdao.insertAll(AtObjs);
		
	}
	
	/**
	 * 删除测试
	 * @throws Exception
	 */

	@Test
	public void deleteTest() throws Exception{
		
		atdao.deleteAtObjByParams(new Long(1), "aaa");
		
	}
	
	@Test
	public void getAtObj() throws Exception{
		
		atdao.getAtObjsByParams(new Long(3), "bbb");
		
	}
	
	
	/**
	 * 批量查询测试
	 */
	
	@Test
	public void searchTest(){
		
		List<Long> ids=new ArrayList<Long>();
		ids.add(new Long(9));
		ids.add(new Long(11));
		
		atdao.getAtObjsByIdList(ids);
		
	}
	
	
	
	
	
	
	
}
