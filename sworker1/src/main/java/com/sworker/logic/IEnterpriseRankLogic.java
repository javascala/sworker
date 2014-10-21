package com.sworker.logic;

import java.util.List;

import com.sworker.entity.SwRankInfoEntity;
import com.sworker.model.RankInfoParam;

/**
 * 
 * @author wanggang 2014/09/11
 *
 */
public interface IEnterpriseRankLogic {
	/**
	 * 查询所有企业等级
	 * @return 企业等级实体列表
	 */
	public List<SwRankInfoEntity> searchRanks();
	
	/**
	 * 判断公司等级是否存在
	 * @param rank 等级
	 * @return 存在标志
	 */
	public Boolean isCompanyRankExist(Integer rank);
	
	/**
	 * 创建等级
	 * @param rankentity 企业等级实体
	 * @return 元组实体 封装返回信息，返回主键
	 */
	public RankInfoParam createRank(SwRankInfoEntity rankentity);
	
	/**
	 * 修改企业等级
	 * @param rankentity 企业等级实体
	 * @return 元组实体 封装返回信息，返回主键
	 */
	public RankInfoParam updateRank(SwRankInfoEntity rankentity);
	
	/**
	 * 删除企业等级
	 * @param rank 等级
	 * @return 删除标志  删除成功返回true,失败返回false
	 */
	public Boolean deleteRank(Integer rank);
	
	/**
	 * 计算公司等级,根据某个指标（综合积分），全部计算
	 * @return 成功返回true,失败返回false
	 */
	public Boolean resetCompanysRank();
	
	/**
	 * 根据积分算等级
	 * @param point 积分
	 * @return 等级
	 */
	public Integer getRankByPoint(Long point);
	
}
