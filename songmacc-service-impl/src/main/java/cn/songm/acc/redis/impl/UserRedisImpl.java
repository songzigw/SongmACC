package cn.songm.acc.redis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import cn.songm.acc.entity.User;
import cn.songm.acc.redis.UserRedis;
import cn.songm.common.redis.BaseRedisImpl;
import cn.songm.common.utils.SerializeUtil;

@Repository("userRedis")
public class UserRedisImpl extends BaseRedisImpl<User> implements UserRedis {

    @Override
    public void save(final User user) {
        redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return serialize(user, connection);
            }
        });
    }

    @Override
    public User read(final Long uid) {
        return redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                User user = new User();
                user.setUserId(uid);
                return unserialize(user, connection);
            }
        });
    }

    @Override
    public void delete(final Long uid) {
        super.del(format(H_USER_ID_KEY, uid));
    }

    public static final String H_USER_ID_KEY = "h_user/%d";
    public static final byte[] USER_FIELD_NO = "no".getBytes();
    public static final byte[] USER_FIELD_VERSION = "version".getBytes();
    public static final byte[] USER_FIELD_CREATED = "created".getBytes();
    public static final byte[] USER_FIELD_UPDATED = "updated".getBytes();
    public static final byte[] USER_FIELD_REMARK = "remark".getBytes();
    public static final byte[] USER_FIELD_USERID = "user_id".getBytes();
    public static final byte[] USER_FIELD_ACCOUNT = "account".getBytes();
    public static final byte[] USER_FIELD_PASSWORD = "password".getBytes();
    public static final byte[] USER_FIELD_NICKNAME = "nickname".getBytes();
    public static final byte[] USER_FIELD_REALNAME = "realname".getBytes();
    public static final byte[] USER_FIELD_AVATAR = "avatar".getBytes();
    public static final byte[] USER_FIELD_GENDER = "gender".getBytes();
    public static final byte[] USER_FIELD_BIRTHYEAR = "birthyear".getBytes();
    public static final byte[] USER_FIELD_BIRTHMONTH = "birthmonth".getBytes();
    public static final byte[] USER_FIELD_BIRTHDAY = "birthday".getBytes();
    public static final byte[] USER_FIELD_SUMMARY = "summary".getBytes();
    
    @Override
    public User serialize(User entity, RedisConnection connection) {
        Map<byte[], byte[]> d = new HashMap<byte[], byte[]>();
        d.put(USER_FIELD_NO, SerializeUtil.serialize(entity.getNo()));
        d.put(USER_FIELD_VERSION, SerializeUtil.serialize(entity.getVersion()));
        d.put(USER_FIELD_CREATED, SerializeUtil.serialize(entity.getCreated()));
        d.put(USER_FIELD_UPDATED, SerializeUtil.serialize(entity.getUpdated()));
        d.put(USER_FIELD_REMARK, SerializeUtil.serialize(entity.getRemark()));
        d.put(USER_FIELD_USERID, SerializeUtil.serialize(entity.getUserId()));
        d.put(USER_FIELD_ACCOUNT, SerializeUtil.serialize(entity.getAccount()));
        d.put(USER_FIELD_PASSWORD, SerializeUtil.serialize(entity.getPassword()));
        d.put(USER_FIELD_NICKNAME, SerializeUtil.serialize(entity.getNickname()));
        d.put(USER_FIELD_REALNAME, SerializeUtil.serialize(entity.getRealName()));
        d.put(USER_FIELD_AVATAR, SerializeUtil.serialize(entity.getAvatar()));
        d.put(USER_FIELD_GENDER, SerializeUtil.serialize(entity.getGender()));
        d.put(USER_FIELD_BIRTHYEAR, SerializeUtil.serialize(entity.getBirthYear()));
        d.put(USER_FIELD_BIRTHMONTH, SerializeUtil.serialize(entity.getBirthMonth()));
        d.put(USER_FIELD_BIRTHDAY, SerializeUtil.serialize(entity.getBirthDay()));
        d.put(USER_FIELD_SUMMARY, SerializeUtil.serialize(entity.getSummary()));
        
        String key = format(H_USER_ID_KEY, entity.getUserId());
        connection.hMSet(key.getBytes(), d);

        return entity;
    }

    @Override
    public User unserialize(User entity, RedisConnection connection) {
        String key = format(H_USER_ID_KEY, entity.getUserId());
        if (!connection.exists(key.getBytes())) return null;
        List<byte[]> vals = connection.hMGet(key.getBytes(),
                USER_FIELD_NO,
                USER_FIELD_VERSION,
                USER_FIELD_CREATED,
                USER_FIELD_UPDATED,
                USER_FIELD_REMARK,
                USER_FIELD_USERID,
                USER_FIELD_ACCOUNT,
                USER_FIELD_PASSWORD,
                USER_FIELD_NICKNAME,
                USER_FIELD_REALNAME,
                USER_FIELD_AVATAR,
                USER_FIELD_GENDER,
                USER_FIELD_BIRTHYEAR,
                USER_FIELD_BIRTHMONTH,
                USER_FIELD_BIRTHDAY,
                USER_FIELD_SUMMARY);
        entity.setNo((String)SerializeUtil.unserialize(vals.get(0)));
        entity.setVersion((Integer)SerializeUtil.unserialize(vals.get(1)));
        entity.setCreated((Date)SerializeUtil.unserialize(vals.get(2)));
        entity.setUpdated((Date)SerializeUtil.unserialize(vals.get(3)));
        entity.setRemark((String)SerializeUtil.unserialize(vals.get(4)));
        entity.setUserId((Long)SerializeUtil.unserialize(vals.get(5)));
        entity.setAccount((String)SerializeUtil.unserialize(vals.get(6)));
        entity.setPassword((String)SerializeUtil.unserialize(vals.get(7)));
        entity.setNickname((String)SerializeUtil.unserialize(vals.get(8)));
        entity.setRealName((String)SerializeUtil.unserialize(vals.get(9)));
        entity.setAvatar((String)SerializeUtil.unserialize(vals.get(10)));
        entity.setGender((Integer)SerializeUtil.unserialize(vals.get(11)));
        entity.setBirthYear((Integer)SerializeUtil.unserialize(vals.get(12)));
        entity.setBirthMonth((Integer)SerializeUtil.unserialize(vals.get(13)));
        entity.setBirthDay((Integer)SerializeUtil.unserialize(vals.get(14)));
        entity.setSummary((String)SerializeUtil.unserialize(vals.get(15)));
        return entity;
    }

}
