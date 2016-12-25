package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.UserGroupAclManagementLocalBean;
import com.karlchu.mo.core.data.entity.UserGroupAclEntity;
import com.karlchu.mo.core.data.session.UserGroupAclLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.UserGroupAclDTO;

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
@Stateless(name = "UserGroupAclManagementSessionEJB")
public class UserGroupAclManagementSessionBean implements UserGroupAclManagementLocalBean {

    @EJB
    private UserGroupAclLocalBean userGroupAclLocalBean;

    public UserGroupAclManagementSessionBean() {
    }

    @Override
    public UserGroupAclDTO addItem(UserGroupAclDTO pojo) throws DuplicateKeyException {
        UserGroupAclEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserGroupAclEntity.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(now);
        entity = userGroupAclLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, UserGroupAclDTO.class);
    }

    @Override
    public UserGroupAclDTO updateItem(UserGroupAclDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        UserGroupAclEntity dbItem = this.userGroupAclLocalBean.findById(pojo.getUserGroupAclId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find UserACL with ID " + pojo.getUserGroupAclId());
        UserGroupAclEntity item = DozerSingletonMapper.getInstance().map(pojo, UserGroupAclEntity.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        item.setCreatedDate(dbItem.getCreatedDate());
        item.setModifiedDate(now);

        item = userGroupAclLocalBean.update(item);
        return DozerSingletonMapper.getInstance().map(item, UserGroupAclDTO.class);
    }

    @Override
    public Map<Long, Long> findByUserGroupId(Long userId) {
        List<UserGroupAclEntity> listEntity = this.userGroupAclLocalBean.findByUserId(userId);
        Map<Long, Long> map = new HashMap<Long, Long>();
        for(UserGroupAclEntity entity : listEntity){
            map.put(Long.valueOf(entity.getPermission().getPermissionId()), entity.getUserGroup().getUserGroupId());
        }
        return map;
    }

    @Override
    public void deleteByPermissionIdUserGroupId(Long permissionId, Long userGroupId) {
        this.userGroupAclLocalBean.deleteByRoleUserId(permissionId, userGroupId);
    }

    @Override
    public void deleteByUserGroupId(Long userGroupId) {
        this.userGroupAclLocalBean.deletByRoleId(userGroupId);
    }

    @Override
    public List<PermissionDTO> getPermissionByUserId(Long userGroupId) {
        List<UserGroupAclEntity> listEntity = this.userGroupAclLocalBean.findByUserId(userGroupId);
        List<PermissionDTO> res = new ArrayList<>();
        for(UserGroupAclEntity entity : listEntity){
            res.add(DozerSingletonMapper.getInstance().map(entity.getPermission(), PermissionDTO.class));
        }
        return res;
    }
}
