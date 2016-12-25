package com.karlchu.mo.web.editor;

import org.apache.commons.lang.StringEscapeUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: 51101
 * Date: 6/1/16
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringEscapeEditor extends PropertyEditorSupport {

    private boolean escapeHTML;
    private boolean escapeJavaScript;
    private boolean escapeSQL;

    public StringEscapeEditor() {
        super();
    }

    public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript,
                              boolean escapeSQL) {
        super();
        this.escapeHTML = escapeHTML;
        this.escapeJavaScript = escapeJavaScript;
        this.escapeSQL = escapeSQL;
    }

    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String value = text;
            if (escapeHTML) {
                value = StringEscapeUtils.escapeHtml(value);
            }
            if (escapeJavaScript) {
                value = StringEscapeUtils.escapeJavaScript(value);
            }
            if (escapeSQL) {
                value = StringEscapeUtils.escapeSql(value);
            }
            setValue(value);
        }
    }

    public String getAsText() {
        Object value = getValue();
        return (value != null ? value.toString() : "");
    }
}