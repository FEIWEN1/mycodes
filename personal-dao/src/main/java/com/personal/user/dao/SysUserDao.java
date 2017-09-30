package com.personal.user.dao;

import org.springframework.stereotype.Component;

import com.personal.base.orm.mybatis.BaseMybatisDao;
import com.personal.user.model.SysUser;

/**
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月14日/下午8:43:08
 * 
 */
@Component
public class SysUserDao extends BaseMybatisDao<SysUser, Long>{

	@Override
	public String getMybatisMapperNamesapce() {
		return "com.personal.user.sysUserDao";
	}
	public SysUser getByuserCode(String userCode){
		return (SysUser) getSqlSession().selectOne(getMybatisMapperNamesapce()+".getByUsercode", userCode);
	}
	

}
