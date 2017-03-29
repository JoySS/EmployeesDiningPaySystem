package com.sz7road.eat.web.core.filter;

import com.sz7road.eat.web.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CsrfFilter extends AbstractFilter  {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfFilter.class);

    // 白名单
    private List<String> whiteUrls;

    private int _size = 0;

    @Override
    public void init(FilterConfig filterConfig) {
        // 读取文件
        String path = CsrfFilter.class.getResource("/").getFile();
        whiteUrls = readFile(path + "csrfWhite.txt");
        _size = null == whiteUrls ? 0 : whiteUrls.size();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            // 获取请求url地址
            String url = req.getRequestURL().toString();
            String referurl = req.getHeader("Referer");
            LOGGER.info("referurl----->" + referurl);
            if (isWhiteReq(referurl)) {
                chain.doFilter(request, response);
            } else {

                req.getRequestDispatcher("/").forward(req, res);

                // 记录跨站请求日志
                String log = "";
                String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String clientIp = WebUtil.getHost(req);

                log = clientIp + "||" + date + "||" + referurl + "||" + url;
                LOGGER.warn(log);
                return;
            }

        } catch (Exception e) {
            LOGGER.error("doFilter", e);
        }
    }

    @Override
    public void destroy() {
    }
    /*
     * 判断是否是白名单
     */
    private boolean isWhiteReq(String referUrl) {
        if (referUrl == null || "".equals(referUrl) || _size == 0) {
            return true;
        } else {
            String refHost = "";
            referUrl = referUrl.toLowerCase();
            if (referUrl.startsWith("http://")) {
                refHost = referUrl.substring(7);
            } else if (referUrl.startsWith("https://")) {
                refHost = referUrl.substring(8);
            }

            for (String urlTemp : whiteUrls) {
                if (refHost.indexOf(urlTemp.toLowerCase()) > -1) {
                    return true;
                }
            }
        }

        return false;
    }

}
