package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.NewsEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface NewsLocalBean extends GenericSessionBean<NewsEntity, Long>{

    List<NewsEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    NewsEntity findByCode(String code) throws ObjectNotFoundException ;
    NewsEntity findByName(String name) throws ObjectNotFoundException ;
    List<NewsEntity> findByCodeOrName(String code, String name);
}
