package com.clps.tmp.tech.plan.vo;

import java.io.Serializable;


/**
 * 
 * @ClassName: PlanVo
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年12月3日 下午2:44:38
 */
@SuppressWarnings("serial")
public class PlanVo implements Serializable{
	
	//注意该对象不能修改--->修改之后原有的数据库对象会出错

	//TEACH_PLAN 教学计划表
	// id自增主键
	private String id;
	// 课程id
	private String course_id;
	private String course_id_name;
	// 班级id
	private String class_id;
	private String class_id_name;
	// 详细教学计划id
	private String detail_plan_id;
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	//创建时间
	private String create_time;
	//创建人
	private String create_person;
	//更新时间
	private String update_time;
	//更新人
	private String update_person;
	//DETAIL_PLAN 详细表
	// 教学计划id
	private String teach_plan_id;
	// 知识点id
	private String point_id;
	// 知识点授课计划开始时间
	private String starttime;
	// 知识点授课计划结束时间
	private String endtime;
	// 授课老师id(即为person_id)
	private String teacher_id;
	//其他
	//总条数
	private String allcount;
	//名字
	private String name;
	//一级技能
	private String first;
	private String first_en_US;
	//一级技能ID
	private String first_id;
	//二级技能
	private String second;
	private String second_en_US;
	//二级技能ID
	private String second_id;
	//三级技能
	private String third;
	private String third_en_US;
	//三级技能ID
	private String third_id;
	//知识点
	private String point;
	private String point_en_US;
	//知识点ID
	//private String point_id;
	//开始时间
	//private String starttime;
	//结束时间
	//private String endtime;
	//老师编号
	//private String teacher_id;
	//老师姓名
	private String teacher_name;
	//数据
	private String info;
	//时长
	private String cost;
	//状态值
	private String finish_state;
	private String finish_state_name;
	//小时数
	private String hours;
	//分钟数
	private String mins;
	//知识点关系
	private String tree_snap;
	//类型
	private String type;
	/////个人教学计划
	//课程开课地点
	private String location;
	private String location_id;

	public String getPoint_en_US() {
		return point_en_US;
	}

	public void setPoint_en_US(String point_en_US) {
		this.point_en_US = point_en_US;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getDetail_plan_id() {
		return detail_plan_id;
	}

	public void setDetail_plan_id(String detail_plan_id) {
		this.detail_plan_id = detail_plan_id;
	}

	public String getTeach_plan_id() {
		return teach_plan_id;
	}

	public void setTeach_plan_id(String teach_plan_id) {
		this.teach_plan_id = teach_plan_id;
	}

	public String getPoint_id() {
		return point_id;
	}

	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
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

	public String getCourse_id_name() {
		return course_id_name;
	}

	public void setCourse_id_name(String course_id_name) {
		this.course_id_name = course_id_name;
	}

	public String getClass_id_name() {
		return class_id_name;
	}

	public void setClass_id_name(String class_id_name) {
		this.class_id_name = class_id_name;
	}

	public String getAllcount() {
		return allcount;
	}

	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getFirst_id() {
		return first_id;
	}

	public void setFirst_id(String first_id) {
		this.first_id = first_id;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getSecond_id() {
		return second_id;
	}

	public void setSecond_id(String second_id) {
		this.second_id = second_id;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public String getThird_id() {
		return third_id;
	}

	public void setThird_id(String third_id) {
		this.third_id = third_id;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getFinish_state() {
		return finish_state;
	}

	public void setFinish_state(String finish_state) {
		this.finish_state = finish_state;
	}

	public String getFinish_state_name() {
		return finish_state_name;
	}

	public void setFinish_state_name(String finish_state_name) {
		this.finish_state_name = finish_state_name;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMins() {
		return mins;
	}

	public void setMins(String mins) {
		this.mins = mins;
	}

	public String getTree_snap() {
		return tree_snap;
	}

	public void setTree_snap(String tree_snap) {
		this.tree_snap = tree_snap;
	}

	public String getFirst_en_US() {
		return first_en_US;
	}

	public void setFirst_en_US(String first_en_US) {
		this.first_en_US = first_en_US;
	}

	public String getSecond_en_US() {
		return second_en_US;
	}

	public void setSecond_en_US(String second_en_US) {
		this.second_en_US = second_en_US;
	}

	public String getThird_en_US() {
		return third_en_US;
	}

	public void setThird_en_US(String third_en_US) {
		this.third_en_US = third_en_US;
	}

	
	
}
