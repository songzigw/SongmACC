package cn.songm.acc.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.songm.acc.dao.UserDao;
import cn.songm.acc.entity.User;
import cn.songm.common.dao.BaseDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public static final String SQL_PWD_BY_ACCOUNT = "pwdByAccount";
    
    @Override
    public User queryPrivacyByAccount(String account) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        return super.selectOneByColumn(param);
    }

    @Override
    public User queryPrivacyById(long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return super.selectOneByColumn(param);
    }

    @Override
    public int countByAccount(String account) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        return selectCountByColumn(param).intValue();
    }

    @Override
    public int countByNick(String nick) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nick", nick);
        return selectCountByColumn(param).intValue();
    }

    @Override
    public String queryPwdByAccount(String account) {
        return sessionTemplate.selectOne(getStatement(SQL_PWD_BY_ACCOUNT), account);
    }

    @Override
    public User queryByAccount(String account) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        return super.selectOneByColumn(param);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        User user = new User(userId);
        user.setPassword(password);
        update(user);
    }

    @Override
    public void updatePhoto(long userId, String avatarServer, String avatarOldPath, String avatarPath, String avatar) {
        User user = new User(userId);
        user.setAvatarServer(avatarServer);
        user.setAvatarOldPath(avatarOldPath);
        user.setAvatarPath(avatarPath);
        user.setAvatar(avatar);
        update(user);
    }

    @Override
    public void update(Long userId, String nick, String userName,
            Integer gender, Integer birthYear, Integer birthMonth, Integer birthDay,
            String summary) {
        User user = new User(userId);
        user.setNick(nick);
        user.setRealName(userName);
        user.setGender(gender);
        user.setBirthYear(birthYear);
        user.setBirthMonth(birthMonth);
        user.setBirthDay(birthDay);
        user.setSummary(summary);
        update(user);
    }

	@Override
	public void updateAccount(long userId, String account, String password) {
		User user = new User(userId);
		user.setAccount(account);
        user.setPassword(password);
        update(user);
	}

}
