package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.RoleAclEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface RoleAclLocalBean extends GenericSessionBean<RoleAclEntity, Long> {
    List<RoleAclEntity> findByRoleId(Long roleId);
    void deletByRoleId(Long roleId);
    void deleteByPermissionRoleId(Long permissionId, Long roleId);
}
