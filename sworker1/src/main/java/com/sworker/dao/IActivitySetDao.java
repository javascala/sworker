package com.sworker.dao;

import org.springframework.stereotype.Repository;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwActivitySetEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
public interface IActivitySetDao extends IOperations<SwActivitySetEntity> {
	
	/**
	 * 通过userid和项目id查找用户设置
	 * @param userId 用户id
	 * @param activityItemKey 动态项目的id
	 * @return 返回是否接收动态
	 */
	public Integer findSetting(Long userId, String activityItemKey);
	/**
	 * 根据用户id删除设置
	 * @param userId 用户id
	 */
	public void deleteByUserId(Long userId);

}
