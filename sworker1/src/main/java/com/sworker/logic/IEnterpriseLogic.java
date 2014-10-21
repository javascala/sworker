package com.sworker.logic;

import java.util.List;

/**
 * Created by jiangcy on 2014/8/29.
 */
import com.sworker.entity.SwEnterpriseInfoEntity;

public interface IEnterpriseLogic {
	
	/**
	 * 查找企业所有账户信息，并将结果分页
	 * @param pageCount 每页的实体个数
	 * @param currentPage 当前所要显示的页码
	 * @return 返回企业账户信息列表
	 */
	public List<SwEnterpriseInfoEntity> getCompanyList(Integer pageCount, Integer currentPage);
	/**
	 * 通过企业的名字查找企业帐户信息
	 * @param enterpriseName 企业名
	 * @return 返回企业账号实体列表
	 */
	public List<SwEnterpriseInfoEntity> getCompanyListByName(String enterpriseName);
	/**
	 * 通过企业id查找企业账号信息
	 * @param enterpriseId 企业的id
	 * @return 企业账号实体列表
	 */
	public SwEnterpriseInfoEntity getCompanyDetail(Long enterpriseId);
	/**
	 * 创建企业账户
	 * @param enterpriseEntity 企业账户实体
	 * @return 若公司存在返回false,若不存在创建账号并返回true
	 */
	public Boolean createCompany(SwEnterpriseInfoEntity enterpriseEntity);
	/**
	 * 修改账号信息
	 * @param enterpriseEntity 企业账号实体
	 * @return 若创建成功返回true,否则false
	 */
	public Boolean modifyCompany(SwEnterpriseInfoEntity enterpriseEntity);
	/**
	 * 将企业状态变为删除,0为删除状态
	 * @param enterpriseId 企业的id
	 * @return 改变成功返回true,否则false
	 */
	public Boolean removeCompany(Long enterpriseId);
	/**
	 * 将企业状态变为激活,1为激活状态
	 * @param enterpriseId 企业的id
	 * @return 改变成功返回true,否则false
	 */
	public Boolean activateCompany(Long enterpriseId);
	/**
	 * 将企业状态变为关闭,2为关闭状态
	 * @param enterpriseId 企业的id
	 * @return 改变成功返回true,否则false
	 */
	public Boolean disActivateCompany(Long enterpriseId);
	
	public String importCompanyInfo(List<SwEnterpriseInfoEntity> enterpriseList);
	
	public Boolean exportCompanyInfo();
	/**
	 * 检索企业名称是否存在
	 * @param enterpriseName 企业名称
	 * @return 若存在返回true,否则返回false
	 */
	public Boolean isCompanyNameExit(String enterpriseName);
	/**
	 *检查文件大小是否大于企业文件储存的剩余空间
	 * @param enterpriseId 企业的id
	 * @param fileSize 文件的大小
	 * @return 若小于等于剩余空间返回false,否则返回true
	 */
	public Boolean checkCompanyDisk(Long enterpriseId, Integer fileSize);
	/**
	 * 减少企业已用空间量
	 * @param enterpriseId 企业的id
	 * @param fileSize 需改变的大小
	 * @return 操作成功返回true
	 */
	public Boolean reduceCompanyDisk(Long enterpriseId, Integer fileSize);
	/**
	 * 增加企业已用空间量
	 * @param enterpriseId 企业的id
	 * @param fileSize 需改变的大小
	 * @return 操作成功返回true
	 */
	public Boolean addCompanyDisk(Long enterpriseId, Integer fileSize);

}
