package com.sz7road.eat.web.core;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Strings;
import com.sz7road.eat.web.common.ErrorCode;
import com.sz7road.eat.web.common.PageResult;
import com.sz7road.eat.web.common.SimpleResult;

/**
 * @author Panda.Z
 */
public abstract class AbstractController {


    protected final SimpleResult<?> returnOK() {
        return new SimpleResult<>(ErrorCode.OK.value(), ErrorCode.OK.getReasonPhrase());
    }

    protected final SimpleResult<?> returnOK(Object data) {
        return new SimpleResult<>(ErrorCode.OK.value(), ErrorCode.OK.getReasonPhrase(), data);
    }

    protected final SimpleResult<?> returnError(ErrorCode errorCode) {
        return new SimpleResult<>(errorCode.value(), errorCode.getReasonPhrase());
    }

    protected final SimpleResult<?> returnError(ErrorCode errorCode, String message) {
        return new SimpleResult<>(errorCode.value(), message);
    }

    protected final SimpleResult<?> returnError(ErrorCode errorCode, String message, Object data) {
        if (Strings.isNullOrEmpty(message)){
            return new SimpleResult<>(errorCode.value(), errorCode.getReasonPhrase(), data);
        }else{
            return new SimpleResult<>(errorCode.value(), message, data);
        }
    }

    protected final PageResult<?> returnPage(Page page) {
        PageResult<?> result = new PageResult<>(ErrorCode.OK.value(), ErrorCode.OK.getReasonPhrase(), page.getRecords());
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setPages(page.getPages());
        result.setiTotalRecords(page.getTotal());
        result.setiTotalDisplayRecords(page.getTotal());
        return result;
    }
}
