
package cn.songm.acc.dao;

import cn.songm.common.dao.Fields;
import cn.songm.common.dao.Tables;

public interface Database {

    public static enum Account implements Tables {
        /** 用户表 */
        ACC_USER,
        /** 用的登入日志 */
        ACC_USER_LOGIN,
        /** 用户报到日志 */
        ACC_USER_REPORT,
    }
    
    /**
     * 用户表的字段
     * 
     * @author 张松
     * 
     */
    public static enum UserF implements Fields {
        /** 用户ID */
        USER_ID,
        /** 账号 */
        ACCOUNT,
        /** 密码 */
        PASSWORD,
        /** 昵称 */
        NICK,
        /** 真实姓名 */
        REAL_NAME,
        /** 添加时间 */
        CREATED,
        /** 修改时间 */
        UPDATED,
        /** 头像路径 */
        AVATAR,
        /** 性别 */
        GENDER,
        /** 生日-年 */
        BIRTH_YEAR,
        /** 生日-月 */
        BIRTH_MONTH,
        /** 生日-日 */
        BIRTH_DAY,
        /** 简介 */
        SUMMARY,
        
        /** 电子邮箱 */
        //EMAIL,
        /** ENABLE(激活的) */
        //EN_EMAIL,
        /** 电子邮件激活验证码 */
        //EM_IC_ID,
    }

    /**
     * 用户登入日志
     * 
     * @author zhangsong
     *
     */
    public static enum UserLoginF implements Fields {
        /** Account */
        ACCOUNT,
        /** 用户ID */
        USER_ID,
        /** 用户登入时间 */
        LTIME,
        /** 记录创建时间 */
        CREATED,
        /** 登入失败/成功标示 */
        LFLAG
    }
    
    /**
     * 用户报到日志
     * @author zhangsong
     *
     */
    public static enum UserReportF implements Fields {
        /** Session ID */
        SES_ID,
        /** 用户ID */
        USER_ID,
        /** 用户报到时间 */
        RTIME,
        /** 记录创建时间 */
        CREATED
    }
}
