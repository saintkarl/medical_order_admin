package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.UserGroupAclEntity;

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
public interface UserGroupAclLocalBean extends GenericSessionBean<UserGroupAclEntity, Long>{
    List<UserGroupAclEntity> findByUserId(Long userGroupId);
    void deletByRoleId(Long userGroupId);
    void deleteByRoleUserId(Long permissionId, Long userGroupId);
}
