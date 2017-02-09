package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.InvoiceManagementLocalBean;
import com.karlchu.mo.core.data.entity.*;
import com.karlchu.mo.core.data.session.InvoiceDetailLocalBean;
import com.karlchu.mo.core.data.session.InvoiceLocalBean;
import com.karlchu.mo.core.dto.InvoiceDTO;
import com.karlchu.mo.core.dto.InvoiceDetailDTO;

import javax.ejb.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless(name = "InvoiceManagementSessionEJB")
public class InvoiceManagementSessionBean implements InvoiceManagementLocalBean {
    @EJB
    private InvoiceLocalBean invoiceLocalBean;
    @EJB
    private InvoiceDetailLocalBean invoiceDetailLocalBean;

    public InvoiceManagementSessionBean() {
    }

    @Override
    public InvoiceDTO saveOrUpdate(InvoiceDTO dto) throws ObjectNotFoundException, DuplicateKeyException {
        InvoiceEntity entity = null;
        if (dto.getInvoiceId() != null && dto.getInvoiceId() > 0) {
            entity = invoiceLocalBean.findById(dto.getInvoiceId());

            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            if(dto.getStore() != null && dto.getStore().getStoreId() != null){
                entity.setStore(new StoreEntity());
                entity.getStore().setStoreId(dto.getStore().getStoreId());
            }
            entity = invoiceLocalBean.update(entity);
            saveOrUpdateInvoiceDetails(entity.getInvoiceDetails(),dto.getInvoiceDetails(),entity);

        } else {
            entity = new InvoiceEntity();
            UsersEntity loginUser = new UsersEntity();
            loginUser.setUserId(dto.getCreatedBy().getUserId());
            entity.setCreatedBy(loginUser);
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            if(dto.getStore() != null && dto.getStore().getStoreId() != null){
                entity.setStore(new StoreEntity());
                entity.getStore().setStoreId(dto.getStore().getStoreId());
            }
            entity.setInvoiceNo(invoiceLocalBean.generateInvoiceNo());
            entity = this.invoiceLocalBean.save(entity);
            dto.setInvoiceId(entity.getInvoiceId());
            saveOrUpdateInvoiceDetails(null,dto.getInvoiceDetails(),entity);
        }
        return dto;
    }

    private void saveOrUpdateInvoiceDetails(List<InvoiceDetailEntity> oldInvoiceDetails, List<InvoiceDetailDTO> invoiceDetails, InvoiceEntity invoiceEntity) throws DuplicateKeyException {
        for(InvoiceDetailDTO item : invoiceDetails){
            if(item.getProduct() != null && item.getProduct().getProductId() != null && item.getProduct().getProductId() > 0 && item.getQuantity() != null){
                InvoiceDetailEntity invoiceDetailEntity = null;
                if(oldInvoiceDetails !=null && oldInvoiceDetails.size() > 0){ //case update
                    for(int i = oldInvoiceDetails.size() - 1; i >= 0; i--){
                        InvoiceDetailEntity dbInvoiceDetailEntity = oldInvoiceDetails.get(i);
                        if(dbInvoiceDetailEntity.getProduct().getProductId().equals(item.getProduct().getProductId())){
                            invoiceDetailEntity = dbInvoiceDetailEntity;
                            oldInvoiceDetails.remove(i);
                            break;
                        }
                    }
                    if(invoiceDetailEntity == null){
                        invoiceDetailEntity = new InvoiceDetailEntity();
                        saveOrUpdateInvoiceDetail(item,invoiceDetailEntity,invoiceEntity);
                    }else{
                        saveOrUpdateInvoiceDetail(item,invoiceDetailEntity,invoiceEntity);
                    }
                }else{ //case add new
                    invoiceDetailEntity = new InvoiceDetailEntity();
                    saveOrUpdateInvoiceDetail(item,invoiceDetailEntity,invoiceEntity);
                }
            }
        }
        if(oldInvoiceDetails!= null && !oldInvoiceDetails.isEmpty()){
            this.invoiceDetailLocalBean.deleteAll(oldInvoiceDetails);
        }

    }

