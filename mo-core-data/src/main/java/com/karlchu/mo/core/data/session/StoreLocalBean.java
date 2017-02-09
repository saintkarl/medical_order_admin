package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.StoreEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface StoreLocalBean extends GenericSessionBean<StoreEntity, Long>{

    List<StoreEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    StoreEntity findByCode(String code) throws ObjectNotFoundException ;
    StoreEntity findByName(String name) throws ObjectNotFoundException ;
    List<StoreEntity> findByCodeOrName(String code, String name);
}
