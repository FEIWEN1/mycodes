package com.personal.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.user.service.UserService;

/**
 * 有关用户操作的controller
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月13日/下午8:19:17
 * 
 */
@Controller
@RequestMapping(value = { "/user/space"})
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping(value = { "/login" })
	public String login(HttpSession session, String userCode, String password)
			throws Exception {
		System.out.println("cs");
		return null;
	}

}
