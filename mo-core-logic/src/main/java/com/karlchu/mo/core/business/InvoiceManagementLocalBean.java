package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.InvoiceDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface InvoiceManagementLocalBean {
    InvoiceDTO findByInvoiceName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);
    Boolean deleteByIds(String[] checkList);
    InvoiceDTO findById(Long id) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    InvoiceDTO saveOrUpdate(InvoiceDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<InvoiceDTO> findAll();

    InvoiceDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList);
    InvoiceDTO findByCode(String code) throws ObjectNotFoundException;
    List<InvoiceDTO> findByCodeOrName(String code, String name);
}
