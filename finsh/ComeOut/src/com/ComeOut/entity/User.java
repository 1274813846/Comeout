package com.ComeOut.entity;

import java.lang.annotation.Annotation;

import javax.xml.bind.annotation.XmlElement;


public class User {

	/**
	 * 属性：openid,nickName,avatarUrl：图像的url,major
	 */
	@XmlElement(name = "IsId")    
	public String openid;
	
	
	public String nickName;
	public String avatarUrl;
	public String major;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public User(String openid, String nickName, String avatarUrl, String major) {
		super();
		this.openid = openid;
		this.nickName = nickName;
		this.avatarUrl = avatarUrl;
		this.major = major;
	}
	public User() {
		super();
	}
	
	
}
