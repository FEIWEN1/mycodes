package com.personal.permission.service;

import java.util.List;

import com.personal.permission.model.Syspermission;

/**
 * 权限逻辑实现
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月14日/下午8:56:43
 * 
 */
public interface PermissionService {

	public List<Syspermission> findPermissionListByUserId(String userId);
}
