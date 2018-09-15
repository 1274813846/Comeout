package com.ComeOut.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Task {

	/**
	 * 目标计划
	 */
	@XmlElement(name = "IsId") 
	public String openid;
	public String name;
	public String startTime;
	public String endTime;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Task(String openid, String name, String startTime, String endTime) {
		super();
		this.openid = openid;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Task() {
		super();
	}

	
	
}
