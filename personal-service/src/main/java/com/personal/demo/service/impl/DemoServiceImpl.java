package com.personal.demo.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.base.orm.mybatis.EntityDao;
import com.personal.base.service.impl.BaseServiceMybatisImpl;
import com.personal.base.until.Page;
import com.personal.demo.dao.DemoDao;
import com.personal.demo.model.Demo;
import com.personal.demo.service.DemoService;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月25日/下午4:03:01
 * 
 */
@Service
public class DemoServiceImpl extends BaseServiceMybatisImpl<Demo, Long> implements DemoService{
	
	@Resource
	private DemoDao demoDao;

	@Override
	protected EntityDao getEntityDao() {
		return demoDao;
	}

	@Override
	public Page<Demo> findPage(Page<Demo> page,Map parameter) {
		return demoDao.findPage(page, parameter);
	}

}
