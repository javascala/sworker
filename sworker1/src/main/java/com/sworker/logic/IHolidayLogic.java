package com.sworker.logic;

import com.sworker.entity.SwHolidayinfoEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
public interface IHolidayLogic {
    /**
     * 实现一个节日信息的新增
     * @param holidayinfoEntity 节日实体类
     * @return 布尔型 成功or失败
     */
    public Boolean addHoliday (SwHolidayinfoEntity holidayinfoEntity);

    /**
     * 实现节日信息删除功能
     * @param id 主键ID
     */
    public Boolean delHoliday (long id);

    /**
     * 实现一个节日信息内容的编辑
     * @param holidayinfoEntity 节日实体类
     * @return 布尔型 成功or失败
     */
    public Boolean editHoliday (SwHolidayinfoEntity holidayinfoEntity);

    /**
     * 查询节日信息列表
     * @param country 国家，全部为‘2’,中国为‘0’，外国为‘1’
     * @return 节日信息列表
     */
    public List<SwHolidayinfoEntity> queryHoliday(Integer country);
}
