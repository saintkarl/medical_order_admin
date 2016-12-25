package com.karlchu.mo.core.data.session.imp;


import com.karlchu.mo.core.data.entity.UserDemographicEntity;
import com.karlchu.mo.core.data.session.UserDemographicLocalBean;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless(name = "UserDemographicSessionEJB")
public class UserDemographicSessionBean extends AbstractSessionBean<UserDemographicEntity, Long> implements UserDemographicLocalBean {
    public UserDemographicSessionBean() {
    }

    @Override
    public UserDemographicEntity findByUserId(Long userId){
        try{
            StringBuffer str = new StringBuffer("FROM UserDemographicEntity usro WHERE usro.user.userId = :userId");
            Query query = entityManager.createQuery(str.toString());
            query.setParameter("userId", userId);
            return (UserDemographicEntity)query.getSingleResult();
        }catch (NoResultException e) {
            return null;
//            throw new ObjectNotFoundException("Not found demographic for user " + userId);
        }
    }

    @Override
    public void deleteByUserId(Long userId) {
        StringBuffer str = new StringBuffer("DELETE FROM UserDemographicEntity usro WHERE 1 = 1 ");
        if(userId != null && userId > 0){
            str.append(" AND  usro.user.userId = :userId ");
        }

        Query query = entityManager.createQuery(str.toString());
        if(userId != null && userId > 0){
            query.setParameter("userId", userId);
        }
        query.executeUpdate();
    }


}
