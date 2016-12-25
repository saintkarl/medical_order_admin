package com.karlchu.mo.core.data.session.imp;

import com.karlchu.mo.core.data.entity.UsersEntity;
import com.karlchu.mo.core.data.session.UsersLocalBean;
import org.apache.commons.lang.StringUtils;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "UsersSessionEJB")
public class UsersSessionBean extends AbstractSessionBean<UsersEntity, Long> implements UsersLocalBean {

    public UsersSessionBean() {
    }

    @Override
    public List<UsersEntity> findByName(String fullName, String name) {
        if (StringUtils.isNotBlank(name)) {
            StringBuffer sqlQuery = new StringBuffer(" FROM UsersEntity pte WHERE 1 = 1 ");
            sqlQuery.append(" AND UPPER(pte.fullname) like  UPPER(:value)");
            StringBuffer sqlQueryClause = new StringBuffer();
            sqlQueryClause.append(sqlQuery.toString());
            Query query = entityManager.createQuery(sqlQueryClause.toString());
            query.setParameter("value", "%" + name + "%");
            return (List<UsersEntity>) query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    public List<UsersEntity> findByUsernameCodeOrEmail(String username, String code, String email, Long userId) {
        Query query;
        StringBuffer sql = new StringBuffer("FROM UsersEntity WHERE ( username = :username OR email = :email");
        sql.append(" OR code = :code )");

        if (userId != null) {
            sql.append(" AND userId <> :userId");
        }


        query = entityManager.createQuery(sql.toString());
        query.setParameter("username", username);
        query.setParameter("email", email);
        query.setParameter("code", code);
        if (userId != null) {
            query.setParameter("userId", userId);
        }
        return query.getResultList();
    }

    @Override
    public UsersEntity findByCode(String code) {
        Query query = entityManager.createQuery("FROM UsersEntity u WHERE u.code = :code");
        query.setParameter("code", code);
        return (UsersEntity) query.getSingleResult();
    }

    @Override
    public Boolean checkDuplicatedUniqueData(UsersEntity usersEntity) {
        boolean isDuplicated = false;
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE u.userName = :username OR u.email = :email");
        sqlClause.append(" OR u.code = :code");
        if (usersEntity.getUserId() != null) {
            sqlClause.append("AND u.userId <> :userId");
        }
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("username", usersEntity.getUserName());
        query.setParameter("email", usersEntity.getEmail());
        query.setParameter("code", usersEntity.getCode());
        if (usersEntity.getUserId() != null) {
            query.setParameter("userId", usersEntity.getUserId());
        }
        if (query.getResultList().size() > 0) {
            isDuplicated = true;
        }
        return isDuplicated;
    }

    @Override
    public List<UsersEntity> searchByUserGroup(String code) {
        List<UsersEntity> usersEntities = new ArrayList<>();
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE u.userGroup.code = :code");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("code", code);
        usersEntities = query.getResultList();
        return usersEntities;
    }

    @Override
    public List<UsersEntity> searchByMangerLine(String managerCode) {
        List<UsersEntity> usersEntities = new ArrayList<>();
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE u.managerCode = :managerCode");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("managerCode", managerCode);
        usersEntities = query.getResultList();
        return usersEntities;
    }

    @Override
    public UsersEntity findByUserName(String username) throws ObjectNotFoundException {
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE UPPER(u.userName) = UPPER(:userName)");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("userName", username);
        try {
            return (UsersEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("NOT FOUND USER " + username);
        }
    }

    @Override
    public List<UsersEntity> findByUserNames(List<String> userNames) {
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE UPPER(u.userName) IN (:userNames)");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("userNames", userNames);
        return query.getResultList();
    }

    @Override
    public List<UsersEntity> findByRole(String roleCode) {
        return entityManager.createQuery("FROM UsersEntity u WHERE EXISTS (SELECT 1 FROM UserRoleEntity ur WHERE u.userId = ur.user.userId AND ur.role.code = :roleCode)")
                .setParameter("roleCode", roleCode)
                .getResultList();
    }

    @Override
    public List<UsersEntity> findByEmails(List<String> emails) {
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE UPPER(u.email) IN (:emails)");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("emails", emails);
        return query.getResultList();
    }

    @Override
    public UsersEntity findByEmail(String email) throws ObjectNotFoundException {
        StringBuilder sqlClause = new StringBuilder("FROM UsersEntity u WHERE UPPER(u.email) = UPPER(:email)");
        Query query = entityManager.createQuery(sqlClause.toString());
        query.setParameter("email", email);
        try {
            return (UsersEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("NOT FOUND EMAIL " + email);
        }
    }


    @Override
    public List<UsersEntity> findByUserNameQuery(String queryStr) {
        String sql = "FROM UsersEntity WHERE UPPER(userName) LIKE :queryStr";
        Query query = entityManager.createQuery(sql);
        query.setParameter("queryStr", "%" + queryStr.toUpperCase() + "%");
        return query.getResultList();
    }
}
