package com.sworker.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwActivityEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
public interface IActivtityDao extends IOperations<SwActivityEntity> {

    /**
     * 更行动态的私有属性
     * @param userId 用户的id
     * @param isPrivate 是否为私有
     */
    public void updatePrivate(Long userId, Integer isPrivate);
    /**
     * 查找制定用户的更新数量
     * @param activity 更新实体 
     * @param userId 用户的id
     * @return 返回更新的数量
     */
    public Integer findCount(SwActivityEntity activity, Long userId);
    /**
     * 查找用户动态
     * @param  lastTime 最后更新时间
     * @param userId 用户id
     * @return  返回动态实体列表
     */
    public List<SwActivityEntity> findActivities(Timestamp lastTime, Long userId);
	/**
	 * 查找动态
	 * @param userId 用户id
	 * @param followGroupType 关注类型，-2相互关注，-3悄悄关注
	 * @param appKey 应用主键
	 * @param mediaType 媒体类型
	 * @param isOriginal 是否原创
	 * @param pageIndex 当前页
	 * @param pageSize 每页数据个数
	 * @return 动态实体列表
	 */
	public List<SwActivityEntity> findActivities(Long userId, Integer followGroupType, String appKey,
			Integer mediaType, Integer isOriginal, Integer pageIndex, Integer pageSize);

}
