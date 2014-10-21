package com.sworker.logic;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.IActivityInboxDao;
import com.sworker.dao.IActivityItemDao;
import com.sworker.dao.IActivitySetDao;
import com.sworker.dao.IActivtityDao;
import com.sworker.entity.SwActivityEntity;
import com.sworker.entity.SwActivityInboxEntity;
import com.sworker.entity.SwActivityItemEntity;
import com.sworker.entity.SwActivitySetEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
public class ActivityLogic implements IActivityLogic {

    @Autowired
    private IActivtityDao activityDao;
    @Autowired
    private IActivityInboxDao inboxDao;
    @Autowired
    private IActivityItemDao itemDao;
    @Autowired
    private IActivitySetDao setDao;

    /**
     * 生成动态
     * @param activityEntity 动态实体
     * @param uerSet 用户id集合
     */
    @Override
    public void genarateActivity(SwActivityEntity activityEntity, Set<Long> userIdSet) {
        activityDao.create(activityEntity);
        insertUserInboxs(activityEntity.getActivityid(), userIdSet);
    }

    /**
     * 将动态加入到用户动态收件箱
     * @param: activityId 动态的id 
     * @param userIds 用户id集合
     */
    @Transactional
    @Override
    public void insertUserInboxs(Long activityId, Set<Long> userIds) {
        SwActivityInboxEntity inbox = new SwActivityInboxEntity();
        Iterator<Long> ids = userIds.iterator();
        while(ids.hasNext()){
            long userId = ids.next();
            inbox.setActivityid(activityId);
            inbox.setUserid(userId);
            inboxDao.create(inbox);
        }
    }

    /**
     * 根据id查找动态内容
     * @param activity 动态的id
     * @return 返回动态实体
     */
    @Transactional
    @Override
    public SwActivityEntity getActivity(Long activityId) {
        return activityDao.findById(activityId);
    }

    /**
     *将动态加入到动态表中
     *@param activityEntity 动态实体
     *@return 返回动态实体
     */
    @Transactional
    @Override
    public SwActivityEntity insertActivity(SwActivityEntity activityEntity) {
        activityDao.create(activityEntity);
        return activityEntity;
    }

    /**
     * 根据id删除动态实体
     * @param activityId 动态实体的id
     */
    @Transactional
    @Override
    public void deleteActivity(Long activityId) {
        activityDao.deleteById(activityId);
    }

    /**
     * 更新动态的私有状态
     * @param activityId 动态的id
     * @param isPrivate 动态是否为私有
     */
    @Transactional
    @Override
    public void updatePrivateStatus(Long activityId, Integer isPrivate) {
        activityDao.updatePrivate(activityId, isPrivate);
    }

    /**
	 * 按条件查询动态
	 * @param userId 用户id
	 * @param followGroupId 关注类型，-2相互关注，-3悄悄关注
	 * @param appId 应用主键
	 * @param mediaType 媒体类型
	 * @param isOriginalThread 是否原创
	 * @param pageIndex 当前页
	 * @param pageSize 每页数据个数
	 * @return 动态实体列表
	 */
    @Transactional
    @Override
    public List<SwActivityEntity> getMyActivity(Long userId, Integer followGroupId,
    	String appId, Integer mediaType, Integer isOriginalThread, Integer pageIndex, Integer pageSize) {
        return activityDao.findActivities(userId, followGroupId, appId, mediaType, 
        		isOriginalThread, pageIndex, pageSize);
    }

    /**
     * 获取查询自lastActivityId用户动态的更新数量
     * @param userId 用户的id 
     * @param lastActivityEntity 最近一次更新的实体
     * @return 返回更新的数量
     */
    @Transactional
    @Override
    public Integer getNewerCount(Long userId, SwActivityEntity lastActivityEntity) {
        return activityDao.findCount(lastActivityEntity, userId);
    }

    /**
     * 获取指定人员的最新动态
     * @param userId 人员id
     * @param lastActivityEntity 指定的动态实体
     * @return 动态实体列表
     */
    @Transactional
    @Override
    public List<SwActivityEntity> getNewerActivity(Long userId, SwActivityEntity lastActivityEntity) {
        return activityDao.findActivities(lastActivityEntity.getLastmodified(), userId);
    }

    /**
     * 为APP注册动态项目
     * @param activityItemEntity 动态项目实体
     */
    @Transactional
    @Override
    public void regisiterActivityItem(SwActivityItemEntity activityItemEntity) {
        itemDao.create(activityItemEntity);
    }

    /**
     * 为APP注销动态项目
     * @param activityItemKey 动态项目的id
     */
    @Transactional
    @Override
    public void cancleActivityItem(String activityItemKey) {
        itemDao.deleteByKey(activityItemKey);
    }

    /**
     * 更新动态项目
     * @param activityItemEntity 动态项目表实体
     */
    @Transactional
    @Override
    public void updateActivityItem(SwActivityItemEntity activityItemEntity) {
        itemDao.update(activityItemEntity);
    }

    /**
     * 获取所有动态项目
     * @return 返回动态项目实体列表
     */
    @Transactional
    @Override
    public List<SwActivityItemEntity> getActivityItem() {
        return itemDao.findAll();
    }

    /**
     * 根据ActivityItemKey获取指定动态项目
     * @param activityItemKey 动态项目的key
     * @return 返回动态项目实体
     */
    @Transactional
    @Override
    public SwActivityItemEntity getActivityItem(String activityItemKey) {
        return itemDao.findByKey(activityItemKey);
    }

    /**
     * 创建用户动态设置
     * @param userId 用户的id 
     * @param activityItemKey 动态项目实体id 
     * @param isReceived 是否接收
     */
    @Transactional
    @Override
    public void createActivityItemUserSetting(Long userId, String activityItemKey, Integer isReceived) {
        SwActivitySetEntity set = new SwActivitySetEntity();
        set.setIsreceived(isReceived);
        set.setItemkey(activityItemKey);
        set.setUserid(userId);
        setDao.create(set);
    }

    /**
     * 获取用户动态设置
     * @param userId 用户的id 
     * @param activityItemKey 动态的id
     * @return 返回是否接收
     */
    @Transactional
    @Override
    public Integer getActivityItemUserSetting(Long userId, String activityItemKey) {
        return setDao.findSetting(userId, activityItemKey);
    }

    /**
     * 删除用户的动态设置
     * @param userId 用户的id
     */
    @Transactional
    @Override
    public void deleteActivityItemUserSetting(Long userId) {
        setDao.deleteByUserId(userId);
    }
}
