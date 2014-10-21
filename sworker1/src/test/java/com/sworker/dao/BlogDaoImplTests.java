package com.sworker.dao;

import com.sworker.entity.AcitvityEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangjin on 2014/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class BlogDaoImplTests {

    @Resource
    private IBlogDao blogDao;

    /**
     * 简单保存测试
     * @throws Exception
     */
    @Test
    public void saveActivity() throws Exception {
        AcitvityEntity entity = new AcitvityEntity();
        entity.setType("Blog");
        entity.setContent("haha");
        entity.setBlogAttr("aaa");
        entity.setOwner("gougou");

        blogDao.addActivity(entity);
//        Assert.assertEquals();
    }

    /**
     * 异常估计测试
     */
    @Test(expected = HibernateOptimisticLockingFailureException.class)
    public void saveException(){
        AcitvityEntity entity = new AcitvityEntity();
        entity.setId(8);
        entity.setType("Blog");
        entity.setContent("haha");
        entity.setBlogAttr("aaa");
        entity.setOwner("gougou");

        blogDao.addActivity(entity);
    }

    /**
     * 空值测试
     */
    @Test
    public void testList(){
        List<AcitvityEntity> list = blogDao.dynamicList();

        Assert.assertNotNull("shout not be null", list);
    }
}
