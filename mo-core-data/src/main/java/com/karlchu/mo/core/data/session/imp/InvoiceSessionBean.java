package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.InvoiceEntity;
import com.karlchu.mo.core.data.session.InvoiceLocalBean;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "InvoiceSessionEJB")
public class InvoiceSessionBean extends AbstractSessionBean<InvoiceEntity, Long> implements InvoiceLocalBean {
    public InvoiceSessionBean() {
    }


    @Override
    public InvoiceEntity findByName(String name) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM InvoiceEntity re WHERE re.name =:name");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("name", name);
            return (InvoiceEntity) query.getResultList().get(0);
        }catch (NoResultException e){
            throw new ObjectNotFoundException("Not found entity with name " + name);
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM InvoiceEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<InvoiceEntity> findAll() {
        StringBuffer sql = new StringBuffer("FROM InvoiceEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString());
        return (List<InvoiceEntity>) query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM InvoiceEntity usro WHERE 1 = 1 ");
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
    public InvoiceEntity findByCode(String code) throws ObjectNotFoundException {
        try {
            StringBuffer sql = new StringBuffer("FROM InvoiceEntity pe WHERE pe.code =:code");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("code", code);
            return (InvoiceEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("Not found entity with code " + code);
        }

    }

    @Override
    public List<InvoiceEntity> findByCodeOrName(String code, String name) {
        StringBuffer sql = new StringBuffer("FROM InvoiceEntity re WHERE re.code =:code OR re.name = :name ");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        query.setParameter("name", name);
        int count = query.getResultList().size();
        List<InvoiceEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add((InvoiceEntity) query.getResultList().get(i));
        }
        return entities;
    }

    @Override
    public String generateInvoiceNo() {
        Query query = entityManager.createNativeQuery("SELECT nextval('{h-schema}invoice_no_seq')");
        return String.format("INV%08d", ((BigInteger)query.getSingleResult()).longValue());
    }
}
