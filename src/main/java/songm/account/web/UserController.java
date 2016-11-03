package songm.account.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import songm.account.entity.Result;
import songm.account.entity.User;
import songm.account.service.SSOAuthServer;
import songm.account.service.ServiceException;
import songm.account.service.UserService;
import songm.account.utils.JsonUtils;
import songm.sso.backstage.SSOException;

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
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login";
    }

    @RequestMapping(value = "login_check", method = RequestMethod.POST)
    public ModelAndView login(String account, String password) throws SSOException {
        Result<Object> result = new Result<Object>();

        User user = null;
        try {
            user = userService.checkLogin(account, password);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrorCode().name());
            result.setErrorDesc(e.getDescription());
        }

        if (result.isSucceed()) {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            SSOAuthUtil saUtil = new SSOAuthUtil(req, resp, ssoAuthServer);
            ssoAuthServer.getSSOClient().login(saUtil.getSessionId(), user.getUserId().toString(),
                    JsonUtils.toJson(user, user.getClass()));
        }

        ModelAndView mv = new ModelAndView("/data");
        mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
        return mv;
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
            result.setErrorCode(e.getErrorCode().name());
            result.setErrorDesc(e.getDescription());
        }

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
