package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.ProductDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface ProductManagementLocalBean {
    ProductDTO findByProductName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);
    Boolean deleteByIds(String[] checkList);
    ProductDTO findById(Long id) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    ProductDTO saveOrUpdate(ProductDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<ProductDTO> findAll();

    ProductDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList);
    ProductDTO findByCode(String code) throws ObjectNotFoundException;
    List<ProductDTO> findByCodeOrName(String code, String name);
}
