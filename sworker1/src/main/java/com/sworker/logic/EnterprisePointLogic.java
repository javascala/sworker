package com.sworker.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.common.CodeBook;
import com.sworker.common.util.DateUtil;
import com.sworker.common.util.ObjectUtil;
import com.sworker.dao.IEnterprisePointDao;
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
@Component("pointlogic")
public class EnterprisePointLogic implements IEnterprisePointLogic {

	//定义一个全局变量用于isEndDateExist saveEndDatePoints存储查询出来的ID，以免重复查询
	private Long id;
	
	
	@Resource
	private IEnterprisePointDao pointdao;
	
	
	/**
	 * 查询积分列表 
	 * @param level 等级
	 * @param enterprisename 企业名称
	 * @param pageIndex 开始页
	 * @param pageSize 分页大小
	 * @return 积分列表
	 */
	@Override
	@Transactional
	public List<EnterprisePointParam> searchPointList(Integer level,String enterprisename,
			Integer pageIndex,Integer pageSize) {
		return 	pointdao.searchPoint(level, enterprisename, pageIndex, pageSize);
	}


	
	@Override
	@Transactional
	public Boolean exportPointList(Integer level, String enterprisename,
			Integer pageIndex, Integer pageSize) {
		
		//TODO
		
		
		
		
		
		return null;
	}


	/**
	 * 保存企业积分
	 * @param record 积分统计实体
	 * @param enddate 到期时间
	 * @return 增加积分返回信息
	 */
	@Override
	@Transactional
	public PointParamInfo savePointForCompany(SwPointRecordsEntity record,Date enddate) {
		if(record != null){//判断积分统计是否为空
			//判断各个属性是否为空
			if(record.getEnterpriseid()!=null&&record.getType()!=null
					&&record.getTime()!=null&&record.getPoint()!=null
					&&enddate!=null){
				//保存积分统计
				if(!this.savePointStatistics(record)){
					return new PointParamInfo(false,"保存积分统计失败");
				}else{
						//保存积分统计成功则：
						SwExpiredpointEntity expired=new SwExpiredpointEntity();
						expired.setEnterpriseid(record.getEnterpriseid());
						expired.setExpiredpoint(record.getPoint());
						expired.setEnddate(enddate);
						//保存到期积分
						if(this.saveEndDatePoints(expired)){
							
							//判断积分值是否大于0，若大于0，则添加企业累计积分，添加积分值，
							//若小于0，则只添加积分值
							if(record.getPoint()>0){
								//添加企业累计积分，添加积分值
										if(pointdao.modifyEnterprisePoint(record.getEnterpriseid(),record.getPoint())){
											//修改成功
											return new PointParamInfo(true,"保存积分成功");
										}else{
											//修改失败
											return new PointParamInfo(false,"保存积分失败");
										}
								
							}else{
										//积分小于0情况
										//添加企业积分值
										if(pointdao.modifyEnterprisePointx(record.getEnterpriseid(),record.getPoint())){
											//修改成功
											return new PointParamInfo(true,"保存积分成功");
										}else{
											//修改失败
											return new PointParamInfo(false,"保存积分失败");
										}
							}
				}else{
					//保存失败
					return new PointParamInfo(false,"保存到期积分失败");
					}
				}
			}
		}
		return null;
	}


	/**
	 * 到期时间是否存在
	 * @param enterpriseid 企业ID
	 * @param enddate 到期时间
	 * @return 保存结果标志
	 */
	@Override
	@Transactional
	public Boolean isEndDateExist(Long enterpriseid, Date enddate) {
			id=pointdao.checkEndDateExist(enterpriseid, enddate); 
		if(id!=null){
			//不为空
			return true;
		}
		return false;
	}


	/**
	 * 保存积分统计
	 * @param record 积分统计实体
	 * @return 保存结果
	 */
	@Override
	@Transactional
	public Boolean savePointStatistics(SwPointRecordsEntity record) {
		return  pointdao.savePointStatistics(record);
	}


	
	/**
	 * 保存到期积分
	 * @param expiredpoint 到期积分实体 前台传递过来参数实体应该包括ID
	 * @return 保存结果标志
	 */
	@Override
	@Transactional
	public Boolean saveEndDatePoints(SwExpiredpointEntity expiredpoint) {
		if(expiredpoint!=null){
			//判断各参数是否为空
			if(expiredpoint.getEnterpriseid()!=null&&expiredpoint.getExpiredpoint()!=null
					&&expiredpoint.getEnddate()!=null){
				//判断到期日期是否存在
				if(this.isEndDateExist(expiredpoint.getEnterpriseid(), 
						expiredpoint.getEnddate())){
						expiredpoint.setId(id);
						System.out.println(id);
					//存在，修改信息
					return pointdao.updateEndDatePoints(expiredpoint);
				}else{
					//不存在直接插入
					return pointdao.saveEndDatePoints(expiredpoint);
				}
			}
		}
		return null;
	}


