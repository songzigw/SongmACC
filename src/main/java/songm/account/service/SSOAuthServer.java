package songm.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import songm.sso.backstage.SSOClient;
import songm.sso.backstage.SSOException;
import songm.sso.backstage.SSOException.ErrorCode;
import songm.sso.backstage.client.SSOClientImpl;
import songm.sso.backstage.entity.Backstage;
import songm.sso.backstage.event.ConnectionListener;

/**
 * 单点登入授权服务
 * 
 * @author zhangsong
 *
 */
//@Component("ssoAuthServer")
public class SSOAuthServer {

    private static final Logger LOG = LoggerFactory.getLogger(SSOAuthServer.class);

    private SSOClient ssoClient;

    //@Value("${songm.sso.key}")
    private String key;
    //@Value("${songm.sso.secret}")
    private String secret;

    //@Value("${songm.sso.host}")
    private String host;
    //@Value("${songm.sso.port}")
    private int port;

    public void init() {
        ssoClient = SSOClientImpl.init(host, port);
        ssoClient.addListener(new ConnectionListener() {
            @Override
            public void onDisconnected(ErrorCode errorCode) {
                LOG.info("Disconnected >>> SSOServer, ErrorCode: {}", errorCode.name());
            }

            @Override
            public void onConnecting() {
                LOG.info("Connecting >>> SSOServer ");
            }

            @Override
            public void onConnected(Backstage backstage) {
                LOG.info("Connected >>> SSOServer, BackId: {}", backstage.getBackId());
            }
        });

        this.startServer();
    }

    private void startServer() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    do {
                        ssoClient.connect(key, secret);
                    } while (true);
                } catch (SSOException e) {
                    LOG.error(e.getMessage(), e);
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        });
        t.start();
    }

    public SSOClient getSSOClient() {
        return ssoClient;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
