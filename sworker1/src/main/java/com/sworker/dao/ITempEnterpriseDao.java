package com.sworker.dao;

import com.sworker.model.TempEnterpriseAppEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/25.
 */
public interface ITempEnterpriseDao {

    public List<TempEnterpriseAppEntity> orderPage(Long enterpriseId,Integer pageIndex, Integer pageSize);
}
