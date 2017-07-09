package com.clps.tmp.question.paper.vo;

public class PaperVo {

	// id自增主键
	private int id;
	// 试卷号（使用UUID生成）
	private String no;
	// 试卷名称
	private String name;
	// 试卷用途标识 USE_FLAG ：1-教学，2-校招，3-通用
	private String use_flag;
	// 试卷属性 PAPER_PROP：1-测试，2-练习
	private String property;
	// 试卷描述，考生不可见
	private String description;
	// 试卷说明，考生可见
	private String instruction;
	// 总题数
	private int total_item;
	// 总分数
	private int total_score;
	// 总时长（单位min）-1表示不限制时长
	private int total_time;
	// 限定离开次数，超过限制即默认提交试卷(只针对限定考试总时长的情况，否则默认为-1)
	private String leave_limit="0";
	// 试卷创建完成标志位 PAPER_INIT_DONE：0-未完成创建，1-完成创建
	private String finish_flag;
	// 考试链接
	private String url;
	// 删除标志字段(N-标识正在使用，Y-标识被删除)
	private String del;
	// 生效标识：T-生效，F-不生效
	private String enable;
	// 创建时间（YYYY-MM-DD HH-mm-SS）
	private String create_time;
	// 创建人
	private String create_person;
	// 修改时间（YYYY-MM-DD HH-mm-SS）
	private String update_time;
	// 修改人
	private String update_person;
	
	// 试卷用途标识 USE_FLAG ：1-教学，2-校招，3-通用
	private String use_flag_name;
	// 试卷属性 PAPER_PROP：1-测试，2-练习
	private String property_name;
	// 总时长（单位min）-1表示不限制时长
	private String total_time_name;
	
	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	public String getUse_flag_name() {
		return use_flag_name;
	}

	public void setUse_flag_name(String use_flag_name) {
		this.use_flag_name = use_flag_name;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
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

	public String getUse_flag() {
		return use_flag;
	}

	public void setUse_flag(String use_flag) {
		this.use_flag = use_flag;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public int getTotal_item() {
		return total_item;
	}

	public void setTotal_item(int total_item) {
		this.total_item = total_item;
	}

	public int getTotal_time() {
		return total_time;
	}

	public void setTotal_time(int total_time) {
		this.total_time = total_time;
	}

	public String getFinish_flag() {
		return finish_flag;
	}

	public void setFinish_flag(String finish_flag) {
		this.finish_flag = finish_flag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getLeave_limit() {
		return leave_limit;
	}

	public void setLeave_limit(String leave_limit) {
		this.leave_limit = leave_limit;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getTotal_time_name() {
		return total_time_name;
	}

	public void setTotal_time_name(String total_time_name) {
		this.total_time_name = total_time_name;
	}

	@Override
	public String toString() {
		return "PaperVo [id=" + id + ", no=" + no + ", name=" + name
				+ ", use_flag=" + use_flag + ", property=" + property
				+ ", description=" + description + ", instruction="
				+ instruction + ", total_item=" + total_item + ", total_score="
				+ total_score + ", total_time=" + total_time + ", leave_limit="
				+ leave_limit + ", finish_flag=" + finish_flag + ", url=" + url
				+ ", del=" + del + ", enable=" + enable + ", create_time="
				+ create_time + ", create_person=" + create_person
				+ ", update_time=" + update_time + ", update_person="
				+ update_person + ", use_flag_name=" + use_flag_name
				+ ", property_name=" + property_name + ", total_time_name="
				+ total_time_name + "]";
	}
}
