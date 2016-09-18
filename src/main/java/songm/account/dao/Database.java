package songm.account.dao;

public interface Database {

    public static enum Account implements Tables {
        /** 用户表 */
        ACC_USER,
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
        NICK_NAME,
        /** 用户姓名 */
        USER_NAME,
        /** 添加时间 */
        ADD_TIME,
        /** 头像路径 */
        PHOTO_PATH,
        /** 性别 */
        SEX,
        /** 电子邮箱 */
        EMAIL,
        /** Enable(激活的) */
        EN_EMAIL,
        /** 电子邮件激活验证码 */
        EM_IC_ID,
        /** 生日-年 */
        BIRTHDAY_YEAR,
        /** 生日-月 */
        BIRTHDAY_MONTH,
        /** 生日-日 */
        BIRTHDAY_DAY,
        /** 简介 */
        SUMMARY,
    }

}
