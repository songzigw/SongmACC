package cn.songm.acc.webapi;

import javax.annotation.Resource;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.web.BaseController;
import cn.songm.common.web.Browser;
import cn.songm.sso.service.SSOService;

public class BaseAccController extends BaseController {

	@Resource(name = "ssoService")
    protected SSOService ssoService;
    @Resource(name = "userService")
    protected UserService userService;
    
    protected String getSessionId() {
    	return Browser.getSessionId(getRequest());
    }
    
    protected User getSessionUser() {
        String sesId = Browser.getSessionId(getRequest());
        return (User) ssoService.getUser(sesId);
    }
    
    protected String getSessionUserId() {
    	String sessionId = Browser.getSessionId(getRequest());
        return ssoService.getUserId(sessionId); 
    }
}
