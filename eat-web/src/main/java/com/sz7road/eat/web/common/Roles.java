package com.sz7road.eat.web.common;

/**
 * @author Panda.Z
 */
public enum Roles {

    /**
     * 普通用户
     */
    USER("user");


    private final String role;


    Roles(String role) {
        this.role = role;
    }

    public String value() {
        return this.role;
    }

}
