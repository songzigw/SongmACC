package songm.account.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import songm.account.utils.CookieUtils;
import songm.sso.backstage.ISSOClient;
import songm.sso.backstage.entity.Session;

public class SSOAuthUtil {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private SSOAuth ssoAuth;

    public SSOAuthUtil(HttpServletRequest request,
            HttpServletResponse response, SSOAuth ssoAuth) {
        this.request = request;
        this.response = response;
        this.ssoAuth = ssoAuth;
    }

    /**
     * 获取客户端唯一Id
     * 
     * @return
     */
    public String getSessionId() {
        Cookie c = CookieUtils.getCookieByName(request,
                Session.USER_SESSION_KEY);
        String sessionId = null;
        if (c != null) {
            sessionId = c.getValue();
        }
        if (sessionId == null) {
            sessionId = request.getParameter(Session.USER_SESSION_KEY);
        }
        return sessionId;
    }

    public void report() {
        String sessionId = getSessionId();
        ISSOClient ssoClient = ssoAuth.getSSOClient();
        Session session = null;
        CookieUtils.addCookie(response, Session.USER_SESSION_KEY,
                session.getSesId(), 0);
    }
}
