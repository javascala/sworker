package com.sworker.logic;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.common.util.DateUtil;
import com.sworker.entity.SwGroupApplyEntity;
import com.sworker.entity.SwGroupInfoEntity;
import com.sworker.entity.SwGroupStatisticsEntity;

/**
 * 
 * Created by jiangcy on 2014/9/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class GroupTest {
	
	@Resource
	private IGroupLogic groupLogic;
	
	/**
	 * 检查是否是群组成员
	 */
	@Test
	public void isMemberTest() {
		boolean flag = groupLogic.isMember(1L, 2621440L);
		Assert.assertEquals(true, flag);
		boolean flag2 = groupLogic.isMember(1L, 22L);
		Assert.assertEquals(false, flag2);
	}
		
	/**
	 * 指定成员是否是群组管理员
	 */
	@Test
	public void isGroupAdminTest() {
		boolean flag = groupLogic.isGroupAdmin(1L, 2621440L);
		Assert.assertEquals(true, flag);
		boolean flag2 = groupLogic.isGroupAdmin(1L, 222L);
		Assert.assertEquals(false, flag2);
	}
	
	/**
	 * 指定成员是否是群组创建
	 */
	@Test
	public void isGroupCreatorTest() {
		boolean flag = groupLogic.isGroupCreator(1L, 2621440L);
		Assert.assertEquals(true, flag);
		boolean flag2 = groupLogic.isGroupCreator(1L, 222L);
		Assert.assertEquals(false, flag2);
	}
	
	/**
	 * 指定成员是否被禁言
	 */
	@Test
	public void isForbiddenTest() {
		boolean flag =groupLogic.isForbidden(5L, 2621440L);
		Assert.assertEquals(true, flag);
		boolean flag2 = groupLogic.isForbidden(1L, 222L);
		Assert.assertEquals(false, flag2);
	}
	
	/**
	 * 添加新成员
	 */
	@Test
	public void addMemberTest() {
		long groupId = 2621440L;
		long memberId = 5L;
		long roleId = 262144L;
		groupLogic.addMember(groupId, memberId, roleId);
	}
	
	/**
	 * 添加成员申请
	 */
	@Test
	public void addApplyForTest() {
		long groupId = 2621440L;
		long memberId = 7L;
		String applyForReason = "wo呵呵";
		int applyForStatus = 1;
		Date applyForTime = new Date();
		groupLogic.addApplyFor(groupId, memberId, applyForReason, applyForStatus, applyForTime);
	}
	
	/**
	 * 删除群组成员
	 */
	@Test
	public void removeMemberTest() {
		groupLogic.removeMember(2621440L, 1L);
		groupLogic.removeMember(2621440L, 3L);
	}
	
	/**
	 * 更新成员在群组中角色
	 */
	@Test
	public void modifyMemberRoleTest() {
		groupLogic.modifyMemberRole(2621440L, 2L, 262144L);
		groupLogic.modifyMemberRole(345L, 2234L, 262144L);
	}
	
	/**
	 * 更改申请理由
	 */
	@Test
	public void modifyApplyForReasonTest() {
		groupLogic.modifyApplyForReason(2621440L, 6L, "哈哈");
		groupLogic.modifyApplyForReason(768L, 6456L, "哈哈");
	}
	
	/**
	 * 更该申请状态
	 */
	@Test
	public void modifyApplyForStatusTest() {
		groupLogic.modifyApplyForStatus(2621440L, 6L, 1);
		groupLogic.modifyApplyForStatus(45L, 234L, 1);
	}
	
	/**
	 * 屏蔽指定群组信息，0为屏蔽状态，1为不屏蔽状态
	 */
	@Test
	public void shieldedGroupTest() {
		boolean flag = groupLogic.shieldedGroup(234L, 4465456L);
		Assert.assertEquals(false, flag);
		boolean flag2 = groupLogic.shieldedGroup(2621440L, 2L);
		Assert.assertEquals(true, flag2);
		
	}
	
	/**
	 *取消指定群组屏蔽信息，0为屏蔽状态，1为不屏蔽状态
	 */
	@Test
	public void shieldedCancleTest() {
		boolean flag = groupLogic.shieldedCancle(234L, 4465456L);
		Assert.assertEquals(false, flag);
		boolean flag2 = groupLogic.shieldedCancle(2621440L, 2L);
		Assert.assertEquals(true, flag2);
	}
	
	/**
	 * 根据群组id获取成员信息
	 */
	@Test
	public void getMembersTest() {
		List<Object> member = groupLogic.getMembers(2621440L);
		Assert.assertNotNull(member);
		System.out.println(member.size());
	}
	
	/**
	 * 根据群组id查找群组申请信息
	 */
	@Test
	public void getApplyForTest() {
		List<SwGroupApplyEntity> applyList = groupLogic.getApplyFor(2621440L);
		Assert.assertNotNull(applyList);
		Assert.assertEquals(2, applyList.size());
	}
	
	/**
	 * 查找群组中成员申请状态
	 */
	 @Test
	public void getMemApplyForTest() {
		int apply = groupLogic.getMemApplyFor(2621440L, 6L);
		Assert.assertEquals(1, apply);
	}
	
	/**
	 * 禁止群组中具体成员，1为禁止状态
	 */
	@Test
	public void banMemberTest() {
		long groupId = 2621440L;
		long memberId = 2L;
		groupLogic.banMember(groupId, memberId);
	}
	
	/**
	 * 解禁群组中具体成员，0为正常状态
	 */
	@Test
	public void unbanMemberTest() {
		long groupId = 2621440L;
		long memberId = 2L;
		groupLogic.unbanMember(groupId, memberId);
	}
	
	/**
	 * 查看群组是否存在
	 */
	@Test
	public void isGroupExistTest() {
		boolean flag = groupLogic.isGroupExist("aa", null, 196608L);
		Assert.assertEquals(false, flag);
		boolean flag2 = groupLogic.isGroupExist("Test", null, 196608L);
		Assert.assertEquals(true, flag2);
	}
	
	/**
	 * 创建群组
	 */
	@Test
	public void createGroupTest() {
		SwGroupInfoEntity groupInfo = new SwGroupInfoEntity();
		groupInfo.setName("Test4");
		groupInfo.setAbbr("test4");
		groupInfo.setEnterpriseid(196608L);
		groupInfo.setCreatedate(DateUtil.dateToTimeStamp(new Date()));
		groupLogic.createGroup(groupInfo, 1L);
	}
	
	/**
	 * 删除群组
	 */
	@Test
	public void removeGroupTest() {
		groupLogic.removeGroup(2588672L);
	}
	
	/**
	 * 更新群组皮肤
	 */
	@Test
	public void modifySkinTest() {
		groupLogic.modifySkin(2621440L, 111L);
	}
	
	/**
	 * 更新群组信息表
	 */
	@Test
	public void modifyGroupInfoTest() {
		SwGroupInfoEntity groupInfo = new SwGroupInfoEntity();
		groupInfo.setId(2654208L);
		groupInfo.setName("Test3");
		groupInfo.setAbbr("testhihi");
		groupInfo.setEnterpriseid(196608L);
		groupInfo.setCreatedate(DateUtil.dateToTimeStamp(new Date()));
		groupLogic.modifyGroupInfo(groupInfo);
	}
	
	/**
	 * 查找某人创建的群组信息
	 */
	@Test
	public void getGroupCreatTest() {
		List<SwGroupInfoEntity> groupList = groupLogic.getGroupCreat(1L, 196608L);
		Assert.assertEquals(3, groupList.size());
	}
	
	/**
	 * 查找某人所属的群组信息
	 */
	@Test
	public void getGroupBelongTest() {
		List<SwGroupInfoEntity> groupList = groupLogic.getGroupBelong(1L, null);
		Assert.assertEquals(3, groupList.size());
	}
	
	/**
	 * 获取所有群组计数信息
	 */
	@Test
	public void getCounteInfoTest() {
		List<SwGroupStatisticsEntity> statistics = groupLogic.getCounteInfo();
		Assert.assertEquals(0, statistics.size());
	}
	
	/**
	 * 根据id获取群组信息
	 */
	@Test
	public void getGroupInfoTest() {
		SwGroupInfoEntity group = groupLogic.getGroupInfo(2621440L);
		Assert.assertNotNull(group);
	}
	
	/**
	 * 群组类型级关闭
	 */
	@Test
	public void closeGroupTypeTest() {
		groupLogic.closeGroupType(1L, 196608L);
	}
	
	/**
	 * 关闭具体群组
	 */
	@Test
	public void closeGroupTest() {
		groupLogic.closeGroup(2621440L);
	}
	
	/**
	 * 启用群组类型
	 */
	@Test
	public void restartGroupTypeTest() {
		groupLogic.restartGroupType(2L, 196608L);
	}
	
	/**
	 * 启用群组
	 */
	@Test
	public void restartGroupTest() {
		groupLogic.restartGroup(2621440L);
	}
	
	/**
	 * 检查某应用是否被群组使用
	 */
	@Test
	public void isApplicationExistTest() {
		boolean flag = groupLogic.isApplicationExist("1234", 2621440L);
		Assert.assertEquals(true, flag);
		boolean flag2 = groupLogic.isApplicationExist("111", 2621440L);
		Assert.assertEquals(false, flag2);
	}
	
	/**
	 * 添加群组应用
	 */
	@Test
	public void addApplicationTest() {
		groupLogic.addApplication(2621440L, "1123");
	}
	
	/**
	 * 获取指定群组应用的key列表
	 */
	@Test
	public void getAppKeysTest() {
		List<String> appList = groupLogic.getAppKeys(2621440L);
		Assert.assertEquals(1, appList.size());
	}
	
	/**
	 * 禁用群组的具体应用
	 */
	@Test
	public void banApplicationTest() {
		groupLogic.banApplication(2621440L, "1234");
	}
	
	/**
	 * 解禁群组的具体应用
	 */
	@Test
	public void unbanApplicationTest() {
		groupLogic.unbanApplication(2621440L, "1234");
	}
	
	/**
	 * 移除群组的具体应用
	 */
	@Test
	public void removeApplicationTest() {
		groupLogic.removeApplication(2621440L, "1123");
	}
		
}
