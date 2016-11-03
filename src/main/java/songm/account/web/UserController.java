package songm.account.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import songm.account.entity.Result;
import songm.account.service.SSOAuthServer;
import songm.account.utils.JsonUtils;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Resource(name = "ssoAuthServer")
    private SSOAuthServer ssoAuthServer;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login";
    }
    
    @RequestMapping(value = "login_check", method = RequestMethod.POST)
    public ModelAndView login(String account, String passord, String vcode) {
        Result<Object> result = new Result<Object>();
        
        ModelAndView mv = new ModelAndView("/data");
        mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
        return mv;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toRegister() {
        return "/register";
    }
    
    @RequestMapping(value = "registry", method = RequestMethod.POST)
    public ModelAndView registry(String account, String passord, String vcode) {
        Result<Object> result = new Result<Object>();
        
        ModelAndView mv = new ModelAndView("/data");
        mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
        return mv;
    }
    
    @RequestMapping(value = "vcode", method = RequestMethod.GET)
    public ModelAndView validateCode() {
        ModelAndView mv = new ModelAndView("/vcode");
        mv.addObject("ssoAuthServer", ssoAuthServer);
        return mv;
    }
}
