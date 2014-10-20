package com.sworker.dao;

import com.sworker.entity.SwUserInfoEntity;

/**
 * Created by cuijh on 2014/9/18.
 */
public interface IUserInfoDao {

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱号
     * @return 用户信息实体
     */
    public SwUserInfoEntity getUserInfoByEmail(String email);

    /**
     * 根据手机号获取用户信息
     * @param phone 手机号
     * @return 用户信息实体
     */
    public SwUserInfoEntity getUserInfoByPhone(String phone);

    /**
     * 添加新的用户信息
     * @param userInfoEntity 新增用户信息实体
     */
    public void addUserInfo(SwUserInfoEntity userInfoEntity);

    /**
     * 根据账号Id删除用户信息
     * @param accountId 账号Id
     */
    public void deleteUserInfo(Long accountId);

    /**
     * 根据账号Id获取用户信息
     * @param accountId 账号Id
     * @return 用户信息实体
     */
    public SwUserInfoEntity getUserInfo(Long accountId);

    /**
     * 更新用户信息实体
     * @param newUserInfoEntity 更新成的用户信息实体
     */
    public void updateUserInfo(SwUserInfoEntity newUserInfoEntity);

    /**
     * 绑定/解除绑定联系方式
     * @param accountId 账号Id
     * @param contactWay 联系方式
     * @param isBind 1：绑定；0：解除绑定
     */
    public void updateBindingWay(Long accountId,String contactWay,Integer isBind);

}
