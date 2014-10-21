package com.sworker.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.IMicroblogsDao;
import com.sworker.entity.SwMicroblogsEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Component(value = "blogsLogic")
public class MicroBlogsLogic implements IMicroBlogsLogic {

	@Resource
	private IMicroblogsDao microblogsDao;

	/**
	 * 创建微博
	 * @param microBlogEntity 微博实体
	 */
	@Transactional
	@Override
	public void createMicroBlog(SwMicroblogsEntity microBlogEntity) {
		if (microBlogEntity != null) {
			microBlogEntity.setForwardedcount(Long.valueOf(0));
			microBlogEntity.setReplycount(Long.valueOf(0));
			microblogsDao.create(microBlogEntity);
		}
	}

	/**
	 * 删除微博同时更改转发数 
	 * @param microBlogEntity 微博实体
	 */
	@Transactional
	@Override
	public void deleteMicroBlog(SwMicroblogsEntity microBlogEntity) {
		if (microBlogEntity != null) {
			Long count = Long.valueOf(-1);
			Long forwardId = microBlogEntity.getForwardedmicroblogid();
			Long originalId = microBlogEntity.getOriginalmicroblogid();
			// 如果微博的forwardedmicroblogid不为null，则被转发的微博的转发数减1
			if (forwardId != null) {
				microblogsDao.updateForwardCount(forwardId, count);
			}
			// 如果微博的originalmicroblogid不为null，则源微博的转发数减1
			if (originalId != null) {
				microblogsDao.updateForwardCount(originalId, count);
			}
			microblogsDao.delete(microBlogEntity);
		}

	}

	/**
	 * 根据userId列表获取微博 
	 * @param userIdList 用户id列表
	 * @return 返回微博实体
	 */
	@Transactional
	@Override
	public List<SwMicroblogsEntity> getMicroBlogs(List<Long> userIdList) {
		if (userIdList.size() != 0) {
			return microblogsDao.findByList(userIdList);
		}
		return null;
	}

	/**
	 * 获取指定用户的微博
	 * @param userId 用户的id
	 * @return 返回指定用户的微博列表
	 */
	@Transactional
	@Override
	public List<SwMicroblogsEntity> getMicroBlogs(Long userId) {
		if (userId != null) {
			List<Long> userIdList = new ArrayList<Long>();
			userIdList.add(userId);
			return microblogsDao.findByList(userIdList);
		}
		return null;
	}

	/**
	 * 转发微博 
	 * @param srcMicroBlogEntity 被转发的微博 
	 * @param newMicroBlogEntity 新微博
	 */
	@Transactional
	@Override
	public void forwordMicroBlog(SwMicroblogsEntity srcMicroBlogEntity,
			SwMicroblogsEntity newMicroBlogEntity) {
		if (srcMicroBlogEntity != null && newMicroBlogEntity != null) {
			Long originalId = srcMicroBlogEntity.getOriginalmicroblogid();
			// 如果被转发微博的originalmicroblogid不为null，则源微博的转发数加1
			// 如果被转发微博的originalmicroblogid为null，则新微博的originalmicroblogid设为被转发微博的id
			if (originalId != null) {
				newMicroBlogEntity.setOriginalmicroblogid(originalId);
				SwMicroblogsEntity originalEntity = microblogsDao
						.findById(originalId);
				originalEntity.setForwardedcount(originalEntity
						.getForwardedcount() + 1);
				microblogsDao.update(originalEntity);
			} else {
				newMicroBlogEntity.setOriginalmicroblogid(srcMicroBlogEntity
						.getMicroblogid());
			}
			// 将新微博的forwardedmicroblogid设为被转发微博的id
			newMicroBlogEntity.setForwardedmicroblogid(srcMicroBlogEntity
					.getMicroblogid());
			String newbody = newMicroBlogEntity.getBody() + "//"
					+ srcMicroBlogEntity.getBody();
			newMicroBlogEntity.setBody(newbody);
			// 将被转发微博的转发数加1
			srcMicroBlogEntity.setForwardedcount(srcMicroBlogEntity
					.getForwardedcount() + 1);
			microblogsDao.update(srcMicroBlogEntity);
			createMicroBlog(newMicroBlogEntity);
		}
	}

	/**
	 * 减少微博的回复计数
	 * @param microBlogEntity 微博实体
	 * @param step 回复减少的个数
	 */
	@Transactional
	@Override
	public void decreaseMicroBlogReplyCount(SwMicroblogsEntity microBlogEntity,
			int step) {
		if (microBlogEntity != null) {
			if (Long.valueOf(step).compareTo(
					microBlogEntity.getReplycount()) == 1) {
				microBlogEntity.setReplycount(Long.valueOf(0));
			} else {
				microBlogEntity.setReplycount(microBlogEntity.getReplycount() - step);
			}
			microblogsDao.update(microBlogEntity);
		}
	}

}
