package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.UserDemoGraphicManagementLocalBean;
import com.karlchu.mo.core.data.entity.UserDemographicEntity;
import com.karlchu.mo.core.data.entity.UsersEntity;
import com.karlchu.mo.core.data.session.UserDemographicLocalBean;
import com.karlchu.mo.core.dto.UserDemographicDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 3:46 PM
 */
@Stateless(name = "UserDemographicManagementSessionEJB")
public class UserDemoGraphicManagementSessionBean implements UserDemoGraphicManagementLocalBean {
    @EJB
    private UserDemographicLocalBean userDemographicLocalBean;

    public UserDemoGraphicManagementSessionBean() {
    }

    @Override
    public UserDemographicDTO addItem(UserDemographicDTO pojo) throws DuplicateKeyException {
        UserDemographicEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserDemographicEntity.class);
        entity.setUser(DozerSingletonMapper.getInstance().map(pojo.getUser(), UsersEntity.class));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(now);
        entity = userDemographicLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, UserDemographicDTO.class);
    }

    @Override
    public UserDemographicDTO updateItem(UserDemographicDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        UserDemographicEntity dbItem = this.userDemographicLocalBean.findByUserId(pojo.getUser().getUserId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find UserACL with ID " + pojo.getUserDemographicId());
        UserDemographicEntity item = DozerSingletonMapper.getInstance().map(pojo, UserDemographicEntity.class);
        item.setUser(dbItem.getUser());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        item.setCreatedDate(dbItem.getCreatedDate());
        item.setModifiedDate(now);

        //userDemographicLocalBean.deleteByUserId(dbItem.getUsers().getUserId());
        item = userDemographicLocalBean.update(item);
        return DozerSingletonMapper.getInstance().map(item, UserDemographicDTO.class);
    }

    @Override
    public UserDemographicDTO findByUserId(Long id){
        UserDemographicEntity entity = userDemographicLocalBean.findByUserId(id);
        UserDemographicDTO dto = new UserDemographicDTO();
        if (entity != null){
            dto = DozerSingletonMapper.getInstance().map(entity, UserDemographicDTO.class);
        }
        return dto;
    }

    @Override
    public UserDemographicDTO findById(Long id) {
        try {
            UserDemographicEntity userDemographicEntity = this.userDemographicLocalBean.findById(id);
            if(userDemographicEntity != null) {
                return DozerSingletonMapper.getInstance().map(userDemographicEntity, UserDemographicDTO.class);
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public void deleteByUserId(Long id) {
        userDemographicLocalBean.deleteByUserId(id);
    }

}
