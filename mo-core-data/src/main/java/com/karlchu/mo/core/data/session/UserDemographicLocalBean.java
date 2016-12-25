package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.UserDemographicEntity;

import javax.ejb.Local;

@Local
public interface UserDemographicLocalBean extends GenericSessionBean<UserDemographicEntity, Long> {
    UserDemographicEntity findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
