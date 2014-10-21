package com.sworker.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sworker.entity.SwExpiredpointEntity;
import com.sworker.entity.SwPointRecordsEntity;
import com.sworker.entity.SwPointRuleEntity;
import com.sworker.model.EnterprisePointParam;

/**
 * 
 * @author wanggang 2014/09/16
 *
 */
public interface IEnterprisePointDao {

	/**
	 * 查询积分列表
	 * @param level 企业等级
	 * @param enterprisename 企业名称
	 * @param pageIndex 起始记录
	 * @param pageSize 分页大小
	 * @return 积分列表
	 */
	public List<EnterprisePointParam> searchPoint(Integer level,String enterprisename,
			Integer pageIndex,Integer pageSize);
	
	
	
	/**
	 * 保存积分统计
	 * @param record 积分统计
	 */
	public Boolean savePointStatistics(SwPointRecordsEntity record);
	
	/**
	 * 保存到期积分实体
	 * @param expiredpoint 到期积分实体
	 * @return 保存结果标志
	 */
	public Boolean saveEndDatePoints(SwExpiredpointEntity expiredpoint);
	
	
	/**
	 * 修改到期积分信息
	 * @param expiredpoint 到期积分实体
	 * @return 修改标志
	 */
	public Boolean updateEndDatePoints(SwExpiredpointEntity expiredpoint);
	
	/**
	 * 查询到期积分实体
	 * @param enterpriseid 企业ID
	 * @param enddate 到期时间
	 * @return 到期积分实体的ID
	 */
	public Long checkEndDateExist(Long enterpriseid, Date enddate);
	
	/**
	 * 修改企业积分累计积分值，积分值
	 * @param totalpoint 累计积分值
	 * @param point 积分值
	 * @return 修改结果标志
	 */
	public Boolean modifyEnterprisePoint(Long enterpriseid,Long point);
	
	/**
	 * 修改企业积分值
	 * @param point 积分值
	 * @return 修改标志
	 */
	public Boolean modifyEnterprisePointx(Long enterpriseid,Long point);
	
	/**
	 * 查询到期积分
	 * @param enterpriseid 企业ID
	 * @return 到期积分值
	 */
	public Long searchEndDatePoint(Long enterpriseid);
	
	/**
	 * 修改到期积分
	 * @param enterpriseid 企业ID
	 * @param point 积分值
	 */
	public void modifyExpiredPoint(Long enterpriseid,Long point);
	
	/**
	 * 删除到期积分
	 * @param enterpriseid 企业ID
	 */
	public void deleteExpiredPoint(Long enterpriseid);
	
	/**
	 * 删除到期积分
	 * @param enterpriseid 企业ID
	 * @param enddate 到期日期
	 */
	public Boolean deleteExpiredPoint(Long enterpriseid,Date enddate);
	
	/**
	 * 查询积分规则实体
	 * @param type 规则类型
	 * @return 积分规则实体列表
	 */
	public List<SwPointRuleEntity> getPointRuleByType(Integer type);
	
	
	/**
	 * 查询积分规则积分
	 * @param rank 等级
	 * @param type 类型
	 * @param standard 标准值
	 * @return 积分值
	 */
	public Long getPointByRankRule(Integer rank,Integer type,Long standard);
	
	/**
	 * 查询企业积分统计
	 * @param enterpriseid 企业ID
	 * @param time 年份
	 * @param type 统计类型
	 * @return 积分统计实体列表
	 */
	public List<SwPointRecordsEntity> searchPointRecord(Long enterpriseid,Timestamp begindate,Timestamp enddate,Integer type);
	
	/**
	 * 查询企业的到期日期 SQL 13
	 * @param enterpriseid 企业ID
	 * @return 到期积分实体，封装date和point 
	 */
	public SwExpiredpointEntity searchEndDate(Long enterpriseid);
	
	
	/**
	 * 修改到期积分
	 * @param enterpriseid 企业ID
	 * @param date 新的到期日期
	 * @param ExPoint 参数.积分值
	 */
	public void updateExpiredPoint(Long enterpriseid,Date date,Long ExPoint);
	
	
	/**
	 * 查询企业基本信息积分值
	 * @param enterpriseid 企业ID
	 * @return  积分值
	 */
	public Long searchEnterprisePoint(Long enterpriseid);
	
	/**
	 * 删除所有的积分规则
	 * @return 是否成功
	 */
	public Boolean deleteAllPointRules();
	
	/**
	 * 保存积分规则实体
	 * @param rule 积分规则实体
	 */
	public void savePointRule(SwPointRuleEntity rule);
	
	/**
	 * 查询企业积分信息实体
	 * @param enterpriseid 企业ID
	 * @return 积分信息实体 封装字段形成的实体
	 */
	public EnterprisePointParam searchPointInfoById(Long enterpriseid);
	
}
