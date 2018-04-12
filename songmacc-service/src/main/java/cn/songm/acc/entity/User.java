package cn.songm.acc.entity;

import cn.songm.common.beans.Entity;

/**
 * 用户信息
 * 
 * @author zhangsong
 * 
 */
public class User extends Entity implements java.io.Serializable {

    private static final long serialVersionUID = 8776550770295897410L;

    /** 用户ID */
    private Long userId;
    /** 账号 */
    private String account;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String nick;
    /** 真实姓名 */
    private String realName;
    /** 头像服务器 */
    private String avatarServer;
    /** 原始头像路径 */
    private String avatarOldPath;
    /** 最终头像路径 */
    private String avatarPath;
    /** 最终头像 */
    private String avatar;
    /** 性别 */
    private Integer gender;
    /** 生日-年 */
    private Integer birthYear;
    /** 生日-月 */
    private Integer birthMonth;
    /** 生日-日 */
    private Integer birthDay;
    /** 简介 */
    private String summary;

    public User() {
        super();
    }

    public User(Long userId) {
        this();
        this.userId = userId;
    }

    /** 用户ID */
    public Long getUserId() {
        return userId;
    }

    /** 用户ID */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** 账号 */
    public String getAccount() {
        return account;
    }

    /** 账号 */
    public void setAccount(String account) {
        this.account = account;
    }

    /** 密码 */
    public String getPassword() {
        return password;
    }

    /** 密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatarServer() {
		return avatarServer;
	}

	public void setAvatarServer(String avatarServer) {
		this.avatarServer = avatarServer;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getAvatarOldPath() {
		return avatarOldPath;
	}

	public void setAvatarOldPath(String avatarOldPath) {
		this.avatarOldPath = avatarOldPath;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null) return false;
        } else if (!userId.equals(other.userId)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sBui = new StringBuilder();
        sBui.append("User [");
        sBui.append(super.toString());
        sBui.append(", userId=").append(userId);
        sBui.append(", account=").append(account);
        sBui.append(", password=").append(password);
        sBui.append(", nick=").append(nick);
        sBui.append(", realName=").append(realName);
        sBui.append(", avatarServer=").append(avatarServer);
        sBui.append(", avatarPath=").append(avatarPath);
        sBui.append(", avatarOldPath=").append(avatarOldPath);
        sBui.append(", avatar=").append(avatar);
        sBui.append(", gender=").append(gender);
        sBui.append(", birthYear=").append(birthYear);
        sBui.append(", birthMonth=").append(birthMonth);
        sBui.append(", birthDay=").append(birthDay);
        sBui.append(", summary=").append(summary);
        sBui.append("]");
        return sBui.toString();
    }

}
