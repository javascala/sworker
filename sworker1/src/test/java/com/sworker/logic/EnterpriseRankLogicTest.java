package com.sworker.logic;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwRankInfoEntity;
import com.sworker.model.RankInfoParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class EnterpriseRankLogicTest {
	
	@Resource
	private IEnterpriseRankLogic enterpriseranklogic;
	
	
	@Test
	public void createRankTest(){
		
		SwRankInfoEntity rankentity=new SwRankInfoEntity();
		rankentity.setPointlower(new Long(1000));
		rankentity.setRankname("cc");
		rankentity.setRank(98304);
		RankInfoParam param=enterpriseranklogic.createRank(rankentity);
		
		System.out.println(param.getMsg()+param.getRank());
		
	}

	@Test
	public void updateRankTest(){
		SwRankInfoEntity rankentity=new SwRankInfoEntity();
		rankentity.setPointlower(new Long(1000));
		rankentity.setRankname("cc");
		rankentity.setRank(98304);
		enterpriseranklogic.updateRank(rankentity);
			
	}
	
	@Test
	
	public void resetCompanysRankTest(){
		
		enterpriseranklogic.resetCompanysRank();
		
	}
	
}
