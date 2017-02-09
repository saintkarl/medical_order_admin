package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.InvoiceEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface InvoiceLocalBean extends GenericSessionBean<InvoiceEntity, Long>{

    List<InvoiceEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    InvoiceEntity findByCode(String code) throws ObjectNotFoundException ;
    InvoiceEntity findByName(String name) throws ObjectNotFoundException ;
    List<InvoiceEntity> findByCodeOrName(String code, String name);

    String generateInvoiceNo();

}
