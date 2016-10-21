package songm.account.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import songm.account.service.SSOAuthServer;
import songm.account.utils.CookieUtils;
import songm.sso.backstage.SSOClient;
import songm.sso.backstage.SSOException;
import songm.sso.backstage.entity.Session;

public class SSOAuthUtil {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private SSOAuthServer ssoAuthServer;

    public SSOAuthUtil(HttpServletRequest request,
            HttpServletResponse response, SSOAuthServer ssoAuthServer) {
        this.request = request;
        this.response = response;
        this.ssoAuthServer = ssoAuthServer;
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

    public Session report() throws SSOException {
        String sessionId = getSessionId();
        SSOClient ssoClient = ssoAuthServer.getSSOClient();
        return ssoClient.report(sessionId);
    }
}
