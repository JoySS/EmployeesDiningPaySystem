package com.sz7road.eat.web.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.sz7road.eat.web.common.SimpleResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static com.sz7road.eat.web.common.ErrorCode.UNAUTHORIZED;

/**
 * @author Panda.Z
 */
public class IFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(IFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [{}]", getLoginUrl());
            }
            try (ServletOutputStream out = response.getOutputStream()) {
                SimpleResult<Object> objectSimpleResult = new SimpleResult<>(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase());
                out.write(JSONObject.toJSONString(objectSimpleResult).getBytes("UTF-8"));
                out.flush();
            }
            return false;
        }
    }
}
