package com.personal.base.service.impl;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.personal.base.orm.mybatis.EntityDao;
import com.personal.base.service.BaseServiceMybatis;
import com.personal.base.until.Page;

/**
 * TODO:提供了基本的增删改和通过主键查询的基本方法，并且为类的所有方法都提供了事务（没提供分页查询方法，需自己去实现）
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月18日/下午4:16:07
 * 
 */
@Transactional
public abstract class BaseServiceMybatisImpl<E, PK extends Serializable>
		implements BaseServiceMybatis<E, PK> {
	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	public E getById(PK id) throws DataAccessException {
		return (E)getEntityDao().getById(id);
	}

	@Override
	public void save(E entity) throws DataAccessException {
		getEntityDao().save(entity);
	}

	@Override
	public void removeById(PK id) throws DataAccessException {
		getEntityDao().deleteById(id);
	}

	@Override
	public void update(E entity) throws DataAccessException {
		getEntityDao().update(entity);
		
	}
	
}
