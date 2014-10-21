package com.sworker.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.common.util.DateUtil;
import com.sworker.entity.SwExpiredpointEntity;
import com.sworker.entity.SwPointRecordsEntity;
import com.sworker.model.EnterprisePointParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class EnterprisePointDaoTest {

	@Resource
	private IEnterprisePointDao pointdao;
	
	
	@Test
	public void searchPointTest(){
		
		List<EnterprisePointParam> result=pointdao.searchPoint(1, "xx", 0, 10);
		
		Iterator<EnterprisePointParam> it=result.iterator();
		System.out.println(result.size());
		
		while(it.hasNext()){
			EnterprisePointParam param=it.next(); 	
			
			System.out.println(param.getEnterpriseid());
			System.out.println(param.getExpiredpoint());
			System.out.println(param.getEnddate());
			
			
		}
		
	}
	
	@Test
	public void savePointStatisticsTest(){
		SwPointRecordsEntity record=new SwPointRecordsEntity();
		record.setEnterpriseid(new Long(1));
		record.setPoint(new Long(10));
		record.setDes("aaa");
		record.setType(2);
		//record.setTime(new Timestamp(new Long(2014)));
		Boolean flag=pointdao.savePointStatistics(record);
		Assert.assertTrue(flag);
		
		
	}
	
	
	@Test
	public void saveEndDatePointsTest() throws ParseException{
		
		SwExpiredpointEntity expiredpoint=new SwExpiredpointEntity();
		expiredpoint.setEnterpriseid(new Long(2));
		expiredpoint.setExpiredpoint(new Long(10));
//		//string 转换成Date
//		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD");
//		String str="2014-9-19";
//		
//		
//		Date date=sdf.parse(str);
		
		Date date=DateUtil.convert("10/01/2014");
		
		expiredpoint.setEnddate(date);
		Boolean flag=pointdao.saveEndDatePoints(expiredpoint);
		Assert.assertTrue(flag);
	}
	
	
	
	
	@Test
	public void updateEndDatePointsTest(){
		SwExpiredpointEntity expiredpoint=new SwExpiredpointEntity();
			expiredpoint.setId(327680l);
			expiredpoint.setExpiredpoint(new Long(1000));
			expiredpoint.setEnterpriseid(new Long(2));
			
			//Date date=DateUtil.convert("10/01/2014");
			
			expiredpoint.setEnddate(new Date());
			//Date date=new Date();
//			SimpleDateFormat sdate=new SimpleDateFormat("YYYY-MM-DD");
//			sdate.format(date);
			
			//expiredpoint.setEnddate(date);
		pointdao.updateEndDatePoints(expiredpoint);
		
		
	}
	
	@Test
	public void checkEndDateExistTest(){
		Date date=DateUtil.convert("09/24/2014");
		System.out.println(date.toString());
		Long id=pointdao.checkEndDateExist(1l, date);
		System.out.println(id);
		
	}
	
	
	@Test
	public void modifyEnterprisePointTest(){
		
		pointdao.modifyEnterprisePoint(1l, 125l);
	}
	
	
	@Test
	public void modifyEnterprisePointxTest(){
		
		pointdao.modifyEnterprisePointx(1l, 125l);
	}
	
	
	@Test
	public void searchEndDatePointTest(){
		
		Long point=pointdao.searchEndDatePoint(2l);
		System.out.println(point);
	}
	
	
	@Test
	public void modifyExpiredPointTest(){
		
		pointdao.modifyExpiredPoint(2l, 1000l);
	}
	
/**--------------------尚未测试-------------------------------------*/	
	
	
	@Test
	public void deleteExpiredPointTest(){
		
		pointdao.deleteExpiredPoint(2l);
		
	}
	
	
	@Test
	public void deleteExpiredPoint2Test(){
		//重载的方法
		Boolean flag=pointdao.deleteExpiredPoint(2l, DateUtil.convert("19/09/2014"));
		
		Assert.assertTrue(flag);
	}
	
	
	@Test
	public void searchEndDateTest(){
		SwExpiredpointEntity ex=pointdao.searchEndDate(2l);
		System.out.println(ex.getId());
		
	}
	
	//.......
	
	
	@Test
	public void searchPointRecordTest(){
		String str="18/09/2014";
		String str2="20/09/2014";
		
		Date date1=DateUtil.convert(str);
		
		Date date2=DateUtil.convert(str2);
		
		Timestamp begindate=DateUtil.dateToTimeStamp(date1);
		
		Timestamp enddate=DateUtil.dateToTimeStamp(date2);
		
		pointdao.searchPointRecord(1l,begindate,enddate, 2);
		
	}
	
	@Test
	public void searchPointInfoByIdTest(){
		
		pointdao.searchPointInfoById(1l);
		
	}
	
	
}
