package com.sworker.dao;

import java.util.List;

import com.sworker.entity.SwAtInfoEntity;

/**
 * 
 * @author wanggang 2014/8/22.
 *
 */

	public interface IAtDao {
	/**
	 * 插入所有的At实体
	 * @param atObjEntityList AT对象实体集合
	 * @return 是否插入成功
	 */
	public Boolean insertAll(List<SwAtInfoEntity> atObjEntityList);
/**
	 * 通过参数删除AT记录
	 * @param associateId 关联项的Id
	 * @param appTypeId  应用类型Id
	 */
	public void deleteAtObjByParams(Long associateId, String appTypeId);
/**
	 * 通过参数查找AT记录
	 * @param associateId 关联项的Id
	 * @param appTypeId  应用类型Id
	 * @return 返回AT对象实体集合
	 */
	public List<SwAtInfoEntity> getAtObjsByParams(Long associateId, String appTypeId);
	/**
	 * 通过ID列表检索AT记录
	 * @param atObjIdList AT对象Id集合
	 * @return 返回AT对象实体集合
	 */
	public List<SwAtInfoEntity> getAtObjsByIdList(List<Long> atObjIdList);
	
	
}
