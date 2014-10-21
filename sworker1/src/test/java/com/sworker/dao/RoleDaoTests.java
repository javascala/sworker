package com.sworker.dao;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwRoleInfoEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class RoleDaoTests {

	@Resource
	private IRoleDao roledao;
	
	
	@Test
	public void isExist(){
		
		List list=roledao.tryToFindByParam("aa",new Long(2), 1, 2);
		
		System.out.println(list.size());
	}
	
	
	
	
	
	@Test
	public void findParamTest(){
		
		roledao.findParam(new Long("1"));
		
	}
	
	
	@Test
	public void checkUserTest(){
		
		roledao.checkUser(new Long(1));
		
		
		
	}
	
	@Test
	public void addRoleTest(){
		
		SwRoleInfoEntity roleEntity=new SwRoleInfoEntity();
		
		roleEntity.setRole("aa");
		roleEntity.setDescription("bb");
		roleEntity.setEnterpriseid(new Long(2));
		roleEntity.setLevel(1);
		roleEntity.setRoletype(1);
		roleEntity.setStatus(1);
		roleEntity.setSublevel(2);
		
		roledao.addRole(roleEntity);
		
	}
	
	@Test
	public void getRoleTest(){
		
	roledao.getRole(new Long(1));
		
	}
	
	@Test
	public void  getRoleListTest(){
		
	List list=roledao.getRoleList(new Long(1), 1, 10);
		
		System.out.println(list.size());
	}
	
	
	@Test
	public void deleteRoleAuthorityTest(){
		
		roledao.deleteRoleAuthority(new Long(1));
		
	}
	
	@Test
	public void deleteRoleTest(){
		roledao.deleteRole(new Long(1));
		
	}
	
	@Test
	public void updateRoleStatusTest(){
		
		roledao.updateRoleStatus(new Long(32768), 1);
		
		
	}
	
	@Test
	public void updateRoleDescTest(){
		
		roledao.updateRoleDesc(new Long(32768), "kk");
		
	}
	
	@Test
	public void checkAuthorityTest(){
		
		roledao.checkAuthority(new Long(2), "noticeRelease1");
		
	}
	
	@Test
	public void addAuthorityTest(){
		
		roledao.addAuthority(new Long(5), "yyy");
		
	}
	
	@Test
	public void getAuthoritykeysTest(){
		
		roledao.getAuthoritykeys(new Long(4));
		
	}
	
	@Test
	public void deleteRoleAuthority2Test(){
		roledao.deleteRoleAuthority(new Long(5),"yyy");
		
	}
	
	
	@Test
	public void checkUserExistTest(){
		
		roledao.checkUserExist(new Long(4), new Long(2));
		
	}
	
	

	@Test
	public void addUserTest(){
		
		roledao.addRoleUser(new Long(4), new Long(2));
		
	}
	
	@Test
	public void getRoleUsersTest(){
		
		roledao.getRoleUsers(new Long(1), 1, 10);
		
	}
	@Test
	public void deleteRoleUserTest(){
		
		roledao.deleteRoleUser(new Long(4));
		
	}
	
	
	
}
