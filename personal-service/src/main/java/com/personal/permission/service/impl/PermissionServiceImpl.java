package com.personal.permission.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personal.permission.dao.SyspermissionDao;
import com.personal.permission.model.Syspermission;
import com.personal.permission.service.PermissionService;

/**
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月14日/下午8:57:10
 * 
 */
@Service
public class PermissionServiceImpl implements PermissionService{
	@Resource
	private SyspermissionDao syspermissionDao;

	@Override
	public List<Syspermission> findPermissionListByUserId(String userId) {
		return syspermissionDao.findPermissionListByUserId(userId);
	}

}
