/**
 * 
 */
package com.karlchu.mo.web.security;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.web.util.RequestUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nguyen Hai Vien
 *
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private transient final Logger logger = Logger.getLogger(getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String logoutSuccessUrl = "/login.html?action=logout";

	/**
	 * @param logoutSuccessUrl the logoutSuccessUrl to set
	 */
	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        String myLocalLogoutSuccessUrl = this.logoutSuccessUrl;
        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
        Cookie portalCookie = RequestUtil.getCookie(request, Constants.ACEGI_SECURITY_ORG_KEY);
        if(portalCookie != null && portalCookie.getValue() != null){
            myLocalLogoutSuccessUrl = "/" + portalCookie.getValue() + ".html?action=logout";
        }
		Cookie terminate = new Cookie(Constants.ACEGI_REMEMBER_ME_COOKIE_KEY, null);
		String contextPath = request.getContextPath();
		terminate.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
		terminate.setMaxAge(0);
		response.addCookie(terminate);
		/**
		 * Delete user's cookies for content security 
		 */
		RequestUtil.deleteCookie(response, new Cookie(Constants.LOGIN_USER_ID_COOKIE, null), "/");
		RequestUtil.deleteCookie(response, new Cookie(Constants.LOGIN_CHECKSUM, null), "/");
		/**
		 * Invalidate session
		 */
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
        SecurityContextHolder.clearContext();
        request.getSession(true); //Create new session

        if ("XMLHttpRequest".equals(ajaxHeader)){
           try{
               JSONObject jsonObject = new JSONObject();
               jsonObject.put("result", true);
               response.setContentType("application/json");
               response.setHeader("Cache-Control", "no-cache");
               response.getWriter().write(jsonObject.toString());
           }catch (JSONException e){
               logger.error(e.getMessage());
               e.printStackTrace();
           }
        }else {
            redirectStrategy.sendRedirect(request, response, myLocalLogoutSuccessUrl);

        }

	}
}
