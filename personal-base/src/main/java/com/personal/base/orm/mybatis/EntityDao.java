package com.personal.base.orm.mybatis;

import java.io.Serializable;
import org.springframework.dao.DataAccessException;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月17日/下午3:33:13
 * 
 */
public interface EntityDao <E,PK extends Serializable>{
	
	/** 通过主键获取数据 */
	public Object getById(PK id)throws DataAccessException;
	/** 通过主键删除数据 */
	public void deleteById(PK id)throws DataAccessException;
	/** 插入数据 */
	public void save(E entity)throws DataAccessException;
	/** 更新数据*/
	public void update(E entity)throws DataAccessException;
	/** 获取所有数据*/
//	public List<E> findAll()throws DataAccessException;

}
