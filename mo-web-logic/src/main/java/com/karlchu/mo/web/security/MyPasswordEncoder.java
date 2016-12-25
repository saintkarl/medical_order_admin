/**
 * 
 */
package com.karlchu.mo.web.security;

import com.karlchu.mo.common.security.DesEncryptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author Nguyen Hai Vien
 *
 */
public class MyPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String password, Object salt)
			throws DataAccessException {
		return DesEncryptionUtils.getInstance().encrypt(password);
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
			throws DataAccessException {
		String encPass2 = DesEncryptionUtils.getInstance().encrypt(rawPass);
		return encPass.equals(encPass2);
    }

}
