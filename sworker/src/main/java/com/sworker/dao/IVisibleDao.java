package com.sworker.dao;

import com.sworker.entity.SwVisibleRangeEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by wangying on 2014/8/26.
 */
@Repository
public interface IVisibleDao  {
    /**
     * 查询可见范围群组ID列表
     * @param objectid 用户id
     * @return 可见范围实体
     */
    public List<SwVisibleRangeEntity> findViewRangeID(Long objectid);

    /**
     *插入可见范围实体
     * @param visibleRangeEntityList 可见范围列表
     */
    public void insertAll(List<SwVisibleRangeEntity> visibleRangeEntityList);
    /**
     * 删除可见范围
     * @param objectid 用户id
     * @return 是否删除成功 true:成功 false:失败
     */
    public boolean deleteByObjectId(Long objectid) ;
    /**
     * 查询可见范围实体列表列表
     * @param objectid  对象id
     * @return 可见范围实体列表
     */
    public List<SwVisibleRangeEntity> findByObjectId(Long objectid);
    /**
     * 查询群组ID列表
     * @param objectid 用户Id
     * @param viewrangetype 范围类型
     * @return Id列表
     */
    public List<BigInteger> findgroupIdList(Long objectid,String viewrangetype);

    /**
     * 查询用户ID列表
     * @param objectid 用户Id
     * @param viewrangetype 范围类型
     * @return
     */
    public List<BigInteger> finduserIdList(Long objectid,String viewrangetype);
}