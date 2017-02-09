package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.InvoiceDetailEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface InvoiceDetailLocalBean extends GenericSessionBean<InvoiceDetailEntity, Long>{

    List<InvoiceDetailEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    InvoiceDetailEntity findByCode(String code) throws ObjectNotFoundException ;
    InvoiceDetailEntity findByName(String name) throws ObjectNotFoundException ;
    List<InvoiceDetailEntity> findByCodeOrName(String code, String name);
}
