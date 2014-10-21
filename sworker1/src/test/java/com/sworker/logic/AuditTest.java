package com.sworker.logic;

import com.sworker.entity.SwAuditApplyEntity;
import com.sworker.entity.SwAuditRecordsEntity;
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
 * Created by cuijh on 2014/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class AuditTest {
    @Resource
    private IAuditLogic auditLogic;

    @Test
    public void testApply() {
        SwAuditApplyEntity auditApplyEntity = new SwAuditApplyEntity();
        //auditApplyEntity.setId(222222);
        auditApplyEntity.setEnterpriseid((long) 111222314);
        auditApplyEntity.setObjectid((long) 333);
        auditApplyEntity.setAppkey("notice");
        auditApplyEntity.setOwnerid((long) 55);
        auditApplyEntity.setProposerid((long) 11);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        auditApplyEntity.setApplyfordate(Timestamp.valueOf(dateFormat.format(now)));
        auditApplyEntity.setReason("测试");
        auditApplyEntity.setAuditorid((long) 22);
        auditApplyEntity.setStatus(1);
        auditApplyEntity.setResult(1);

        auditLogic.apply(auditApplyEntity);
    }

    @Test
    public void testAuditing() {
        SwAuditRecordsEntity auditRecordsEntity = new SwAuditRecordsEntity();

        auditRecordsEntity.setEnterpriseid((long) 111222334);
        auditRecordsEntity.setApplicationid((long) 98304);
        auditRecordsEntity.setAuditorid((long) 22);
        auditRecordsEntity.setAuditdetails("批准");
        auditRecordsEntity.setAuditopinion(2);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        auditRecordsEntity.setAuditdate(Timestamp.valueOf(dateFormat.format(now)));

        Long nextUserId = (long) 23;

        auditLogic.auditing(auditRecordsEntity, nextUserId);
    }

    @Test
    public void testDeleteApply() {
        SwAuditApplyEntity auditApplyEntity = new SwAuditApplyEntity();
        auditApplyEntity.setId(131072);
        auditApplyEntity.setEnterpriseid((long) 111222334);
        auditApplyEntity.setObjectid((long) 333);
        auditApplyEntity.setAppkey("notice");
        auditApplyEntity.setOwnerid((long) 55);
        auditApplyEntity.setProposerid((long) 11);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        auditApplyEntity.setApplyfordate(Timestamp.valueOf(dateFormat.format(now)));
        auditApplyEntity.setReason("测试");
        auditApplyEntity.setAuditorid((long) 22);
        auditApplyEntity.setStatus(2);
        auditApplyEntity.setResult(1);

        auditLogic.deleteApply(auditApplyEntity);
    }

    @Test
    public void testGetApplyInfoByApplyId() {
        auditLogic.getApplyInfoByApplyId((long) 65536);
    }

    @Test
    public void testGetAuditInfoByAppKey() {

        Long enterpriseId = (long) 111222334;
        String appKey = "notice";
        int pageIndex = 1;
        int pageSize = 2;
        auditLogic.getAuditInfoByAppKey(enterpriseId, appKey, pageIndex, pageSize);

    }

    @Test
    public void testGetAuditInfoByObject(){

        Long enterpriseId = (long) 111222334;
        Long objectId = (long)333;

        auditLogic.getAuditInfoByObject(enterpriseId, objectId);

    }

    @Test
    public void testGetAuditInfoByProposer(){

        Long enterpriseId = (long) 111222334;
        Long proposerId = (long)22;
        int pageIndex = 1;
        int pageSize = 2;
        auditLogic.getAuditInfoByProposer(enterpriseId, proposerId, pageIndex, pageSize);

    }

    @Test
    public void testGetNeedAuditByAuditor(){

        Long enterpriseId = (long) 111222334;
        Long auditorId = (long)22;
        int pageIndex = 2;
        int pageSize = 1;
        auditLogic.getNeedAuditByAuditor(enterpriseId, auditorId, pageIndex, pageSize);

    }

    @Test
    public void testGetAuditInfoByAuditor(){

        Long enterpriseId = (long) 111222334;
        Long auditorId = (long)22;
        int pageIndex = 1;
        int pageSize = 2;
        auditLogic.getAuditInfoByAuditor(enterpriseId, auditorId, pageIndex, pageSize);

    }

    @Test
    public void testGetAuditInfoByApplyId() {

        Long applicationId = (long) 98304;
        auditLogic.getAuditInfoByApplyId(applicationId);

    }

}
