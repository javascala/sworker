package com.sworker.logic;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.sworker.common.BaseTest;
import com.sworker.common.util.DateUtil;
import com.sworker.entity.SwExpiredpointEntity;
import com.sworker.entity.SwPointRecordsEntity;
import com.sworker.model.PointParamInfo;

public class EnterprisePointLogicTest extends BaseTest {

	@Resource
	private IEnterprisePointLogic pointlogic;
	
	
	
	@Test
	public void savePointForCompanyTest(){
		
		SwPointRecordsEntity record=new SwPointRecordsEntity();
		record.setEnterpriseid(3l);
		record.setType(2);
		record.setDes("aaaa");
		record.setTime(new Timestamp(System.currentTimeMillis()));
		record.setPoint(1111l);
		
		
		Date enddate=new Date();
		
		PointParamInfo param=pointlogic.savePointForCompany(record, enddate);
		System.out.println(param.getMsg());
	}
	
	
	@Test
	public void saveEndDatePointsTest(){
		SwExpiredpointEntity expiredpoint=new SwExpiredpointEntity();
		expiredpoint.setEnterpriseid(1l);
		expiredpoint.setExpiredpoint(99l);
		Date date=DateUtil.convert("09/24/2014");
		expiredpoint.setEnddate(date);
		
		pointlogic.saveEndDatePoints(expiredpoint);
		
	}

	
	@Test
	public void reduceEndDatePointTest(){
		//该方法中有迭代
		pointlogic.reduceEndDatePoint(1l, 100l);
		
	}
	
	
	@Test
	public void updatePointTest(){
		
		pointlogic.updatePoint(1l, 100l,500l, DateUtil.convert("09/24/2014"), true, "modify");
		
	}
	
	
	@Test
	public void tradeToSystemTest(){
		
		PointParamInfo param=pointlogic.tradeToSystem(100l, 1l);
		System.out.println(param.getMsg());
		
	}
	
	
	
	
	@Test
	public void updatePointRulesTest(){
		
		
		
	}
	
	
	@Test
	public void getPointRuleByTypeTest(){
		
		pointlogic.getPointRuleByType(1);
		
	}
	
	
	@Test
	public void getPointByRankRuleTest(){
		
		Long point=pointlogic.getPointByRankRule(1, 1, 1l);
		System.out.println(point);
		Assert.assertNull(point);
		
	}
	
	@Test
	public void searchPointRecordTest(){
		pointlogic.searchPointRecord(1l, new Date(), 1);
		
		
	}
	
	
	@Test
	public void searchPointInfoByIdTest(){
		
		pointlogic.searchPointInfoById(1l);
	}
	
	
	
}
