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
import cn.songm.acc.service.ServiceConfig;
import cn.songm.acc.service.UserService;
import cn.songm.common.dao.SeqBuild;
import cn.songm.common.dao.SeqTableDao;
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
    @Autowired
    private SeqTableDao seqTableDao;
    @Autowired
    private ServiceConfig serviceConfig;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String account, String password, String nick)
            throws ServiceException {
        if (StringUtils.isEmptyOrNull(password)
                || StringUtils.isEmptyOrNull(nick)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.isEmptyOrNull(account)) {
            // 验证账号格式
            if (!StringUtils.matches(account, "^\\w{5,50}$")) {
                throw new ServiceException(UserError.ACC_105, "账号格式错误");
            }
            account = account.toLowerCase();
            // 验证账号中的关键字
            verifyAccKey(account);
            // 验证账号是否重复
            if (this.verifyAccountRep(account)) {
                throw new ServiceException(UserError.ACC_101, "账号已经被使用");
            }
        }

        // 验证昵称格式
        if (!StringUtils.matches(nick, "^.{1,12}$")) {
            throw new ServiceException(UserError.ACC_106, "昵称格式错误");
        }
        // 验证密码格式
        if (!StringUtils.matches(password, "^.{6,20}$")) {
            throw new ServiceException(UserError.ACC_107, "密码格式错误");
        }
        // 验证昵称中的关键字
        verifyNicKey(nick);
        // 验证昵称是否重复
        if (this.verifyNickRep(nick)) {
            throw new ServiceException(UserError.ACC_102, "昵称已经被使用");
        }

        User user = new User();
        user.setAccount(account);
        // 加密处理
        password = CodeUtils.md5(password);
        user.setPassword(password);
        user.setNick(nick);
        user.setVersion(serviceConfig.getVersion());
        return this.addUser(user);
    }

    private void verifyAccKey(String word) throws ServiceException {
        if (word.indexOf(KEY_ACC) > -1)
            throw new ServiceException(UserError.ACC_113, "账号中不能包含关键字");
    }

    private void verifyNicKey(String word) throws ServiceException {
        if (word.indexOf(KEY_NIC) > -1)
            throw new ServiceException(UserError.ACC_114, "昵称中不能包含关键字");
    }

    private User addUser(User user) {
        user.setUserId(Long.valueOf(getSeqNextValue(UserDao.SEQ_NAME)));
        userDao.insert(user);
        return user;
    }

    @Override
    public User checkLogin(String account, String password)
            throws ServiceException {
        password = CodeUtils.md5(password);
        String pwd = userDao.queryPwdByAccount(account);
        if (pwd == null || !password.equals(pwd)) {
            throw new ServiceException(UserError.ACC_109, "用户账号或者密码错误");
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
        return userDao.getById(userId);
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
            throw new ServiceException(UserError.ACC_107, "密码格式错误");
        }

        // 密码MD5加密
        oldPsw = CodeUtils.md5(oldPsw);
        User user = this.getUserById(userId);
        if (!user.getPassword().equals(oldPsw)) {
            throw new ServiceException(UserError.ACC_103, "用户原始密码错误");
        }

        newPsw = CodeUtils.md5(newPsw);
        userDao.updatePsw(userId, newPsw);
    }

    @Override
    public void editUserPhoto(Long userId, String avatar) {
        userDao.updatePhoto(userId, avatar);
    }

    @Override
    public void editUserBasic(Long userId, String nick, String userName,
            Integer gender, int birthdayYear, int birthdayMonth,
            int birthdayDay, String summary) throws ServiceException {
        // 数据不能为空
        if (userId <= 0 || nick.trim().equals("")
                || userName.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        // 验证昵称格式
        if (!StringUtils.matches(nick, "^.{1,12}$")) {
            throw new ServiceException(UserError.ACC_106, "昵称格式错误");
        }
        // 验证生日格式
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(birthdayYear, birthdayMonth - 1, birthdayDay);
        } catch (Exception e) {
            throw new ServiceException(UserError.ACC_104, "生日格式错误", e);
        }
        // 验证昵称
        User user = this.getUserById(userId);
        if (!user.getNick().equals(nick)) {
            // 验证昵称中的关键字
            verifyNicKey(nick);
            if (this.verifyNickRep(nick)) {
                throw new ServiceException(UserError.ACC_102, "昵称已经被使用");
            }
        }

        userDao.update(userId, nick, userName, gender, birthdayYear,
                birthdayMonth, birthdayDay, summary);
    }

    @Override
    public void recordReport(UserReport report) {
        userReportDao.insert(report);
    }

    /**
     * 根据序列名称,获取序列值
     */
    @Transactional(rollbackFor = Exception.class)
    public String getSeqNextValue(String seqName) {
        SeqBuild seqBuild = new SeqBuild();
        seqBuild.setSeqName(seqName);
        return seqTableDao.getSeqNextValue(seqBuild);
    }

}
