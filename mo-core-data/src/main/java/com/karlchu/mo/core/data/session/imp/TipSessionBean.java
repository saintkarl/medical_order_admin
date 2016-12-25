package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.TipEntity;
import com.karlchu.mo.core.data.session.TipLocalBean;

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
@Stateless(name = "TipSessionEJB")
public class TipSessionBean extends AbstractSessionBean<TipEntity, Long> implements TipLocalBean {
    public TipSessionBean() {
    }


    @Override
    public TipEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM TipEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (TipEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM TipEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<TipEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM TipEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<TipEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM TipEntity usro WHERE 1 = 1 ");
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
    public TipEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM TipEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (TipEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<TipEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM TipEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<TipEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((TipEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
