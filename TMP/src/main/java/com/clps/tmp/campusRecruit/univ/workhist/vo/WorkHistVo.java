package com.clps.tmp.campusRecruit.univ.workhist.vo;
/**
 * @author Wellen
 *
 *         2016年3月14日
 */
public class WorkHistVo {
	// 自增Id
    private int id;
    // 操作时间
    private String op_time;
    // 操办人名
    private String op_user_name;
    // 学校名称
    private String univ_name;
    // 学校id
    private int univ_id;
    // 分校名称
    private String univ_branch_name;
    // 分校id
    private int univ_branch_id;
    // 学院名称
    private String college_name;
    // 学院id
    private int college_id;
    // 工作内容
    private String work_content;
    // 执行状态
    private String op_status;
    // 状态
    private String status;
    // 备注
    private String remark;
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
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	public String getUniv_name() {
		return univ_name;
	}
	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
	public int getUniv_id() {
		return univ_id;
	}
	public void setUniv_id(int univ_id) {
		this.univ_id = univ_id;
	}
	public String getUniv_branch_name() {
		return univ_branch_name;
	}
	public void setUniv_branch_name(String univ_branch_name) {
		this.univ_branch_name = univ_branch_name;
	}
	public int getUniv_branch_id() {
		return univ_branch_id;
	}
	public void setUniv_branch_id(int univ_branch_id) {
		this.univ_branch_id = univ_branch_id;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public int getCollege_id() {
		return college_id;
	}
	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	public String getWork_content() {
		return work_content;
	}
	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}
	public String getOp_status() {
		return op_status;
	}
	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}
	public String getRemark() {
		return remark;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	@Override
	public String toString() {
		return "WorkHistVo [id=" + id + ", op_time=" + op_time + ", op_user_name=" + op_user_name + ", univ_name="
				+ univ_name + ", univ_id=" + univ_id + ", univ_branch_name=" + univ_branch_name + ", univ_branch_id="
				+ univ_branch_id + ", college_name=" + college_name + ", college_id=" + college_id + ", work_content="
				+ work_content + ", op_status=" + op_status + ", status=" + status + ", remark=" + remark
				+ ", create_time=" + create_time + ", create_user=" + create_user + ", update_time=" + update_time
				+ ", update_user=" + update_user + ", del=" + del + "]";
	}
	
}

