package songm.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import songm.sso.backstage.ISSOClient;
import songm.sso.backstage.SSOException;
import songm.sso.backstage.client.SSOClient;

public class SSOAuth {

private static final Logger LOG = LoggerFactory.getLogger(SSOAuth.class);
    
    private ISSOClient ssoClient;
    
    @Value("${songm.sso.key}")
    private String key;
    @Value("${songm.sso.secret}")
    private String secret;

    public SSOAuth(String ip, int port) {
        ssoClient = SSOClient.init(ip, port);
    }
    
    public void start() {
        try {
            ssoClient.connect(key, secret);
        } catch (SSOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void stop() {
        ssoClient.disconnect();
    }
    
    public ISSOClient getSSOClient() {
        return ssoClient;
    }
}
