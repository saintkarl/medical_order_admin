package com.karlchu.mo.core.business.utils;

import com.karlchu.mo.core.data.entity.UsersEntity;
import com.karlchu.mo.core.dto.UserDTO;

public class UserBeanUtil {

    public static UserDTO entity2DTO(UsersEntity entity) {
        UserDTO dto = new UserDTO(entity.getUserId(), entity.getUserName(), entity.getPassword(),
                entity.getFirstName(), entity.getLastName(), entity.getEmail(),entity.getPhoneNumber(),
                entity.getCode(),entity.getStatus(),
                entity.getCreatedDate(), entity.getModifiedDate());
        dto.setUserGroup(UserGroupBeanUtil.entity2DTO(entity.getUserGroup()));
        return dto;
    }

    public static UsersEntity dto2Entity(UserDTO dto){
        UsersEntity entity = new UsersEntity();
        entity.setUserId(dto.getUserId());
        entity.setCode(dto.getCode());
        entity.setEmail(dto.getEmail());
        entity.setUserName(dto.getUserName());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUserGroup(UserGroupBeanUtil.dto2Entity(dto.getUserGroup()));
        return entity;
    }
}
