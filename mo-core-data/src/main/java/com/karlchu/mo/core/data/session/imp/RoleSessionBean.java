package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.RoleEntity;
import com.karlchu.mo.core.data.session.RoleLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "RoleSessionEJB")
public class RoleSessionBean extends AbstractSessionBean<RoleEntity, Long> implements RoleLocalBean {
    public RoleSessionBean() {
    }


    @Override
    public RoleEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM RoleEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (RoleEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM RoleEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<RoleEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM RoleEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<RoleEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM RoleEntity usro WHERE 1 = 1 ");
        if (roleId != null && roleId > 0) {
            str.append(" AND  usro.roleId = :roleId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if (roleId != null && roleId > 0) {
            query.setParameter("roleId", roleId);
        }
        query.executeUpdate();
    }

    @Override
    public RoleEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM RoleEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (RoleEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<RoleEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM RoleEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<RoleEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((RoleEntity) query.getResultList().get(i));
        }
        return entities;
    }

    @Override
    public List<RoleEntity> findRoleOfUser(Long userId) {
        StringBuffer sql = new StringBuffer("SELECT distinct ur.role FROM UserRoleEntity ur WHERE ur.user.userId =:userId");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
