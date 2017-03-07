package cn.songm.acc.entity;

import java.util.Date;

import cn.songm.common.beans.Entity;

/**
 * 用户报到日志
 * 
 * @author zhangsong
 *
 */
public class UserReport extends Entity implements java.io.Serializable {

    private static final long serialVersionUID = -5114957638327151193L;
    
    /** Session ID */
    private String sesId;
    
    /** 用户ID */
    private Long userId;

    /** 用户报到时间 */
    private Date rtime;
    
    public String getSesId() {
        return sesId;
    }

    public void setSesId(String sesId) {
        this.sesId = sesId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

}
