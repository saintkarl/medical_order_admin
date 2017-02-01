package com.karlchu.rest.security.filter;


import com.karlchu.cache.utils.CacheUtil;
import com.karlchu.rest.security.exception.UsernameNotFoundException;
import com.karlchu.rest.security.user.UserDetails;
import com.karlchu.rest.security.user.UserDetailsService;
import com.karlchu.rest.security.utils.*;
import com.karlchu.rest.security.xml.XInterceptUrls;
import com.karlchu.rest.security.xml.XRest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * REST API security based-on JWT
 * User: Hieu Le
 * Date: 6/23/16
 * Time: 9:08 AM
 */
public class RestApiSecurityFilter implements Filter {
    protected Logger logger = Logger.getLogger(getClass());

    private static final String SEC_FILE_LOCATION_INIT_PARAM_NAME = "restSecurityConfig";
    private static final String NO_FILTER_ROLE = "IS_AUTHENTICATED_ANONYMOUSLY";

    protected List<RestInterceptUrl> interceptUrls = new ArrayList<>();
    protected List<String> anonymousPatterns = new ArrayList<>();
    protected String userDetailsServiceClass;
    protected boolean tokenExpiration;
    protected UserDetailsService userDetailsService;

    private FilterConfig filterConfig;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        readSecurityConfig();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (!contextPath.equals("/")) {
            requestUri = requestUri.substring(contextPath.length());
        }
        boolean isAllowAccess = false;
        for (String pattern : anonymousPatterns) {
            if (RequestUtil.isMatchWildcard(pattern, requestUri)) {
                isAllowAccess = true;
                break;
            }
        }

        UserDetails userDetails = null;
        if (!isAllowAccess) {
            if (!interceptUrls.isEmpty()) {
                userDetails = getUserDetails(request);
                if (userDetails != null && userDetails.isEnabled()) {
                    isAllowAccess = true;
                    for (RestInterceptUrl restInterceptUrl : interceptUrls) {
                        Boolean[] isAllowAccessUri = checkAllowAccessUri(restInterceptUrl, userDetails.getAuthorities(), requestUri);
                        if (isAllowAccessUri[0] && isAllowAccessUri[1]) {
                            break;
                        } else if (!isAllowAccessUri[0] && isAllowAccessUri[1]) {
                            isAllowAccess = false;
                            break;
                        }
                    }
                }
            } else {
                isAllowAccess = true;
            }
        }

