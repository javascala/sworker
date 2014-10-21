package com.sworker.logic;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.IEnterpriseRankDao;
import com.sworker.entity.SwRankInfoEntity;
import com.sworker.model.EnterpriseRankParam;
import com.sworker.model.RankInfoParam;

/**
 * 
 * @author wanggang 2014/09/11
 *
 */
@Component("enterpriseranklogic")
public class EnterpriseRankLogic implements IEnterpriseRankLogic {
	
	@Resource
	private IEnterpriseRankDao enterpriserankdao;
	
	
	/**
	 * 查询所有企业等级
	 * @return 企业等级实体列表
	 */
	@Override
	@Transactional
	public List<SwRankInfoEntity> searchRanks() {
		return enterpriserankdao.findAllRank();
	}

	/**
	 * 判断公司等级是否存在
	 * @param rank 等级
	 * @return 存在标志
	 */
	@Override
	@Transactional
	public Boolean isCompanyRankExist(Integer rank) {
	  if(null !=rank){
		List<SwRankInfoEntity> resultList=enterpriserankdao.checkRankExist(rank);
		if(resultList.size()>0){
			return true;
		}
	  }
	  return false;
	}

	
	/**
	 * 创建等级
	 * @param rankentity 企业等级实体
	 * @return 元组实体 封装返回信息，返回主键
	 */
	@Override
	@Transactional
	public RankInfoParam createRank(SwRankInfoEntity rankentity) {
		if(rankentity !=null){
			if(rankentity.getRank()!=null&&rankentity.getPointlower()!=null
					&&rankentity.getRankname()!=null){
				
				if(isCompanyRankExist(rankentity.getRank())){
					
					return new RankInfoParam("保存失败，实体已存在",null);
				}else{
					
					enterpriserankdao.saveRankInfo(rankentity);
					return new RankInfoParam("保存成功",rankentity.getRank());
				}
			}
		}
		return null;
	}

	
	/**
	 * 修改企业等级
	 * @param rankentity 企业等级实体
	 * @return 元组实体 封装返回信息，返回主键
	 */
	@Override
	@Transactional
	public RankInfoParam updateRank(SwRankInfoEntity rankentity) {
		if(rankentity !=null){
			if(rankentity.getRank()!=null&&rankentity.getPointlower()!=null
					&&rankentity.getRankname()!=null){
				enterpriserankdao.updateRankInfo(rankentity);
				return new RankInfoParam("修改成功",null);
			}
		}
		return null;
	}

	/**
	 * 删除企业等级
	 * @param rank 等级
	 * @return 删除标志  删除成功返回true,失败返回false
	 */
	@Override
	@Transactional
	public Boolean deleteRank(Integer rank) {
		if(rank !=null){
			enterpriserankdao.deleteRankInfo(rank);
			//返回值问题
			return true;
		}
		return false;
	}

	/**
	 * 计算公司等级,根据某个指标（综合积分），全部计算
	 * @return 成功返回true,失败返回false
	 */
	@Override
	@Transactional
	public Boolean resetCompanysRank() {
		List<EnterpriseRankParam> list=enterpriserankdao.getEnterprisePoint();
		Iterator<EnterpriseRankParam> it =list.iterator();
		while(it.hasNext()){
			EnterpriseRankParam param=it.next();
			if(param.getEnterpriseid() !=null &&param.getPoint() !=null){
				
				//通过积分计算出企业相应的等级
				Integer rank=this.getRankByPoint(param.getPoint());
				//针对每一个point都要执行一遍 searchRanks()
				enterpriserankdao.UpdateEnterpriseRank(param.getEnterpriseid(), rank);
			}
		}
		
		return null;
	}

	/**
	 * 根据积分算等级
	 * @param point 积分
	 * @return 等级名称
	 */
	@Override
	@Transactional
	public Integer getRankByPoint(Long point) {
		List<SwRankInfoEntity> result=searchRanks();
		//result 按lowerpoint大小逆序排列
		Iterator it =result.iterator();
		while(it.hasNext()){
			SwRankInfoEntity rank=(SwRankInfoEntity)it.next();
			//计算等级算法
			//lowerpoint逆序排列
			if(rank.getPointlower()<=point){
				return rank.getRank();
			}
		}
		
		return null;
	}

	

	
	
	
	
	
}
