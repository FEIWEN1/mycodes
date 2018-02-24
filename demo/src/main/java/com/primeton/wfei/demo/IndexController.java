package com.primeton.wfei.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

	@Autowired
	private RestTemplate resttemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@RequestMapping(value = "/demoGetStrs")
	public String index() {
		ServiceInstance serviceInstance = loadBalancerClient
				.choose("serviceClient");
		String url = "http://" + serviceInstance.getHost() + ":"
				+ serviceInstance.getPort() + "/getStrs";
		resttemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = resttemplate.getForEntity(url,
				String.class);
		String body = responseEntity.getBody();
		return body;
	}
}
