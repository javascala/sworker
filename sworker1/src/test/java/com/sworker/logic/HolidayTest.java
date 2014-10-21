package com.sworker.logic;

import com.sworker.entity.SwHolidayinfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class HolidayTest {
    @Resource
    private IHolidayLogic holidayLogic;

    /**
     * 实现一个节日信息的新增
     */
    @Test
    public void addHolidayTest(){
        SwHolidayinfoEntity holidayinfoEntity = new SwHolidayinfoEntity();
        holidayinfoEntity.setHolidayname("情人节");
        holidayinfoEntity.setCountry(0);
        holidayinfoEntity.setDateinfo(Date.valueOf("2014-02-14"));
        holidayinfoEntity.setHolidaytype(0);
        holidayinfoEntity.setOperateid("Ellen");
        holidayinfoEntity.setOperatetime(Timestamp.valueOf("2014-09-17 13:01:12"));
        holidayLogic.addHoliday(holidayinfoEntity);
    }

    /**
     * 实现节日信息删除功能
     */
    @Test
    public void delHolidayTest(){
        long id = 32768;
        holidayLogic.delHoliday(id);
    }

    /**
     *实现一个节日信息内容的编辑
     */
    @Test
    public void editHolidayTest(){
        SwHolidayinfoEntity holidayinfoEntity = new SwHolidayinfoEntity();
        holidayinfoEntity.setId(1);
        holidayinfoEntity.setHolidayname("清明");
        holidayinfoEntity.setCountry(1);
        holidayinfoEntity.setDateinfo(Date.valueOf("2014-04-05"));
        holidayinfoEntity.setHolidaytype(0);
        holidayinfoEntity.setOperateid("Jack");
        holidayinfoEntity.setOperatetime(Timestamp.valueOf("2014-08-14 13:41:12"));
        holidayLogic.editHoliday(holidayinfoEntity);
    }

    /**
     * 查询节日信息列表
     */
    @Test
    public void queryHolidayTest(){
        Integer aa = 2;
        List<SwHolidayinfoEntity> bb = holidayLogic.queryHoliday(aa);
    }
}

