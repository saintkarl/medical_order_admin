package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.RoleEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface RoleLocalBean extends GenericSessionBean<RoleEntity, Long>{

    List<RoleEntity> findAll();
    void deleteById(Long roleid);
    Boolean checkDuplicatedName(String name);
    RoleEntity findByCode(String code) throws ObjectNotFoundException ;
    RoleEntity findByName(String name) throws ObjectNotFoundException ;
    List<RoleEntity> findByCodeOrName(String code, String name);

    List<RoleEntity> findRoleOfUser(Long userId);
}
