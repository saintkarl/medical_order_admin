package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.StoreManagementLocalBean;
import com.karlchu.mo.core.data.entity.StoreEntity;
import com.karlchu.mo.core.data.session.StoreLocalBean;
import com.karlchu.mo.core.dto.StoreDTO;

import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless(name = "StoreManagementSessionEJB")
public class StoreManagementSessionBean implements StoreManagementLocalBean {
    @EJB
    private StoreLocalBean storeLocalBean;

    public StoreManagementSessionBean() {
    }

    @Override
    public StoreDTO saveOrUpdate(StoreDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        StoreEntity entity = null;
        if (dto.getStoreId() != null && dto.getStoreId() > 0) {
            entity = storeLocalBean.findById(dto.getStoreId());
            entity.setName(dto.getName());
            entity.setAddress(dto.getAddress());
            entity.setCode(dto.getCode());
            entity.setEmail(dto.getEmail());
            entity.setOwner(dto.getOwner());
            entity.setStatus(dto.getStatus());
            entity.setTel(dto.getTel());
            storeLocalBean.update(entity);
        } else {
            entity = DozerSingletonMapper.getInstance().map(dto, StoreEntity.class);
            entity = storeLocalBean.save(entity);
            dto.setStoreId(entity.getStoreId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause) {
        Object[] objs = storeLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems, whereClause);
        List<StoreEntity> roleEntries = (List<StoreEntity>)objs[1];
        List<StoreDTO> roleDTOs = new ArrayList<>();
        for(StoreEntity storeEntity : roleEntries){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(storeEntity, StoreDTO.class));
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
            storeLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public StoreDTO findById(Long roleId) throws ObjectNotFoundException {
        StoreEntity entity = storeLocalBean.findById(roleId);
        return DozerSingletonMapper.getInstance().map(entity, StoreDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return storeLocalBean.checkDuplicatedName(name);
    }

    @Override
    public StoreDTO findByStoreName(String name) throws ObjectNotFoundException {
        StoreEntity entity = storeLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found role " + name);
        }
        return DozerSingletonMapper.getInstance().map(entity, StoreDTO.class);
    }
    @Override
    public  List<StoreDTO> findAll(){

        List<StoreDTO>  roleDTOs = new ArrayList<StoreDTO>();
        List<StoreEntity> entity = storeLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(entity.get(i), StoreDTO.class));

        }
        return roleDTOs;


    }

    @Override
    public Integer deleteItems(String[] checkList) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){
                storeLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }


    @Override
    public StoreDTO findByCode(String code) throws ObjectNotFoundException{
        StoreEntity entity = this.storeLocalBean.findByCode(code);
        return DozerSingletonMapper.getInstance().map(entity, StoreDTO.class);

    }

    @Override
    public StoreDTO findByName(String name) throws ObjectNotFoundException{
            StoreEntity entity  = this.storeLocalBean.findByName(name);
        return DozerSingletonMapper.getInstance().map(entity, StoreDTO.class);
    }

    public List<StoreDTO> findByCodeOrName(String code, String name){
        List<StoreEntity> entities = this.storeLocalBean.findByCodeOrName(code, name);
        List<StoreDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){
            
            dtos.add(DozerSingletonMapper.getInstance().map(entities.get(i), StoreDTO.class));
        }
        return dtos;
    }
}
