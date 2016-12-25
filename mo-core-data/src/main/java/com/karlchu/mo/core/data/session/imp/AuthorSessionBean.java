package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.AuthorEntity;
import com.karlchu.mo.core.data.session.AuthorLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "AuthorSessionEJB")
public class AuthorSessionBean extends AbstractSessionBean<AuthorEntity, Long> implements AuthorLocalBean {
    public AuthorSessionBean() {
    }


    @Override
    public AuthorEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM AuthorEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (AuthorEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM AuthorEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<AuthorEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM AuthorEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<AuthorEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM AuthorEntity usro WHERE 1 = 1 ");
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
    public AuthorEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM AuthorEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (AuthorEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<AuthorEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM AuthorEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<AuthorEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((AuthorEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
