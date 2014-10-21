package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwUserInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by cuijh on 2014/9/18.
 */
@Resource
@Component("userInfoDao")
public class UserInfoDao extends AbstractHibernateDao<SwUserInfoEntity> implements IUserInfoDao{
    public UserInfoDao(){
        super();
        setClazz(SwUserInfoEntity.class);
    }

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱号
     * @return 用户信息实体
     */
    @Override
    public SwUserInfoEntity getUserInfoByEmail(String email) {

        String hql = "from SwUserInfoEntity as SUI" +
                " where SUI.email = ? ";
        Object[] params = new Object[1];
        params[0] = email;

        List<SwUserInfoEntity> usersInfo = findByHql(hql, params);
        if (usersInfo != null && usersInfo.size() > 0) {
            return usersInfo.get(0);
        } else {
            return null;
        }

    }

    /**
     * 根据手机号获取用户信息
     * @param phone 手机号
     * @return 用户信息实体
     */
    @Override
    public SwUserInfoEntity getUserInfoByPhone(String phone) {

        String hql = "from SwUserInfoEntity as SUI" +
                " where SUI.phone = ? ";
        Object[] params = new Object[1];
        params[0] = phone;

        List<SwUserInfoEntity> usersInfo = findByHql(hql, params);
        if (usersInfo != null && usersInfo.size() > 0) {//todo 测试如果不判断是否会报错
            return usersInfo.get(0);
        } else {
            return null;
        }

    }

    /**
     * 添加新的用户信息
     * @param userInfoEntity 新增用户信息实体
     */
    @Transactional
    @Override
    public void addUserInfo(SwUserInfoEntity userInfoEntity) {
        create(userInfoEntity);
    }

    /**
     * 根据账号Id删除用户信息
     * @param accountId 账号Id
     */
    @Transactional
    @Override
    public void deleteUserInfo(Long accountId) {

        Session session = getCurrentSession();
        String hql = "delete SwUserInfoEntity as SUI" +
                " where SUI.accountid = ?";
        Query query= session.createQuery(hql);
        query.setLong(0,accountId);

        query.executeUpdate();

    }

    /**
     * 根据账号Id获取用户信息
     * @param accountId 账号Id
     * @return 用户信息实体
     */
    @Override
    public SwUserInfoEntity getUserInfo(Long accountId) {
        Session session = getCurrentSession();
        String hql = "from SwUserInfoEntity as SUI" +
                " where SUI.accountid = ?";
        Query query= session.createQuery(hql);
        query.setLong(0,accountId);

        return (SwUserInfoEntity)query.uniqueResult();//todo 测试如果返回为null，会不会异常
    }

    /**
     * 更新用户信息实体
     * @param newUserInfoEntity 更新成的用户信息实体
     */
    @Transactional
    @Override
    public void updateUserInfo(SwUserInfoEntity newUserInfoEntity) {
        update(newUserInfoEntity);
    }

    /**
     * 绑定/解除绑定联系方式
     * @param accountId  账号Id
     * @param contactWay 联系方式
     * @param isBind     1：绑定；0：解除绑定
     */
    @Transactional
    @Override
    public void updateBindingWay(Long accountId, String contactWay, Integer isBind) {

        String hqlupdate = "";
        if(contactWay == "email"){
            hqlupdate = "set SUI.isphonebound = ?";
        }else if(contactWay == "phone"){
            hqlupdate = "set SUI.isemailbound = ?";
        }
        //todo 需要判断其他的吗?

        Session session = getCurrentSession();
        String hql = "update SwUserInfoEntity as SUI" +
                hqlupdate +
                " where SAI.id = ?";
        Query query= session.createQuery(hql);
        query.setInteger(0, isBind);
        query.setLong(1, accountId);

        query.executeUpdate();

    }
}
