package com.clps.tmp.demo.vo;

import java.io.Serializable;

public class DemoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -255777800703218249L;
	private int id;
	private String no;
	private String name;
	private String sex;
	private String age;
	private String nativePlace;
	private String enable;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "DemoVo [id=" + id + ", no=" + no + ", name=" + name + ", sex="
				+ sex + ", age=" + age + ", nativePlace=" + nativePlace
				+ ", enable=" + enable + "]";
	}
	
}
