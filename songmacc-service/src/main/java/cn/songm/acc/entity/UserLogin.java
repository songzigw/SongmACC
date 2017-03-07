package cn.songm.acc.entity;

import java.util.Date;

import cn.songm.common.beans.Entity;

/**
 * 
 * 用户登入日志
 * 
 * @author zhangsong
 *
 */
public class UserLogin extends Entity implements java.io.Serializable {

    private static final long serialVersionUID = -7371727080858172976L;
    
    /** Account */
    private String account;
    
    /** 用户ID */
    private Long userId;
    
    /** 用户登入时间 */
    private Date ltime;

    /** 登入失败/成功标示 */
    private Boolean lflag;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public Boolean getLflag() {
        return lflag;
    }

    public void setLflag(Boolean lflag) {
        this.lflag = lflag;
    }
}
