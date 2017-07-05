package cn.songm.acc.web.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.web.Browser;
import cn.songm.common.beans.Result;
import cn.songm.common.utils.JsonUtils;
import cn.songm.sso.service.SongmSSOService;

public class VcodeInterceptor implements HandlerInterceptor {

    // private static final Logger LOG =
    // LoggerFactory.getLogger(VcodeInterceptor.class);

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String sVcode = songmSsoService
                .getValidateCode(Browser.getSessionId(request));
        String vcode = request.getParameter("vcode");
        if (sVcode != null && vcode.equalsIgnoreCase(sVcode)) {
            return true;
        }

        Result<Object> result = new Result<Object>();
        result.setSucceed(false);
        result.setErrorDesc("验证码错误");
        response.setContentType("text/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtils.getInstance().toJson(result));

        return false;
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
