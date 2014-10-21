package com.sworker.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwRankInfoEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class EnterpriseRankDaoTest {

	@Resource
	private IEnterpriseRankDao enterpriserankdao;
	
	@Test
	public void findAllRankTest(){
		
		List result=enterpriserankdao.findAllRank();
		
		Assert.assertNotNull(result);
	}
	
	
	@Test
	public void checkRankExistTest(){
		
		enterpriserankdao.checkRankExist(2);
		
	}
	
	@Test
	public void deleteRankInfoTest(){
		
		enterpriserankdao.deleteRankInfo(1);
		
	}
	
	@Test
	public void saveRankInfoTest(){
		
		SwRankInfoEntity rankentity=new SwRankInfoEntity();
		rankentity.setPointlower(new Long(100));
		rankentity.setRankname("aa");
		enterpriserankdao.saveRankInfo(rankentity);
		
	}
	
	

	@Test
	public void updateRankInfoTest(){
		SwRankInfoEntity rankentity=new SwRankInfoEntity();
		rankentity.setRank(65539);
		rankentity.setPointlower(new Long(100));
		rankentity.setRankname("bb");
		enterpriserankdao.updateRankInfo(rankentity);
		
	}
	
	@Test
	public void UpdateEnterpriseRankTest(){
		
		enterpriserankdao.UpdateEnterpriseRank(new Long(1),2);
		
	}
	
	
	
}
