package com.sworker.dao;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwEnterpriseInfoEntity;

import java.util.List;

/**
 * Created by jiangcy on 2014/8/29.
 */
public interface IEnterpriseDao extends IOperations<SwEnterpriseInfoEntity> {
	
	/**
	 * 通过企业的名字查找企业帐户信息
	 * @param name 企业名
	 * @return 返回企业账号实体列表
	 */
	public List<SwEnterpriseInfoEntity> findByName(String name);
	/**
	 * 改变企业的当前状态
	 * @param enterpriseId 企业的id
	 * @param status 0为删除;1为激活;2为关闭
	 * @return 改变成功返回true,否则false
	 */
	public Boolean modifyStatus(Long enterpriseId, Integer status);
	/**
	 * 查看企业文件储存的剩余容量
	 * @param enterpriseId 企业的id
	 * @return 企业的剩余容量
	 */
	public Long findLeftDisk(Long enterpriseId);
	/**
	 * 更改企业已用空间量
	 * @param enterpriseId 企业的id
	 * @param fileSize 需改变的大小
	 * @return 操作成功返回true
	 */
	public Boolean midifyUsagespace(Long enterpriseId, Integer fileSize);

// wangying on 2014/9/19.

    /**
     * 检索企业的等级
     * @param level 等级
     * @return 企业Id
     */
    public List<Long> searchEnterpriseLevel(Integer level);

    /**
     * 检索企业的等级
     * @param enterpriseId 企业Id
     * @return 等级
     */
    public Integer searchEnterpriseLevel(Long enterpriseId);

}