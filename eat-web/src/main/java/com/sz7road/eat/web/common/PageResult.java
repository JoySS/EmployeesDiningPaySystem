package com.sz7road.eat.web.common;

/**
 * @author Panda.Z
 */
public class PageResult<E> extends SimpleResult<E> {

    private int current;
    private int size;
    private int pages;
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    public PageResult(int httpCode) {
        super(httpCode);
    }

    public PageResult(int httpCode, String msg) {
        super(httpCode, msg);
    }

    public PageResult(int httpCode, String msg, E data) {
        super(httpCode, msg, data);
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
}
