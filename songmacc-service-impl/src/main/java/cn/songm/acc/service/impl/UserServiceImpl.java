package cn.songm.acc.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.songm.acc.dao.UserDao;
import cn.songm.acc.dao.UserReportDao;
import cn.songm.acc.entity.User;
import cn.songm.acc.entity.UserReport;
import cn.songm.acc.service.UserError;
import cn.songm.acc.service.UserService;
import cn.songm.common.service.ServiceException;
import cn.songm.common.utils.CodeUtils;
import cn.songm.common.utils.StringUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static String KEY_ACC = "songm";
    private static String KEY_NIC = "松美";

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserReportDao userReportDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String account, String password, String nick,
            String sysVcode, String vcode) throws ServiceException {
        if (!vcode.equalsIgnoreCase(sysVcode)) {
            throw new ServiceException(UserError.ACC_116.getErrCode(), "验证码错误");
        }
        if (StringUtils.isEmptyOrNull(password)
                || StringUtils.isEmptyOrNull(nick)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.isEmptyOrNull(account)) {
            // 验证账号格式
            if (!StringUtils.matches(account, "^\\w{5,50}$")) {
                throw new ServiceException(UserError.ACC_105.getErrCode(), "账号格式错误");
            }
            account = account.toLowerCase();
            // 验证账号中的关键字
            verifyAccKey(account);
            // 验证账号是否重复
            if (this.verifyAccountRep(account)) {
                throw new ServiceException(UserError.ACC_101.getErrCode(), "账号已经被使用");
            }
        }

        // 验证昵称格式
        if (!StringUtils.matches(nick, "^.{1,12}$")) {
            throw new ServiceException(UserError.ACC_106.getErrCode(), "昵称格式错误");
        }
        // 验证密码格式
        if (!StringUtils.matches(password, "^.{6,20}$")) {
            throw new ServiceException(UserError.ACC_107.getErrCode(), "密码格式错误");
        }
        // 验证昵称中的关键字
        verifyNicKey(nick);
        // 验证昵称是否重复
        if (this.verifyNickRep(nick)) {
            throw new ServiceException(UserError.ACC_102.getErrCode(), "昵称已经被使用");
        }

        User user = new User();
        user.setAccount(account);
        // 加密处理
        password = CodeUtils.md5(password);
        user.setPassword(password);
        user.setNick(nick);
        return this.addUser(user);
    }

    private void verifyAccKey(String word) throws ServiceException {
        if (word.indexOf(KEY_ACC) > -1)
            throw new ServiceException(UserError.ACC_113.getErrCode(), "账号中不能包含关键字");
    }

    private void verifyNicKey(String word) throws ServiceException {
        if (word.indexOf(KEY_NIC) > -1)
            throw new ServiceException(UserError.ACC_114.getErrCode(), "昵称中不能包含关键字");
    }

    private User addUser(User user) {
        user.setUserId(userDao.selectSequence());
        userDao.insert(user);
        return user;
    }

    @Override
    public User checkLogin(String account, String password, String sysVcode, String vcode)
            throws ServiceException {
        if (!vcode.equalsIgnoreCase(sysVcode)) {
            throw new ServiceException(UserError.ACC_116.getErrCode(), "验证码错误");
        }
        
        password = CodeUtils.md5(password);
        String pwd = userDao.queryPwdByAccount(account);
        if (pwd == null || !password.equals(pwd)) {
            throw new ServiceException(UserError.ACC_109.getErrCode(), "用户账号或者密码错误");
        }
        return userDao.queryByAccount(account);
    }

    @Override
    public boolean verifyAccountRep(String account) {
        int n = userDao.countByAccount(account);
        if (n != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyNickRep(String nick) {
        int n = userDao.countByNick(nick);
        if (n != 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.selectOneById(userId);
    }

    @Override
    public User getUserPrivacyById(long userId) {
        return userDao.queryPrivacyById(userId);
    }

    public User getUserPrivacyByAccount(String account) {
        return userDao.queryPrivacyByAccount(account);
    }

    @Override
    public void editUserPasswore(Long userId, String oldPsw, String newPsw)
            throws ServiceException {
        // 验证密码格式
        if (!StringUtils.matches(newPsw, "^.{6,20}$")) {
            throw new ServiceException(UserError.ACC_107.getErrCode(), "密码格式错误");
        }

        // 密码MD5加密
        oldPsw = CodeUtils.md5(oldPsw);
        User user = this.getUserById(userId);
        if (!user.getPassword().equals(oldPsw)) {
            throw new ServiceException(UserError.ACC_103.getErrCode(), "用户原始密码错误");
        }

        newPsw = CodeUtils.md5(newPsw);
        userDao.updatePsw(userId, newPsw);
    }

    @Override
    public void editUserPhoto(long userId, String avatarServer, String avatarOldPath, String avatarPath, String avatar) {
        userDao.updatePhoto(userId, avatarServer, avatarOldPath, avatarPath, avatar);
    }

    @Override
    public void editUserBasic(long userId, String nick, String userName,
            Integer gender, Integer birthYear, Integer birthMonth,
            Integer birthDay, String summary) throws ServiceException {
        // 数据不能为空
        if (userId <= 0 || nick.trim().equals("")
                || userName.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        // 验证昵称格式
        if (!StringUtils.matches(nick, "^.{1,12}$")) {
            throw new ServiceException(UserError.ACC_106.getErrCode(), "昵称格式错误");
        }
        // 验证生日格式
        if (birthYear != null && birthMonth != null && birthDay != null) {
        	Calendar calendar = Calendar.getInstance();
            try {
                calendar.set(birthYear, birthMonth - 1, birthDay);
            } catch (Exception e) {
                throw new ServiceException(UserError.ACC_104.getErrCode(), "生日格式错误", e);
            }
        }
        // 验证昵称
        User user = this.getUserById(userId);
        if (!user.getNick().equals(nick)) {
            // 验证昵称中的关键字
            verifyNicKey(nick);
            if (this.verifyNickRep(nick)) {
                throw new ServiceException(UserError.ACC_102.getErrCode(), "昵称已经被使用");
            }
        }

        userDao.update(userId, nick, userName, gender, birthYear,
                birthMonth, birthDay, summary);
    }

    @Override
    public void recordReport(UserReport report) {
        userReportDao.insert(report);
    }

    @Override
    public void editUserNick(long userId, String nick) throws ServiceException {
        // 数据不能为空
        if (userId <= 0 || nick.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        
        // 验证昵称格式
        if (!StringUtils.matches(nick, "^.{1,12}$")) {
            throw new ServiceException(UserError.ACC_106.getErrCode(), "昵称格式错误");
        }
        
        // 验证昵称
        User user = this.getUserById(userId);
        if (!user.getNick().equals(nick)) {
            // 验证昵称中的关键字
            verifyNicKey(nick);
            if (this.verifyNickRep(nick)) {
                throw new ServiceException(UserError.ACC_102.getErrCode(), "昵称已经被使用");
            }
        }

        userDao.update(userId, nick, null, null, null,
                null, null, null);
    }

    @Override
    public void editRealName(long userId, String realName) {
        if (userId <= 0 || realName.trim().equals("")) {
            throw new IllegalArgumentException();
        }

        userDao.update(userId, null, realName, null, null,
                null, null, null);
    }

    @Override
    public void editUserGender(long userId, Integer gender) {
        if (userId <= 0) {
            throw new IllegalArgumentException();
        }
        
        userDao.update(userId, null, null, gender, null, null, null, null);
        
    }

    @Override
    public void editUserBirthday(long userId, int birthYear, int birthMonth,
            int birthDay) throws ServiceException {
        // 数据不能为空
        if (userId <= 0) {
            throw new IllegalArgumentException();
        }
        
        // 验证生日格式
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(birthYear, birthMonth - 1, birthDay);
        } catch (Exception e) {
            throw new ServiceException(UserError.ACC_104.getErrCode(), "生日格式错误", e);
        }

        userDao.update(userId, null, null, null, birthYear,
                birthMonth, birthDay, null);
    }

    @Override
    public void editSummary(long userId, String summary) {
        if (userId <= 0) {
            throw new IllegalArgumentException();
        }

        userDao.update(userId, null, null, null, null, null, null, summary);
    }

}
