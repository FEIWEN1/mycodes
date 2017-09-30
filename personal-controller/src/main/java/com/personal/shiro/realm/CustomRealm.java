package com.personal.shiro.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;

import com.personal.permission.model.Syspermission;
import com.personal.permission.service.PermissionService;
import com.personal.user.model.SysUser;
import com.personal.user.service.UserService;

/**
 * shiro的自定义realm
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月21日/上午11:04:16
 * 
 */
@Controller
public class CustomRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	@Resource
	private PermissionService permissionService;

	public String getName() {
		return "customRealm";
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//获取身份信息
		SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		String userCode=sysUser.getUserCode();
		List<Syspermission> permissions=permissionService.findPermissionListByUserId(userCode);
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		for(Syspermission permission:permissions){
			simpleAuthorizationInfo.addStringPermission(permission.getPercode());
		}
		return simpleAuthorizationInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户的身份信息
		String userName = (String) token.getPrincipal();
		// 拿username去数据库中查询
		// 要从数据库里面拿哪些信息
		SysUser sysUser = null;
		try {
			sysUser = userService.findUserByUserCode(userName);
			if (sysUser == null) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String salt = sysUser.getSalt();
		String password = sysUser.getPassWord();
//		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
//				sysUser, password, getName());
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				sysUser, password, ByteSource.Util.bytes(salt), getName());
		return simpleAuthenticationInfo;
	}
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
