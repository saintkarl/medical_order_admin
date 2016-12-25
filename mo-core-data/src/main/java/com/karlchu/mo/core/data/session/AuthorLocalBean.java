package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.AuthorEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface AuthorLocalBean extends GenericSessionBean<AuthorEntity, Long>{

    List<AuthorEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    AuthorEntity findByCode(String code) throws ObjectNotFoundException ;
    AuthorEntity findByName(String name) throws ObjectNotFoundException ;
    List<AuthorEntity> findByCodeOrName(String code, String name);
}
