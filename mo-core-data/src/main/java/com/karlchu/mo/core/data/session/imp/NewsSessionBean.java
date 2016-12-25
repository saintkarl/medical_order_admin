package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.NewsEntity;
import com.karlchu.mo.core.data.session.NewsLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "NewsSessionEJB")
public class NewsSessionBean extends AbstractSessionBean<NewsEntity, Long> implements NewsLocalBean {
    public NewsSessionBean() {
    }


    @Override
    public NewsEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM NewsEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (NewsEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM NewsEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<NewsEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM NewsEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<NewsEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM NewsEntity usro WHERE 1 = 1 ");
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
    public NewsEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM NewsEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (NewsEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<NewsEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM NewsEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<NewsEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((NewsEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
