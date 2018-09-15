package com.ComeOut.entity;

public class Major {

	public String major;
	public String detail;
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Major(String major, String detail) {
		super();
		this.major = major;
		this.detail = detail;
	}
	public Major() {
		super();
	}
	
	public Major(String major) {
		super();
		this.major = major;
	}
	
	
	public static void main(String[] args) {
		Major m=new Major("计算机");
		System.out.println(m.toString());
	}
	
	
}
