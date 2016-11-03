package songm.account.web.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import songm.account.entity.Result;
import songm.account.service.SSOAuthServer;
import songm.account.utils.JsonUtils;
import songm.account.web.SSOAuthUtil;
import songm.sso.backstage.entity.Attribute;

public class VcodeInterceptor implements HandlerInterceptor {

    //private static final Logger LOG = LoggerFactory.getLogger(VcodeInterceptor.class);

    @Resource(name = "ssoAuthServer")
    private SSOAuthServer ssoAuthServer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        SSOAuthUtil saUtil = new SSOAuthUtil(request, response, ssoAuthServer);
        Attribute attr = ssoAuthServer.getSSOClient().getAttribute(saUtil.getSessionId(), "V_CODE");
        String vcode = request.getParameter("vcode");
        if (attr != null && vcode.equalsIgnoreCase(attr.getValue())) {
            return true;
        }

        Result<Object> result = new Result<Object>();
        result.setSucceed(false);
        result.setErrorDesc("验证码错误");
        response.setContentType("text/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtils.toJson(result, result.getClass()));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
