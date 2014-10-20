package com.sworker.logic;

import java.util.List;

import com.sworker.entity.SwMicroblogsEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */

public interface IMicroBlogsLogic {
	
	/**
	 * 创建微博
	 * @param microBlogEntity 微博实体
	 */
	public void createMicroBlog(SwMicroblogsEntity microBlogEntity);
	/**
	 * 删除微博同时更改转发数
	 * @param microBlogEntity 微博实体
	 */
	public void deleteMicroBlog(SwMicroblogsEntity microBlogEntity);
	/**
	 * 根据userId列表获取微博
	 * @param userIdList 用户id列表
	 * @return 返回微博实体
	 */
	public List<SwMicroblogsEntity> getMicroBlogs(List<Long> userIdList);
	/**
	 *获取指定用户的微博
	 * @param userId 用户的id
	 * @return 返回指定用户的微博列表
	 */
	public List<SwMicroblogsEntity> getMicroBlogs(Long userId);
	/**
	 * 转发微博
	 * @param srcMicroBlogEntity 被转发的微博
	 * @param newMicroBlogEntity 新微博
	 */
	public void forwordMicroBlog(SwMicroblogsEntity srcMicroBlogEntity, SwMicroblogsEntity newMicroBlogEntity);
	/**
	 * 减少微博的回复计数
	 * @param microBlogEntity 微博实体
	 * @param step 回复减少的个数
	 */
	public void decreaseMicroBlogReplyCount(SwMicroblogsEntity microBlogEntity, int step);

}