        if (!isAllowAccess) {
            response.sendError(403);
        } else {
            boolean isValid = checkClientVersion(request);
            if (isValid) {
                RestSecUtils.setPrincipal(userDetails);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(505, "Invalid Client Version");
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * Override if needed, this will check client version
     * @param request
     * @return true if the version is valid, false otherwise
     */
    protected boolean checkClientVersion(HttpServletRequest request) {
        return true;
    }

    protected final ServletContext getServletContext() {
        return this.filterConfig.getServletContext();
    }

    protected UserDetails getUserDetails(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(jwt)) {
            Map<String, Object> claims = JwtUtils.verify(jwt);
            if (claims != null) {
                Object userName = claims.get(JwtConstants.JWT_CLAIM_USER_NAME);
                if (userName != null) {
                    UserDetails userDetails = (UserDetails) CacheUtil.getInstance().getValue(JwtConstants.REST_PRINCIPAL_CACHE_PREFIX + userName);
                    if (userDetails == null && !tokenExpiration) { //TODO implement token expiration
                        try {
                            userDetails = userDetailsService.loadUserByUsername(userName.toString());
                            CacheUtil.getInstance().putValue(JwtConstants.REST_PRINCIPAL_CACHE_PREFIX + userName, userDetails, JwtConstants.REST_PRINCIPAL_DEF_CACHE_EXPIRE_TIME_SECS);
                        } catch (UsernameNotFoundException e) {
                            logger.error("Could NOT load username: " + userName, e);
                        }
                    }
                    return userDetails;
                }
            }
        }
        return null;
    }

    protected Boolean[] checkAllowAccessUri(final RestInterceptUrl restInterceptUrl, final Collection<String> authorities, String requestUri) {
        boolean isAllow = true, isMatchPattern = false;
        if (RequestUtil.isMatchWildcard(restInterceptUrl.getPattern(), requestUri)) {
            boolean temp = false;
            isMatchPattern = true;
            for (String role : restInterceptUrl.getRoles()) {
                if (authorities.contains(role)) {
                    temp = true;
                    break;
                }
            }
            if (!temp) {
                isAllow = false;
            }
        }
        return new Boolean[]{isAllow, isMatchPattern};
    }

    protected void readSecurityConfig() throws ServletException {
        try {
            String configFileLocation = getServletContext().getInitParameter(SEC_FILE_LOCATION_INIT_PARAM_NAME);
            if (StringUtils.isNotBlank(configFileLocation)) {
                File file = new File(getServletContext().getRealPath(configFileLocation));
                if (file.isFile()) {
                    JAXBContext jaxbContext = JAXBContext.newInstance("com.karlchu.rest.security.xml");
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    XRest xrest = (XRest) jaxbUnmarshaller.unmarshal(file);

                    buildInterceptUrls(xrest);
                } else {
                    throw new ServletException("NOT found REST security config file: " + file.getName());
                }
            } else {
                throw new ServletException("NOT found REST security config context parameter: " + SEC_FILE_LOCATION_INIT_PARAM_NAME);
            }

        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException("Could NOT read REST security config file", e);
        }

    }

    protected void buildInterceptUrls(XRest xrest) throws ServletException {
        Set<String> patternSet = new HashSet<>();
        try {
            checkRequiredValues(xrest);
            this.userDetailsServiceClass = xrest.getUserDetailsServiceClass();
            this.tokenExpiration = xrest.isTokenExpiration();
            createUserDetailsServiceInstance();

            for (XInterceptUrls.XInterceptUrl xInterceptUrl : xrest.getInterceptUrls().getInterceptUrls()) {
                if (StringUtils.isNotBlank(xInterceptUrl.getPattern()) && StringUtils.isNotBlank(xInterceptUrl.getAccess())) {
                    if (!patternSet.contains(xInterceptUrl.getPattern())) {
                        Object[] objArr = parseAccessValue(xInterceptUrl.getAccess());
                        if ((Boolean)objArr[1]) {
                            anonymousPatterns.add(xInterceptUrl.getPattern());
                        } else {
                            interceptUrls.add(new RestInterceptUrl(xInterceptUrl.getPattern(), (List<String>)objArr[0]));
                        }
                        patternSet.add(xInterceptUrl.getPattern());
                    }
                } else {
                    throw new ServletException("Empty 'pattern/access' property in intercept-url");
                }
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException("Could NOT parse REST security config file", e);
        }
    }

    private void checkRequiredValues(XRest xRest) throws ServletException {
        if (!xRest.isTokenExpiration() && StringUtils.isBlank(xRest.getUserDetailsServiceClass())) {
            throw new ServletException("The 'user-details-service' class name is required");
        }
    }

    private void createUserDetailsServiceInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (StringUtils.isNotBlank(userDetailsServiceClass)) {
            userDetailsService = (UserDetailsService) Class.forName(userDetailsServiceClass.trim()).newInstance();
        }
    }

    protected Object[] parseAccessValue(String access) throws ServletException {
        boolean isAno = false;
        String[] arr = StringUtils.deleteWhitespace(access).split(",");
        List<String> roles = new ArrayList<>(arr.length);
        for (String s : arr) {
            if (StringUtils.isNotBlank(s)) {
                if (NO_FILTER_ROLE.equals(s)) {
                    isAno = true;
                    break;
                } else {
                    roles.add(s);
                }
            } else {
                throw new ServletException("Empty 'access' property in intercept-url");
            }
        }
        return new Object[] {roles, Boolean.valueOf(isAno)};
    }
   
}
