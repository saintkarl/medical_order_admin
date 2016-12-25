package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.UserGroupAclEntity;
import com.karlchu.mo.core.data.session.UserGroupAclLocalBean;

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
@Stateless(name = "UserGroupAclSessionEJB")
public class UserGroupAclSessionBean extends AbstractSessionBean<UserGroupAclEntity, Long> implements UserGroupAclLocalBean {
    public UserGroupAclSessionBean() {
    }

    @Override
    public void deleteByRoleUserId(Long permissionId, Long userGroupId) {
        StringBuffer str = new StringBuffer("DELETE FROM UserGroupAclEntity usro WHERE 1 = 1 ");
        if(permissionId != null && permissionId > 0){
            str.append(" AND  usro.permission.permissionId = :permissionId ");
        }
        if(userGroupId != null && userGroupId > 0){
            str.append(" AND  usro.userGroup.userGroupId = :userGroupId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(permissionId != null && permissionId > 0){
            query.setParameter("permissionId", permissionId);
        }
        if(userGroupId != null && userGroupId > 0){
            query.setParameter("userGroupId", userGroupId);
        }
        query.executeUpdate();
    }

    @Override
    public List<UserGroupAclEntity> findByUserId(Long userGroupId) {
        StringBuffer str = new StringBuffer("FROM UserGroupAclEntity usro WHERE 1 = 1");
        if(userGroupId != null && userGroupId > 0){
            str.append(" AND  usro.userGroup.userGroupId = :userGroupId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(userGroupId != null && userGroupId > 0){
            query.setParameter("userGroupId", userGroupId);
        }
        return (List<UserGroupAclEntity>)query.getResultList();
    }

    @Override
    public void deletByRoleId(Long userGroupId) {
        StringBuffer str = new StringBuffer("DELETE FROM UserGroupAclEntity usro WHERE 1 = 1 ");
        if(userGroupId != null && userGroupId > 0){
            str.append(" AND  usro.userGroup.userGroupId = :userGroupId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(userGroupId != null && userGroupId > 0){
            query.setParameter("userGroupId", userGroupId);
        }
        query.executeUpdate();
    }


}
