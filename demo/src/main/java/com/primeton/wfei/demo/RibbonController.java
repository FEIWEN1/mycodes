package com.primeton.wfei.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	HystrixService hystrixService;
	
	@GetMapping("/consumer")
	public String dc(){
		return restTemplate.getForObject("http://serviceClient/getStrs", String.class);
	}
	
	@PostMapping("/postConumer")
	public String didi(){
		return restTemplate.postForObject("http://serviceClient/postStrs", null, String.class);
	}
	
	@GetMapping("/HystrixConumer")
	public String hystrixdidi(){
		return hystrixService.hystrixService();
	}
	
	@PostMapping("/postHystrixConumer")
	public String postHystrixdidi(){
		return hystrixService.postHystrService();
	}
	
}
