package com.karlchu.mo.web.security;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.core.business.UserManagementLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.RoleDTO;
import com.karlchu.mo.core.dto.UserDTO;
import com.karlchu.mo.web.security.util.MyUserDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Nguyen Hai Vien
 * 
 */

public class MyUserDetailService implements UserDetailsService {
    private transient final Logger log = Logger.getLogger(getClass());

	protected UserCache userCache = null;

	private UserManagementLocalBean userManagementLocalBean;


    public void setUserManagementLocalBean(UserManagementLocalBean userManagementLocalBean) {
        this.userManagementLocalBean = userManagementLocalBean;
    }

    /**
	 * Creates new instance of MyUserDetailService
	 */
	public MyUserDetailService() {

	}

	/**
	 * Set UserCache
	 *
	 * @param userCache
	 *            user cache to set
	 */
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may possibly be case insensitive, or case insensitive depending on
	 * how the implementaion instance is configured. In this case, the
	 * <code>UserDetails</code> object that comes back may have a username
	 * that is of a different case than what was actually requested..
	 *
	 * @param username
	 *            the username presented to the {@link
	 *            org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             if the user could not be found or the user has no
	 *             GrantedAuthority
	 * @throws org.springframework.dao.DataAccessException
	 *             if user could not be found for a repository-specific reason
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
        UserDTO account = null;

        try{
            account = userManagementLocalBean.findByUserNameAndFetchPermissions(username);
        }catch (Exception e) {
            log.error("Could not load user info for login with username:" + username + ". Exception: " + e.getMessage(), e);
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(Constants.LOGON_DEFAULT_PERMISSION));
        authorities.add(new SimpleGrantedAuthority(account.getUserGroup().getCode()));
        for (RoleDTO roleDTO : account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(roleDTO.getCode()));
        }

        for (PermissionDTO permissionDTO : account.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permissionDTO.getCode()));
        }
        if(account.getUserGroup() != null && account.getUserGroup().getCode() != null){
            authorities.add(new SimpleGrantedAuthority(account.getUserGroup().getCode()));
        }

		MyUserDetail loginUser = new MyUserDetail(username, account.getPassword(), true, true, true, true, authorities);
        loginUser.setDisplayName(account.getFirstName() + " " + account.getLastName());
        loginUser.setCreatedDate(account.getCreatedDate());
		BeanUtils.copyProperties(account, loginUser);
		return loginUser;
	}
}
