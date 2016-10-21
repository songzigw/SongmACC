package songm.account.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import songm.account.service.SSOAuthServer;
import songm.account.utils.CookieUtils;
import songm.account.web.SSOAuthUtil;
import songm.sso.backstage.entity.Session;

public class MustInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MustInterceptor.class);

    @Resource(name = "ssoAuthServer")
    private SSOAuthServer ssoAuthServer;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        LOG.info("URI: {}", request.getRequestURI());

        SSOAuthUtil saUtil = new SSOAuthUtil(request, response, ssoAuthServer);
        Session session = saUtil.report();
        CookieUtils.addCookie(response, Session.USER_SESSION_KEY, session.getSesId(), 0);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {

    }

}
