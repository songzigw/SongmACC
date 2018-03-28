package cn.songm.acc.web;

import javax.annotation.Resource;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.web.BaseController;
import cn.songm.sso.service.SongmSSOService;

public class AccBaseController extends BaseController {

    @Resource(name = "songmSsoService")
    protected SongmSSOService songmSsoService;
    @Resource(name = "userService")
    protected UserService userService;
    
    protected User getSessionUser() {
        String sessionId = Browser.getSessionId(this.getRequest());
        String userJson = songmSsoService.getUserInfo(sessionId);
        if (userJson == null) return null;
        return JsonUtils.getInstance().fromJson(userJson, User.class);
    }
}
