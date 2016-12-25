package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.TipEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface TipLocalBean extends GenericSessionBean<TipEntity, Long>{

    List<TipEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    TipEntity findByCode(String code) throws ObjectNotFoundException ;
    TipEntity findByName(String name) throws ObjectNotFoundException ;
    List<TipEntity> findByCodeOrName(String code, String name);
}
