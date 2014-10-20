package com.sworker.logic;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwRoleInfoEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class RoleLogicTest {
	
	@Resource
	private IRoleLogic rolelogic;
	
	/**
	 * 测试判断角色是否已经存在
	 */
	@Test
	public void isExistTest(){
		Boolean flag=rolelogic.isExist("bbb", new Long(2), 1, 2);
		Assert.assertFalse(flag);
	}
	
	
	@Test
	public void isAlterableTest(){
		Boolean flag=rolelogic.isAlterable(new Long(32768));
		//Assert.assertFalse(flag);
		
		System.out.println(flag);
		
	}
	
	@Test
	public void isEnableTest(){
		
		Boolean flag=rolelogic.isEnable(new Long(32768));
		Assert.assertFalse(flag);
		System.out.println(flag);
	}
	
	@Test
	public void addRoleTest(){
		
		SwRoleInfoEntity roleEntity=new SwRoleInfoEntity();
		
		roleEntity.setRole("aa");
		roleEntity.setDescription("bb");
		roleEntity.setEnterpriseid(new Long(1));
		roleEntity.setLevel(1);
		roleEntity.setRoletype(1);
		roleEntity.setStatus(0);
		roleEntity.setSublevel(1);
		rolelogic.addRole(roleEntity);
		
	}
	
	@Test
	public void getRoleTest(){
		rolelogic.getRole(new Long(65536));
	}
	
	
	@Test
	public void getAllRolesTest(){
		rolelogic.getAllRoles(new Long(1),1,10);
	}
	
	/**
	 * 删除角色测试，测试前确保数据库中有数据
	 */
	@Test
	public void removeRoleTest(){
		rolelogic.removeRole(new Long(98304));
	}
	
	@Test
	public void modifyRoleStatusTest(){
		rolelogic.modifyRoleStatus(new Long(131072),0);
	}
	
	@Test
	public void modifyRoleDescTest(){
		rolelogic.modifyRoleDesc(new Long(131072),"vvv");
	}
	
	@Test
	public void isAuthorityExistTest(){
		Boolean flag=rolelogic.isAuthorityExist(new Long(131072),"vvv");
		Assert.assertFalse(flag);
	
	}
	
	
	@Test
	public void addRoleAuthorityTest(){
		rolelogic.addRoleAuthority(new Long(131072),"vvv");
	
	}
	
	@Test
	public void getRoleAuthoritiesTest(){
		List result=rolelogic.getRoleAuthorities(new Long(4));
		System.out.println(result.size());
	
	}
	
	@Test
	public void removeRoleAuthorityTest(){
		rolelogic.removeRoleAuthority(new Long(131072),"vvv");
	}
	
	@Test
	public void removeAllRoleAuthoritiesTest(){
		rolelogic.removeAllRoleAuthorities(new Long(131072));
	}
	
	@Test
	public void isUserExistTest(){
		Boolean flag=rolelogic.isUserExist(new Long(131072),new Long(2));
		Assert.assertFalse(flag);
		System.out.println(flag);
	}
	
	@Test
	public void addRoleUserTest(){
		rolelogic.addRoleUser(new Long(131072),new Long(2));
	}
	
	@Test
	public void getRoleUsersTest(){
		rolelogic.getRoleUsers(new Long(131072),1,10);
	}
	
	@Test
	public void removeRoleUserTest(){
		rolelogic.removeRoleUser(new Long(131072),new Long(2));
	}
	
	@Test
	public void removeAllRoleUsersTest(){
		rolelogic.removeAllRoleUsers(new Long(131072));
	}
	
}
