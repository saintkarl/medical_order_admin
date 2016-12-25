package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.UserGroupEntity;
import com.karlchu.mo.core.data.session.UserGroupLocalBean;
import org.apache.commons.lang.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "UserGroupSessionEJB")
public class UserGroupSessionBean extends AbstractSessionBean<UserGroupEntity, Long> implements UserGroupLocalBean {
    public UserGroupSessionBean() {
    }

    @Override
    public List<UserGroupEntity> findByName(String fullName, String name) {
        if (StringUtils.isNotBlank(name)){
            StringBuffer sqlQuery = new StringBuffer(" FROM UserGroupEntity pte WHERE 1 = 1 ");
            sqlQuery.append(" AND UPPER(pte.name) like  UPPER(:value)");
            StringBuffer sqlQueryClause = new StringBuffer();
            sqlQueryClause.append(sqlQuery.toString());
            Query query = entityManager.createQuery(sqlQueryClause.toString());
            query.setParameter("value", "%"+name+"%");
            return (List<UserGroupEntity>)query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM UserGroupEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<UserGroupEntity> findAll(){
        StringBuffer  sql = new StringBuffer("FROM UserGroupEntity re WHERE 1 = 1 ");
        Query query = entityManager.createQuery(sql.toString()) ;
        return (List<UserGroupEntity>)query.getResultList();

    }

    @Override
    public void deleteById(Long roleId) {
        StringBuffer str = new StringBuffer("DELETE FROM UserGroupEntity usro WHERE 1 = 1 ");
        if(roleId != null && roleId > 0){
            str.append(" AND  usro.userGroupId = :roleId ");
        }
        Query query = entityManager.createQuery(str.toString());
        if(roleId != null && roleId > 0){
            query.setParameter("roleId", roleId);
        }
        query.executeUpdate();
    }

    @Override
    public UserGroupEntity findByCode(String code) {
        StringBuffer sql = new StringBuffer("FROM UserGroupEntity pe WHERE pe.code =:code");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", code);
        if(query.getResultList().size() > 0){
            return (UserGroupEntity)query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public UserGroupEntity findDuplicatedName(String name) {
        StringBuffer sql = new StringBuffer("FROM UserGroupEntity re WHERE re.name = :name");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        if(query.getResultList().size() > 0) {
            return (UserGroupEntity)query.getResultList().get(0);
        }

        return null;
    }
}
