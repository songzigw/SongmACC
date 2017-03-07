package cn.songm.acc.dao.impl;

import org.springframework.stereotype.Repository;

import cn.songm.acc.dao.Tab01Dao;
import cn.songm.acc.entity.Tab01;
import cn.songm.common.dao.BaseDaoImpl;

@Repository("tab01Dao")
public class Tab01DaoImpl extends BaseDaoImpl<Tab01> implements Tab01Dao {

    public static final String SQL_UPDATE_COUNT_INCR = "updateCountIncr";
    
    @Override
    public void incr(String no) {
        sessionTemplate.update(getStatement(SQL_UPDATE_COUNT_INCR), no);
    }

}
