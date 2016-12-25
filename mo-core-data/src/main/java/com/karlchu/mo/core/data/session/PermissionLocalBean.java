package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.PermissionEntity;

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
public interface PermissionLocalBean extends GenericSessionBean<PermissionEntity, Long>{
    PermissionEntity findByName(String name) throws ObjectNotFoundException;
    List<PermissionEntity> findAll();
    PermissionEntity findByCode(String code) throws ObjectNotFoundException;
    List<PermissionEntity> findByCodeOrName(String code, String name);

    List<PermissionEntity> findPermissionOfUserId(Long userId, Long userGroupId, List<Long> roleIds);
}
