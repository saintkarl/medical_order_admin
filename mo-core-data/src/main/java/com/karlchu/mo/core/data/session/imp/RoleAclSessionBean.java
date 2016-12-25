package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.RoleAclEntity;
import com.karlchu.mo.core.data.session.RoleAclLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "RoleAclSessionEJB")
public class RoleAclSessionBean extends AbstractSessionBean<RoleAclEntity, Long> implements RoleAclLocalBean {
    @Override
    public List<RoleAclEntity> findByRoleId(Long roleId) {
        StringBuffer str = new StringBuffer("FROM RoleAclEntity usro WHERE 1 = 1");
        if(roleId != null && roleId > 0){
            str.append(" AND  usro.role.roleId = :roleId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(roleId != null && roleId > 0){
            query.setParameter("roleId", roleId);
        }
        return (List<RoleAclEntity>)query.getResultList();
    }

    @Override
    public void deletByRoleId(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM RoleAclEntity usro WHERE 1 = 1 ");
        if(roleId != null && roleId > 0){
            str.append(" AND  usro.role.roleId = :roleId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(roleId != null && roleId > 0){
            query.setParameter("roleId", roleId);
        }
        query.executeUpdate();
    }

    @Override
    public void deleteByPermissionRoleId(Long permissionId, Long roleId) {

        StringBuffer str = new StringBuffer("DELETE FROM RoleAclEntity usro WHERE 1 = 1 ");
        if(permissionId != null && permissionId > 0){
            str.append(" AND  usro.permission.permissionId = :permissionId ");
        }
        if(roleId != null && roleId > 0){
            str.append(" AND  usro.role.roleId = :roleId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(permissionId != null && permissionId > 0){
            query.setParameter("permissionId", permissionId);
        }
        if(roleId != null && roleId > 0){
            query.setParameter("roleId", roleId);
        }
        query.executeUpdate();
    }
}
