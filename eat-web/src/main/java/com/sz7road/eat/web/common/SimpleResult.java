package com.sz7road.eat.web.common;

/**
 * @author Panda.Z
 */
@SuppressWarnings("unused")
public class SimpleResult<E> {

    private int code;
    private String message;
    private E data;
    private long timestamp =  System.currentTimeMillis();

    public SimpleResult(int code) {
        this.code = code;
    }

    public SimpleResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SimpleResult(int code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public E getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
