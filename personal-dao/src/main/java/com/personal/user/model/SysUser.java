package com.personal.user.model;

import java.io.Serializable;

/**
 * 系统用户信息
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月13日/下午7:46:58
 * 
 */
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String userCode;
	private String passWord;
	private String salt;
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
