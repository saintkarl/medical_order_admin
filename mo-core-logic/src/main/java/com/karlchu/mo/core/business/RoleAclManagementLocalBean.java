package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.RoleAclDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface RoleAclManagementLocalBean {

    RoleAclDTO addItem(RoleAclDTO pojo) throws DuplicateKeyException;

    RoleAclDTO updateItem(RoleAclDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;

    Map<Long, Long> findByRoleId(Long roleId);

    void deleteByPermissionIdRoleId(Long permissionId, Long roleId);

    void deleteByRoleId(Long roleId);
    List<PermissionDTO> getPermissionByRoleId(Long roleId);
}
