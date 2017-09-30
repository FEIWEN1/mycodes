package com.personal.demo.service;

import java.util.Map;

import com.personal.base.service.BaseServiceMybatis;
import com.personal.base.until.Page;
import com.personal.demo.model.Demo;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月25日/下午3:14:10
 * 
 */
public interface DemoService extends BaseServiceMybatis<Demo, Long>{
	public Page<Demo> findPage(Page<Demo> page,Map parameter);
}
