package cn.songm.acc.dao.impl;

import org.springframework.stereotype.Repository;

import cn.songm.acc.dao.UserLoginDao;
import cn.songm.acc.entity.UserLogin;
import cn.songm.common.dao.BaseDaoImpl;

@Repository("userLoginDao")
public class UserLoginDaoImpl extends BaseDaoImpl<UserLogin>
        implements UserLoginDao {

}
