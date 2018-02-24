package com.primeton.wfei.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="hystrixFallback")
	public String hystrixService(){
//		return restTemplate.postForObject("http://serviceClient/postStrs", null, String.class);
		return restTemplate.getForObject("http://serviceClient/getStrs",String.class);
	}
	
	@HystrixCommand(fallbackMethod="hystrixFallback")
	public String postHystrService(){
		return restTemplate.postForObject("http://serviceClient/postStrs", null, String.class);
	}
	public String hystrixFallback(){
		return "hello  error";
	}
	
}
