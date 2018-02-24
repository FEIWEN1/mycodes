package com.primeton.wfei.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("serviceClient")
public interface HelloService {
	@RequestMapping("/getStrs")
	public String hello();
}
