package com.personal.permission.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.personal.base.orm.mybatis.BaseMybatisDao;
import com.personal.permission.model.Syspermission;

/**
 * 系统权限操作dao（目前理解权限对应者url，就是记录哪些url属于哪些角色，当用户登录时，会取相应的url放入session中）
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月14日/下午8:45:01
 * 
 */
@Component
public class SyspermissionDao extends BaseMybatisDao<Syspermission, Long>{

	@Override
	public String getMybatisMapperNamesapce() {
		return "com.personal.syspermissionDao";
	}
	public List<Syspermission> findPermissionListByUserId(String userCode){
		return getSqlSession().selectList(getMybatisMapperNamesapce()+".findPermissionListByUserId", userCode);
	}

}
