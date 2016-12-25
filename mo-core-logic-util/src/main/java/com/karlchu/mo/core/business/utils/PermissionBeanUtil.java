package com.karlchu.mo.core.business.utils;

import com.karlchu.mo.core.data.entity.PermissionEntity;
import com.karlchu.mo.core.dto.PermissionDTO;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionBeanUtil {
    public static PermissionDTO entity2DTO(PermissionEntity entity) {
        PermissionDTO dto = new PermissionDTO(entity.getPermissionId(),entity.getCode(),entity.getName(),entity.getCreatedDate(),entity.getModifiedDate());
        return dto;
    }
}
