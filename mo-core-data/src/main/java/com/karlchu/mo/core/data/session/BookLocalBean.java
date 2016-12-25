package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.BookEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface BookLocalBean extends GenericSessionBean<BookEntity, Long>{

    List<BookEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    BookEntity findByCode(String code) throws ObjectNotFoundException ;
    BookEntity findByName(String name) throws ObjectNotFoundException ;
    List<BookEntity> findByCodeOrName(String code, String name);
}
