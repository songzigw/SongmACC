package cn.songm.acc.webapi.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.common.web.Browser;
import cn.songm.common.web.CookieUtils;
import cn.songm.monitor.api.Monitor;
import cn.songm.sso.entity.Session;
import cn.songm.sso.service.SSOService;

public class MustInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MustInterceptor.class);

    @Resource(name = "ssoService")
    private SSOService ssoService;
    @Resource(name = "monitor")
    private Monitor monitor;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        LOG.info("URI: {}", request.getRequestURI());
        String sid = Browser.getSessionId(request);
        Session session = ssoService.report(sid);
        User user = (User) ssoService.getUser(sid);
        String userId = null;
        if (user != null) userId = user.getUserId().toString();
        // 写入Cookie
        CookieUtils.addCookie(response, Browser.COOKIE_SESSIONID_KEY, session.getSesId(), 0);
        // 写入消息头
        response.addHeader(Browser.HEADER_SESSIONID_KEY, session.getSesId());
        
        monitor.onRequest(request, response, sid, userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {
    	monitor.onResponse(request, response);
    }

}
