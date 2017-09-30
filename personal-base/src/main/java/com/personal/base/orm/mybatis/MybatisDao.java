package com.personal.base.orm.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.ReflectionUtils;

import com.personal.base.until.Page;

/**
 * TODO:Mybatis分页查询工具类
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月17日/下午4:16:17
 * 
 */
public class MybatisDao extends SqlSessionDaoSupport{

	/**
	 * TODO:根据sql语句与参数查询数据
	 * 
	 * @param page  分页的工具类
	 * @param statementName 查询sql
	 * @param countStatementName  查询结果集条数的sql
	 * @param parameter  参数
	 * @return
	 */
	public <T> Page<T> selectPage(Page<T> page,String statementName,String countStatementName,Object parameter){
		Number totalItems=(Number)getSqlSession().selectOne(countStatementName, parameter);
		if(totalItems!=null&&totalItems.longValue()>0){
			List list=getSqlSession().selectList(statementName, toParameterMap(parameter, page));
			page.setResult(list);
			page.setTotalCount(totalItems.longValue());
		}
		return page;
	}
	/**
	 * TODO:根据sql语句与参数 查询数据（不分页）
	 * 
	 * @param page 分页的工具类
	 * @param statementName  查询sql
	 * @param parameter 参数
	 * @return
	 */
	public <T> Page<T> selectPage(Page<T> page,String statementName,Object parameter){
		String countStatementName=statementName+"Count";
		return selectPage(page, statementName, countStatementName, parameter);
	}
	 /**
	 * TODO:往map里面塞入分页的参数
	 * 
	 * @param parameter 外部传入带有参数的map
	 * @param p page参数带有分页的信息
	 * @return
	 */
	protected static Map toParameterMap(Object parameter, Page p) {
         Map map = toParameterMap(parameter);
         map.put("startRow", p.getStartRow());
         //map.put("startRow", p.getOffset());
         map.put("endRow", p.getEndRow());
         map.put("offset", p.getOffset());
         map.put("limit", p.getPageSize());
         return map;
	 }

	 /**
	 * TODO:检查传入是不是个map
	 * 
	 * @param parameter
	 * @return
	 */
	protected static Map toParameterMap(Object parameter) {
         if (parameter instanceof Map) {
                 return (Map) parameter;
         } else {
                 try {
                         return PropertyUtils.describe(parameter);
                 } catch (Exception e) {
                         ReflectionUtils.handleReflectionException(e);
                         return null;
                 }
         }
 }
}
