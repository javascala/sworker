package com.sworker.service;

import com.sworker.dao.IBlogDao;
import com.sworker.entity.AcitvityEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangjin on 2014/8/7.
 */
@Component("blogService")
public class BlogServiceImpl implements IBlogService {

    @Resource(name="blogDao")
    private IBlogDao blogDao;


    @Transactional
    @Override
    public List<AcitvityEntity> dynamicList(){
        return blogDao.dynamicList();
    }

    @Transactional
    @Override
    public void addDynamic(AcitvityEntity entity){
        if(entity != null)
            blogDao.addActivity(entity);
    }
}
