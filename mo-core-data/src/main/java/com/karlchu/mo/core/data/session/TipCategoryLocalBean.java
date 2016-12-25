package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.TipCategoryEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
@Local
public interface TipCategoryLocalBean extends GenericSessionBean<TipCategoryEntity, Long>{

    List<TipCategoryEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    TipCategoryEntity findByCode(String code) throws ObjectNotFoundException ;
    TipCategoryEntity findByName(String name) throws ObjectNotFoundException ;
    List<TipCategoryEntity> findByCodeOrName(String code, String name);
}
