package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.RoleAclManagementLocalBean;
import com.karlchu.mo.core.business.RoleManagementLocalBean;
import com.karlchu.mo.core.business.utils.RoleBeanUtil;
import com.karlchu.mo.core.data.entity.RoleEntity;
import com.karlchu.mo.core.data.session.RoleLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.RoleAclDTO;
import com.karlchu.mo.core.dto.RoleDTO;

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
@Stateless(name = "RoleManagementSessionEJB")
public class RoleManagementSessionBean implements RoleManagementLocalBean {
    @EJB
    private RoleLocalBean roleLocalBean;

    @EJB
    private RoleAclManagementLocalBean roleAclManagementLocalBean;

    public RoleManagementSessionBean() {
    }

    @Override
    public RoleDTO saveOrUpdate(RoleDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        RoleEntity entity = null;
        if (dto.getRoleId() != null && dto.getRoleId() > 0) {
            entity = roleLocalBean.findById(dto.getRoleId());
            entity.setName(dto.getName());
            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            roleLocalBean.update(entity);
        } else {
            entity = new RoleEntity();
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity = roleLocalBean.save(entity);
            dto.setRoleId(entity.getRoleId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = roleLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<RoleEntity> roleEntries = (List<RoleEntity>)objs[1];
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for(RoleEntity RoleEntity : roleEntries){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(RoleEntity, RoleDTO.class));
        }

        Long totals = Long.valueOf(objs[0].toString());
        Object[] results = new Object[2];
        results[0] = totals;
        results[1] = roleDTOs;

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
            roleLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public RoleDTO findById(Long roleId) throws ObjectNotFoundException {
        RoleEntity entity = roleLocalBean.findById(roleId);
        return DozerSingletonMapper.getInstance().map(entity, RoleDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return roleLocalBean.checkDuplicatedName(name);
    }

    @Override
    public RoleDTO findByRoleName(String name) throws ObjectNotFoundException {
        RoleEntity entity = roleLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found role " + name);
        }
        return RoleBeanUtil.entity2DTO(entity);
    }
    @Override
    public  List<RoleDTO> findAll(){

        List<RoleDTO>  roleDTOs = new ArrayList<RoleDTO>();
        List<RoleEntity> entity = roleLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            roleDTOs.add(RoleBeanUtil.entity2DTO(entity.get(i)))    ;

        }
        return roleDTOs;


    }

    @Override
    public Integer deleteItems(String[] checkList,Map<Long,Long> mapPermissions) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){

                roleAclManagementLocalBean.deleteByRoleId(Long.parseLong(id));
                roleLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }

    @Override
    public RoleDTO addItem(RoleDTO pojo, Map<Long, Long> mapPermissions) throws DuplicateKeyException {
        RoleEntity entity = DozerSingletonMapper.getInstance().map(pojo, RoleEntity.class);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity = roleLocalBean.save(entity);
        updateRolePermissions(entity.getRoleId(),mapPermissions);
        return DozerSingletonMapper.getInstance().map(entity, RoleDTO.class);
    }

    private void updateRolePermissions(Long roleId, Map<Long, Long> currentMaps) throws DuplicateKeyException {
        Map<Long, Long> oldMaps = this.roleAclManagementLocalBean.findByRoleId(roleId);
        for(Long key : currentMaps.keySet()){
            if(oldMaps.get(key) == null && currentMaps.get(key) != null) {
                RoleAclDTO roleACLDTO = new RoleAclDTO();
                PermissionDTO permission = new PermissionDTO();
                permission.setPermissionId(key);
                RoleDTO role = new RoleDTO();
                role.setRoleId(roleId);
                roleACLDTO.setRole(role);
                roleACLDTO.setPermission(permission);
                roleACLDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                roleACLDTO.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                this.roleAclManagementLocalBean.addItem(roleACLDTO);
            }
        }
        for(Long key : oldMaps.keySet()){
            if(currentMaps.get(key) == null) {
                this.roleAclManagementLocalBean.deleteByPermissionIdRoleId(key, roleId);
            }
        }
    }

    @Override
    public RoleDTO updateItem(RoleDTO pojo, Map<Long, Long> mapPermissions) throws ObjectNotFoundException, DuplicateKeyException {
        RoleEntity dbItem = this.roleLocalBean.findById(pojo.getRoleId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find Role with ID " + pojo.getRoleId());
        dbItem.setName(pojo.getName());
        dbItem.setCode(pojo.getCode());
        dbItem.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        dbItem = roleLocalBean.update(dbItem);
        updateRolePermissions(pojo.getRoleId(), mapPermissions);
        return DozerSingletonMapper.getInstance().map(dbItem, RoleDTO.class);
    }

    @Override
    public RoleDTO findByCode(String code) throws ObjectNotFoundException{
        RoleEntity entity = this.roleLocalBean.findByCode(code);
        return RoleBeanUtil.entity2DTO(entity);

    }

    @Override
    public RoleDTO findByName(String name) throws ObjectNotFoundException{
            RoleEntity entity  = this.roleLocalBean.findByName(name);
        return RoleBeanUtil.entity2DTO(entity);
    }

    public List<RoleDTO> findByCodeOrName(String code, String name){
        List<RoleEntity> entities = this.roleLocalBean.findByCodeOrName(code, name);
        List<RoleDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){
            dtos.add(RoleBeanUtil.entity2DTO(entities.get(i)));
        }
        return dtos;
    }
}
