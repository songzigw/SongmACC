package songm.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础响应信息。
 * 
 * @author zhangsong
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6785550014828467801L;

    /**
     * true成功 false失败
     */
    private Boolean succeed;

    /** 如果执行失败，有失败错误码 */
    private String errorCode;

    /** 如果执行失败，有失败原因描述 */
    private String errorDesc;

    /** 记录执行的时间 */
    private Date exeTime;
    
    private T data;

    public Result() {
        this.succeed = true;
        this.exeTime = new Date();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getExeTime() {
        return exeTime;
    }

    public Boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.succeed = false;
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.succeed = false;
        this.errorDesc = errorDesc;
    }

    public Boolean getSucceed() {
        return succeed;
    }

}
