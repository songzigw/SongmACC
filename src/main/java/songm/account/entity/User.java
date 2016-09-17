package songm.account.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * 
 * @author 张松
 * 
 */
public class User extends LazyLoadEntity {

    private static final long serialVersionUID = 8776550770295897410L;

    public static final List<Integer> YEARS = new ArrayList<Integer>();
    public static final List<Integer> MONTHS = new ArrayList<Integer>();
    public static final List<Integer> DAYS = new ArrayList<Integer>();

    static {
        int start = 1930;
        for (int i = 0; i < 100; i++) {
            YEARS.add(start + i);
        }
        for (int i = 1; i <= 12; i++) {
            MONTHS.add(i);
        }
        for (int i = 1; i <= 31; i++) {
            DAYS.add(i);
        }
    }

    /** 用户ID */
    private Long userId;
    /** 账号 */
    private String account;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String nickName;
    /** 用户姓名 */
    private String userName;
    /** 添加时间 */
    private Date addTime;
    /** 头像路径 */
    private String photoPath;
    /** 性别 */
    private Integer sex;
    /** 生日-年 */
    private Integer birthYear;
    /** 生日-月 */
    private Integer birthMonth;
    /** 生日-日 */
    private Integer birthDay;
    /** 简介 */
    private String summary;
    
    /** 电子邮箱 */
    private String email;
    /** Enable(激活的) */
    private String enEmail;
    /** 电子邮件激活验证码 */
    private Long emIcId;

    public User() {
        super();
    }

    public User(Long userId) {
        this();
        this.userId = userId;
    }

    public void init() {
        if (this.addTime == null)
            this.addTime = new Date();
    }

    /** 用户ID */
    public Long getUserId() {
        return userId;
    }

    /** 用户ID */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** 用户姓名 */
    public String getUserName() {
        return userName;
    }

    /** 用户姓名 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 添加时间 */
    public Date getAddTime() {
        return addTime;
    }

    /** 添加时间 */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    /** 昵称 */
    public String getNickName() {
        return nickName;
    }

    /** 昵称 */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setEnEmail(String enEmail) {
        this.enEmail = enEmail;
    }

    public void setEmIcId(Long emIcId) {
        this.emIcId = emIcId;
    }

    public String getEnEmail() {
        return enEmail;
    }

    public Long getEmIcId() {
        return emIcId;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
