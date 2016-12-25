package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.UserGroupEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserGroupLocalBean extends GenericSessionBean<UserGroupEntity, Long>{
    List<UserGroupEntity> findByName(String fullName, String name);
    List<UserGroupEntity> findAll();
    void deleteById(Long roleid);
    UserGroupEntity findByCode(String code);
    Boolean checkDuplicatedName(String name);
    UserGroupEntity findDuplicatedName(String name);
}
