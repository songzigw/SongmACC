package songm.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import songm.sso.backstage.ISSOClient;
import songm.sso.backstage.SSOException;
import songm.sso.backstage.SSOException.ErrorCode;
import songm.sso.backstage.client.SSOClient;
import songm.sso.backstage.entity.Backstage;
import songm.sso.backstage.event.ConnectionListener;

@Component("ssoAuth")
public class SSOAuth {

private static final Logger LOG = LoggerFactory.getLogger(SSOAuth.class);
    
    private ISSOClient ssoClient;
    
    @Value("${songm.sso.key}")
    private String key;
    @Value("${songm.sso.secret}")
    private String secret;
    
    @Value("${songm.sso.ip}")
    private String ip;
    @Value("${songm.sso.port}")
    private int port;

    public SSOAuth() {
        ssoClient = SSOClient.init(ip, port);
        ssoClient.addListener(new ConnectionListener() {
            @Override
            public void onDisconnected(ErrorCode errorCode) {
                System.out.println("===============Disconnected" + errorCode.name());
            }
            
            @Override
            public void onConnecting() {
                System.out.println("===============Connection");
            }
            
            @Override
            public void onConnected(Backstage backstage) {
                System.out.println("===============Connected" + backstage.getBackId());
            }
        });
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
