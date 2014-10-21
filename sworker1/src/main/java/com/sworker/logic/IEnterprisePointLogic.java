package com.sworker.logic;

import java.util.Date;
import java.util.List;

import com.sworker.entity.SwExpiredpointEntity;
import com.sworker.entity.SwPointRecordsEntity;
import com.sworker.entity.SwPointRuleEntity;
import com.sworker.model.EnterprisePointParam;
import com.sworker.model.PointParamInfo;

/**
 * 
 * @author wanggang 2014/09/16
 *
 */
public interface IEnterprisePointLogic {
	
	/**
	 * 查询积分列表 
	 * @param level 等级
	 * @param enterprisename 企业名称
	 * @param pageIndex 开始页
	 * @param pageSize 分页大小
	 * @return 积分列表
	 */
	public List<EnterprisePointParam> searchPointList(Integer level,String enterprisename,
			Integer pageIndex,Integer pageSize);
	
	
	
	/**
	 * 导出积分列表 
	 * @param level 等级
	 * @param enterprisename 企业名称
	 * @param pageIndex 开始页
	 * @param pageSize 分页大小
	 * @return 导出结果标志
	 */
	public Boolean exportPointList(Integer level,String enterprisename,
			Integer pageIndex,Integer pageSize);
	
	
	/**
	 * 保存企业积分
	 * @param record 积分统计实体
	 * @param enddate 到期时间
	 * @return 增加积分返回信息
	 */
	public PointParamInfo savePointForCompany(SwPointRecordsEntity record,Date enddate);
	
	/**
	 * 到期时间是否存在
	 * @param enterpriseid 企业ID
	 * @param enddate 到期时间
	 * @return 保存结果标志
	 */
	public Boolean isEndDateExist(Long enterpriseid,Date enddate);
	
	/**
	 * 保存积分统计
	 * @param record 积分统计实体
	 * @return 保存结果
	 */
	public Boolean savePointStatistics(SwPointRecordsEntity record);
	
	/**
	 * 保存到期积分
	 * @param expiredpoint 到期积分实体
	 * @return 保存结果标志
	 */
	public Boolean saveEndDatePoints(SwExpiredpointEntity expiredpoint);
	
	
	/**
	 * 扣除到期积分值
	 * @param enterpriseid 企业ID
	 * @param point 积分值
	 */
	public void reduceEndDatePoint(Long enterpriseid,Long point);
	
	
	/**
	 * 删除到期积分 
	 * @param enterpriseid 企业ID
	 * @param enddate 到期日期
	 * @return 删除结果标志
	 */
	public Boolean deleteEndDatePoint(Long enterpriseid,Date enddate);
	
	
	
	/**
	 * 给企业积分进行调整
	 * @param enterpriseid 企业ID
	 * @param presentPoint 当前积分值
	 * @param modifiedPoint 调整后积分值
	 * @param enddate 到期日期
	 * @param isModifyEnddate 是否调整到期日期
	 * @param description 调整积分描述
	 */
	public void updatePoint(Long enterpriseid,Long presentPoint,Long modifiedPoint,
			Date enddate,Boolean isModifyEnddate,String description);
	
	
	
	
	/**
	 * 积分系统交易,积分商品兑换、功能使用积分兑换
	 * @param point 交易积分值
	 * @param enterpriseid 企业ID
	 * @return 返回信息实体
	 */
	public PointParamInfo tradeToSystem(Long point,Long enterpriseid);
	
	
	
	/**
	 * 修改企业积分规则
	 * @param rule 积分规则列表
	 * @return 返回值实体
	 */
	public PointParamInfo updatePointRules(List<SwPointRuleEntity> rule);
	
	
	
	/**
	 * 根据类型返回积分规则列表
	 * @param type 规则类型
	 * @return 积分规则实体列表
	 */
	public List<SwPointRuleEntity> getPointRuleByType(Integer type);
	
	
	/**
	 * 根据类型等级标准值返回积分值
	 * @param rank 等级
	 * @param type 类型
	 * @param standard 标准值（消费的金钱数）
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
	public List<SwPointRecordsEntity> searchPointRecord(Long enterpriseid,Date time,Integer type);
	
	
	/**
	 * 查看企业基本信息（可用积分，累计获得积分 最近快到期积分，最近到期时间）
	 * @param enterpriseid 企业ID
	 * @return 积分信息实体
	 */
	public EnterprisePointParam searchPointInfoById(Long enterpriseid);
	
	
}
