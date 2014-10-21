package com.sworker.dao;

import com.sworker.model.TempUserAppEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/25.
 */

public interface ITempUserAppDao {
    public List<TempUserAppEntity> orderByPage(Integer pageIndex,Integer pageSize);
}
