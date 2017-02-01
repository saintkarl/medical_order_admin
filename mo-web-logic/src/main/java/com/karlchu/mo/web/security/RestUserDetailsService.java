package com.karlchu.mo.web.security;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.RoleDTO;
import com.karlchu.mo.core.dto.UserDTO;
import com.karlchu.mo.core.business.UserManagementLocalBean;
import com.karlchu.mo.web.context.AppContext;
import com.karlchu.rest.security.exception.UsernameNotFoundException;
import com.karlchu.rest.security.user.UserDetails;
import com.karlchu.rest.security.user.UserDetailsService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hieu Le on 10/14/2016.
 */
@Component
public class RestUserDetailsService implements UserDetailsService {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserManagementLocalBean userManagementLocalBean = AppContext.getApplicationContext().getBean(UserManagementLocalBean.class);
        UserManagementLocalBean customerMetaManagementLocalBean = AppContext.getApplicationContext().getBean(UserManagementLocalBean.class);
        RestUserDetails userDetails = null;
        try {
            final UserDTO userDTO = userManagementLocalBean.findByUserNameAndFetchPermissions(username);

            if (Constants.ACTIVE != userDTO.getStatus()) {
                throw new UsernameNotFoundException("User " + username + " is disabled");
            }
            userDetails = new RestUserDetails(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(), true,
                    buildUserAuthorities(userDTO));
//
//            if(!(userDetails.getAuthorities().contains(Constants.ROLE_CUSTOMER)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_SALESMAN)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_SE)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_ASM)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_RSM)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_NW)
//                    || userDetails.getAuthorities().contains(Constants.ROLE_CN)
//            )){
//                throw new UsernameNotFoundException("User " + username + " is disabled");
//            }

            BeanUtils.copyProperties(userDetails, userDTO);
//            if(userDetails.getAuthorities().contains(Constants.ROLE_CUSTOMER)){
//                CustomerDTO customerDTO = customerMetaManagementLocalBean.findByUserId(userDTO.getUserId());
//                userDetails.setCustomerId(customerDTO.getCustomerId());
//                userDetails.setCustomerCode(customerDTO.getCode());
//                userDetails.setNeedChangePassword(customerDTO.getNeedChangePassword() != null ? customerDTO.getNeedChangePassword() : false);
//            }
        } catch (Exception e) {
            logger.warn("NOT found user " + username, e);
            throw new UsernameNotFoundException(e);
        }

        return userDetails;
    }

    public static List<String> buildUserAuthorities(UserDTO account) {
        List<String> authorities = new ArrayList<>();
        authorities.add(Constants.LOGON);
        authorities.add(account.getUserGroup().getCode());
        if (account.getRoles() != null && account.getRoles().size() > 0) {
            for (RoleDTO roleDTO : account.getRoles()) {
                authorities.add(roleDTO.getCode());
                if (roleDTO.getPermissions() != null && roleDTO.getPermissions().size() > 0) {
                    for (PermissionDTO permissionDTO : roleDTO.getPermissions()) {
                        authorities.add(permissionDTO.getCode());
                    }
                }
            }
        }

        if (account.getPermissions() != null && account.getPermissions().size() > 0) {
            for (PermissionDTO permissionDTO : account.getPermissions()) {
                authorities.add(permissionDTO.getCode());
            }
        }

        if(account.getUserGroup() != null && account.getUserGroup().getCode() != null){
            authorities.add(account.getUserGroup().getCode());
        }

        return authorities;
    }
}
