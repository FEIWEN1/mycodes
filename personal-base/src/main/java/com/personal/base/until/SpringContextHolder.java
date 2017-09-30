package com.personal.base.until;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * TODO: 以静态变量保存spring ApplicationContext,可在任何地方取出applicationContext
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月22日/下午3:08:17
 * 
 */
public class SpringContextHolder implements ApplicationContextAware,
		DisposableBean {

	private static ApplicationContext applicationContext = null;


	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clear();
	}

	/**
	 * 实现ApplicationContextAware接口，注入context到静态变量
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext=applicationContext;
	}
	
	/**
	 * TODO:取得存储在静态变量中的applicationContext
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		assertContextInjected();
		return applicationContext;
	}
	/**
	 * TODO:通过bean名称从静态变量applicationContext中取得bean，自动类型转换为所赋值的对象类型
	 * 
	 * @param name
	 * @return
	 */
	public static <T> T getBean(String name){
		assertContextInjected();
		return (T)applicationContext.getBean(name);
	}
	/**
	 * TODO:通过bean类型从静态变量applicationContext中取得bean，自动类型转换为所赋值的对象类型
	 * 
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType){
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}
	/**
	 * TODO: 将applicationContext赋值为null
	 * 
	 */
	public static void clear(){
		applicationContext=null;
	}
	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}

}
