package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAccountInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cuijh on 2014/9/18.
 */
@Component("accountDao")
@Resource
public class AccountDao extends AbstractHibernateDao<SwAccountInfoEntity> implements IAccountDao{
    public AccountDao() {
        super();
        setClazz(SwAccountInfoEntity.class);
    }

    /**
     * 根据账号密码获取账号信息
     * @param account  账号
     * @param password 密码（加密后）
     * @return 账号实体
     */
    @Override
    public SwAccountInfoEntity getAccountInfo(String account, String password) {

        Session session = getCurrentSession();
        String hql = "from SwAccountInfoEntity as SAI" +
                " where SAI.account = ? and SAI.password = ?";
        Query query= session.createQuery(hql);
        query.setString(0,account);
        query.setString(1,password);

        return (SwAccountInfoEntity)query.uniqueResult();

    }

    /**
     * 根据账号Id获取账号信息
     * @param accountId 账号Id
     * @return 账号实体
     */
    @Override
    public SwAccountInfoEntity getAccountInfo(Long accountId) {
        return findById(accountId);
    }

    /**
     * 根据账号获取账号信息
     * @param enterpriseId 企业Id
     * @param account      账号
     * @return 账号实体
     */
    @Override
    public SwAccountInfoEntity getAccountInfo(Long enterpriseId, String account){
        String hql = "from SwAccountInfoEntity as SAI" +
                " where SAI.enterpriseid = ? and SAI.account = ?" +
                " order by SRA.?";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = account;

        List<SwAccountInfoEntity> accountInfo = findByHql(hql, params);
        if (accountInfo != null && accountInfo.size() > 0) {
            return accountInfo.get(0);
        } else {
            return null;
        }

    }

    /**
     * 根据账号类型获取账号信息
     * @param enterpriseId 企业Id
     * @param userType     账号类别
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体组成的列表
     */
    @Override
    public List<SwAccountInfoEntity> getAccountsByUserType(Long enterpriseId, Integer userType, String order, Integer pageIndex, Integer pageSize) {

        String hql = "from SwAccountInfoEntity as SAI" +
                " where SAI.enterpriseid = ? and SAI.usertype = ?" +
                " order by SRA.?";
        Object[] params = new Object[3];
        params[0] = enterpriseId;
        params[1] = userType;
        params[2] = order;
        return findByPageNew(hql, params, pageIndex, pageSize);

    }

    /**
     * 根据账号状态获取账号信息
     * @param enterpriseId 企业Id
     * @param status       账号状态
     * @param order        排序
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 账号实体组成的列表
     */
    @Override
    public List<SwAccountInfoEntity> getAccountsByStatus(Long enterpriseId, Integer status, String order, Integer pageIndex, Integer pageSize) {

        String hql = "from SwAccountInfoEntity as SAI" +
                " where SAI.enterpriseid = ? and SAI.status = ?" +
                " order by SAI.?";
        Object[] params = new Object[3];
        params[0] = enterpriseId;
        params[1] = status;
        params[2] = order;
        return findByPageNew(hql, params, pageIndex, pageSize);

    }

    /**
     * 获取企业内所有账号信息
     * @param enterpriseId 企业Id
     * @param pageIndex    页码
     * @param order        排序
     * @param pageSize     每页的条数
     * @return 账号实体列表
     */
    public List<SwAccountInfoEntity> getAccountsByEnterprise(Long enterpriseId, String order, Integer pageIndex, Integer pageSize){
        return null;
    }


    /**
     * 添加新账号
     * @param accountInfoEntity 新增账号实体
     */
    @Transactional
    @Override
    public void addAccountInfo(SwAccountInfoEntity accountInfoEntity) {
        create(accountInfoEntity);
    }

    /**
     * 更新账号
     * @param newAccountInfoEntity 更新成的账号实体
     */
    @Transactional
    @Override
    public void updateAccountInfo(SwAccountInfoEntity newAccountInfoEntity) {
        update(newAccountInfoEntity);
    }

    /**
     * 更新密码
     * @param accountId   账号Id
     * @param newPassword 新密码（加密后）
     */
    @Transactional
    @Override
    public void updatePassword(Long accountId, String newPassword) {

        Session session = getCurrentSession();
        String hql = "update SwAccountInfoEntity as SAI" +
                " set SAI.password = ?" +
                " where SAI.id = ?";
        Query query= session.createQuery(hql);
        query.setString(0, newPassword);
        query.setLong(1, accountId);

        query.executeUpdate();

    }

    /**
     * 删除账号信息
     * @param accountId 账号Id
     */
    @Transactional
    @Override
    public void deleteAccountInfo(Long accountId) {
        deleteById(accountId);
    }

    /**
     * 获取账号拥有的所有角色
     * @param accountId 账号Id
     * @return 所有角色Id组成的列表
     */
    @Override
    public List<Integer> getRoleIds(Long accountId) {

        Session session = getCurrentSession();
        String hql = "select SRA.roleid from SwRoleAccountEntity as SRA" +
                " where SRA.userid = ?" +
                " order by SRA.id asc";
        Query query= session.createQuery(hql);
        query.setLong(0,accountId);

        return query.list();

    }

    /**
     * 删除账号拥有的所有角色
     * @param accountId 账号Id
     */
    @Transactional
    @Override
    public void deleteAllRoles(Long accountId) {

        Session session = getCurrentSession();
        String hql = "delete SwRoleAccountEntity as SRA" +
                " where SRA.userid = ?";
        Query query= session.createQuery(hql);
        query.setLong(0,accountId);

        query.executeUpdate();

    }
}
