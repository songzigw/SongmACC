package cn.songm.acc.service;

import cn.songm.acc.entity.User;
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
	 * @param nickname
	 * @param sysVcode
	 * @param vcode
	 * @return
	 * @throws ServiceException
	 */
	public User register(String account, String password, String nickname, String sysVcode, String vcode)
			throws ServiceException;

	/**
	 * 验证用户登入信息
	 * 
	 * @param account
	 * @param password
	 * @param sysVcode
	 * @param vcode
	 */
	public User checkLogin(String account, String password, String sysVcode, String vcode) throws ServiceException;

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
	 * @param nickname
	 * @return
	 */
	public boolean verifyNickRep(String nickname);

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
	 * @param nickname
	 * @param realName
	 * @param gender
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @param summary
	 */
	public void editUserBasic(long userId, String nickname, String realName, Integer gender, Integer birthYear,
			Integer birthMonth, Integer birthDay, String summary) throws ServiceException;

	public void editUserAccount(long userId, String account, String password) throws ServiceException;

	/**
	 * 修改用户头像
	 * 
	 * @param userId
	 * @param avatarServer
	 *            头像服务器
	 * @param avatarPath
	 *            最终头像路径
	 */
	public void editUserPhoto(long userId, String avatarServer, String avatarPath);

	/**
	 * 修改用户登入密码
	 * 
	 * @param userId
	 * @param oldPsw
	 * @param newPsw
	 */
	public void editUserPassword(long userId, String oldPsw, String newPsw) throws ServiceException;

	public void editNickname(long userId, String nickname) throws ServiceException;

	public void editRealName(long userId, String realName);

	public void editUserGender(long userId, Integer gender);

	public void editUserBirthday(long userId, int birthYear, int birthMonth, int birthDay) throws ServiceException;

	public void editSummary(long userId, String summary);

}
