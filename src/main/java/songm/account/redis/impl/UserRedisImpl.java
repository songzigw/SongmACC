package songm.account.redis.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import songm.account.bean.User;
import songm.account.redis.UserRedis;

@Repository("userDao")
public class UserRedisImpl implements UserRedis{

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Override
    public void save(final User user) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(
                        redisTemplate.getStringSerializer().serialize(
                                "user.uid." + user.getUserId()),
                        redisTemplate.getStringSerializer().serialize(
                                user.getAccount()));
                return null;
            }
        });
    }

    @Override
    public User read(final String uid) {
        return redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize(
                        "user.uid." + uid);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String address = redisTemplate.getStringSerializer()
                            .deserialize(value);
                    
                    User user = new User();
                    user.setAccount(address);
                    //user.setUserId(uid);
                    return user;
                }
                return null;
            }
        });
    }

    @Override
    public void delete(final String uid) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) {
                connection.del(redisTemplate.getStringSerializer().serialize(
                        "user.uid." + uid));
                return null;
            }
        });
    }
    
}

