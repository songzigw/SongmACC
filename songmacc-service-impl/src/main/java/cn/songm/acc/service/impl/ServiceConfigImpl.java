package cn.songm.acc.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.songm.acc.service.ServiceConfig;

@Service("serviceConfig")
public class ServiceConfigImpl implements ServiceConfig {

    @Value("${version}")
    private String version;
    
    @Override
    public String getVersion() {
        return this.version;
    }

}
