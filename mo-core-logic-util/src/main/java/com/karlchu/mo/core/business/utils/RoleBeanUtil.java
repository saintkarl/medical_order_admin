package com.karlchu.mo.core.business.utils;

import com.karlchu.mo.core.data.entity.RoleEntity;
import com.karlchu.mo.core.dto.RoleDTO;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoleBeanUtil {
    public static RoleDTO entity2DTO(RoleEntity entity) {
        RoleDTO dto = new RoleDTO(entity.getRoleId(),entity.getCode(),entity.getName(),entity.getCreatedDate(),entity.getModifiedDate());
        return dto;
    }
}
