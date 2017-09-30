package com.personal.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.personal.base.until.Page;
import com.personal.demo.dao.DemoDao;
import com.personal.demo.model.Demo;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月22日/上午10:51:35
 * 
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@Transactional
public class DemoDao_test {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Resource
	private DemoDao demoDao;
	
	@Test
//	@Rollback(false)//junit 默认要回滚
	public void saveDemoTest(){
		Demo demo=new Demo();
		demo.setEnterpriseName("山西博华科技有限公司");
		demo.setAddress("山西省太原市南中环街");
		demo.setSalary(3500l);
		demo.setJoinTime(new Date());
		demo.setLeaveTime(new Date());
		demoDao.save(demo);
	}
	@Test
	public void getDemoList(){
		Page<Demo> page=new Page<Demo>(1, 10);
		Map<String, String> parameter=new HashMap<String, String>();
		demoDao.findPage(page, parameter);
	}
	@Test
	@Rollback(false)
	public void deleteDemoById(){
		demoDao.deleteById(85l);
	}
	@Test
	@Rollback(false)
	public void updateDemo(){
		Demo demo=new Demo();
		demo.setId(83);
		demo.setSalary(3000l);
		demoDao.update(demo);
	}
	@Test
	public void getDemoById(){
		Demo demo=demoDao.getById(83l);
		logger.info("查询demo通过主键，{}", demo);
	}
	
}
