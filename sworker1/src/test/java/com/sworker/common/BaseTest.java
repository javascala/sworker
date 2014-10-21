package com.sworker.common;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.common.util.DateUtil;

/**
 * Created by zhangjin on 2014/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class BaseTest {
	
	@Test
	public void test(){
		System.out.print(DateUtil.dateToTimeStamp(new Date()));
	}
	
}
