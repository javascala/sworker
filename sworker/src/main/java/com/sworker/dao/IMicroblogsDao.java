package com.sworker.dao;

import java.util.List;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwMicroblogsEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
public interface IMicroblogsDao extends IOperations<SwMicroblogsEntity> {
	
	/**
	 * 更改微博转发个数
	 * @param id 微博的id
	 * @param count 需要更改数
	 */
	public void updateForwardCount(Long id, Long count);
	/**
	 * 通过用户id查找微博
	 * @param userIdList 用户的id集合
	 * @return 返回微博列表
	 */
	public List<SwMicroblogsEntity> findByList(List<Long> userIdList);

}
