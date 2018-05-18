package cn.songm.acc.webapi;

import javax.annotation.Resource;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.web.BaseController;
import cn.songm.sso.service.SongmSSOService;

public class BaseAccController extends BaseController {

	@Resource(name = "songmSsoService")
    protected SongmSSOService songmSsoService;
    @Resource(name = "userService")
    protected UserService userService;
    
    protected String getSessionId() {
    	return Browser.getSessionId(getRequest());
    }
    
    protected User getSessionUser() {
        String sessionId = Browser.getSessionId(this.getRequest());
        String userJson = songmSsoService.getUserInfo(sessionId);
        if (userJson == null) return null;
        return JsonUtils.getInstance().fromJson(userJson, User.class);
    }
    
    protected String getSessionUid() {
    	String sessionId = Browser.getSessionId(this.getRequest());
        return songmSsoService.getUserId(sessionId); 
    }
}
