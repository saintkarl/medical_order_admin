package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.UserGroupDTO;

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
public interface UserGroupManagementLocalBean {
    UserGroupDTO findByUserGroupName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);
    Boolean deleteByIds(String[] checkList);
    UserGroupDTO findById(Long userGroupId) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    UserGroupDTO saveOrUpdate(UserGroupDTO userGroupDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<UserGroupDTO> findAll();

    Integer deleteItems(String[] checkList, Map<Long,Long> mapPermissions);
    UserGroupDTO addItem(UserGroupDTO pojo, Map<Long, Long> mapPermissions) throws DuplicateKeyException;
    UserGroupDTO updateItem(UserGroupDTO pojo, Map<Long, Long> mapPermissions) throws ObjectNotFoundException, DuplicateKeyException;
    UserGroupDTO findByCode(String code);

    UserGroupDTO findDuplicatedName(String name);

}
