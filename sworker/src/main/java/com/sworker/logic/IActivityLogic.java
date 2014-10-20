package com.sworker.logic; 

import java.util.List;
import java.util.Set;

import com.sworker.entity.SwActivityEntity;
import com.sworker.entity.SwActivityItemEntity;


/**
 * Created by jiangcy on 2014/8/21.
 */
public interface IActivityLogic {
	
	
	public void genarateActivity(SwActivityEntity activityEntity, Set<Long> userIdSet); 
	/**
	 * 将动态加入到用户动态收件箱
	 * @param  activityId 动态的id  
	 * @param userIds 用户id集合
	 */
	public void insertUserInboxs(Long activityId, Set<Long> userIds);
	/**
	 * 根据id查找动态内容
	 * @param  activity 动态的id
	 * @return  返回动态实体
	 */
	public SwActivityEntity getActivity(Long activityId);
	/**
	 *将动态加入到动态表中
	 *@param  activityEntity 动态实体
	 *@return  返回动态实体 
	 */
	public SwActivityEntity insertActivity(SwActivityEntity activityEntity); 
	/**
	 * 根据id删除动态实体
	 * @param  activityId 动态实体的id
	 */
	public void deleteActivity(Long activityId); 
	/**
	 * 更新动态的私有状态
	 * @param  activityId 动态的id 
	 * @param isPrivate 动态是否为私有
	 */
	public void updatePrivateStatus(Long activityId, Integer isPrivate); 
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
	public List<SwActivityEntity> getMyActivity(Long userId, Integer followGroupId,
	    	String appId, Integer mediaType, Integer isOriginalThread, Integer pageIndex, Integer pageSize);
	/**
	 * 获取查询自lastActivityId用户动态的更新数量
	 * @param userId 用户的id 
	 * @param lastActivityEntity 最近一次更新的实体
	 * @return 返回更新的数量
	 */
	public Integer getNewerCount(Long userId, SwActivityEntity lastActivityEntity);
	 /**
     * 获取指定人员的最新动态
     * @param userId 人员id
     * @param lastActivityEntity 指定的动态实体
     * @return 动态实体列表
     */
	public List<SwActivityEntity> getNewerActivity(Long userId, SwActivityEntity lastActivityEntity); 
	/**
	 * 为APP注册动态项目
	 * @param activityItemEntity 动态项目实体
	 */
	public void regisiterActivityItem(SwActivityItemEntity activityItemEntity); 
	/**
	 * 为APP注销动态项目
	 * @param activityItemKey  动态项目的id
	 */
	public void cancleActivityItem(String activityItemKey); 
	/**
	 * 更新动态项目
	 * @param activityItemEntity 动态项目表实体
	 */
	public void updateActivityItem(SwActivityItemEntity activityItemEntity); 
	/**
	 * 获取所有动态项目
	 * @return 返回动态项目实体列表
	 */
	public List<SwActivityItemEntity> getActivityItem(); 
	/**
	 * 根据ActivityItemKey获取指定动态项目
	 * @param activityItemKey 动态项目的key
	 * @return 返回动态项目实体
	 */
	public SwActivityItemEntity getActivityItem(String activityItemKey); 
	/**
	 * 创建用户动态设置
	 * @param userId 用户的id 
	 * @param activityItemKey 动态项目实体id  
	 * @param isReceived 是否接收
	 */
	public void createActivityItemUserSetting(Long userId, String activityItemKey, Integer isReceived); 
	/**
	 * 获取用户动态设置
	 * @param userId 用户的id 
	 * @param activityItemKey 动态的id
	 * @return 返回是否接收
	 */
	public Integer getActivityItemUserSetting(Long userId, String activityItemKey); 
	/**
	 * 删除用户的动态设置
	 * @param userId 用户的id
	 */
	public void deleteActivityItemUserSetting(Long userId); 

}
