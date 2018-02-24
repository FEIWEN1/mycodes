package com.primeton.wfei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients//启用feign
@EnableDiscoveryClient
@SpringBootApplication
public class FeignConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}

}
