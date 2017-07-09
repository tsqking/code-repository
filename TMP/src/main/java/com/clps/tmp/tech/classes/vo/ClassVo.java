package com.clps.tmp.tech.classes.vo;

/**
 * 班级管理 页面VO类
 * @author Seven
 *
 * 2015年11月3日
 */
public class ClassVo {
	//自增主键
	private int id;
	//班级号
	private String no;
	//班级名称
	private String name;
	//班级名称（英文）
	private String name_en_US;
	//班级情况描述
	private String description;
	//班级情况简介（英文）
	private String description_en_US;
	//班长（学生id号-person_id）
	private int student_monitor;
	//班主任（教员id-person_id）
	private int teacher_monitor;
	//班级学习方向（技术方向）
	private String direction;
	//班级总人数
	private int size;
	//班级级数（2015年春季班）
	private String session;
	//班级级数 英文
	private String session_en_US;
	//班级地点（上海/大连）
	private String location;
	//生效标识
	private String enable;
	//创建时间
	private String create_time;
	//创建人（person_id）
	private String create_person;
	//修改时间
	private String update_time;
	//修改人（person_id）
	private String update_person;
	
	public String getSession_en_US() {
		return session_en_US;
	}
	public void setSession_en_US(String session_en_US) {
		this.session_en_US = session_en_US;
	}
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
	public String getName_en_US() {
		return name_en_US;
	}
	public void setName_en_US(String name_en_US) {
		this.name_en_US = name_en_US;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription_en_US() {
		return description_en_US;
	}
	public void setDescription_en_US(String description_en_US) {
		this.description_en_US = description_en_US;
	}
	public int getStudent_monitor() {
		return student_monitor;
	}
	public void setStudent_monitor(int student_monitor) {
		this.student_monitor = student_monitor;
	}
	public int getTeacher_monitor() {
		return teacher_monitor;
	}
	public void setTeacher_monitor(int teacher_monitor) {
		this.teacher_monitor = teacher_monitor;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	@Override
	public String toString() {
		return "ClassVo [id=" + id + ", no=" + no + ", name=" + name
				+ ", name_en_US=" + name_en_US + ", description=" + description
				+ ", description_en_US=" + description_en_US
				+ ", student_monitor=" + student_monitor + ", teacher_monitor="
				+ teacher_monitor + ", direction=" + direction + ", size="
				+ size + ", session=" + session + ", session_en_US="
				+ session_en_US + ", location=" + location + ", enable="
				+ enable + ", create_time=" + create_time + ", create_person="
				+ create_person + ", update_time=" + update_time
				+ ", update_person=" + update_person + "]";
	}

}
