package com.sworker.service;

import com.sworker.entity.AcitvityEntity;

import java.util.List;

/**
 * Created by zhangjin on 2014/8/7.
 */
public interface IBlogService {

    List<AcitvityEntity> dynamicList();

    void addDynamic(AcitvityEntity entity);
}
