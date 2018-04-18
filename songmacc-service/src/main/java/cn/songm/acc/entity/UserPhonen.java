package cn.songm.acc.entity;

import cn.songm.common.beans.Entity;

/**
 * 用户电话号码
 * 
 * @author zhangsong
 *
 */
public class UserPhonen extends Entity {

	private static final long serialVersionUID = 5451469972090415555L;

	/** 用户ID */
	private Long userId;
	/** 电话号码 */
	private String phonen;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhonen() {
		return phonen;
	}

	public void setPhonen(String phonen) {
		this.phonen = phonen;
	}

}
