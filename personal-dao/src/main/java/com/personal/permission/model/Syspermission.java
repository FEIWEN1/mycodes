package com.personal.permission.model;

import java.io.Serializable;

/**
 * 
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月14日/下午4:51:39
 * 
 */
public class Syspermission implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String type;
	private String url;
	private String percode;
	private String parentid;
	private String sortstring;
	private String available;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSortstring() {
		return sortstring;
	}

	public void setSortstring(String sortstring) {
		this.sortstring = sortstring;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

}
