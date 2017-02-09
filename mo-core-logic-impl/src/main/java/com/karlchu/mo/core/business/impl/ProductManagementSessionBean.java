package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.ProductManagementLocalBean;
import com.karlchu.mo.core.data.entity.ProductEntity;
import com.karlchu.mo.core.data.session.ProductLocalBean;
import com.karlchu.mo.core.dto.ProductDTO;

import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless(name = "ProductManagementSessionEJB")
public class ProductManagementSessionBean implements ProductManagementLocalBean {
    @EJB
    private ProductLocalBean productLocalBean;

    public ProductManagementSessionBean() {
    }

    @Override
    public ProductDTO saveOrUpdate(ProductDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        ProductEntity entity = null;
        if (dto.getProductId() != null && dto.getProductId() > 0) {
            entity = productLocalBean.findById(dto.getProductId());
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            productLocalBean.update(entity);
        } else {
            entity = DozerSingletonMapper.getInstance().map(dto, ProductEntity.class);
            entity = productLocalBean.save(entity);
            dto.setProductId(entity.getProductId());
        }
        return dto;
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause) {
        Object[] objs = productLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems, whereClause);
        List<ProductEntity> roleEntries = (List<ProductEntity>)objs[1];
        List<ProductDTO> roleDTOs = new ArrayList<>();
        for(ProductEntity productEntity : roleEntries){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(productEntity, ProductDTO.class));
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
            productLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public ProductDTO findById(Long roleId) throws ObjectNotFoundException {
        ProductEntity entity = productLocalBean.findById(roleId);
        return DozerSingletonMapper.getInstance().map(entity, ProductDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return productLocalBean.checkDuplicatedName(name);
    }

    @Override
    public ProductDTO findByProductName(String name) throws ObjectNotFoundException {
        ProductEntity entity = productLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found role " + name);
        }
        return DozerSingletonMapper.getInstance().map(entity, ProductDTO.class);
    }
    @Override
    public  List<ProductDTO> findAll(){

        List<ProductDTO>  roleDTOs = new ArrayList<ProductDTO>();
        List<ProductEntity> entity = productLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(entity.get(i), ProductDTO.class));

        }
        return roleDTOs;


    }

    @Override
    public Integer deleteItems(String[] checkList) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){
                productLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }


    @Override
    public ProductDTO findByCode(String code) throws ObjectNotFoundException{
        ProductEntity entity = this.productLocalBean.findByCode(code);
        return DozerSingletonMapper.getInstance().map(entity, ProductDTO.class);

    }

    @Override
    public ProductDTO findByName(String name) throws ObjectNotFoundException{
            ProductEntity entity  = this.productLocalBean.findByName(name);
        return DozerSingletonMapper.getInstance().map(entity, ProductDTO.class);
    }

    public List<ProductDTO> findByCodeOrName(String code, String name){
        List<ProductEntity> entities = this.productLocalBean.findByCodeOrName(code, name);
        List<ProductDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){
            
            dtos.add(DozerSingletonMapper.getInstance().map(entities.get(i), ProductDTO.class));
        }
        return dtos;
    }
}
