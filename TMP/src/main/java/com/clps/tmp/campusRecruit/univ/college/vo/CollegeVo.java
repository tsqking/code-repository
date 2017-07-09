package com.clps.tmp.campusRecruit.univ.college.vo;
/**
 * @author Wellen
 *
 *         2016年3月14日
 */
public class CollegeVo {
	// 自增Id
    private int id;
    // 学校名称
 	private String univ_name;
    // 学校id
    private int univ_id;
    //学院名称
    private String name;
    // 创建时间
    private String create_time;
    // 创建人
    private String create_user;
    //更新时间
    private String update_time;
    //更新人
    private String update_user;
    //删除标志位
    private String del;
    
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUniv_id() {
		return univ_id;
	}
	public void setUniv_id(int univ_id) {
		this.univ_id = univ_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	
	public String getUniv_name() {
		return univ_name;
	}
	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
	@Override
	public String toString() {
		return "CollegeVo [id=" + id + ", univ_name=" + univ_name + ", univ_id=" + univ_id + ", name=" + name
				+ ", create_time=" + create_time + ", create_user=" + create_user + ", update_time=" + update_time
				+ ", update_user=" + update_user + ", del=" + del + "]";
	}
}
