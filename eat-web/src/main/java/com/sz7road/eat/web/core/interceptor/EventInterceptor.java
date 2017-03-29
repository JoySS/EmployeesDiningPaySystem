package com.sz7road.eat.web.core.interceptor;

import com.sz7road.eat.api.core.Constants;
import com.sz7road.eat.api.utils.DateUtil;
import com.sz7road.eat.web.utils.WebUtil;
import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志拦截器
 *
 * @author Panda.Z
 */
public class EventInterceptor extends BaseInterceptor {

    protected static Logger logger = LoggerFactory.getLogger(EventInterceptor.class);

    private final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    private static UASparser uasParser = null;

    // 初始化uasParser对象
    static {
        try {
            uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 开始时间（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(System.currentTimeMillis());
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ApiOperation apiOperation = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
            if (null != apiOperation){
                request.setAttribute(Constants.OPERATION_NAME, apiOperation.value());
            }
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
        }
        return super.preHandle(request, response, handler);
    }

    public void afterCompletion(final HttpServletRequest request, HttpServletResponse response, Object handler,
                                final Exception ex) throws Exception {
        final Long startTime = startTimeThreadLocal.get();
        final Long endTime = System.currentTimeMillis();
        // 保存日志

        String userAgent = null;
        try {
            UserAgentInfo userAgentInfo = uasParser.parse(request.getHeader("user-agent"));
            userAgent = userAgentInfo.getOsName() + " " + userAgentInfo.getType() + " " + userAgentInfo.getUaName();
        } catch (IOException e) {
            logger.error("{}", e.getMessage(), e);
        }
        String path = request.getServletPath();
        if (!path.contains("/read/") && !path.contains("/unauthorized") && !path.contains("/forbidden")) {
            if (logger.isDebugEnabled()){
                logger.debug("开始时间: {}; 结束时间: {}; 耗时: {}s; URI: {}; ",
                        DateUtil.format(startTime, "HH:mm:ss.SSS"),
                        DateUtil.format(endTime, "HH:mm:ss.SSS"),
                        (endTime - startTime) / 1000.00,
                        request.getRequestURI());
            }
            logger.info("开始时间: {}; 结束时间: {}; 耗时: {}s; URI: {}; ",
                    DateUtil.format(startTime, "HH:mm:ss.SSS"),
                    DateUtil.format(endTime, "HH:mm:ss.SSS"),
                    (endTime - startTime) / 1000.00,
                    request.getRequestURI());
        } else if (path.contains("/unauthorized")) {
            logger.warn("用户[{}]没有登录", WebUtil.getHost(request) + "@" + userAgent);
        } else if (path.contains("/forbidden")) {
            logger.warn("用户[{}]没有权限", WebUtil.getCurrentUser() + "@" + WebUtil.getHost(request) + "@" + userAgent);
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