	/**
	 * 扣除到期积分值 n 内部迭代
	 * @param enterpriseid 企业ID
	 * @param point 积分值
	 */
	@Override
	@Transactional
	public void reduceEndDatePoint(Long enterpriseid, Long point) {
		if(enterpriseid !=null && point != null){
			if(pointdao.searchEndDatePoint(enterpriseid)>=point){
				
			pointdao.modifyExpiredPoint(enterpriseid, point);
				
			}else{
				Long enddatepoint=pointdao.searchEndDatePoint(enterpriseid);
				pointdao.deleteExpiredPoint(enterpriseid);
				//迭代自身，直到积分减够为止
				reduceEndDatePoint(enterpriseid,point-enddatepoint);
			}
			
		}
	}


	/**
	 * 删除到期积分 
	 * @param enterpriseid 企业ID
	 * @param enddate 到期日期
	 * @return 删除结果标志
	 */
	@Override
	@Transactional
	public Boolean deleteEndDatePoint(Long enterpriseid, Date enddate) {
			if(ObjectUtil.isNotEmpty(enterpriseid) && ObjectUtil.isNotEmpty(enddate)){
				return pointdao.deleteExpiredPoint(enterpriseid,enddate);
			}

		return false;
	}


	

	/**
	 * 给企业积分进行调整
	 * @param enterpriseid 企业ID
	 * @param presentPoint 当前积分值
	 * @param modifiedPoint 调整后积分值
	 * @param enddate 到期日期
	 * @param isModifyEnddate 是否调整到期日期
	 * @param description 调整积分描述
	 */
	@Override
	@Transactional
	public void updatePoint(Long enterpriseid, Long presentPoint,
			Long modifiedPoint, Date enddate, Boolean isModifyEnddate,
			String description) {
		//参数校验
		if(enterpriseid != null && presentPoint != null && modifiedPoint != null
				&& enddate != null && isModifyEnddate !=null){
			//构造统计积分实体，保存统计积分实体
			SwPointRecordsEntity record=new SwPointRecordsEntity(enterpriseid,CodeBook.PONINT_RECORDS_TYPE_MODIFY,
					description,new Timestamp(System.currentTimeMillis()),modifiedPoint-presentPoint);
			//参照savePointStatistics,保存积分统计实体，保存成功进行下一步
			if(savePointStatistics(record)){
					//判断是否调整到期日期，若调整
					if(isModifyEnddate){
								//查询企业到期日期，到期积分,此处返回到期积分实体，封装了date和point
								SwExpiredpointEntity expired=pointdao.searchEndDate(enterpriseid);
								//参照【isEndDateExist】，查询调整后的最小到期日期是否存在
								
								//删除到期积分
								pointdao.deleteExpiredPoint(enterpriseid,expired.getEnddate());
								//实现date+1年
								Calendar c = Calendar.getInstance(); 
								c.setTime(expired.getEnddate());
								c.add(Calendar.YEAR, 1);
								Date date=c.getTime();
								
								if(isEndDateExist(enterpriseid,date)){
									//到期积分存在，修改到期积分
									//SQL14    修改的积分值为原先的积分值与传递进来的（修改前的积分值和修改后积分值得差之和）
									pointdao.updateExpiredPoint(enterpriseid, date,
											expired.getExpiredpoint()+modifiedPoint-presentPoint);
								}else{
									//到期日期不存在，直接新建到期日期
									//SQL 3 保存到期积分
									SwExpiredpointEntity expiredpoint=new SwExpiredpointEntity(enterpriseid,
											expired.getExpiredpoint()+modifiedPoint-presentPoint,date); 
									pointdao.saveEndDatePoints(expiredpoint);
									
								}
					}
					
					//保存企业积分值，累计积分值
					//假设 调整后积分值-当前积分值>0
					pointdao.modifyEnterprisePoint(enterpriseid, modifiedPoint-presentPoint);
					
			}
		}
	}


	


	/**
	 * 积分系统交易,积分商品兑换、功能使用积分兑换
	 * @param point 交易积分值
	 * @param enterpriseid 企业ID
	 * @return 返回信息实体
	 */
	@Override
	@Transactional
	public PointParamInfo tradeToSystem(Long point, Long enterpriseid) {
		//参数校验
		if(ObjectUtil.isNotEmpty(point) && ObjectUtil.isNotEmpty(enterpriseid)){
			//查询企业信息积分值 SQl19
			//Enpoint 查询得到的企业拥有的积分值
			Long Enpoint=pointdao.searchEnterprisePoint(enterpriseid);
			if(Enpoint<point){
				//判断Enpoint若小于 参数.积分值
				return new PointParamInfo(false,"积分值不足");
			}else{
				//参照【savePointStatistics】，保存积分历史记录
				SwPointRecordsEntity record=new SwPointRecordsEntity(enterpriseid,CodeBook.PONINT_RECORDS_TYPE_COUSUME,"tradeToSystem",
						new Timestamp(System.currentTimeMillis()),0-point);
				
				if(savePointStatistics(record)){
					//保存成功，进行下一步
					//参照方法【reduceEndDatePoint】，扣除到期积分
						try{
							reduceEndDatePoint(enterpriseid, point);
							}catch(Exception ex){
							ex.printStackTrace();
							return new PointParamInfo(false,"积分交易失败");
							}
					//扣除成功则进行下一步
					
							//参照【SQL6】，修改企业基本信息的当前积分
							
							if(pointdao.modifyEnterprisePointx(enterpriseid, 0-point)){
								//修改成功
								return new PointParamInfo(true,"交易成功");
							}else{
								return new PointParamInfo(false,"交易失败");
							}
				}else{
					//保存失败
					return new PointParamInfo(false,"积分交易失败");
				}
			}
			
		}
		return null;
	}


