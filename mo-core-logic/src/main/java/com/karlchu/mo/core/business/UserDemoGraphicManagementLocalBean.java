package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.UserDemographicDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserDemoGraphicManagementLocalBean {
    UserDemographicDTO addItem(UserDemographicDTO pojo) throws DuplicateKeyException;

    UserDemographicDTO updateItem(UserDemographicDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;

    UserDemographicDTO findByUserId(Long id) throws ObjectNotFoundException;

    UserDemographicDTO findById(Long id) throws ObjectNotFoundException;

    void deleteByUserId(Long id);
}
