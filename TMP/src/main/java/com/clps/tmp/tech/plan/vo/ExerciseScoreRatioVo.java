package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName ExerciseScoreRatioVo
 * @Description TODO(平时成绩组成加权表)
 * @author liuchen
 * @Date 2016年6月14日 下午4:04:59
 * @version 1.0.0
 */
public class ExerciseScoreRatioVo {

	private int id;//主键ID
	private int plan_id;//教学计划ID
	private int paper_id;//试卷ID
	private int ratio;//加权比例
	private int flag;//是否为最终测验卷 0-不是 1-是
	private String del;//删除标示 Y-已删除 N-未删除
	private String create_time;//创建时间(20150901 115959000)
	private String create_person;//创建人
	private String update_time;//修改时间(20150901 115959000)
	private String update_person;//修改人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
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

	
}
