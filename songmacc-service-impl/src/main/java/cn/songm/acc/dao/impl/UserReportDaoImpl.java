package cn.songm.acc.dao.impl;

import org.springframework.stereotype.Repository;

import cn.songm.acc.dao.UserReportDao;
import cn.songm.acc.entity.UserReport;
import cn.songm.common.dao.BaseDaoImpl;

@Repository("userReportDao")
public class UserReportDaoImpl extends BaseDaoImpl<UserReport>
        implements UserReportDao {

}
