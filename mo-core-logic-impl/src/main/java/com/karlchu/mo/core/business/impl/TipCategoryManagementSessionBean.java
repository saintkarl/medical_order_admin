package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.TipCategoryManagementLocalBean;
import com.karlchu.mo.core.data.entity.TipCategoryEntity;
import com.karlchu.mo.core.data.session.TipCategoryLocalBean;
import com.karlchu.mo.core.dto.TipCategoryDTO;

import javax.ejb.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless(name = "TipCategoryManagementSessionEJB")
public class TipCategoryManagementSessionBean implements TipCategoryManagementLocalBean {
    @EJB
    private TipCategoryLocalBean tipCategoryLocalBean;

    public TipCategoryManagementSessionBean() {
    }

    @Override
    public TipCategoryDTO saveOrUpdate(TipCategoryDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        TipCategoryEntity entity = null;
        if (dto.getTipCategoryId() != null && dto.getTipCategoryId() > 0) {
            entity = tipCategoryLocalBean.findById(dto.getTipCategoryId());
            entity.setName(dto.getName());
            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            tipCategoryLocalBean.update(entity);
        } else {
            entity = new TipCategoryEntity();
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity = tipCategoryLocalBean.save(entity);
            dto.setTipCategoryId(entity.getTipCategoryId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause) {
        Object[] objs = tipCategoryLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems, whereClause);
        List<TipCategoryEntity> roleEntries = (List<TipCategoryEntity>)objs[1];
        List<TipCategoryDTO> roleDTOs = new ArrayList<>();
        for(TipCategoryEntity TipCategoryEntity : roleEntries){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(TipCategoryEntity, TipCategoryDTO.class));
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
            tipCategoryLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public TipCategoryDTO findById(Long roleId) throws ObjectNotFoundException {
        TipCategoryEntity entity = tipCategoryLocalBean.findById(roleId);
        return DozerSingletonMapper.getInstance().map(entity, TipCategoryDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return tipCategoryLocalBean.checkDuplicatedName(name);
    }

    @Override
    public TipCategoryDTO findByTipCategoryName(String name) throws ObjectNotFoundException {
        TipCategoryEntity entity = tipCategoryLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found role " + name);
        }
        return DozerSingletonMapper.getInstance().map(entity, TipCategoryDTO.class);
    }
    @Override
    public  List<TipCategoryDTO> findAll(){

        List<TipCategoryDTO>  roleDTOs = new ArrayList<TipCategoryDTO>();
        List<TipCategoryEntity> entity = tipCategoryLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(entity.get(i), TipCategoryDTO.class));

        }
        return roleDTOs;


    }

    @Override
    public Integer deleteItems(String[] checkList,Map<Long,Long> mapPermissions) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){
                tipCategoryLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }

    @Override
    public TipCategoryDTO addItem(TipCategoryDTO pojo) throws DuplicateKeyException {
        TipCategoryEntity entity = DozerSingletonMapper.getInstance().map(pojo, TipCategoryEntity.class);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity = tipCategoryLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, TipCategoryDTO.class);
    }

    @Override
    public TipCategoryDTO updateItem(TipCategoryDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        TipCategoryEntity dbItem = this.tipCategoryLocalBean.findById(pojo.getTipCategoryId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find TipCategory with ID " + pojo.getTipCategoryId());
        dbItem.setName(pojo.getName());
        dbItem.setCode(pojo.getCode());
        dbItem.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        dbItem = tipCategoryLocalBean.update(dbItem);
        return DozerSingletonMapper.getInstance().map(dbItem, TipCategoryDTO.class);
    }

    @Override
    public TipCategoryDTO findByCode(String code) throws ObjectNotFoundException{
        TipCategoryEntity entity = this.tipCategoryLocalBean.findByCode(code);
        return DozerSingletonMapper.getInstance().map(entity, TipCategoryDTO.class);

    }

    @Override
    public TipCategoryDTO findByName(String name) throws ObjectNotFoundException{
            TipCategoryEntity entity  = this.tipCategoryLocalBean.findByName(name);
        return DozerSingletonMapper.getInstance().map(entity, TipCategoryDTO.class);
    }

    public List<TipCategoryDTO> findByCodeOrName(String code, String name){
        List<TipCategoryEntity> entities = this.tipCategoryLocalBean.findByCodeOrName(code, name);
        List<TipCategoryDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){

            dtos.add(DozerSingletonMapper.getInstance().map(entities.get(i), TipCategoryDTO.class));
        }
        return dtos;
    }
}