	/**
	 * 修改企业积分规则
	 * @param rule 积分规则列表
	 * @return 返回值实体
	 */
	@Override
	@Transactional
	public PointParamInfo updatePointRules(List<SwPointRuleEntity> rule) {
		//参数校验
		if(rule.size()>0){
			//list不为空
			//参照【SQL9】，删除所有的积分规则，若删除成功，则进行下一步
			if(!pointdao.deleteAllPointRules()){
				//删除失败
				return new PointParamInfo(false,"修改失败");
			}
		//循环积分规则列表,参照【SQL10】，保存积分规则
			Iterator<SwPointRuleEntity> it=rule.iterator();
			try{
				while(it.hasNext()){
					//循环列表把每一对象都保存一遍
					SwPointRuleEntity ruleEntity=it.next();
					pointdao.savePointRule(ruleEntity);
				}
			return new PointParamInfo(true,"修改成功");
			}catch(Exception ex){
				//捕捉到异常，保存失败
				ex.printStackTrace();
				return new  PointParamInfo(false,"修改失败");
			}
		}
		return null;
	}



	/**
	 * 根据类型返回积分规则列表
	 * @param type 规则类型
	 * @return 积分规则实体列表
	 */
	@Override
	@Transactional
	public List<SwPointRuleEntity> getPointRuleByType(Integer type) {
		if(ObjectUtil.isNotEmpty(type)){
			
		return	pointdao.getPointRuleByType(type);
			
		}
		return null;
	}


	
	
	/**
	 * 根据类型等级标准值返回积分值
	 * @param rank 等级
	 * @param type 类型
	 * @param standard 标准值（消费的金钱数）
	 * @return 积分值
	 */
	@Override
	@Transactional
	public Long getPointByRankRule(Integer rank, Integer type, Long standard) {
		if(ObjectUtil.isNotEmpty(rank) && ObjectUtil.isNotEmpty(type)
				&& ObjectUtil.isNotEmpty(standard)){
			
			Long point =pointdao.getPointByRankRule(rank, type, standard);
			//若查询返回积分值为空，则返回积分值为0，若不为空，则返回查询的积分值
			if(point !=null){
				
				return point;
			}else{
				return new Long(0);
			}
		}
		
		return null;
	}


	/**
	 * 查询企业积分统计
	 * @param enterpriseid 企业ID
	 * @param time 年份
	 * @param type 统计类型
	 * @return 积分统计实体列表
	 */
	@Override
	@Transactional
	public List<SwPointRecordsEntity> searchPointRecord(Long enterpriseid,
			Date time, Integer type) {
		if(time != null && type !=null){
				//参照【SQL16】,查询统计实体，若查询成功，则返回积分统计实体列表
				//获得int类型的年份
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(time);
				//将Date类型转换为Calendar类型，通过calendar获取年份
				int year=calendar.get(Calendar.YEAR);
				calendar.clear();
				calendar.set(Calendar.YEAR,year);
				//开始时间，年份的第一天
				Date begindate=calendar.getTime();
			//结束时间
				//获取今年年份
				Calendar cal=Calendar.getInstance();
				int thisyear=cal.get(Calendar.YEAR);
				Date enddate=null;
			if(year == thisyear){
				//如果年份是今年,则结束时间为今天+1天
				cal.add(Calendar.DAY_OF_MONTH, 1);
				 enddate=cal.getTime();
			}else if(year<thisyear){
				//如果年份为往年，则结束时间为往年最后一天+1天,即第二年的第一天
				cal.clear();
				cal.set(Calendar.YEAR, year+1);
				enddate=cal.getTime();
			}
			pointdao.searchPointRecord(enterpriseid, DateUtil.dateToTimeStamp(begindate),
					DateUtil.dateToTimeStamp(enddate), type);
		}
		return null;
	}


	
	
	/**
	 * 查看企业基本信息（可用积分，累计获得积分 最近快到期积分，最近到期时间）SQL8
	 * @param enterpriseid 企业ID
	 * @return 积分信息实体
	 */
	@Override
	@Transactional
	public EnterprisePointParam searchPointInfoById(Long enterpriseid) {
		
		return pointdao.searchPointInfoById(enterpriseid);
		
	}
	
	
	
	
	
	
	
}
