package com.sworker.logic;

import com.sworker.dao.IHolidayDao;
import com.sworker.entity.SwHolidayinfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
@Component
public class HolidayLogic implements IHolidayLogic{
    @Resource
    private IHolidayDao holidayDao;

    /**
     * 实现一个节日信息的新增
     * @param holidayinfoEntity 节日实体类
     * @return 布尔型 成功or失败
     */
    @Override
    @Transactional
    public Boolean addHoliday(SwHolidayinfoEntity holidayinfoEntity) {
        if(holidayinfoEntity.getHolidayname() != null && holidayinfoEntity.getCountry() != null
                && holidayinfoEntity.getDateinfo() !=null){
            holidayDao.create(holidayinfoEntity);
            return true;
        }
        return false;
    }

    /**
     * 实现节日信息删除功能
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Boolean delHoliday(long id) {
        if(id != 0) {
            holidayDao.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * 实现一个节日信息内容的编辑
     * @param holidayinfoEntity 节日实体类
     * @return 布尔型 成功or失败
     */
    @Override
    @Transactional
    public Boolean editHoliday(SwHolidayinfoEntity holidayinfoEntity) {
        if(holidayinfoEntity.getId() != 0){
            holidayinfoEntity.setHolidaytype(1);
            holidayDao.update(holidayinfoEntity);
            return true;
        }
        return false;
    }

    /**
     * 查询节日信息列表
     * @param country 国家，全部为‘2’,中国为‘0’，外国为‘1’
     * @return 节日信息列表
     */
    @Override
    @Transactional
    public List<SwHolidayinfoEntity> queryHoliday(Integer country) {
        if (country != null) {
            return holidayDao.searchHoliday(country);
        }
        return null;
    }
}
