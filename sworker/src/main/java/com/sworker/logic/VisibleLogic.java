
package com.sworker.logic;

import com.sworker.dao.IVisibleDao;
import com.sworker.entity.SwVisibleRangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangying on 2014/8/26.
 */
@Component
public class VisibleLogic implements IVisibleLogic {
    @Autowired
    private IVisibleDao visibleDao;

    /**
     * 新增可见范围
     * @param visibleRangeEntityList 可见范围列表
     */
    @Transactional
    @Override
    public void addVisibleRange(List<SwVisibleRangeEntity> visibleRangeEntityList) {
        visibleDao.insertAll(visibleRangeEntityList);
    }

    /**
     * 根据对象ID查询可见范围列表
     * @param objectId 对象ID
     * @return  返回可见范围实体列表
     */
    @Transactional
    @Override
    public List<SwVisibleRangeEntity> searchViewRangeList(Long objectId) {
        if (objectId != null) {
           return  visibleDao.findViewRangeID(objectId);
        }
        return  null;
    }

    /**
     * 根据objectId删除可见范围
     * @param objectId 对象ID
     * @return 是否删除成功 true:成功  false:失败
     */
    @Transactional
    @Override
    public Boolean deleteVisibleRange(Long objectId) {
        Boolean flag = false;
        if (objectId != null) {
            visibleDao.deleteByObjectId(objectId);
            flag = true;
        }
        return flag;
    }


    /**
     * 修改可见范围
     * @param objectId 对象ID
     * @param visibleRangeEntityList 可见范围实体列表
     */
    @Transactional
    @Override
    public void updateVisibleRange(Long objectId, List<SwVisibleRangeEntity> visibleRangeEntityList) {
        List<SwVisibleRangeEntity> visible = visibleDao.findByObjectId(objectId);
        if (objectId != null  ) {
            if (visible.size()!=0) {
                visibleDao.deleteByObjectId(objectId);
                visibleDao.insertAll(visibleRangeEntityList);
            }
        }
    }

    /**
     *判断当前用户可以看见资源
     * @param objectId 对象id
     * @param userId 用户id
     * @return 是否可见 true:可见   false：不可见
     */
    @Transactional
    @Override
    public Boolean isUserView(Long objectId, Long userId) {

        if (objectId != null && userId != null) {
            List<BigInteger> userIdList = visibleDao.finduserIdList(objectId,"用户");
           if(userIdList.size() != 0){
               Iterator<BigInteger> it = userIdList.iterator();
               while (it.hasNext()) {
                 if (it.next().equals(userId)) {
                     return true;
                 } else {
                    List<BigInteger> groupIdList = visibleDao.findgroupIdList(objectId,"群组");
                     if (groupIdList.size() != 0) {
                         //TODO:getGroupsByUserID无法调用
                     }
                 }
               }
           }
        }
        return false;
    }
}

