package cn.songm.acc.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.acc.entity.User;
import cn.songm.acc.service.UserService;
import cn.songm.common.utils.JsonUtils;
import cn.songm.common.web.BaseController;
import cn.songm.sso.service.SongmSSOService;

public class AccBaseController extends BaseController {

	@Value("${user.avatar.default}")
	protected String userAvatarDefault;
	@Value("${user.avatar.upload}")
	protected String userAvatarUpload;
	@Value("${user.avatar.cut}")
	protected String userAvatarCut;
	
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
    
    public ModelAndView getGlobalData() {
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("userAvatarDefault", userAvatarDefault);
    	mv.addObject("userAvatarUpload", userAvatarUpload);
    	mv.addObject("userAvatarCut", userAvatarCut);
    	return mv;
    }
}
