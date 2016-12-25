package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.UserGroupAclManagementLocalBean;
import com.karlchu.mo.core.business.UserGroupManagementLocalBean;
import com.karlchu.mo.core.business.utils.UserGroupBeanUtil;
import com.karlchu.mo.core.data.entity.UserGroupEntity;
import com.karlchu.mo.core.data.session.UserGroupLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.UserGroupAclDTO;
import com.karlchu.mo.core.dto.UserGroupDTO;

import javax.ejb.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "UserGroupManagementSessionEJB")
public class UserGroupManagementSessionBean implements UserGroupManagementLocalBean {
    @EJB
    private UserGroupLocalBean userGroupLocalBean;

    @EJB
    private UserGroupAclManagementLocalBean userGroupAclManagementLocalBean;

    public UserGroupManagementSessionBean() {
    }

    @Override
    public UserGroupDTO saveOrUpdate(UserGroupDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        UserGroupEntity entity = null;
        if (dto.getUserGroupId() != null && dto.getUserGroupId() > 0) {
            entity = userGroupLocalBean.findById(dto.getUserGroupId());
            entity.setName(dto.getName());
            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            userGroupLocalBean.update(entity);
        } else {
            entity = new UserGroupEntity();
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity = userGroupLocalBean.save(entity);
            dto.setUserGroupId(entity.getUserGroupId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = userGroupLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<UserGroupEntity> userGroupEntries = (List<UserGroupEntity>)objs[1];
        List<UserGroupDTO> userGroupDTOs = new ArrayList<>();
        for(UserGroupEntity UserGroupEntity : userGroupEntries){
            userGroupDTOs.add(DozerSingletonMapper.getInstance().map(UserGroupEntity, UserGroupDTO.class));
        }

        Long totals = Long.valueOf(objs[0].toString());
        Object[] results = new Object[2];
        results[0] = totals;
        results[1] = userGroupDTOs;

        return results;
    }

    @Override
    public Boolean deleteByIds(String[] checkList) {
        Long[] ids = new Long[checkList.length];
        int index = 0;
        for(String id : checkList){
            ids[index] = Long.valueOf(id);
            index++;
        }
        try{
            userGroupLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public UserGroupDTO findById(Long userGroupId) throws ObjectNotFoundException {
        UserGroupEntity entity = userGroupLocalBean.findById(userGroupId);
        return DozerSingletonMapper.getInstance().map(entity, UserGroupDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return userGroupLocalBean.checkDuplicatedName(name);
    }

    @Override
    public UserGroupDTO findByUserGroupName(String name) throws ObjectNotFoundException {
        UserGroupEntity entity = userGroupLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found user " + name);
        }
        return UserGroupBeanUtil.entity2DTO(entity);
    }
    @Override
    public  List<UserGroupDTO> findAll(){

        List<UserGroupDTO>  userGroupDTOs = new ArrayList<UserGroupDTO>();
        List<UserGroupEntity> entity = userGroupLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            userGroupDTOs.add(UserGroupBeanUtil.entity2DTO(entity.get(i)))    ;

        }
        return userGroupDTOs;
    }

    @Override
    public Integer deleteItems(String[] checkList, Map<Long, Long> mapPermissions) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){

                userGroupAclManagementLocalBean.deleteByUserGroupId(Long.parseLong(id));
                userGroupLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }

    @Override
    public UserGroupDTO addItem(UserGroupDTO pojo, Map<Long, Long> mapPermissions) throws DuplicateKeyException {
        UserGroupEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserGroupEntity.class);

//        RegionEntity regionEntity = new RegionEntity();
//        regionEntity.setRegionId(pojo.getRegion().getRegionId());
//        entity.setRegion(regionEntity);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity = userGroupLocalBean.save(entity);
        updateUserGroupPermissions(entity.getUserGroupId(), mapPermissions);
        return DozerSingletonMapper.getInstance().map(entity, UserGroupDTO.class);
    }

    private void updateUserGroupPermissions(Long roleId, Map<Long, Long> currentMaps) throws DuplicateKeyException {
        Map<Long, Long> oldMaps = this.userGroupAclManagementLocalBean.findByUserGroupId(roleId);
        for(Long key : currentMaps.keySet()){
            if(oldMaps.get(key) == null && currentMaps.get(key) != null) {
                UserGroupAclDTO roleACLDTO = new UserGroupAclDTO();
                PermissionDTO permission = new PermissionDTO();
                permission.setPermissionId(key);
                UserGroupDTO role = new UserGroupDTO();
                role.setUserGroupId(roleId);
                roleACLDTO.setUserGroup(role);
                roleACLDTO.setPermission(permission);
                roleACLDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                roleACLDTO.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                this.userGroupAclManagementLocalBean.addItem(roleACLDTO);
            }
        }
        for(Long key : oldMaps.keySet()){
            if(currentMaps.get(key) == null) {
                this.userGroupAclManagementLocalBean.deleteByPermissionIdUserGroupId(key, roleId);
            }
        }
    }
    
    @Override
    public UserGroupDTO updateItem(UserGroupDTO pojo, Map<Long, Long> mapPermissions) throws ObjectNotFoundException, DuplicateKeyException {
        UserGroupEntity dbItem = this.userGroupLocalBean.findById(pojo.getUserGroupId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find UserGroup with ID " + pojo.getUserGroupId());

        UserGroupEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserGroupEntity.class);

//        RegionEntity regionEntity = new RegionEntity();
//        regionEntity.setRegionId(pojo.getRegion().getRegionId());
//        entity.setRegion(regionEntity);
        entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        entity = userGroupLocalBean.update(entity);
        updateUserGroupPermissions(pojo.getUserGroupId(), mapPermissions);
        return DozerSingletonMapper.getInstance().map(entity, UserGroupDTO.class);
    }

    @Override
    public UserGroupDTO findByCode(String code) {
        UserGroupEntity entity = this.userGroupLocalBean.findByCode(code);
        if(entity != null){
            return DozerSingletonMapper.getInstance().map(entity, UserGroupDTO.class);
        }
        return null;
    }

    @Override
    public UserGroupDTO findDuplicatedName(String name) {
        UserGroupEntity entity = this.userGroupLocalBean.findDuplicatedName(name);
        if(entity != null) {
            return DozerSingletonMapper.getInstance().map(entity, UserGroupDTO.class);
        }
        return null;
    }

}
