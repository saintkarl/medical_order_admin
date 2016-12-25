package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.RoleDTO;

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
public interface RoleManagementLocalBean {
    RoleDTO findByRoleName(String name) throws ObjectNotFoundException;
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);
    Boolean deleteByIds(String[] checkList);
    RoleDTO findById(Long roleId) throws ObjectNotFoundException;
    Boolean checkDuplicatedName(String name);
    RoleDTO saveOrUpdate(RoleDTO roleDTO) throws ObjectNotFoundException, DuplicateKeyException;
    List<RoleDTO> findAll();

    RoleDTO findByName(String name) throws ObjectNotFoundException;
    Integer deleteItems(String[] checkList, Map<Long,Long> mapPermissions);
    RoleDTO findByCode(String code) throws ObjectNotFoundException;
    RoleDTO addItem(RoleDTO pojo, Map<Long, Long> mapPermissions) throws DuplicateKeyException;
    RoleDTO updateItem(RoleDTO pojo, Map<Long, Long> mapPermissions) throws ObjectNotFoundException, DuplicateKeyException;
    List<RoleDTO> findByCodeOrName(String code, String name);
}
