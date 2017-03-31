package cn.songm.acc.webapi;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.utils.RandomCode;
import cn.songm.sso.service.SongmSSOService;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login";
    }

    @RequestMapping(value = "login_check", method = RequestMethod.POST)
    public ModelAndView login(String account, String password) {
        Result<Object> result = new Result<Object>();

        User user = null;
        try {
            user = userService.checkLogin(account, password);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
        }

        if (result.isSucceed()) {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            // HttpServletResponse resp = ((ServletRequestAttributes)
            // RequestContextHolder.getRequestAttributes())
            //        .getResponse();
            songmSsoService.login(Browser.getSessionId(req), user.getUserId().toString(),
                    JsonUtils.toJson(user, user.getClass()));
        }

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toRegister() {
        return "/register";
    }

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    public ModelAndView registry(String account, String password, String nick) {
        Result<Object> result = new Result<Object>();

        try {
            userService.register(account, password, nick);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
        }

        ModelAndView mv = new ModelAndView("/data");
        mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
        return mv;
    }

    @RequestMapping(value = "vcode", method = RequestMethod.GET)
    public ModelAndView validateCode() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        
        RandomCode rcode = new RandomCode();
        songmSsoService.setValidateCode(Browser.getSessionId(req), rcode.getCode());
        
        ModelAndView mv = new ModelAndView("/vcode");
        mv.addObject("rcode", rcode);
        return mv;
    }
}
