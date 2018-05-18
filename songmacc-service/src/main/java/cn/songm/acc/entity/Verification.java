package cn.songm.acc.entity;

import cn.songm.common.beans.Entity;

/**
 * 数据验证
 * 
 * @author zhangsong
 *
 */
public class Verification extends Entity {

	private static final long serialVersionUID = 2247938690730671398L;

	/** 验证ID */
	private Long verId;
	/** 验证码（摘要算法） */
	private String vcode;
	/** 目标数据（摘要算法） */
	private String target;
	/** 到期时长 */
	private Long expire;
	/** 验证状态 0待验证 1已验证 */
	private Integer status;

	public Long getVerId() {
		return verId;
	}

	public void setVerId(Long verId) {
		this.verId = verId;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
