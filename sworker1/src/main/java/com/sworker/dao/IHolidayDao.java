package com.sworker.dao;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwHolidayinfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
@Repository
public interface IHolidayDao extends IOperations<SwHolidayinfoEntity> {

    /**
     * 节日设置查询处理
     * @param country  国家：全部为‘2’,中国为‘0’，外国为‘1’
     * @return 节日信息列表
     */
    public List<SwHolidayinfoEntity> searchHoliday(Integer country);
}
