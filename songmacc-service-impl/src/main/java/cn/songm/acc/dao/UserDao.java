package cn.songm.acc.dao;

import cn.songm.acc.entity.User;
import cn.songm.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {

    User queryPrivacyByAccount(String account);

    int countByAccount(String account);

    int countByNick(String nick);

    String queryPwdByAccount(String account);

    User queryByAccount(String account);

    User queryPrivacyById(long userId);

    void updatePassword(Long userId, String password);

    void updatePhoto(long userId, String avatarServer, String avatarPath);

    void updateAccount(long userId, String account, String password);
    
    void update(Long userId, String nickname, String userName, Integer gender,
            Integer birthYear, Integer birthMonth, Integer birthDay,
            String summary);

    int insert(User user);

}
