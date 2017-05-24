package cn.songm.acc.webapi;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserError;
import cn.songm.acc.service.UserService;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.utils.RandomCode;
import cn.songm.common.utils.StringUtils;
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

    @RequestMapping(value = "user")
    public ModelAndView user(long userId) {
        User user = userService.getUserById(userId);
        user.setAccount(null);
        user.setPassword(null);
        Result<User> result = new Result<User>();
        result.setData(user);
        
        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data", JsonUtils.toJson(result));
    }
    
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
            user.setPassword(password);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
        }

        if (result.isSucceed()) {
            HttpServletRequest req = this.getRequest();
            songmSsoService.login(Browser.getSessionId(req),
                    user.getUserId().toString(),
                    JsonUtils.toJson(user, user.getClass()));
            result.setData(user);
        }

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data",
                JsonUtils.toJson(result, result.getClass()));
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ModelAndView logout() {
        Result<Object> result = new Result<Object>();

        HttpServletRequest req = this.getRequest();
        songmSsoService.logout(Browser.getSessionId(req));

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
    public ModelAndView registry(String account, String password, String nick, String vcode) {
        Result<Object> result = new Result<Object>();

        String code = songmSsoService.getValidateCode(Browser.getSessionId(this.getRequest()));
        if (!vcode.equalsIgnoreCase(code)) {
            result.setErrorCode(UserError.ACC_116.getErrCode());
            result.setErrorDesc("验证码错误");
        } else {
            try {
                userService.register(account, password, nick);
            } catch (ServiceException e) {
                result.setErrorCode(e.getErrCode());
                result.setErrorDesc(e.getErrNotice());
            }
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
        User user = JsonUtils.fromJson(uinfo, User.class);
        Result<User> result = new Result<User>();
        result.setData(user);

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data", JsonUtils.toJson(result, result.getClass()));
    }
    
    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping(value = "member/user/edit", method = RequestMethod.POST)
    public ModelAndView edit(String avatar, String nick, String realName,
            Integer gender, Integer birthYear, Integer birthMonth,
            Integer birthDay, String summary) {
        HttpServletRequest req = this.getRequest();
        // 定义返回的结果
        Result<User> result = new Result<User>();
        
        // 获取在线用户信息
        String uinfo = songmSsoService.getUserInfo(Browser.getSessionId(req));
        User user = JsonUtils.fromJson(uinfo, User.class);

        // 修改头像
        if (!StringUtils.isEmptyOrNull(avatar)) {
            userService.editUserPhoto(user.getUserId(), avatar);
            user.setAvatar(avatar);
        }
        
        // 信息修改
        try {
         // 修改昵称
            if (!StringUtils.isEmptyOrNull(nick)) {
                userService.editUserNick(user.getUserId(), nick);
                user.setNick(nick);
            }
            // 修改RealName
            if (!StringUtils.isEmptyOrNull(realName)) {
                userService.editRealName(user.getUserId(), realName);
                user.setRealName(realName);
            }
            // 修改Gender
            if (gender != null) {
                userService.editUserGender(user.getUserId(), gender);
                user.setGender(gender);
            }
            // 修改BirthdayYear 修改BirthdayMonth 修改BirthdayDay
            if (birthYear != null && birthMonth != null && birthDay != null) {
                userService.editUserBirthday(user.getUserId(), birthYear, birthMonth, birthDay);
                user.setBirthYear(birthYear);
                user.setBirthMonth(birthMonth);
                user.setBirthDay(birthDay);
            }
            // 修改Summary
            if (summary != null) {
                userService.editSummary(user.getUserId(), summary);
                user.setSummary(summary);
            }
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrDesc());
        }
        
        // 修改单点登入服务
        songmSsoService.login(Browser.getSessionId(req),
                user.getUserId().toString(),
                JsonUtils.toJson(user, user.getClass()));
        
        // 返回用户信息
        result.setData(user);

        ModelAndView mv = new ModelAndView("/data");
        return mv.addObject("data", JsonUtils.toJson(result));
    }
}
