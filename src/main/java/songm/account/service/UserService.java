package songm.account.service;

import songm.account.entity.PageInfo;
import songm.account.entity.User;

/**
 * 用户业务逻辑
 * 
 * @author 张松
 * 
 */
public interface UserService {

    /**
     * 注册用户信息
     * 
     * @param account
     * @param password
     * @param nick
     * @return
     * @throws ServiceException
     */
    public User register(String account, String password, String nick)
            throws ServiceException;

    /**
     * 验证用户登入信息
     * 
     * @param account
     * @param password
     */
    public User checkLogin(String account, String password)
            throws ServiceException;

    /**
     * 获取注册用户信息
     * 
     * @param nick
     * @param currPage
     * @param pageSize
     * @return
     */
    public PageInfo<User> getUserList(String nick, int currPage,
            int pageSize);

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
     * @param birthdayYear
     * @param birthdayMonth
     * @param birthdayDay
     * @param summary
     */
    public void editUserBasic(Long userId, String nick, String realName,
            Integer gender, int birthdayYear, int birthdayMonth, int birthdayDay,
            String summary) throws ServiceException;

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

}
