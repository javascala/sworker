package com.sworker.dao;

import com.sworker.entity.AcitvityEntity;

import java.util.List;

/**
 * Created by zhangjin on 2014/8/7.
 */
public interface IBlogDao {
    List<AcitvityEntity> dynamicList();
//    AcitvityEntity entity
    void addActivity(AcitvityEntity entity);
}
