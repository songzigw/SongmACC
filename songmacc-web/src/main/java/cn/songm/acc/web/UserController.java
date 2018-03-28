package cn.songm.acc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.common.beans.Result;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/member/")
public class UserController extends AccBaseController {

    @RequestMapping(value = "user/edit")
    public ModelAndView toUserEdit() {
        return new ModelAndView("/user_edit");
    }
    
    @RequestMapping(value = "user/edit.json", method=RequestMethod.POST)
    @ResponseBody
    public Result<Object> eidtUser() {
        Result<Object> result = new Result<Object>();
        
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
