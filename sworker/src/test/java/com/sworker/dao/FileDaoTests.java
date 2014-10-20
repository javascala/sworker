package com.sworker.dao;

import com.sworker.common.BaseTest;
import com.sworker.common.CodeBook;
import com.sworker.entity.SwFileAuthorityEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjin on 2014/8/29.
 */
public class FileDaoTests extends BaseTest{

    @Resource
    private IFileDao fileDao;

    @Test
    public void getFileList(){
        fileDao.queryGroupAuthorityList(12l, 12l, 123123l);
    }

    @Test
    public void getAuList(){
        fileDao.groupAuthorityList(12l, 12l, 123123l);
    }

    @Test
    public void getUserAuList(){
        fileDao.userAuthorityList(12l, 12l, 12l);
    }

    @Test
    public void getEntity(){
        fileDao.getDirectory(12l);
    }

    @Test
    public void querySubGroupAuListTest(){
        fileDao.querySubGroupAuList(12l, 123l, 123l);
    }

    /**
     * 查询子目录列表测试
     */
    @Test
    public void querySubFileDirectoryListTest() {
        fileDao.querySubFileDirectoryList(123l);
    }

    /**
     * 删除目录测试
     */
    @Test
    public void deleteDirectoryTest(){
        fileDao.deleteDirectory(123l, 123l);
    }

    /**
     * 删除权限测试
     */
    @Test
    public void deleteObjectAuthTest(){
        fileDao.deleteObjectAuth(123l, 12l);
    }

    /**
     * 批量保存权限列表测试
     */
    @Test(timeout = 10000)
    public void createFileAuListTest() {
        List<SwFileAuthorityEntity> userAuList = new ArrayList<SwFileAuthorityEntity>();
        for (int i = 0; i < 100; i++) {
            SwFileAuthorityEntity userEntity = new SwFileAuthorityEntity();
            userEntity.setEnterpriseid(123l);
            userEntity.setAuthtype(CodeBook.FILEAU_TYPE_CANVIEW);
            userEntity.setAuthrole(CodeBook.FILEAU_ROLE_USER);
            userEntity.setUserroleid(123l);
            userEntity.setResourceid(12l);
            userAuList.add(userEntity);
        }

        fileDao.createFileAuList(userAuList);
    }

    /**
     * 查询群组列表
     */
    @Test
    public void querySourceGroupAuListTest() {
        fileDao.querySourceGroupAuList(123l, 123l, CodeBook.FILEAU_ROLE_GROUP);
    }


    /**
     * 模糊查询路径列表
     */
    @Test
    public void queryDirectoryListTest() {
        fileDao.queryDirectoryList(123l, "1", 1, 10);
    }

    /**
     * 获取文件权限或目录权限
     */
    @Test
    public void queryFileAuthorityList() {
        fileDao.queryFileAuthorityList(123l, 122l, CodeBook.FILE_RESOUCE_TYPE_DIR);
        fileDao.queryFileAuthorityList(123l, 458752l, CodeBook.FILE_RESOURCE_TYPE_FILE);
    }

    @Test
    public void getFileModelTest() {
        fileDao.getFileModel(123l, 458752l);
    }

    /**
     * 更新文件状态
     */
    @Test
    public void updateFileStatusTest() {
        fileDao.updateFileStatus(458752l, 3);
    }

    /**
     * 修改浏览次数
     */
    @Test
    public void updateViewCountTest() {
        Boolean flag = fileDao.updateViewCount(458752l);
        Assert.assertTrue(flag);
    }
}
