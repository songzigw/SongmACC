package songm.account.redis;

import songm.account.bean.User;

public interface UserRedis {
    /**
     * @param uid
     * @param address
     */
    void save(User user);
    /**
     * @param uid
     * @return
     */
    User read(String uid);
    /**
     * @param uid
     */
    void delete(String uid);
}
