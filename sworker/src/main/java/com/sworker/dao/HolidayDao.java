package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwHolidayinfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
@Repository("holidayDao")
public class HolidayDao extends AbstractHibernateDao<SwHolidayinfoEntity> implements IHolidayDao{

    public HolidayDao(){
        super();
        this.setClazz(SwHolidayinfoEntity.class);
    }

    /**
     * 节日设置查询处理
     * @param country  国家：全部为‘2’,中国为‘0’，外国为‘1’
     * @return 节日信息列表
     */
    @Override
    @Transactional
    public List<SwHolidayinfoEntity> searchHoliday(Integer country) {
        if(country == 2){
            Session session = getCurrentSession();
            Query query = session.createQuery(" from SwHolidayinfoEntity s where s.country = 2");
            return query.list();
        }else{
            Session session = getCurrentSession();
            Query query = session.createQuery("from SwHolidayinfoEntity s where s.country = :ct");
            query.setInteger("ct",country);
            return query.list();
        }
    }
}
