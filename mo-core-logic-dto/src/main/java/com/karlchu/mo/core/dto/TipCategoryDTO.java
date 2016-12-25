package com.karlchu.mo.core.dto;

import java.io.Serializable;

/**
 * Created by khanhcq on 25-Oct-16.
 */
public class TipCategoryDTO implements Serializable {
    private static final long serialVersionUID = 7001327093969597407L;

    private Long tipCategoryId;
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTipCategoryId() {
        return tipCategoryId;
    }

    public void setTipCategoryId(Long tipCategoryId) {
        this.tipCategoryId = tipCategoryId;
    }
}
