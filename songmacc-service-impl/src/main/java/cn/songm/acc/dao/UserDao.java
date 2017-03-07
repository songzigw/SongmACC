package cn.songm.acc.dao;

import cn.songm.acc.entity.User;
import cn.songm.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {

    public static final String SEQ_NAME = "acc_user_id_seq";

    User queryPrivacyByAccount(String account);

    int countByAccount(String account);

    int countByNick(String nick);

    String queryPwdByAccount(String account);

    User queryByAccount(String account);

    User queryPrivacyById(long userId);

    void updatePsw(Long userId, String newPsw);

    void updatePhoto(Long userId, String avatar);

    void update(Long userId, String nick, String userName, Integer gender,
            int birthdayYear, int birthdayMonth, int birthdayDay,
            String summary);

    int insert(User user);

    User getById(Object userId);

}
