package com.sworker.dao;

import com.sworker.entity.SwAccountInfoEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/9/18.
 */
public interface IAccountDao {

    /**
     * 根据账号密码获取账号信息
     * @param account  账号
     * @param password 密码（加密后）
     * @return 账号实体
     */
    public SwAccountInfoEntity getAccountInfo(String account, String password);

    /**
     * 根据账号Id获取账号信息
     * @param accountId 账号Id
     * @return 账号实体
     */
    public SwAccountInfoEntity getAccountInfo(Long accountId);

    /**
     * 根据账号获取账号信息
     * @param enterpriseId 企业Id
     * @param account      账号
     * @return 账号实体
     */
    public SwAccountInfoEntity getAccountInfo(Long enterpriseId, String account);

    /**
     * 根据账号类型获取账号信息
     * @param enterpriseId 企业Id
     * @param userType     账号类别
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体组成的列表
     *///TODO 在写一个根据企业id的呢？还是复用呢？待定
    public List<SwAccountInfoEntity> getAccountsByUserType(Long enterpriseId, Integer userType, String order, Integer pageIndex, Integer pageSize);

    /**
     * 根据账号状态获取账号信息
     * @param enterpriseId 企业Id
     * @param status       账号状态
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体组成的列表
     */
    public List<SwAccountInfoEntity> getAccountsByStatus(Long enterpriseId, Integer status, String order, Integer pageIndex, Integer pageSize);

    /**
     * 添加新账号
     * @param accountInfoEntity 新增账号实体
     */
    public void addAccountInfo(SwAccountInfoEntity accountInfoEntity);

    /**
     * 更新账号
     * @param newAccountInfoEntity 更新成的账号实体
     */
    public void updateAccountInfo(SwAccountInfoEntity newAccountInfoEntity);

    /**
     * 更新密码
     * @param accountId   账号Id
     * @param newPassword 新密码（加密后）
     */
    public void updatePassword(Long accountId, String newPassword);

    /**
     * 删除账号信息
     * @param accountId 账号Id
     */
    public void deleteAccountInfo(Long accountId);

    /**
     * 获取账号拥有的所有角色
     * @param accountId 账号Id
     * @return 所有角色Id组成的列表
     */
    public List<Integer> getRoleIds(Long accountId);

    /**
     * 删除账号拥有的所有角色
     * @param accountId 账号Id
     *///todo 有可能用到吗
    public void deleteAllRoles(Long accountId);

}
