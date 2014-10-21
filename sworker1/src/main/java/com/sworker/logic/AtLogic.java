package com.sworker.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.IAtDao;
import com.sworker.entity.SwAtInfoEntity;
/**
 * 
 * @author wanggang 2014/8/22.
 *
 */

@Repository
@Component("atLogic")
public class AtLogic implements IAtLogic {
	
	@Resource(name="atdao")
	private IAtDao atdao;
	
	/**
	 * 批量创建AT对象
	 * @param atObjEntityList AT对象实体集合
	 * @return 返回操作是否成功
	 */
	@Override
	@Transactional
	public Boolean batchCreateAtUser(List<SwAtInfoEntity> atObjEntityList) {
		
		return atdao.insertAll(atObjEntityList);
		
	}
	

	/**
	 * 清除AT对象
	 * @param associateId 关联项的Id
	 * @param appTypeId  应用类型Id
	 */
	@Override
	@Transactional
	public void clearAtObj(Long associateId, String appTypeId) {
		
		atdao.deleteAtObjByParams(associateId, appTypeId);
		
	}
	
	/**
	 * 获取AT对象集合
	 * @param associateId 关联项的Id
	 * @param appTypeId 应用类型Id
	 * @return 返回AT对象实体集合
	 */
	@Override
	@Transactional
	public List<SwAtInfoEntity> getAtObjs(Long associateId, String appTypeId) {
		List<SwAtInfoEntity> atObjs=new ArrayList<SwAtInfoEntity>();
		
		atObjs=atdao.getAtObjsByParams(associateId, appTypeId);
		
		return atObjs;
		
	}
	
	/**
	 * 获取AT对象集合（根据AT对象的ID获取）
	 * @param atObjIdList AT对象Id集合
	 * @return 返回AT对象实体集合
	 */
	@Override
	@Transactional
	public List<SwAtInfoEntity> getAtObjs(List<Long> atObjIdList) {
		
		List<SwAtInfoEntity> atObjs=new ArrayList<SwAtInfoEntity>();
		
		atObjs=atdao.getAtObjsByIdList(atObjIdList);
		return atObjs;
	}

}
