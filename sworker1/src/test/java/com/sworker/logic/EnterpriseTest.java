package com.sworker.logic;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwEnterpriseInfoEntity;

/**
 * Created by jiangcy on 2014/9/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class EnterpriseTest {
	
	@Autowired
	private IEnterpriseLogic enterpriseLogic;
	
	/**
	 * 分页获取企业账号信息
	 */
	@Test
	public void getCompanyListTest() {
		List<SwEnterpriseInfoEntity> list = enterpriseLogic.getCompanyList(3, 2);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}

	/**
	 * 通过公司名获取企业账号信息
	 */
	@Test
	public void getCompanyByNameTest() {
		List<SwEnterpriseInfoEntity> list = enterpriseLogic.getCompanyListByName("jiangc");
		Assert.assertEquals(2, list.size());
	}
	
	/**
	 * 通过id获取企业账号的详细信息
	 */
	@Test
	public void getCompanyDetailTest() {
		SwEnterpriseInfoEntity entity = enterpriseLogic.getCompanyDetail(65536L);
		Assert.assertNotNull(entity);
	}
	
	/**
	 * 插入企业账号信息
	 */
	@Test
	public void createEnterpriseTest() {
		SwEnterpriseInfoEntity entity = new SwEnterpriseInfoEntity();
		entity.setEnterpisename("jia");
		entity.setUsageusers(1234L);
		entity.setMaxusers(123L);
		entity.setUsagespacemount(3452L);
		entity.setMaxspacemount(10009L);
		entity.setUsagemailamount(123L);
		entity.setMaxmailamount(1000L);
		boolean flag = enterpriseLogic.createCompany(entity);
		Assert.assertEquals(true, flag);
	}
	
	/**
	 * 更改企业账号信息
	 */
	@Test
	public void modifyTest() {
		SwEnterpriseInfoEntity entity = new SwEnterpriseInfoEntity();
		entity.setEnterpiseid(65536L);
		entity.setEnterpisename("jiangcy");
		entity.setUsagespacemount(125L);
		entity.setMaxspacemount(280L);
		enterpriseLogic.modifyCompany(entity);
	}
	
	/**
	 * 将企业的状态设为删除状态
	 */
	@Test
	public void removeTest() {
		enterpriseLogic.removeCompany(65536L);	
	}
	
	/**
	 * 将企业的状态设为激活状态
	 */
	@Test
	public void activiteTest() {
		enterpriseLogic.activateCompany(65536L);
	}
	
	/**
	 * 将企业的状态设为关闭状态
	 */
	@Test
	public void disActiviteTest() {
		enterpriseLogic.disActivateCompany(65536L);
	}
	
	/**
	 * 检测公司名是否存在
	 */
	@Test
	public void companyExitTest() {
		boolean flag = enterpriseLogic.isCompanyNameExit("jiang");
		Assert.assertEquals(false, flag);
		boolean flag2 =enterpriseLogic.isCompanyNameExit("jiangcy");
		Assert.assertEquals(true, flag2);
	}
	
	/**
	 * 检测装入的文件是否超过最大容量
	 */
	@Test
	public void checkDiskTest() {
		boolean flag = enterpriseLogic.checkCompanyDisk(65536L, 156);
		Assert.assertEquals(false, flag);
		boolean flag2 = enterpriseLogic.checkCompanyDisk(65536L, 154);
		Assert.assertEquals(true, flag2);
	}
	
	/**
	 * 减少已用盘的大小
	 */
	@Test
	public void reduceDisk() {
		boolean flag = enterpriseLogic.reduceCompanyDisk(32768L, 120);
		Assert.assertEquals(true, flag);
	}
	
	/**
	 * 增加已用的盘的大小
	 */
	@Test
	public void addDisk() {
		boolean flag = enterpriseLogic.addCompanyDisk(32768L, 120);
		Assert.assertEquals(true, flag);
	}
}