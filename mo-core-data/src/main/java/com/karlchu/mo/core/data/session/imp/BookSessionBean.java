package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.BookEntity;
import com.karlchu.mo.core.data.session.BookLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "BookSessionEJB")
public class BookSessionBean extends AbstractSessionBean<BookEntity, Long> implements BookLocalBean {
    public BookSessionBean() {
    }


    @Override
    public BookEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM BookEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (BookEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM BookEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<BookEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM BookEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<BookEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM BookEntity usro WHERE 1 = 1 ");
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
    public BookEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM BookEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (BookEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<BookEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM BookEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<BookEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((BookEntity) query.getResultList().get(i));
        }
        return entities;
    }
}
