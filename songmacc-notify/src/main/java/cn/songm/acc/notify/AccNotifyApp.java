package cn.songm.acc.notify;

import cn.songm.acc.JsonUtilsInit;
import cn.songm.common.service.AppBoot;

/**
 * 账号系统通知服务
 * 
 * @author zhangsong
 *
 */
public class AccNotifyApp {
    public static void main(String[] args) {
        JsonUtilsInit.initialization();
        AppBoot.start("app-acc-notify.xml", args);
    }
}
