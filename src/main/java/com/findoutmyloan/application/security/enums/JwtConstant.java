package com.findoutmyloan.application.security.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum JwtConstant {
    BEARER("Bearer ")
    ;
    private String constant;

    JwtConstant(String constant) {
        this.constant=constant;
    }

    @Override
    public String toString() {
        return constant;
    }

    public String getConstant() {
        return constant;
    }
}
