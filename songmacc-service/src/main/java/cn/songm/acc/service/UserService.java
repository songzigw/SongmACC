package cn.songm.acc.service;

import cn.songm.acc.entity.User;
import cn.songm.acc.entity.UserReport;
import cn.songm.common.service.ServiceException;

/**
 * 用户业务逻辑
 * 
 * @author zhangsong
 *
 */
public interface UserService {

    /**
     * 注册用户信息
     * 
     * @param account
     * @param password
     * @param nick
     * @param sysVcode
     * @param vcode
     * @return
     * @throws ServiceException
     */
    public User register(String account, String password, String nick, String sysVcode, String vcode)
            throws ServiceException;

    /**
     * 验证用户登入信息
     * 
     * @param account
     * @param password
     * @param sysVcode
     * @param vcode
     */
    public User checkLogin(String account, String password, String sysVcode, String vcode)
            throws ServiceException;

    /**
     * 验证账号是否重复
     * 
     * @param account
     * @return
     */
    public boolean verifyAccountRep(String account);

    /**
     * 验证昵称是否重复
     * 
     * @param nick
     * @return
     */
    public boolean verifyNickRep(String nick);

    /**
     * 根据主键获取
     * 
     * @param userId
     * @return
     */
    public User getUserById(Long userId);

    /**
     * 根据ID获取用户隐私
     * 
     * @param userId
     * @return
     */
    public User getUserPrivacyById(long userId);

    /**
     * 根据账号获取用户隐私
     * 
     * @param account
     * @return
     */
    public User getUserPrivacyByAccount(String account);

    /**
     * 编辑用户基本信息
     * 
     * @param userId
     * @param nick
     * @param realName
     * @param gender
     * @param birthYear
     * @param birthMonth
     * @param birthDay
     * @param summary
     */
    public void editUserBasic(Long userId, String nick, String realName,
            Integer gender, int birthYear, int birthMonth,
            int birthDay, String summary) throws ServiceException;

    /**
     * 修改用户头像
     * 
     * @param userId
     * @param avatar
     */
    public void editUserPhoto(Long userId, String avatar);

    /**
     * 修改用户登入密码
     * 
     * @param userId
     * @param oldPsw
     * @param newPsw
     */
    public void editUserPasswore(Long userId, String oldPsw, String newPsw)
            throws ServiceException;
    
    /**
     * 记录用户的报到信息
     * @param report
     */
    public void recordReport(UserReport report);

    public void editUserNick(long userId, String nick) throws ServiceException;

    public void editRealName(long userId, String realName);

    public void editUserGender(long userId, Integer gender);

    public void editUserBirthday(long userId, int birthYear, int birthMonth,
            int birthDay) throws ServiceException;

    public void editSummary(long userId, String summary);
    
}
