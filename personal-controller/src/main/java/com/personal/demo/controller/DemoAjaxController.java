package com.personal.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.base.until.DateUtil;
import com.personal.base.until.JsonUtils;
import com.personal.demo.model.Demo;
import com.personal.demo.service.DemoService;

/**
 * TODO:示例demo
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月28日/上午11:02:57
 * 
 */
@Controller
@RequestMapping("/ajax/demo")
public class DemoAjaxController {

	private Logger logger=LoggerFactory.getLogger(getClass());
	@Resource
	private DemoService demoService;
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String addDemo(@RequestBody String requestJson,HttpRequest request){
		Map<String, String> responseMap=new HashMap<String, String>();
		Map<String, String> requestMap=null;
		try {
			requestMap=JsonUtils.toObject(requestJson, Map.class);
			String enterPriseName=requestMap.get("enterpriseName");
			String address=requestMap.get("address");
			long  salary=Long.parseLong(requestMap.get("salary"));
			Date joinTime=DateUtil.str2Date(requestMap.get("joinTime"));
			Date leaveTime=DateUtil.str2Date(requestMap.get("leaveTime"));
			Demo demo=new Demo();
			demo.setAddress(address);
			demo.setEnterpriseName(enterPriseName);
			demo.setJoinTime(joinTime);
			demo.setLeaveTime(leaveTime);
			demo.setSalary(salary);
			demoService.save(demo);
			responseMap.put("status", "0");
		} catch (Exception e) {
			responseMap.put("status", "1");
		}
		requestMap=JsonUtils.toObject(requestJson, Map.class);
		return JsonUtils.getObjectString(responseMap);
	}
}
