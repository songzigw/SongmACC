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
        return getBy(param);
    }

    @Override
    public User queryPrivacyById(long userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return getBy(param);
    }

    @Override
    public int countByAccount(String account) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        return getCountByColumn(param).intValue();
    }

    @Override
    public int countByNick(String nick) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nick", nick);
        return getCountByColumn(param).intValue();
    }

    @Override
    public String queryPwdByAccount(String account) {
        return sessionTemplate.selectOne(getStatement(SQL_PWD_BY_ACCOUNT), account);
    }

    @Override
    public User queryByAccount(String account) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        return getBy(param);
    }

    @Override
    public void updatePsw(Long userId, String newPsw) {
        User user = new User(userId);
        user.setPassword(newPsw);
        update(user);
    }

    @Override
    public void updatePhoto(Long userId, String avatar) {
        User user = new User(userId);
        user.setAvatar(avatar);
        update(user);
    }

    @Override
    public void update(Long userId, String nick, String userName,
            Integer gender, int birthYear, int birthMonth, int birthDay,
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

}
