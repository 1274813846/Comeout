package com.ComeOut.entity;

public class Job {
	
	/**
	 * 工作属性
	 */
	public int id;
	public String location;
	public double salary;
	public String position;
	public String tel;
	public String company;
	public double lng;
	public double lat;
	public String time;
	public String detail;
	public String imageUrl;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	public Job(int id, String location, double salary, String position, String tel, String company, double lng,
			double lat, String time, String detail, String imageUrl) {
		super();
		this.id = id;
		this.location = location;
		this.salary = salary;
		this.position = position;
		this.tel = tel;
		this.company = company;
		this.lng = lng;
		this.lat = lat;
		this.time = time;
		this.detail = detail;
		this.imageUrl = imageUrl;
	}
	
	public Job() {
		super();
	}
	
	
	
}
