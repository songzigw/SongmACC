package songm.account.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import songm.account.web.SSOAuth;
import songm.account.web.SSOAuthUtil;

public class MustInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MustInterceptor.class);

    @Resource(name = "ssoAuth")
    private SSOAuth ssoAuth;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        LOG.info("URI: {}", request.getRequestURI());
        ssoAuth.start();

        SSOAuthUtil saUtil = new SSOAuthUtil(request, response, ssoAuth);
        saUtil.report();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
    }

}
