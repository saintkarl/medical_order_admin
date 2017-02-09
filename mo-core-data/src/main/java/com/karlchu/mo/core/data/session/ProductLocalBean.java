package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.ProductEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface ProductLocalBean extends GenericSessionBean<ProductEntity, Long>{

    List<ProductEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    ProductEntity findByCode(String code) throws ObjectNotFoundException ;
    ProductEntity findByName(String name) throws ObjectNotFoundException ;
    List<ProductEntity> findByCodeOrName(String code, String name);
}
