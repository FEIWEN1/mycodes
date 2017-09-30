package com.personal.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.personal.base.until.Page;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月18日/下午4:04:05
 * 
 */
public interface BaseServiceMybatis<E, PK extends Serializable> {

	/**
	 * TODO:通过主键查询实体的方法
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public abstract E getById(PK id) throws DataAccessException;

	/**
	 * TODO:新增方法
	 * 
	 * @param entity
	 * @throws DataAccessException
	 */
	public abstract void save(E entity) throws DataAccessException;
	
	/**
	 * TODO:通过主键删除方法
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public abstract void removeById(PK id) throws DataAccessException;
	
	/**
	 * TODO:通过主键更新方法
	 * 
	 * @param entity
	 * @throws DataAccessException
	 */
	public abstract void update(E entity) throws DataAccessException;
	
}
