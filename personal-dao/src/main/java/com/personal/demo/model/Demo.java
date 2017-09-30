package com.personal.demo.model;

import java.util.Date;

/**
 * TODO:
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月21日/上午10:36:14
 * 
 */
public class Demo {
	private int id;
	private String enterpriseName;
	private String address;
	private Long salary;
	private Date joinTime;
	private Date leaveTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	@Override
	public String toString() {
		return "Demo [id=" + id + ", enterpriseName=" + enterpriseName
				+ ", address=" + address + ", salary=" + salary + ", joinTime="
				+ joinTime + ", LeaveTime=" + leaveTime + "]";
	}
	

}
