package com.sz7road.eat.web.common;

/**
 * @author Panda.Z
 */
public enum ErrorCode {

    /**
     * 成功
     */
    OK(200, "OK"),

    /**
     * 没有数据
     */
    NO_DATA_FOUND(201, "No Data Found"),

    /**
     * 频繁请求
     */
    MULTI_STATUS(207, "Frequent Request"),

    /**
     * 账号或者密码错误
     */
    ILLEGAL_USER_INFO(202, "Username or Password Error"),

    /**
     * 操作数据失败
     */
    HANDLE_DATA_ERROR(203, "Handle Data Error"),

    /**
     * 参数错误
     */
    PARAM_ERROR(400, "Param Error"),

    /**
     * 未登录
     */
    NOT_LOGIN(401, "Login First"),

    /**
     * 权限不足
     */
    UNAUTHORIZED(402, "Unauthorized"),

    /**
     * 服务端异常
     */
    SERVER_ERROR(500, "Server Error");

    private final int value;

    private final String reasonPhrase;


    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
