package cn.songm.acc.webapi;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.utils.RandomCode;
import cn.songm.common.web.BaseController;
import cn.songm.sso.service.SongmSSOService;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 验证码
     * @return
     */
    @RequestMapping(value = "vcode", method = RequestMethod.GET)
    public ModelAndView validateCode() {
        HttpServletRequest req = this.getRequest();

        RandomCode rcode = new RandomCode();
        songmSsoService.setValidateCode(
                Browser.getSessionId(req), rcode.getCode());

        ModelAndView mv = new ModelAndView("/vcode");
        return mv.addObject("rcode", rcode);
    }
    
    /**
     * 登入检查
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "loginc", method = RequestMethod.POST)
    public ModelAndView loginCheck(String account, String password) {
        Result<Object> result = new Result<Object>();

        User user = null;
        try {
            user = userService.checkLogin(account, password);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
        }

        if (result.isSucceed()) {
            HttpServletRequest req = this.getRequest();
            songmSsoService.login(Browser.getSessionId(req),
                    user.getUserId().toString(),
                    JsonUtils.toJson(user, user.getClass()));
        }

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data",
                JsonUtils.toJson(result, result.getClass()));
    }

    /**
     * 账号注册
     * @param account
     * @param password
     * @param nick
     * @return
     */
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
        return mv.addObject("data",
                JsonUtils.toJson(result, result.getClass()));
    }

    /**
     * 获取登入用户
     * @return
     */
    @RequestMapping(value = "member/user", method = RequestMethod.POST)
    public ModelAndView member() {
        HttpServletRequest req = this.getRequest();

        String uinfo = songmSsoService.getUserInfo(Browser.getSessionId(req));
        // User user = JsonUtils.fromJson(uinfo, User.class);

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data", uinfo);
    }
}
