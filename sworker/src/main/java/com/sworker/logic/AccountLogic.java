package com.sworker.logic;

import com.sworker.dao.IAccountDao;
import com.sworker.dao.IEnterpriseDao;
import com.sworker.dao.IRoleDao;
import com.sworker.dao.IUserInfoDao;
import com.sworker.entity.SwAccountInfoEntity;
import com.sworker.entity.SwEnterpriseInfoEntity;
import com.sworker.entity.SwUserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/9/22.
 */
@Component
public class AccountLogic implements IAccountLogic{
    @Autowired
    public IAccountDao accountDao;

    @Autowired
    public IUserInfoDao userInfoDao;

    @Autowired
    private IEnterpriseDao enterpriseDao;

    @Autowired
    public IRoleDao roleDao;

    /**
     * 判断邮箱是否已经存在
     * @param email 邮箱
     * @return True 存在；False 不存在
     */
    @Override
    public Boolean isEmailExist(String email) {

        SwUserInfoEntity userInfo = userInfoDao.getUserInfoByEmail(email);
        if (userInfo != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断手机号是否已经存在
     * @param phone 邮箱
     * @return True 存在；False 不存在
     */
    @Override
    public Boolean isPhoneExist(String phone) {

        SwUserInfoEntity userInfo = userInfoDao.getUserInfoByPhone(phone);
        if (userInfo != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断密码是否正确
     * @param account 账号
     * @param password  密码
     * @return True 存在；False 不存在
     */
    @Override
    public Boolean isPwdCorrect(String account, String password) {

        SwAccountInfoEntity accountInfo = accountDao.getAccountInfo(account, password);
        if (accountInfo != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 注册
     * @param accountInfoEntity 账号实体
     * @param registerWay 注册方式 email/phone
     */
    @Transactional
    @Override
    public void register(SwAccountInfoEntity accountInfoEntity, String registerWay) {
        //账号唯一性判断
        boolean isExist = true;//todo 有必要初始化吗
        if (getAccountInfoByAccount(accountInfoEntity.getEnterpriseid(), accountInfoEntity.getAccount()) == null) {
            if (registerWay == "email") {
                isExist = isEmailExist(accountInfoEntity.getAccount());
            } else if (registerWay == "phone") {
                isExist = isPhoneExist(accountInfoEntity.getAccount());
            }
        }
        if (isExist == false) {
            //todo 判断是否是邀请
            //被邀请的不需要判断公司是否存在
            //主动注册的，需要判断公司是否存在
            List<SwEnterpriseInfoEntity> enterpriseInfoEntities;
            enterpriseInfoEntities = enterpriseDao.findByName(accountInfoEntity.getCompanyname());
            //公司不存在
            if (enterpriseInfoEntities == null || enterpriseInfoEntities.size() == 0) {
                accountDao.addAccountInfo(accountInfoEntity);
                //todo 需要组和生成userInfoEntity
                SwUserInfoEntity userInfoEntity = new SwUserInfoEntity();
                userInfoEntity.setAccountid(accountInfoEntity.getId());//todo 着重测试，应该是有问题的，因为还没有生成id
                if (registerWay == "email") {
                    userInfoEntity.setEmail(accountInfoEntity.getAccount());
                    userInfoEntity.setIsemailbound(1);
                }
                if (registerWay == "phone") {
                    userInfoEntity.setPhone(Long.valueOf(accountInfoEntity.getAccount()));//todo 斟酌需要修改DB 还是如此
                    userInfoEntity.setIsphonebound(1);
                }
                userInfoDao.addUserInfo(userInfoEntity);
            } else {
                //todo 需要返回该公司已存在的消息或状态字
            }
        }
    }

    /**
     * 判断账号是否被停用
     * @param accountId 账号Id
     * @return 1 正常；0 停用
     */
    @Override
    public Integer isForbidden(Long accountId) {
        return accountDao.getAccountInfo(accountId).getStatus();
    }

    /**
     * 根据账号ID获取账号信息
     * @param accountId 账号Id
     * @return 账号实体
     */
    @Override
    public SwAccountInfoEntity getAccountInfoByID(Long accountId) {
        return accountDao.getAccountInfo(accountId);
    }

    /**
     * 根据账号获取账号信息
     * @param enterpriseId 企业Id
     * @param account      账号
     * @return 账号实体
     */
    @Override
    public SwAccountInfoEntity getAccountInfoByAccount(Long enterpriseId, String account) {
        return accountDao.getAccountInfo(enterpriseId, account);
    }

    /**
     * 根据用户类型获取账号信息
     * @param enterpriseId 企业Id
     * @param userType     账号类别
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    @Override
    public List<SwAccountInfoEntity> getAccountsByUserType(Long enterpriseId, Integer userType, String order, Integer pageIndex, Integer pageSize) {
        return accountDao.getAccountsByUserType(enterpriseId, userType, order, pageIndex, pageSize);
    }

    /**
     * 根据账号状态获取账号信息
     * @param enterpriseId 企业Id
     * @param status       账号状态
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    @Override
    public List<SwAccountInfoEntity> getAccountsByStatus(Long enterpriseId, Integer status, String order, Integer pageIndex, Integer pageSize) {
        return accountDao.getAccountsByStatus(enterpriseId, status, order, pageIndex, pageSize);
    }

    /**
     * 获取企业内所有账号信息
     * @param enterpriseId 企业Id
     * @param pageIndex    页码
     * @param order        排序
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    @Override
    public List<SwAccountInfoEntity> getAccountsByEnterprise(Long enterpriseId, String order, Integer pageIndex, Integer pageSize) {
        return null;//todo
    }

    /**
     * 更新账号信息
     * @param accountInfoEntity 账号信息
     */
    @Transactional
    @Override
    public void modifyAccountInfo(SwAccountInfoEntity accountInfoEntity) {
        accountDao.updateAccountInfo(accountInfoEntity);
    }

    /**
     * 更新密码
     * @param accountId   账号Id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Transactional
    @Override
    public void modifyPwd(Long accountId, String oldPassword, String newPassword) {
        SwAccountInfoEntity accountInfo = accountDao.getAccountInfo(accountId, oldPassword);
        //判断旧密码是否正确
        if (accountInfo != null) {
            accountDao.updatePassword(accountId, newPassword);
        } else {//todo
        }
    }

    /**
     * 重置密码（忘记密码需要重置密码）
     * @param accountId   账号Id
     * @param newPassword 新密码
     */
    @Transactional
    @Override
    public void resetPwd(Long accountId, String newPassword) {
        accountDao.updatePassword(accountId, newPassword);
    }

    /**
     * 删除账号
     * @param accountId 账号Id
     */
    @Transactional
    @Override
    public void removeAccount(Long accountId) {
        userInfoDao.deleteUserInfo(accountId);
        accountDao.deleteAccountInfo(accountId);
    }

    /**
     * 检索账号对应的用户基本信息
     * @param accountId 账号Id
     * @return 用户信息实体
     */
    @Override
    public SwUserInfoEntity getMemberInfo(Long accountId) {
        return userInfoDao.getUserInfo(accountId);
    }

    /**
     * 更新用户信息
     * @param userInfoEntity 用户信息实体
     */
    @Transactional
    @Override
    public void modifyMemberInfo(SwUserInfoEntity userInfoEntity) {
        userInfoDao.updateUserInfo(userInfoEntity);
    }

    /**
     * @param userInfoEntity
     */
    @Override
    public void modifyPersonalInfo(SwUserInfoEntity userInfoEntity) {

    }

    /**
     * @param userInfoEntity
     */
    @Override
    public void modifyWorkInfo(SwUserInfoEntity userInfoEntity) {

    }

    /**
     * 判断某账号是否具有某种角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     * @return True 有；False 无
     */
    @Override
    public Boolean isRoleExist(Long accountId, Long roleId) {

        Long accountRoleId = roleDao.checkUserExist(accountId, roleId);

        if (accountRoleId != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 为账号添加角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     */
    @Transactional
    @Override
    public void addAccountRole(Long accountId, Long roleId) {
        roleDao.addRoleUser(accountId, roleId);
    }

    /**
     * 获取账号的角色列表
     * @param accountId 账号Id
     * @return 角色Id列表
     */
    @Override
    public List<Integer> getAccountRoles(Long accountId) {
        return accountDao.getRoleIds(accountId);
    }

    /**
     * 删除账号具有的某角色
     * @param accountId 账号Id
     * @param roleId    角色Id
     */
    @Transactional
    @Override
    public void removeRole(Long accountId, Long roleId) {
        roleDao.deleteRoleUser(accountId, roleId);
    }

    /**
     * 删除账号具有的所有角色
     * @param accountId 账号Id
     */
    @Transactional
    @Override
    public void removeAllRoles(Long accountId) {
        accountDao.deleteAllRoles(accountId);
    }

    /**
     * 绑定/解除联系方式
     * @param accountId  账号Id
     * @param contactWay 联系方式
     * @param isBind 1：绑定；0：解除绑定
     */
    @Transactional
    @Override
    public void modifyContactWay(Long accountId, String contactWay, Integer isBind) {
        userInfoDao.updateBindingWay(accountId, contactWay, isBind);
    }


}
