package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.UserRoleEntity;

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
public interface UserRoleLocalBean extends GenericSessionBean<UserRoleEntity, Long>{
    List<UserRoleEntity> findByUserId(Long userId);
    void deleteByRoleUserId(Long roleId, Long userId);
    List<UserRoleEntity> findUsersWithRole(Long roleId);
}
