package cn.songm.acc.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.songm.acc.dao.UserLoginDao;
import cn.songm.acc.entity.User;
import cn.songm.acc.entity.UserLogin;

@Component("userLoginAspect")
public class UserLoginAspect {

    @Autowired
    private UserLoginDao userLoginDao;
    
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args  = point.getArgs();
        String account = (String) args[0];
        
        User result = null;
        boolean flag = false;
        try {
            result = (User) point.proceed();
            flag = true;
        } catch (Throwable e) {
            throw e;
        } finally {
            UserLogin login = new UserLogin();
            login.setAccount(account);
            login.setLflag(flag);
            login.setLtime(new Date());
            if (result != null) {
                login.setUserId(result.getUserId());
            }
            userLoginDao.insert(login);
        }

        return result;
    }
}
