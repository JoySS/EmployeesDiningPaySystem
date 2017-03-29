package com.sz7road.eat.web.exception;

import com.sz7road.eat.web.common.SimpleResult;
import com.sz7road.eat.web.common.ErrorCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Panda.Z
 */
@SuppressWarnings({"UnusedParameters", "unused"})
@ControllerAdvice
public class DefaultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object processException(ServletWebRequest request, Exception e) {
        logger.error("request {} error {}", request.getRequest().getRequestURI(), e.getMessage(), e);
        return new SimpleResult<>(ErrorCode.SERVER_ERROR.value(), "系统走神了");
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public Object processAuthenticationException(ServletWebRequest request, AuthenticationException e) {
        logger.error("request {} error {}", request.getRequest().getRequestURI(), e.getMessage());
        return new SimpleResult<>(ErrorCode.ILLEGAL_USER_INFO.value(), "account or password error");
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public Object processUnauthorizedException(ServletWebRequest request, UnauthorizedException e) {
        logger.error("request {} error {}", request.getRequest().getRequestURI(), e.getMessage());
        return new SimpleResult<>(ErrorCode.UNAUTHORIZED.value(), "no permissions");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Object processMissingServletRequestParameterException(ServletWebRequest request, MissingServletRequestParameterException e) {
        logger.error("request {} error {}", request.getRequest().getRequestURI(), e.getMessage());
        return new SimpleResult<>(ErrorCode.PARAM_ERROR.value(), "parameter error");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public Object processMissingServletRequestParameterException(ServletWebRequest request, IllegalArgumentException e) {
        logger.error("request {} error {}", request.getRequest().getRequestURI(), e.getMessage());
        return new SimpleResult<>(ErrorCode.PARAM_ERROR.value(), "parameter error");
    }
}
