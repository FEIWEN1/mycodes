package com.personal.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.base.until.Page;
import com.personal.demo.model.Demo;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月25日/下午4:25:53
 * 
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@Transactional
public class DemoServiceTest {
	@Resource
	private DemoService demoService;
	
	@Test
	public void addDemo(){
		Demo demo=new Demo();
		demo.setAddress("海淀区花园路2号牡丹科技楼5层");
		demo.setEnterpriseName("北京思特奇科技股份有限公司");
		demo.setJoinTime(new Date());
		demo.setLeaveTime(new Date());
		demo.setSalary(7500l);
		demoService.save(demo);
	}
	@Test
	public void deleteDemo(){
		demoService.removeById(91l);
		demoService.removeById(92l);
		demoService.removeById(93l);
	}
	@Test
	public void findDemoById(){
		demoService.getById(83l);
	}
	@Test
	public void findDemoPage(){
		Page<Demo> page=new Page<Demo>(1, 10);
		Map<String, String> map=new HashMap<String, String>();
		demoService.findPage(page, map);
	}

}
