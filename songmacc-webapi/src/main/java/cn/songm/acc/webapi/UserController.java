package cn.songm.acc.webapi;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.sso.service.SongmSSOService;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseAccController {

    @Resource(name = "songmSsoService")
    private SongmSSOService songmSsoService;
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "user.json")
    @ResponseBody
    public Result<User> getUser(
    		@RequestParam(name = "user_id")
    		long userId) {
        User user = userService.getUserById(userId);
        user.setAccount(null);
        user.setPassword(null);
        Result<User> result = new Result<User>();
        result.setData(user);
        
        return result;
    }

    /**
     * 获取当前在线用户
     * @return
     */
    @RequestMapping(value = "member/online.json")
    @ResponseBody
    public Result<User> getOnline() {
    	Result<User> result = new Result<>();
        result.setData(getSessionUser());
        return result;
    }
    
    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping(value = "member/user/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public Result<User> editUser(
    		@RequestParam(name = "nick")
    		String nick,
    		@RequestParam(name = "real_name", required = false)
    		String realName,
    		@RequestParam(name = "gender", required = false)
    		Integer gender,
    		@RequestParam(name = "birth_year", required = false)
    		Integer birthYear,
    		@RequestParam(name = "birth_month", required = false)
    		Integer birthMonth,
    		@RequestParam(name = "birth_day", required = false)
    		Integer birthDay,
    		@RequestParam(name = "summary", required = false)
    		String summary) {
    	Result<User> result = new Result<User>();
        User u = this.getSessionUser();
        try {
			userService.editUserBasic(u.getUserId(), nick, realName, gender, birthYear, birthMonth, birthDay, summary);
			u.setNickname(nick);
			u.setRealName(realName);
			u.setGender(gender);
			u.setBirthYear(birthYear);
			u.setBirthMonth(birthMonth);
			u.setBirthDay(birthDay);
			u.setSummary(summary);
			result.setData(u);
		} catch (ServiceException e) {
			result.setErrorCode(e.getErrCode());
			result.setErrorDesc(e.getErrDesc());
		}
        return result;
    }
    
    @RequestMapping(value = "member/user/avatar.json", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> editAvatar(
    		@RequestParam(name = "avatar_server")
    		String avatarServer,
    		@RequestParam(name = "avatar_path")
    		String avatarPath) {
    	Result<Object> result = new Result<Object>();
        User u = this.getSessionUser();
        userService.editUserPhoto(u.getUserId(), avatarServer, avatarPath);
        return result;
    }
    
    @RequestMapping(value = "member/user/account.json", method=RequestMethod.PUT)
    public Result<Object> eidtAccount(
    		@RequestParam(name = "account")
    		String account,
    		@RequestParam(name = "password")
    		String password) {
    	Result<Object> result = new Result<Object>();
    	User u = this.getSessionUser();
    	try {
			userService.editUserAccount(u.getUserId(), account, password);
		} catch (ServiceException e) {
			result.setErrorCode(e.getErrCode());
			result.setErrorDesc(e.getErrDesc());
		}
    	return result;
    }
    
    @RequestMapping(value = "member/user/password.json", method=RequestMethod.PUT)
    public Result<Object> editPassword(
    		@RequestParam(name = "old_pwd")
    		String oldPwd,
    		@RequestParam(name = "new_pwd")
    		String newPwd) {
    	Result<Object> result = new Result<Object>();
    	User u = this.getSessionUser();
    	try {
			userService.editUserPassword(u.getUserId(), oldPwd, newPwd);
		} catch (ServiceException e) {
			result.setErrorCode(e.getErrCode());
			result.setErrorDesc(e.getErrDesc());
		}
    	return result;
    }
}
