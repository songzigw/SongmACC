package cn.songm.acc.web.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserError;
import cn.songm.acc.web.Browser;
import cn.songm.common.beans.Result;
import cn.songm.common.utils.JsonUtils;
import cn.songm.sso.service.SongmSSOService;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;
    
    public User getUser(HttpServletRequest request) {
        String sessionId = Browser.getSessionId(request);
        String userJson = songmSsoService.getUserInfo(sessionId);
        if (userJson == null) return null;
        return JsonUtils.getInstance().fromJson(userJson, User.class);
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        User user = this.getUser(request);
        if (user != null) return true;
        
        // 判断当前请求是否是ajax请求
        String ajax = request.getParameter("ajax");
        if (ajax == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        
        Result<Object> result = new Result<>();
        result.setSucceed(false);
        result.setErrorCode(UserError.ACC_115.getErrCode());
        result.setErrorDesc("Session failure.");
        response.setContentType("text/plain; charset=UTF-8");
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
