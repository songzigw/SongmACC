package cn.songm.acc.webapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.common.beans.Result;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.CodeUtils;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.utils.RandomCode;
import cn.songm.common.web.Browser;

@Controller
@RequestMapping("/")
public class LoginController extends BaseAccController {

	/**
	 * 用户登入
	 * @param account
	 * @param password
	 * @param vcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login.json")
    @ResponseBody
    public Result<User> login(String account, String password, String vcode,
            HttpServletRequest request) {
        Result<User> result = new Result<User>();
        
        String sesId = Browser.getSessionId(request);
        String sysVcode = (String) ssoService.getAttr(sesId, ITEM_KEY_VCODE);
        
        User user = null;
        try {
            user = userService.checkLogin(account, password, sysVcode, vcode);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
            return result;
        } finally {
        	ssoService.delAttr(sesId, ITEM_KEY_VCODE);
		}

        // 登入成功
        ssoService.login(sesId, user.getUserId().toString(),
                JsonUtils.getInstance().toJson(user));
        result.setData(user);
        return result; 
    }
	
	/**
	 * 用户注册
	 * @param account
	 * @param password
	 * @param nick
	 * @param vcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "register.json")
    @ResponseBody
    public Result<Object> register(
    		@RequestParam(name = "account")
    		String account,
    		@RequestParam(name = "password")
    		String password,
    		@RequestParam(name = "nick")
            String nick,
            @RequestParam(name = "vcode")
            String vcode, HttpServletRequest request) {
        Result<Object> result = new Result<Object>();
        
        String sesId = Browser.getSessionId(request);
        String sysVcode = (String) ssoService.getAttr(sesId, ITEM_KEY_VCODE);

        try {
            userService.register(account, password, nick, sysVcode, vcode);
        } catch (ServiceException e) {
            result.setErrorCode(e.getErrCode());
            result.setErrorDesc(e.getErrNotice());
            return result;
        } finally {
        	ssoService.delAttr(sesId, ITEM_KEY_VCODE);
		}

        return result;
    }
	
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping(value = "logout.json")
	@ResponseBody
    public Result<Object> logout() {
        Result<Object> result = new Result<Object>();

        HttpServletRequest req = this.getRequest();
        ssoService.logout(Browser.getSessionId(req));

        return result;
    }
	/**
	 * 验证码图片
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "vcode", method = RequestMethod.GET)
    public ModelAndView validateCode(HttpServletRequest request) {
        RandomCode rcode = new RandomCode();
        ssoService.setAttr(Browser.getSessionId(request), ITEM_KEY_VCODE, rcode.getCode());
        
        ModelAndView mv = new ModelAndView("/vcode");
        return mv.addObject("rcode", rcode);
    }
	
	@RequestMapping(value = "vcode/base64", method = RequestMethod.GET)
	@ResponseBody
	public String validateCodeBase64(HttpServletRequest request) throws IOException {
        RandomCode rcode = new RandomCode();
        ssoService.setAttr(Browser.getSessionId(request), ITEM_KEY_VCODE, rcode.getCode());
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        rcode.getRandcode(os);
        
        return CodeUtils.encode64(os.toByteArray());
    }
	
	public static final String ITEM_KEY_VCODE = "item_key_vcode";
}
