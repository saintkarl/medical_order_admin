package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.UserRoleEntity;
import com.karlchu.mo.core.data.session.UserRoleLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "UserRoleSessionEJB")
public class UserRoleSessionBean extends AbstractSessionBean<UserRoleEntity, Long> implements UserRoleLocalBean {
    public UserRoleSessionBean() {
    }

    @Override
    public void deleteByRoleUserId(Long roleId, Long userId) {
        StringBuffer str = new StringBuffer("DELETE FROM UserRoleEntity usro WHERE 1 = 1 ");
        if(roleId != null && roleId > 0){
            str.append(" AND  usro.role.roleId = :roleId ");
        }
        if(userId != null && userId > 0){
            str.append(" AND  usro.user.userId = :userId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(roleId != null && roleId > 0){
            query.setParameter("roleId", roleId);
        }
        if(userId != null && userId > 0){
            query.setParameter("userId", userId);
        }
        query.executeUpdate();
    }

    @Override
    public List<UserRoleEntity> findUsersWithRole(Long roleId) {
        StringBuffer strQuery = new StringBuffer("From UserRoleEntity e WHERE e.role.roleId = :roleId");
        Query query = entityManager.createQuery(strQuery.toString());
        query.setParameter("roleId", roleId);
        return (List<UserRoleEntity>)query.getResultList();
    }

    @Override
    public List<UserRoleEntity> findByUserId(Long userId) {
        StringBuffer str = new StringBuffer("FROM UserRoleEntity usro WHERE 1 = 1");
        if(userId != null && userId > 0){
            str.append(" AND  usro.user.userId = :userId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(userId != null && userId > 0){
            query.setParameter("userId", userId);
        }
        return (List<UserRoleEntity>)query.getResultList();
    }


}
