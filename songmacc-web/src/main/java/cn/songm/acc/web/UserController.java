package cn.songm.acc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/member/")
public class UserController extends AccBaseController {

    @RequestMapping(value = "user/*")
    public ModelAndView toUserEdit() {
    	ModelAndView mv = getGlobalData();
    	mv.setViewName("/user_edit");
        return mv;
    }
    
    @RequestMapping(value = "user/edit.json", method=RequestMethod.POST)
    @ResponseBody
    public Result<Object> eidtUser(
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
    		@RequestParam(name = "real_day", required = false)
    		Integer birthDay,
    		@RequestParam(name = "summary", required = false)
    		String summary) {
        Result<Object> result = new Result<Object>();
        User u = this.getSessionUser();
        try {
			userService.editUserBasic(u.getUserId(), nick, realName, gender, birthYear, birthMonth, birthDay, summary);
		} catch (ServiceException e) {
			result.setErrorCode(e.getErrCode());
			result.setErrorDesc(e.getErrDesc());
		}
        return result;
    }
    
    @RequestMapping(value = "user/online.json", method=RequestMethod.POST)
    @ResponseBody
    public Result<User> getOnlineUser() {
        Result<User> result = new Result<>();
        result.setData(getSessionUser());
        return result;
    }
}
