package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.TipCategoryDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface TipCategoryManagementLocalBean {
    TipCategoryDTO findByTipCategoryName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);
    Boolean deleteByIds(String[] checkList);
    TipCategoryDTO findById(Long id) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    TipCategoryDTO saveOrUpdate(TipCategoryDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<TipCategoryDTO> findAll();

    TipCategoryDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList, Map<Long, Long> mapPermissions);
    TipCategoryDTO findByCode(String code) throws ObjectNotFoundException;
    TipCategoryDTO addItem(TipCategoryDTO pojo) throws DuplicateKeyException;
    TipCategoryDTO updateItem(TipCategoryDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;
    List<TipCategoryDTO> findByCodeOrName(String code, String name);
}
