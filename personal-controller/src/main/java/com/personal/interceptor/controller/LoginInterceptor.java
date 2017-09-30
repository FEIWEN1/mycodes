package com.personal.interceptor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用户登录拦截器
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月13日/下午8:41:00
 * 
 */
//public class LoginInterceptor extends HandlerInterceptorAdapter{
public class LoginInterceptor implements HandlerInterceptor{
	Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//得到请求的url
		String url=request.getRequestURI();
		logger.info("请求的url是：{}", url);
		//判断是否公开的地址
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			logger.info("进入postHandle方法");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("进入afterCompletion方法");
	}

}
