package com.personal.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.base.until.MD5Security;
import com.personal.user.dao.SysUserDao;
import com.personal.user.model.SysUser;
import com.personal.user.service.UserService;

/**
 * SysUser系统用户逻辑处理类
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月13日/下午7:56:19
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private SysUserDao sysUserDao;
	@Override
	public SysUser authenticat(String userCode, String password)
			throws Exception {
		SysUser user = findUserByUserCode(userCode);
		if (user == null) {
			throw new Exception();
		}
		String dbPassword = user.getPassWord();
		String password_input = MD5Security.encryptOnconLoginPWD(password);// 将输入的密码加密与数据库进行比较
		if (!password_input.equals(dbPassword)) {
			throw new Exception();
		}
		// 认证通过
		return null;
	}

	@Override
	public SysUser findUserByUserCode(String userCode) throws Exception {
		return sysUserDao.getByuserCode(userCode);
	}

}
