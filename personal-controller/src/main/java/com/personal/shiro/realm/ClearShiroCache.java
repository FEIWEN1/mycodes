package com.personal.shiro.realm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 清理shiro缓存的测试类
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月26日/下午5:20:58
 * 
 */
@Controller
public class ClearShiroCache {
	@Resource
	private CustomRealm customRealm;
	
	@RequestMapping(value={"/shiro/clearCache"})
	public void clearCache(){
		customRealm.clearCached();
	}

}
