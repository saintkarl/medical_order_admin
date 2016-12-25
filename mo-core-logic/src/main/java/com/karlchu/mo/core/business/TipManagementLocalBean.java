package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.TipDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface TipManagementLocalBean {
    TipDTO findByTipName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);
    Boolean deleteByIds(String[] checkList);
    TipDTO findById(Long id) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    TipDTO saveOrUpdate(TipDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<TipDTO> findAll();

    TipDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList);
    TipDTO findByCode(String code) throws ObjectNotFoundException;
    TipDTO addItem(TipDTO pojo, Map<Long, Long> mapPermissions) throws DuplicateKeyException;
    TipDTO updateItem(TipDTO pojo, Map<Long, Long> mapPermissions) throws ObjectNotFoundException, DuplicateKeyException;
    List<TipDTO> findByCodeOrName(String code, String name);
}
