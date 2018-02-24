package com.primeton.wfei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.wfei.service.HelloService;

@RestController
public class ConsumerController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value="/helloConsumer",method=RequestMethod.GET)
	public String helloConsumer(){
		return helloService.hello();
	}

}
