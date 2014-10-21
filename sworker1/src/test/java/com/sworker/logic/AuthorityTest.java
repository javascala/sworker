package com.sworker.logic;

import com.sworker.entity.SwAuthorityEntity;
import com.sworker.entity.SwRoleAuthorityEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cuijh on 2014/8/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class AuthorityTest {
    @Resource
    private IAuthorityLogic authorityLogic;


    @Test
    public void testCreateAuthority(){
        SwAuthorityEntity swAuthorityEntity = new SwAuthorityEntity();

        swAuthorityEntity.setAuthoritykey("noticeRelease");
        swAuthorityEntity.setAuthorityname("发布公告");
        swAuthorityEntity.setDescription("用户有权限在系统内发布公告");
        swAuthorityEntity.setResourcekey("公告");
        swAuthorityEntity.setOperatetype("读写");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        swAuthorityEntity.setCreatdate(Timestamp.valueOf(dateFormat.format(now)));

        authorityLogic.createAuthority(swAuthorityEntity);

    }

    @Test
    public void testDeleteAuthority(){
        String authorityKey = "noticeRelease";
        authorityLogic.deleteAuthority(authorityKey);
    }

    @Test
    public void testModifyAuthority(){

        SwAuthorityEntity swAuthorityEntity = new SwAuthorityEntity();

        swAuthorityEntity.setId(294913);
        swAuthorityEntity.setAuthoritykey("noticeRelease1");

        swAuthorityEntity.setAuthorityname("发布公告");
        swAuthorityEntity.setDescription("用户有权限在系统内发布公告");
        swAuthorityEntity.setResourcekey("公告");
        swAuthorityEntity.setOperatetype("读写");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        swAuthorityEntity.setCreatdate(Timestamp.valueOf(dateFormat.format(now)));

        authorityLogic.modifyAuthority(swAuthorityEntity);
    }

    @Test
    public void testGetAllAuthorities(){
        List<SwAuthorityEntity> allAuthorities= authorityLogic.getAllAuthorities();
        System.out.print(allAuthorities);
    }

    @Test
    public void testGetAuthority(){
        String authority = "noticeRelease1";
        SwAuthorityEntity authorityEntity = null;
        authorityEntity = authorityLogic.getAuthority(authority);
        System.out.print(authorityEntity);
    }

    @Test
    public void testVerifyAuthority(){

        int userid = 3;
        String appKey = "notice";
        String operateType = "read";
        boolean kk;
        kk = authorityLogic.verifyAuthority(userid,appKey,operateType);
    }
    @Test
    public void testAddAuthorityRole(){
        SwRoleAuthorityEntity swRoleAuthorityEntity = new SwRoleAuthorityEntity();

        swRoleAuthorityEntity.setAuthoritykey("noticeaudit");
        swRoleAuthorityEntity.setRoleid((long)4);

        authorityLogic.addAuthorityRole(swRoleAuthorityEntity);
    }

    @Test
    public void testDeleteAuthorityRole(){
        String authorityKey = "noticeRelease1";
        Integer roleId = 2;
        authorityLogic.deleteAuthorityRole(authorityKey,roleId);
    }

    @Test
    public void testDeleteAuthorityAllRoles(){
        String authorityKey = "noticeRelease1";
        authorityLogic.deleteAuthorityAllRoles(authorityKey);
    }

    @Test
    public void testGetAuthorityRoles(){
        String authorityKey = "noticeRelease1";
        List<Long> ids;
        ids = authorityLogic.getAuthorityRoles(authorityKey);

        System.out.print(ids);
    }

}