package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.RoleDTO;
import com.karlchu.mo.core.dto.UserDTO;
import com.karlchu.mo.core.dto.UserRoleDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserRoleManagementLocalBean {
    UserRoleDTO addItem(UserRoleDTO pojo) throws DuplicateKeyException;

    UserRoleDTO updateItem(UserRoleDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;

    Map<Long, Long> findByUserId(Long userId);

    void deleteByRoleIdUserId(Long roleId, Long userId);

    List<RoleDTO> getRoleByUserId(Long userId);

    List<UserDTO> findUsersWithRole(Long roleId);

    Map<String,RoleDTO> findByUser(Long userId);

    List<UserDTO> findUsersWithRoleCode(String code) throws ObjectNotFoundException;

    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

}
