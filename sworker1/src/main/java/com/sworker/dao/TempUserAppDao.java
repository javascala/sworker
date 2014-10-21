package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.model.TempUserAppEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangying on 2014/9/25.
 */
@Component("tempuserappdao")
public class TempUserAppDao extends AbstractHibernateDao implements ITempUserAppDao {

    public TempUserAppDao(){
        super();
        this.setClazz(TempUserAppEntity.class);
    }
    @Override
    public List<TempUserAppEntity> orderByPage(Integer pageIndex, Integer pageSize) {
        return findByPage(pageIndex,pageSize);
    }
}
