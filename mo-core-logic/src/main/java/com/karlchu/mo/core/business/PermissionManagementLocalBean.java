package com.karlchu.mo.core.business;

import com.karlchu.mo.core.dto.PermissionDTO;

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
public interface PermissionManagementLocalBean {
    PermissionDTO findByPermissionName(String name) throws ObjectNotFoundException;

    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

    Boolean deleteByIds(String[] checkList);

    PermissionDTO findById(Long permissionId) throws ObjectNotFoundException;

    PermissionDTO saveOrUpdate(PermissionDTO permissionDTO) throws ObjectNotFoundException, DuplicateKeyException;

    List<PermissionDTO> findAll();

    PermissionDTO addItem(PermissionDTO pojo) throws DuplicateKeyException;

    PermissionDTO updateItem(PermissionDTO pojo) throws ObjectNotFoundException, DuplicateKeyException;

    PermissionDTO findByCode(String code) throws ObjectNotFoundException;

    PermissionDTO findByName(String name) throws ObjectNotFoundException;

    List<PermissionDTO> findByCodeOrName(String code, String name);
}
