package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.UserRoleManagementLocalBean;
import com.karlchu.mo.core.business.utils.UserBeanUtil;
import com.karlchu.mo.core.data.entity.UserRoleEntity;
import com.karlchu.mo.core.data.session.UserRoleLocalBean;
import com.karlchu.mo.core.dto.RoleDTO;
import com.karlchu.mo.core.dto.UserDTO;
import com.karlchu.mo.core.dto.UserRoleDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 3:46 PM
 */
@Stateless(name = "UserRoleManagementSessionEJB")
public class UserRoleManagementSessionBean implements UserRoleManagementLocalBean {
    @EJB
    private UserRoleLocalBean userRoleLocalBean;

    public UserRoleManagementSessionBean() {
    }

    @Override
    public UserRoleDTO addItem(UserRoleDTO pojo) throws DuplicateKeyException {
        UserRoleEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserRoleEntity.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(now);
        entity = userRoleLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, UserRoleDTO.class);
    }

    @Override
    public UserRoleDTO updateItem(UserRoleDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        UserRoleEntity dbItem = this.userRoleLocalBean.findById(pojo.getUserRoleACLId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find UserACL with ID " + pojo.getUserRoleACLId());
        UserRoleEntity item = DozerSingletonMapper.getInstance().map(pojo, UserRoleEntity.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        item.setCreatedDate(dbItem.getCreatedDate());
        item.setModifiedDate(now);

        item = userRoleLocalBean.update(item);
        return DozerSingletonMapper.getInstance().map(item, UserRoleDTO.class);
    }

    @Override
    public Map<Long, Long> findByUserId(Long userId) {
        List<UserRoleEntity> listEntity = this.userRoleLocalBean.findByUserId(userId);
        Map<Long, Long> map = new HashMap<Long, Long>();
        for(UserRoleEntity entity : listEntity){
            map.put(Long.valueOf(entity.getRole().getRoleId()), entity.getUser().getUserId());
        }
        return map;
    }

    @Override
    public void deleteByRoleIdUserId(Long roleId, Long userId) {
        this.userRoleLocalBean.deleteByRoleUserId(roleId, userId);
    }

    @Override
    public List<RoleDTO> getRoleByUserId(Long userId) {
        List<UserRoleEntity> listEntity = this.userRoleLocalBean.findByUserId(userId);
        List<RoleDTO> res = new ArrayList<>();
        for(UserRoleEntity entity : listEntity){
            res.add(DozerSingletonMapper.getInstance().map(entity.getRole(), RoleDTO.class));
        }
        return res;
    }

    @Override
    public List<UserDTO> findUsersWithRole(Long roleId){
        List<UserRoleEntity> userRoleEntities = this.userRoleLocalBean.findUsersWithRole(roleId);
        List<UserDTO> dtos = new ArrayList<>();
        for(UserRoleEntity entity : userRoleEntities){
            dtos.add(DozerSingletonMapper.getInstance().map(entity.getUser(), UserDTO.class));
        }
        return dtos;
    }

    @Override
    public Map<String, RoleDTO> findByUser(Long userId) {
        List<UserRoleEntity> listEntity = this.userRoleLocalBean.findByUserId(userId);
        Map<String, RoleDTO> roleDTOMap = new HashMap<>();
        for(UserRoleEntity entity : listEntity){
            roleDTOMap.put(entity.getRole().getCode(), DozerSingletonMapper.getInstance().map(entity.getRole(), RoleDTO.class));
        }
        return roleDTOMap;
    }

    @Override
    public List<UserDTO> findUsersWithRoleCode(String code) throws ObjectNotFoundException {
        List<UserRoleEntity> entities = userRoleLocalBean.findProperty("role.code", code);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserRoleEntity entity : entities){
            userDTOs.add(UserBeanUtil.entity2DTO(entity.getUser()));
        }
        return userDTOs;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = userRoleLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<UserRoleEntity> userRoleEntities = (List<UserRoleEntity>) objs[1];
        List<UserDTO> users = new ArrayList<>();
        for (UserRoleEntity entity : userRoleEntities) {
            users.add(UserBeanUtil.entity2DTO(entity.getUser()));
        }

        Long totals = Long.valueOf(objs[0].toString());
        Object[] results = new Object[2];
        results[0] = totals;
        results[1] = users;

        return results;
    }
}
