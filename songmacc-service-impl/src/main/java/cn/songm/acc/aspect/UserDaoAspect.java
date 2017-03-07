package cn.songm.acc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.songm.acc.entity.User;
import cn.songm.acc.redis.UserRedis;

@Component("userDaoAspect")
public class UserDaoAspect {

    @Autowired
    private UserRedis userRedis;

    public void insertAfterReturning(JoinPoint point, User result) {
        userRedis.save(result);
    }

    public Object queryByIdBefore(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        Object[] args = point.getArgs();

        try {
            result = userRedis.read((Long) (args[0]));
            if (result == null) {
                result = point.proceed();
            }
        } catch (Throwable e) {
            throw e;
        }

        return result;
    }
}
