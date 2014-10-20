package com.sworker.dao;

import org.springframework.stereotype.Repository;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwActivityItemEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
public interface IActivityItemDao extends IOperations<SwActivityItemEntity> {
	
	/**
	 * 通过id删除动态项目
	 * @param key 动态项目的id
	 */
	public void deleteByKey(String key);
	/**
	 * 通过动态项目id查找动态项目
	 * @param key 动态项目的id
	 * @return 返回动态项目实体
	 */
	public SwActivityItemEntity findByKey(String key);

}
