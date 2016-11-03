package songm.account.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import songm.account.service.SSOAuthServer;

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
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toRegister() {
        return "/register";
    }
    
    @RequestMapping(value = "vcode", method = RequestMethod.GET)
    public ModelAndView validateCode() {
        ModelAndView mv = new ModelAndView("/vcode");
        mv.addObject("ssoAuthServer", ssoAuthServer);
        return mv;
    }
}
