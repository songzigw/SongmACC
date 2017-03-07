package cn.songm.acc.redis;

import cn.songm.acc.entity.User;
import cn.songm.common.redis.BaseRedis;

public interface UserRedis extends BaseRedis {
    /**
     * @param uid
     * @param address
     */
    void save(User user);
    /**
     * @param uid
     * @return
     */
    User read(Long uid);
    /**
     * @param uid
     */
    void delete(Long uid);
    
}
