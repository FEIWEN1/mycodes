package com.personal.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.base.exception.CustomException;

/**
 * 登录处理类
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月21日/下午4:44:13
 * 
 */
@Controller
public class LoginController {
	
	@RequestMapping("/login.action")
	public String login(HttpServletRequest request) throws Exception{
		String exceptionClassName=(String)request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if(UnknownAccountException.class.getName().equals(exceptionClassName)){
				throw new CustomException("账号不存在");
			}
			if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
				throw new CustomException("用户名／密码错误");
			}
			throw new Exception();
		}
		return "login";
	}

}
