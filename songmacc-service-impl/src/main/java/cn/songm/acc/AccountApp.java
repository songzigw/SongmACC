package cn.songm.acc;

import cn.songm.common.service.AppBoot;

/**
 * 统一账号应用服务
 * 
 * @author zhangsong
 *
 */
public class AccountApp {

    public static void main(String[] args) {
    	JsonUtilsInit.initialization();
    	AppBoot.start("application-acc.xml", args);
    }
}
