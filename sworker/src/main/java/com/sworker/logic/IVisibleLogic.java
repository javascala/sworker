package com.sworker.logic;

import com.sworker.entity.SwVisibleRangeEntity;

import java.util.List;

/**
 * Created by wangying on 2014/8/26.
 */
public interface IVisibleLogic {

    public void addVisibleRange(List<SwVisibleRangeEntity> visibleRangeEntityList);
    public List<SwVisibleRangeEntity> searchViewRangeList(Long objectId);
    public Boolean deleteVisibleRange(Long objectId);
    public void updateVisibleRange(Long objectId, List<SwVisibleRangeEntity> visibleRangeEntityList);
    public Boolean isUserView(Long objectId, Long userId);

}