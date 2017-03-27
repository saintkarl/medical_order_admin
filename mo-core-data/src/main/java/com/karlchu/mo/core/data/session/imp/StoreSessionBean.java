package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.StoreEntity;
import com.karlchu.mo.core.data.session.StoreLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "StoreSessionEJB")
public class StoreSessionBean extends AbstractSessionBean<StoreEntity, Long> implements StoreLocalBean {
    public StoreSessionBean() {
    }


    @Override
    public StoreEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM StoreEntity se WHERE se.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (StoreEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM StoreEntity se WHERE se.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<StoreEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM StoreEntity se WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<StoreEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long storeId) {
        StringBuffer str = new StringBuffer("DELETE FROM StoreEntity se WHERE 1 = 1 ");
        if (storeId != null && storeId > 0) {
            str.append(" AND  se.storeId = :storeId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if (storeId != null && storeId > 0) {
            query.setParameter("storeId", storeId);
        }
        query.executeUpdate();
    }

    @Override
    public StoreEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM StoreEntity se WHERE se.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (StoreEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<StoreEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM StoreEntity se WHERE se.code =:code OR se.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<StoreEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((StoreEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
