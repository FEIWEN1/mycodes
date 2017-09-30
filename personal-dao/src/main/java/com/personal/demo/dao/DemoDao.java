package com.personal.demo.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.personal.base.orm.mybatis.BaseMybatisDao;
import com.personal.demo.model.Demo;

/**
 * TODO:dao的实现类需继承BaseMybatisDao
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月21日/上午10:35:59
 * 
 */
@Component
public class DemoDao extends BaseMybatisDao<Demo, Long>{

	@Override
	public String getMybatisMapperNamesapce() {
		return "com.personal.demo.demoDao";
	}

}
