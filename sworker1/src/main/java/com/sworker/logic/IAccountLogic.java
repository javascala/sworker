package com.sworker.logic;

import com.sworker.entity.SwAccountInfoEntity;
import com.sworker.entity.SwUserInfoEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/9/22.
 */
public interface IAccountLogic {

    /**
     * 判断邮箱是否已经存在
     * @param email 邮箱
     * @return True 存在；False 不存在
     */
    public Boolean isEmailExist(String email);

    /**
     * 判断手机号是否已经存在
     * @param phone 邮箱
     * @return True 存在；False 不存在
     */
    public Boolean isPhoneExist(String phone);

    /**
     * 判断密码是否正确
     * @param account 账号
     * @param password  密码
     * @return True 存在；False 不存在
     */
    public Boolean isPwdCorrect(String account, String password);

    /**
     * 注册
     * @param accountInfoEntity 账号实体
     * @param registerWay 注册方式 email/phone
     */
    public void register(SwAccountInfoEntity accountInfoEntity, String registerWay);

    /**
     * 判断账号是否被停用
     * @param accountId 账号Id
     * @return 1 正常；0 停用
     */
    public Integer isForbidden(Long accountId);

    /**
     * 根据账号ID获取账号信息
     * @param accountId 账号Id
     * @return 账号实体
     */
    public SwAccountInfoEntity getAccountInfoByID(Long accountId);

    /**
     * 根据账号获取账号信息
     * @param enterpriseId 企业Id
     * @param account      账号
     * @return 账号实体
     */
    public SwAccountInfoEntity getAccountInfoByAccount(Long enterpriseId, String account);

    /**
     * 根据用户类型获取账号信息
     * @param enterpriseId 企业Id
     * @param userType     账号类别
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    public List<SwAccountInfoEntity> getAccountsByUserType(Long enterpriseId, Integer userType, String order, Integer pageIndex, Integer pageSize);

    /**
     * 根据账号状态获取账号信息
     * @param enterpriseId 企业Id
     * @param status       账号状态
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    public List<SwAccountInfoEntity> getAccountsByStatus(Long enterpriseId, Integer status, String order, Integer pageIndex, Integer pageSize);

    /**
     * 获取企业内所有账号信息
     * @param enterpriseId 企业Id
     * @param pageIndex    页码
     * @param order        排序
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    public List<SwAccountInfoEntity> getAccountsByEnterprise(Long enterpriseId, String order, Integer pageIndex, Integer pageSize);

    /**
     * 更新账号信息
     * @param accountInfoEntity 账号信息
     */
    public void modifyAccountInfo(SwAccountInfoEntity accountInfoEntity);

    /**
     * 更新密码
     * @param accountId   账号Id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void modifyPwd(Long accountId, String oldPassword, String newPassword);

    /**
     * 重置密码（忘记密码需要重置密码）
     * @param accountId   账号Id
     * @param newPassword 新密码
     */
    public void resetPwd(Long accountId, String newPassword);

    /**
     * 删除账号
     * @param accountId 账号Id
     */
    public void removeAccount(Long accountId);

    /**
     * 检索账号对应的用户基本信息
     * @param accountId 账号Id
     * @return 用户信息实体
     */
    public SwUserInfoEntity getMemberInfo(Long accountId);

    /**
     * 更新用户信息
     * @param userInfoEntity 用户信息实体
     */
    public void modifyMemberInfo(SwUserInfoEntity userInfoEntity);

    /**
     * @param userInfoEntity
     */
    public void modifyPersonalInfo(SwUserInfoEntity userInfoEntity);//todo 只更新个人信息部分不包括工作信息，要想想

    /**
     * @param userInfoEntity
     */
    public void modifyWorkInfo(SwUserInfoEntity userInfoEntity);//todo...也可以与上面一体只用一个

    /**
     * 判断某账号是否具有某种角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     * @return True 有；False 无
     */
    public Boolean isRoleExist(Long accountId, Long roleId);

    /**
     * 为账号添加角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     */
    public void addAccountRole(Long accountId, Long roleId);

    /**
     * 获取账号的角色列表
     * @param accountId 账号Id
     * @return 角色Id列表
     */
    public List<Integer> getAccountRoles(Long accountId);

    /**
     * 删除账号具有的某角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     */
    public void removeRole(Long accountId, Long roleId);

    /**
     * 删除账号具有的所有角色
     * @param accountId 账号Id
     */
    public void removeAllRoles(Long accountId);

    /**
     * 绑定/解除联系方式
     * @param accountId  账号Id
     * @param contactWay 联系方式
     * @param isBind 1：绑定；0：解除绑定
     */
    public void modifyContactWay(Long accountId, String contactWay, Integer isBind);


}