package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.PermissionManagementLocalBean;
import com.karlchu.mo.core.business.utils.PermissionBeanUtil;
import com.karlchu.mo.core.data.entity.PermissionEntity;
import com.karlchu.mo.core.data.session.PermissionLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import org.apache.log4j.Logger;

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
@Stateless(name = "PermissionManagementSessionEJB")
public class PermissionManagementSessionBean implements PermissionManagementLocalBean {
    private final Logger logger = Logger.getLogger(getClass());

    @EJB
    private PermissionLocalBean permissionLocalBean;

    public PermissionManagementSessionBean() {
    }

    @Override
    public PermissionDTO saveOrUpdate(PermissionDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        PermissionEntity entity = null;
        if (dto.getPermissionId() != null && dto.getPermissionId() > 0) {
            entity = permissionLocalBean.findById(dto.getPermissionId());
            entity.setName(dto.getName());
            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            permissionLocalBean.update(entity);
        } else {
            entity = new PermissionEntity();
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity = permissionLocalBean.save(entity);
            dto.setPermissionId(entity.getPermissionId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = permissionLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<PermissionEntity> permissionEntries = (List<PermissionEntity>)objs[1];
        List<PermissionDTO> permissionDTOs = new ArrayList<>();
        for(PermissionEntity PermissionEntity : permissionEntries){
            permissionDTOs.add(DozerSingletonMapper.getInstance().map(PermissionEntity, PermissionDTO.class));
        }

        Long totals = Long.valueOf(objs[0].toString());
        Object[] results = new Object[2];
        results[0] = totals;
        results[1] = permissionDTOs;

        return results;
    }

    @Override
    public Boolean deleteByIds(String[] checkList) {
        try{
            Long[] ids = new Long[checkList.length];

            for(int i = 0; i < checkList.length; i++) {
                ids[i] = Long.valueOf(checkList[i]);
            }
            permissionLocalBean.delete(ids);
        }catch (RemoveException e){
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public PermissionDTO findById(Long permissionId) throws ObjectNotFoundException {
        PermissionEntity entity = permissionLocalBean.findById(permissionId);
        return DozerSingletonMapper.getInstance().map(entity, PermissionDTO.class);
    }

    @Override
    public PermissionDTO findByPermissionName(String name) throws ObjectNotFoundException {
        PermissionEntity entity = permissionLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found user " + name);
        }
        return PermissionBeanUtil.entity2DTO(entity);
    }
    @Override
    public  List<PermissionDTO> findAll(){

        List<PermissionDTO>  permissionDTOs = new ArrayList<PermissionDTO>();
        List<PermissionEntity> entity = permissionLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            permissionDTOs.add(PermissionBeanUtil.entity2DTO(entity.get(i)));
        }
        return permissionDTOs;
    }

    @Override
    public PermissionDTO addItem(PermissionDTO pojo) throws DuplicateKeyException {
        PermissionEntity entity = DozerSingletonMapper.getInstance().map(pojo, PermissionEntity.class);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity = permissionLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, PermissionDTO.class);
    }

    @Override
    public PermissionDTO updateItem(PermissionDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        PermissionEntity entity = this.permissionLocalBean.findById(pojo.getPermissionId());
        if(entity == null) throw new ObjectNotFoundException("Can not find Permission with ID " + pojo.getPermissionId());
        entity.setName(pojo.getName());
        entity.setCode(pojo.getCode());
        entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        entity = permissionLocalBean.update(entity);
        return DozerSingletonMapper.getInstance().map(entity, PermissionDTO.class);
    }

    @Override
    public PermissionDTO findByCode(String code) throws ObjectNotFoundException{
        PermissionEntity entity = this.permissionLocalBean.findByCode(code);
        return DozerSingletonMapper.getInstance().map(entity, PermissionDTO.class);
    }

    @Override
    public PermissionDTO findByName(String name) throws ObjectNotFoundException{
        PermissionEntity entity = this.permissionLocalBean.findByName(name);
        return PermissionBeanUtil.entity2DTO(entity);
    }

    @Override
    public List<PermissionDTO> findByCodeOrName(String code, String name){
        List<PermissionEntity> entities = this.permissionLocalBean.findByCodeOrName(code, name);
        List<PermissionDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){
            dtos.add(PermissionBeanUtil.entity2DTO(entities.get(i)));
        }
        return dtos;
    }
}
