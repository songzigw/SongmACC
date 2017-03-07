package cn.songm.acc.redis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Repository;

import cn.songm.acc.dao.Database.Account;
import cn.songm.acc.dao.Database.UserF;
import cn.songm.acc.entity.User;
import cn.songm.acc.redis.UserRedis;
import cn.songm.common.redis.BaseRedisImpl;

@Repository("userRedis")
public class UserRedisImpl extends BaseRedisImpl implements UserRedis {

    @Override
    public void save(final User user) {
        String key = Account.ACC_USER.name() + ":" + user.getUserId();
        BoundHashOperations<String, String, Object> ops = redisTemplate
                .boundHashOps(key);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put(UserF.USER_ID.name(), user.getUserId());
        data.put(UserF.ACCOUNT.name(), user.getAccount());
        data.put(UserF.PASSWORD.name(), user.getPassword());
        data.put(UserF.NICK.name(), user.getNick());
        data.put(UserF.REAL_NAME.name(), user.getRealName());
        data.put(UserF.CREATED.name(), user.getCreated());
        data.put(UserF.UPDATED.name(), user.getUpdated());
        data.put(UserF.AVATAR.name(), user.getAvatar());
        data.put(UserF.GENDER.name(), user.getGender());
        data.put(UserF.BIRTH_YEAR.name(), user.getBirthYear());
        data.put(UserF.BIRTH_MONTH.name(), user.getBirthMonth());
        data.put(UserF.BIRTH_DAY.name(), user.getBirthDay());
        data.put(UserF.SUMMARY.name(), user.getSummary());

        ops.putAll(data);
    }

    @Override
    public User read(final Long uid) {
        String key = Account.ACC_USER.name() + ":" + uid;
        BoundHashOperations<String, String, Object> ops = redisTemplate
                .boundHashOps(key);

        User user = new User();
        user.setUserId((Long) ops.get(UserF.USER_ID.name()));
        user.setAccount((String) ops.get(UserF.ACCOUNT.name()));
        user.setPassword((String) ops.get(UserF.PASSWORD.name()));
        user.setNick((String) ops.get(UserF.NICK.name()));
        user.setRealName((String) ops.get(UserF.REAL_NAME.name()));
        user.setCreated((Date) ops.get(UserF.CREATED.name()));
        user.setUpdated((Date) ops.get(UserF.UPDATED.name()));
        user.setAvatar((String) ops.get(UserF.AVATAR.name()));
        user.setGender((Integer) ops.get(UserF.GENDER.name()));
        user.setBirthYear((Integer) ops.get(UserF.BIRTH_YEAR.name()));
        user.setBirthMonth((Integer) ops.get(UserF.BIRTH_MONTH.name()));
        user.setBirthDay((Integer) ops.get(UserF.BIRTH_DAY.name()));
        user.setSummary((String) ops.get(UserF.SUMMARY.name()));
        return user;
    }

    @Override
    public void delete(final Long uid) {
        String skey = Account.ACC_USER.name() + ":" + uid;
        Set<String> keys = redisTemplate.keys(skey);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

}
