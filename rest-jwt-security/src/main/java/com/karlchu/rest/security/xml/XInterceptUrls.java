package com.karlchu.rest.security.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Hieu Le on 6/28/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XInterceptUrls implements Serializable {

    @XmlElement(required = true, name = "intercept-url")
    private List<XInterceptUrl> interceptUrls;

    public List<XInterceptUrl> getInterceptUrls() {
        return interceptUrls;
    }

    public void setInterceptUrls(List<XInterceptUrl> interceptUrls) {
        this.interceptUrls = interceptUrls;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class XInterceptUrl {
        @XmlAttribute
        private String pattern;
        @XmlAttribute
        private String access;

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getAccess() {
            return access;
        }

        public void setAccess(String access) {
            this.access = access;
        }
    }
}
