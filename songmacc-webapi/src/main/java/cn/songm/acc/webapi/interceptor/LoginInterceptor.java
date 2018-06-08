package cn.songm.acc.webapi.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.service.UserError;
import cn.songm.common.beans.Result;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.web.Browser;
import cn.songm.sso.service.SongmSSOService;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")
        		&& request.getHeader("Access-Control-Request-Method") != null) {
        	return true;
        }
    	
    	String userInfo = songmSsoService
                .getUserInfo(Browser.getSessionId(request));
        if (userInfo != null) {
            return true;
        }

        Result<Object> result = new Result<Object>();
        result.setSucceed(false);
        result.setErrorCode(UserError.ACC_115.getErrCode());
        result.setErrorDesc("Session失效");
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
