package com.personal.user.service;

import com.personal.user.model.SysUser;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月13日/下午7:55:46
 * 
 */
public interface UserService {
	/**
	 * 认证用户信息
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 */
	public SysUser authenticat(String userCode,String password)throws Exception;
	public SysUser findUserByUserCode(String userCode)throws Exception;

}
