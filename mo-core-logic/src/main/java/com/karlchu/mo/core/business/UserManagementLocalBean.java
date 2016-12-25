package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.UserDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import java.util.List;
import java.util.Map;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 3:45 PM
 */
@Local
public interface UserManagementLocalBean {
    UserDTO addItem(UserDTO pojo) throws DuplicateKeyException;

    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

    UserDTO updateItem(UserDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;

    UserDTO findById(Long userId) throws ObjectNotFoundException;

    Object[] search(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems, String whereClause);

    void deleteItems(String[] checkList) throws RemoveException;

    UserDTO findByUserName(String username) throws ObjectNotFoundException;

    UserDTO findByCode(String code);

    List<UserDTO> findByUsernameCodeOrEmail(String userName, String code, String email, Long userId);

    Boolean checkDuplicatedUniqueData(UserDTO userDTO);

    List<UserDTO> searchByUserGroup(String code);

    List<UserDTO> searchByManagerLine(String managerCode);

    List<UserDTO> findByRole(String roleCode);

    void updateUserRoles(Long userId, Map<Long, Long> currentMaps) throws DuplicateKeyException;

    Map<String, UserDTO> findByUserNames(List<String> userNames);

    Map<String, UserDTO> findByEmails(List<String> emails);

    UserDTO findByEmail(String email) throws ObjectNotFoundException;

    List<UserDTO> findByUserNameQuery(String query);

    UserDTO findByUserNameAndFetchPermissions(String username) throws ObjectNotFoundException;
}
