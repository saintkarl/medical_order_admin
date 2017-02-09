package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.StoreDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface StoreManagementLocalBean {
    StoreDTO findByStoreName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);
    Boolean deleteByIds(String[] checkList);
    StoreDTO findById(Long id) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    StoreDTO saveOrUpdate(StoreDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<StoreDTO> findAll();

    StoreDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList);
    StoreDTO findByCode(String code) throws ObjectNotFoundException;
    List<StoreDTO> findByCodeOrName(String code, String name);
}
