package com.personal.base.orm.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.base.until.Page;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月17日/下午4:11:26
 * 
 */
public abstract class BaseMybatisDao<E,PK extends Serializable> extends MybatisDao implements EntityDao<E, PK>{
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * TODO:获取命名空间的抽象方法
	 * 
	 * @return
	 */
	public abstract String getMybatisMapperNamesapce();

	/** 通过主键查询 */
	public E getById(PK primaryKey){
		return (E)getSqlSession().selectOne(getFindByPrimaryKeyStatement(),primaryKey);
	}
	/** 通过主键删除 */
	public void deleteById(PK id){
		getSqlSession().delete(getDeleteStatement(), id);
	}
	/**  插入 */
	public void save(E entity){
		prepareObjectForSaveOrUpdate(entity);
		getSqlSession().insert(getInsertStatement(), entity);
	}
	/** 更新  */
	public void update(E entity){
		prepareObjectForSaveOrUpdate(entity);
		getSqlSession().update(getUpdateStatement(),entity);
	}
	public Page<E> findPage(Page<E> page,Object parameter){
		return selectPage(page, getFindPageStatement(), parameter);
	} 
	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * @param o
	 */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }
    
	
	/**
	 * TODO:主键查询sql
	 * 
	 * @return
	 */
	public String getFindByPrimaryKeyStatement(){
		return getMybatisMapperNamesapce()+".getById";
	}
	/**
	 * TODO:插入sql
	 * 
	 * @return
	 */
	public String getInsertStatement(){
		return getMybatisMapperNamesapce()+".insert";
	}
	/**
	 * TODO:更新sql
	 * 
	 * @return
	 */
	public String getUpdateStatement(){
		return getMybatisMapperNamesapce()+".update";
	}
	/**
	 * TODO:删除sql
	 * 
	 * @return
	 */
	public String getDeleteStatement(){
		return getMybatisMapperNamesapce()+".delete";
	}
	/**
	 * TODO:分页查询
	 * 
	 * @return
	 */
	public String getFindPageStatement(){
		return getMybatisMapperNamesapce()+".findPage";
	}
	
}
