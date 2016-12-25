package com.karlchu.mo.jcr.taglib;

import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class RepositoryFileTag extends TagSupport {
    private String value;
    private boolean fullDisplay;
    private String servletName;
    private String cssStyle;
    private String cssClass;
    private String displayName;
    private String var;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFullDisplay() {
        return fullDisplay;
    }

    public void setFullDisplay(boolean fullDisplay) {
        this.fullDisplay = fullDisplay;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
    public int doStartTag() throws JspException {
        try{
            String res = "";
            String url = "";
            if (StringUtils.isBlank(servletName)) {
                servletName = "repository";
                url = "/"  + servletName + value;
                res = "<a href='" + url + "'";
            }else {
                url = "/" +  servletName + value;
                res = "<a href='" + url  + "'";
            }

            if (StringUtils.isNotBlank(cssStyle)) {
                res += " style='" + cssStyle + "'";
            }
            if (StringUtils.isNotBlank(cssClass)) {
                res += " class='" + cssClass + "'";
            }
            res += ">";

            if (fullDisplay) {
                res += value;
            }else{
                if (StringUtils.isNotBlank(displayName)) {
                    res += displayName;
                }else{
                    res += getFilename(value);
                }
            }
            res += "</a>";
            if (StringUtils.isNotBlank(var)) {
                pageContext.setAttribute(var, url);
            }else{
                pageContext.getOut().write(res);
            }
        }catch(IOException e) {

        }
        return SKIP_BODY;
    }

    private String getFilename(String fileName) {
        String[] tmp = fileName.split("/");
        if (tmp != null && tmp.length > 0) {
            return tmp[tmp.length - 1];
        }
        return fileName;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }
}
