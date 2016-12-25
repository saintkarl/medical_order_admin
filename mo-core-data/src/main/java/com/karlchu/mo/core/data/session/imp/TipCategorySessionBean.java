package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.TipCategoryEntity;
import com.karlchu.mo.core.data.session.TipCategoryLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "TipCategorySessionEJB")
public class TipCategorySessionBean extends AbstractSessionBean<TipCategoryEntity, Long> implements TipCategoryLocalBean {
    public TipCategorySessionBean() {
    }


    @Override
    public TipCategoryEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM TipCategoryEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (TipCategoryEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM TipCategoryEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<TipCategoryEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM TipCategoryEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<TipCategoryEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM TipCategoryEntity usro WHERE 1 = 1 ");
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
    public TipCategoryEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM TipCategoryEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (TipCategoryEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<TipCategoryEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM TipCategoryEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<TipCategoryEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((TipCategoryEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
