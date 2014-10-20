package com.sworker.logic;

import com.sworker.entity.SwVisibleRangeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangying on 2014/8/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class VisibleTest {
    @Resource
    private IVisibleLogic visibleLogic;

    /**
     *新增可见范围
     */
    @Test
    public  void addVisibleRangTest(){
        SwVisibleRangeEntity visibleRangeEntity = new SwVisibleRangeEntity();
        visibleRangeEntity.setObjectid(Long.valueOf(22));
        visibleRangeEntity.setUserviewrangeid(BigDecimal.valueOf(33));
        visibleRangeEntity.setGroupeviewrangeid(BigDecimal.valueOf(44));
        visibleRangeEntity.setViewrangetype("用户");
        List<SwVisibleRangeEntity> visibleRangeEntityList = new ArrayList<SwVisibleRangeEntity>();
        visibleRangeEntityList.add(visibleRangeEntity);
        visibleLogic.addVisibleRange(visibleRangeEntityList);
    }
    /**
     *根据对象ID查询可见范围列表
     */
    @Test
    public void searchViewRangeListTest(){
        Long objectId = new Long("22");
        List<SwVisibleRangeEntity> aa = null;
       aa = visibleLogic.searchViewRangeList(objectId);
    }

    /**
     *根据objectId删除可见范围
     */
    @Test
    public void deleteVisibleRangeTest(){
        Long objectId = new Long("23");
        visibleLogic.deleteVisibleRange(objectId);
    }

    /**
     *修改可见范围
     */
    @Test
    public void updateVisibleRangeTest(){
        Long objectId =22L;
        SwVisibleRangeEntity visibleRangeEntity = new SwVisibleRangeEntity();
        visibleRangeEntity.setObjectid(Long.valueOf(23));
        visibleRangeEntity.setUserviewrangeid(BigDecimal.valueOf(34));
        visibleRangeEntity.setGroupeviewrangeid(BigDecimal.valueOf(45));
        visibleRangeEntity.setViewrangetype("群组");
        List<SwVisibleRangeEntity> visibleRangeEntityList = new ArrayList<SwVisibleRangeEntity>();
        visibleRangeEntityList.add(visibleRangeEntity);
        visibleLogic.updateVisibleRange(objectId,visibleRangeEntityList);
    }

    /**
     *
     */
    @Test
    public void isUserViewTest(){
        Long objectId = new Long("1111");
        Long userId = new Long("1433");
        //TODO
    }
}