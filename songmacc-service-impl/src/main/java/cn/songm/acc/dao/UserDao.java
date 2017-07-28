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

    void updatePsw(Long userId, String newPsw);

    void updatePhoto(Long userId, String avatar);

    void update(Long userId, String nick, String userName, Integer gender,
            Integer birthYear, Integer birthMonth, Integer birthDay,
            String summary);

    int insert(User user);

}
