package com.sworker.logic;

import java.util.List;

import com.sworker.entity.SwAtInfoEntity;

/**
 * 
 * @author wanggang 2014/8/22.
 *
 */
public interface IAtLogic {
	/**
	 * 批量创建AT对象
	 * @param atObjEntityList AT对象实体集合
	 * @return 返回操作是否成功
	 */
	public Boolean batchCreateAtUser(List<SwAtInfoEntity> atObjEntityList);
	
	/**
	 * 清除AT对象
	 * @param associateId 关联项的Id
	 * @param appTypeId 应用类型Id
	 */
	public void clearAtObj(Long associateId,String appTypeId);
	
	/**
	 * 获取AT对象集合
	 * @param associateId  关联项的Id
	 * @param appTypeId  应用类型Id
	 * @return 返回AT对象实体集合
	 */
	public List<SwAtInfoEntity> getAtObjs(Long associateId,String appTypeId);
	

	/**
	 * 获取AT对象集合（根据AT对象的ID获取）
	 * @param atObjIdList AT对象Id集合
	 * @return 返回AT对象实体集合
	 */
	public List<SwAtInfoEntity> getAtObjs(List<Long> atObjIdList);
}
