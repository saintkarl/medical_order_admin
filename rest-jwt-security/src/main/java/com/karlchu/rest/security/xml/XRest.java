package com.karlchu.rest.security.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Hieu Le on 10/14/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rest")
public class XRest implements Serializable {
    @XmlAttribute(required = true, name="token-expiration")
    private boolean tokenExpiration;

    @XmlElement(required = true, name = "intercept-urls")
    private XInterceptUrls interceptUrls;

    @XmlElement(required = false, name = "user-details-service")
    private String userDetailsServiceClass;

    public XInterceptUrls getInterceptUrls() {
        return interceptUrls;
    }

    public void setInterceptUrls(XInterceptUrls interceptUrls) {
        this.interceptUrls = interceptUrls;
    }

    public String getUserDetailsServiceClass() {
        return userDetailsServiceClass;
    }

    public void setUserDetailsServiceClass(String userDetailsServiceClass) {
        this.userDetailsServiceClass = userDetailsServiceClass;
    }

    public boolean isTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(boolean tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }
}