    private void saveOrUpdateInvoiceDetail(InvoiceDetailDTO item, InvoiceDetailEntity invoiceDetailEntity, InvoiceEntity invoiceEntity) throws DuplicateKeyException {
        invoiceDetailEntity.setInvoice(invoiceEntity);
        invoiceDetailEntity.setDiscount(item.getDiscount());
        invoiceDetailEntity.setAmount(item.getAmount());

        if(item.getProduct() != null && item.getProduct().getProductId() != null && item.getProduct().getProductId() > 0){
            invoiceDetailEntity.setProduct(new ProductEntity());
            invoiceDetailEntity.getProduct().setProductId(item.getProduct().getProductId());
        }
        else{
            invoiceDetailEntity.setProduct(null);
        }

        if(item.getQuantity() != null && item.getQuantity() > 0){
            invoiceDetailEntity.setQuantity(item.getQuantity());
        }else{
            invoiceDetailEntity.setQuantity(null);
        }

        if(invoiceDetailEntity.getInvoiceDetailId() != null){
            invoiceDetailLocalBean.update(invoiceDetailEntity);
        }else{
            invoiceDetailLocalBean.save(invoiceDetailEntity);

        }
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause) {
        Object[] objs = invoiceLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems, whereClause);
        List<InvoiceEntity> roleEntries = (List<InvoiceEntity>)objs[1];
        List<InvoiceDTO> roleDTOs = new ArrayList<>();
        for(InvoiceEntity invoiceEntity : roleEntries){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(invoiceEntity, InvoiceDTO.class));
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
            invoiceLocalBean.delete(ids);
        }catch (RemoveException e){
            return false;
        }
        return true;
    }

    @Override
    public InvoiceDTO findById(Long roleId) throws ObjectNotFoundException {
        InvoiceEntity entity = invoiceLocalBean.findById(roleId);
        return DozerSingletonMapper.getInstance().map(entity, InvoiceDTO.class);
    }

    @Override
    public Boolean checkDuplicatedName(String name) {
        return invoiceLocalBean.checkDuplicatedName(name);
    }

    @Override
    public InvoiceDTO findByInvoiceName(String name) throws ObjectNotFoundException {
        InvoiceEntity entity = invoiceLocalBean.findEqualUnique("name", name);
        if (entity == null) {
            throw new ObjectNotFoundException("Not found role " + name);
        }
        return DozerSingletonMapper.getInstance().map(entity, InvoiceDTO.class);
    }
    @Override
    public  List<InvoiceDTO> findAll(){

        List<InvoiceDTO>  roleDTOs = new ArrayList<InvoiceDTO>();
        List<InvoiceEntity> entity = invoiceLocalBean.findAll();
        for(int i = 0; i < entity.size(); i++){
            roleDTOs.add(DozerSingletonMapper.getInstance().map(entity.get(i), InvoiceDTO.class));

        }
        return roleDTOs;


    }

    @Override
    public Integer deleteItems(String[] checkList) {
        Integer res = 0;
        if(checkList != null && checkList.length >= 0){
            for(String id : checkList){
                invoiceLocalBean.deleteById(Long.parseLong(id));
                res++;
            }
        }
        return  res;
    }


    @Override
    public InvoiceDTO findByCode(String code) throws ObjectNotFoundException{
        InvoiceEntity entity = this.invoiceLocalBean.findByCode(code);
        return DozerSingletonMapper.getInstance().map(entity, InvoiceDTO.class);

    }

    @Override
    public InvoiceDTO findByName(String name) throws ObjectNotFoundException{
            InvoiceEntity entity  = this.invoiceLocalBean.findByName(name);
        return DozerSingletonMapper.getInstance().map(entity, InvoiceDTO.class);
    }

    public List<InvoiceDTO> findByCodeOrName(String code, String name){
        List<InvoiceEntity> entities = this.invoiceLocalBean.findByCodeOrName(code, name);
        List<InvoiceDTO> dtos = new ArrayList<>();
        for(int i=0; i< entities.size(); i++){
            
            dtos.add(DozerSingletonMapper.getInstance().map(entities.get(i), InvoiceDTO.class));
        }
        return dtos;
    }
}
