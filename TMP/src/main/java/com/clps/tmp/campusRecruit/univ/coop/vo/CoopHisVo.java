package com.clps.tmp.campusRecruit.univ.coop.vo;

import java.util.List;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;

public class CoopHisVo {
	
    private int id;
    private int coop_id;
    private String time;
    // 联系人json
    private String contacts;
    private String status;
    private String status_name;
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
    
    //联系人信息列表
    private List<AttnVo> contactsList;
    
    
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoop_id() {
		return coop_id;
	}
	public void setCoop_id(int coop_id) {
		this.coop_id = coop_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
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
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public List<AttnVo> getContactsList() {
		return contactsList;
	}
	public void setContactsList(List<AttnVo> contactsList) {
		this.contactsList = contactsList;
	}
	
    
   
	
}
