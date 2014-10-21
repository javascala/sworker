package com.sworker.dao;

import java.util.List;

import com.sworker.entity.SwRankInfoEntity;
import com.sworker.model.EnterpriseRankParam;


/**
 * 
 * @author wanggang 2014/09/11
 *
 */
public interface IEnterpriseRankDao {
	/**
	 * 查询所有企业等级
	 * @return 企业等级实体列表
	 */
	public List<SwRankInfoEntity> findAllRank();
	
	/**
	 * 检查公司等级存在
	 * @param rank 等级
	 * @return 等级列表
	 */
	public List<SwRankInfoEntity> checkRankExist(Integer rank);
	
	/**
	 * 保存企业等级实体
	 * @param rankentity 等级实体
	 * @return 保存操作结果，成功返回true，失败返回false
	 */
	public void saveRankInfo(SwRankInfoEntity rankentity);
	
	/**
	 * 修改企业等级
	 * @param rankentity 企业等级实体
	 */
	public void updateRankInfo(SwRankInfoEntity rankentity);
	
	/**
	 * 删除等级信息
	 * @param rank 等级
	 */
	public void deleteRankInfo(Integer rank);
	
	/**
	 * 获取所有企业积分
	 * @return 包装后的企业积分参数 
	 */
	public List<EnterpriseRankParam> getEnterprisePoint();
	
	/**
	 * 更新企业等级
	 * @param enterpriseid 企业ID
	 * @param level 企业等级
	 */
	public void UpdateEnterpriseRank(Long enterpriseid,Integer level );
}
