package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.PermissionEntity;
import com.karlchu.mo.core.data.session.PermissionLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "PermissionSessionEJB")
public class PermissionSessionBean extends AbstractSessionBean<PermissionEntity, Long> implements PermissionLocalBean {
    public PermissionSessionBean() {
    }

    @Override
    public PermissionEntity findByName(String name) throws ObjectNotFoundException{
        try{
            StringBuffer sql = new StringBuffer("FROM PermissionEntity pe WHERE pe.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);

            return (PermissionEntity)query.getSingleResult();
        }catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }

    }

    @Override
    public List<PermissionEntity> findAll(){
        StringBuffer  sql = new StringBuffer("FROM PermissionEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString()) ;
        return (List<PermissionEntity>)query.getResultList();

    }

    @Override
    public PermissionEntity findByCode(String code) throws ObjectNotFoundException {
        try{
            StringBuffer sql = new StringBuffer("FROM PermissionEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (PermissionEntity)query.getSingleResult();
        }catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found permission with code " + code);
        }

    }

    @Override
    public List<PermissionEntity> findByCodeOrName(String code, String name){
        StringBuffer sql = new StringBuffer("FROM PermissionEntity pe WHERE pe.code =:code or pe.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<PermissionEntity> findPermissionOfUserId(Long userId, Long userGroupId, List<Long> roleIds) {
        StringBuffer sql = new StringBuffer("SELECT pe FROM PermissionEntity pe WHERE EXISTS(SELECT ugacl.userGroupAclId FROM UserGroupAclEntity ugacl WHERE ugacl.userGroup.userGroupId = :userGroupId AND ugacl.permission.permissionId = pe.permissionId)");
        if (roleIds.size() > 0) {
            sql.append(" OR EXISTS(SELECT racl.roleAclId FROM RoleAclEntity racl WHERE racl.role.roleId IN (:roleIds) and racl.permission.permissionId = pe.permissionId)");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("userGroupId", userGroupId);
        if (roleIds.size() > 0) {
            query.setParameter("roleIds", roleIds);
        }
        return query.getResultList();

    }
}
