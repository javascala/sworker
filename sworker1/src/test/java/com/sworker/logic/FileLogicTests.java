package com.sworker.logic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sworker.common.BaseTest;
import com.sworker.common.CodeBook;
import com.sworker.common.util.ObjectUtil;
import com.sworker.model.FileParam;
import com.sworker.model.FileUploadParam;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjin on 2014/9/4.
 */
public class FileLogicTests extends BaseTest{

    @Resource
    private IFileLogic fileLogic;

    /**
     * 测试通过 DirectoryId 有主键
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void getCurrentDirectoryTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        FileParam param = new FileParam();
        param.setCompanyId(1l);
        param.setDirectoryId(123l);
        param.setGroupId(123l);
        param.setUserId(4l);
        fileLogic.getCurrentDirectory(param);
    }

    /**
     * 测试通过
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void getSubDirectorysTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        FileParam param = new FileParam();
        param.setCompanyId(123l);
        param.setBaseDirectoryId(123l);
        param.setGroupId(123l);
        param.setUserId(4l);
        fileLogic.getSubDirectorys(param);
    }

    /**
     * 创建权限测试
     */
    @Test
    public void createObjectAuthTest() {
        FileParam param = new FileParam();
        param.setCompanyId(123l);
        param.setBaseSourceId(12l);
        param.setSourceId(123l);
        param.setSourceType(CodeBook.FILE_RESOURCE_TYPE_FILE);
        param.setIsExtends(CodeBook.FILEAU_EXTENDS);
        List<Integer> managerList = new ArrayList<Integer>();
        managerList.add(123);
        param.setAuthorityUserList(managerList);
        List<Integer> visiableList = new ArrayList<Integer>();
        param.setVisiableUserList(visiableList);
        visiableList.add(123);
        fileLogic.createObjectAuth(param);
    }


    /**
     *上传文件
     */
    @Test
    public void putFileTest() throws FileNotFoundException {
        FileUploadParam fileUploadParam = new FileUploadParam();
        List<File> fileList = new ArrayList<File>();
        File file = new File("g:\\Angularjs.docx");
        fileList.add(file);
        fileUploadParam.setFileList(fileList);
        fileUploadParam.setCompanyId(123l);
        fileUploadParam.setBaseDirectoryId(12l);
        fileUploadParam.setIsExtendsUpFile(CodeBook.FILEAU_EXTENDS);
        fileUploadParam.setCreatorId(123l);
        fileUploadParam.setBucket("sworker");
        List<Integer> managerList = new ArrayList<Integer>();
        managerList.add(123);
        fileUploadParam.setAuthorityUserList(managerList);
        List<Integer> visiableList = new ArrayList<Integer>();
        fileUploadParam.setVisiableUserList(visiableList);
        visiableList.add(123);
        fileLogic.putFile(fileUploadParam);
    }

    /**
     * 修改文件名
     * 测试通过
     */
    @Test
    public void updateFileNameTest() {
        FileParam param = new FileParam();
        param.setFileId(458752l);
        param.setFileName("aaa.doc");
        param.setUpdateUserId(123l);
        Boolean flag = fileLogic.updateFileName(param);
        Assert.assertTrue(flag);
    }

    /**
     * 查询文件列表
     * 测试通过
     */
    @Test
    public void findFileByWordTest() {
        FileParam param = new FileParam();
        param.setCompanyId(123l);
        param.setKeyWord("aaa");
        param.setPageNum(1);
        param.setCountNum(2);
        fileLogic.findFileByWord(param);
    }

    /**
     * 获取文件权限或目录权限
     * 测试通过
     */
    @Test
    public void findObjectAuthTest(){
        fileLogic.findObjectAuth(123l, 122l, CodeBook.FILE_RESOUCE_TYPE_DIR);
        fileLogic.findObjectAuth(123l, 458752l, CodeBook.FILE_RESOURCE_TYPE_FILE);
    }

    /**
     * 获取文件流
     * pass
     */
    @Test
    public void getFileObjectTest() {
        fileLogic.getFileObject("sworker", "375093e3352e4c14e92c874ad681e755");
    }

    @Test
    public void  updateFileDescTest() {
        Boolean flag  = fileLogic.updateFileDesc(458752l, "description", 123l);
        Assert.assertTrue(flag);
    }

    /**
     * 删除oss文件
     */
    @Test
    public void deleteOssFileTest() {
        Boolean flag = fileLogic.deleteOssFile("sworker", "375093e3352e4c14e92c874ad681e755");
        Assert.assertTrue(flag);
    }

    @Test
    public void initGroupFileSpaceTest() {
        Boolean flag = fileLogic.initGroupFileSpace(123l, 123l);
        Assert.assertTrue(flag);
    }

    @Test
    public void isDirectoryEmptyTest() {
        Boolean flag = fileLogic.isDirectoryEmpty(123l);
        Assert.assertFalse(flag);
    }
}
