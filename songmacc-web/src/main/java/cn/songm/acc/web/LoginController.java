package cn.songm.acc.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.utils.RandomCode;

@Controller
public class LoginController extends AccBaseController {

    @RequestMapping(value = "login")
    public String toLogin() {
        return "/login";
    }
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toRegister() {
        return "/register";
    }
    
    @RequestMapping(value = "login.json", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(String account, String password, String vcode,
            HttpServletRequest request) {
        Result<Object> result = new Result<Object>();
        
        String sessionId = Browser.getSessionId(request);
        String sysVcode = songmSsoService.getValidateCode(sessionId);
        
        User user = null;
        try {
            user = userService.checkLogin(account, password, sysVcode, vcode);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
            return result;
        }

        songmSsoService.login(sessionId,
                user.getUserId().toString(),
                JsonUtils.getInstance().toJson(user));
        return result; 
    }
    
    @RequestMapping(value = "register.json", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> register(String account, String password,
            String nick, String vcode, HttpServletRequest request) {
        Result<Object> result = new Result<Object>();
        
        String sessionId = Browser.getSessionId(request);
        String sysVcode = songmSsoService.getValidateCode(sessionId);

        try {
            userService.register(account, password, nick, sysVcode, vcode);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
            return result;
        }

        return result;
    }
    
    @RequestMapping(value = "vcode", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView validateCode(HttpServletRequest request) {
        RandomCode rcode = new RandomCode();
        songmSsoService.setValidateCode(Browser.getSessionId(request), rcode.getCode());
        
        ModelAndView mv = new ModelAndView("/vcode");
        return mv.addObject("rcode", rcode);
    }
}
