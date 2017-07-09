package com.clps.tmp.tech.course.vo;

public class CourseVo {
	// id号，自增主键
	private String id;
	// 课程号 'CSE'+年+序号 (CSE15000)
	private String no;
	// 课程名称
	private String name;
	// 课程名称 英文
	private String name_en_US;
	// 课程描述
	private String description;
	// 课程描述  英文
	private String description_en_US;
	// 课程地点
	//private String location;
	// 课程属性 ：0-系统课程，1-定制课程
	private String property;
	// 课程方向
	private String direction;
	// 课程作用效果 适合职业等描述
	private String function;
	// 课程作用效果 适合职业等描述
	private String function_en_US;
	// 课程锁，锁住后，用户不能添加技能知识点(例：Y-锁住，N-不锁住)
	private String lock;
	// 生效标志(例：T-生效，F-不生效)
	private String enable;
	// 创建时间(20150901 115959000)
	private String create_time;
	// 创建人
	private String create_person;
	// 更新时间(20150901 115959000)
	private String update_time;
	// 更新人
	private String update_person;
	// 课程类别
	private String category;
	// 子类别
	private String sub_category;
	// 子子类别
	private String sub_sub_category;
	// 课程花费
	private float cost_number;
	// 课程花费单位
	private String cost_unit;
	// 课程难易级别
	private String level;
	// 教授者是否可得
	private String trainer_avl;
	// 教材讲义是否可得
	private String presentation_avl;
	// 学生手册是否可得
	private String stu_manual_avl;
	// 实验手册是否可得
	private String act_book_avl;
	// 测验是否提供
	private String quiz_avl;
	// 练习平台是否提供
	private String exec_pf_avl;
	// 目标客户
	private String target_client;
	
	//***************************
	private String searchNo;
	private String searchName;
	//private String searchLocation;
	private String searchDirection;
	private String searchProperty;
	private String searchEnable;
	private String searchCategory;
	private String searchSubCategory;
	private String searchSubSubCategory;
	//***************************
	private String last_update_time;
	private String last_update_person;
	
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getSearchSubCategory() {
		return searchSubCategory;
	}
	public void setSearchSubCategory(String searchSubCategory) {
		this.searchSubCategory = searchSubCategory;
	}
	public String getSearchSubSubCategory() {
		return searchSubSubCategory;
	}
	public void setSearchSubSubCategory(String searchSubSubCategory) {
		this.searchSubSubCategory = searchSubSubCategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public String getSub_sub_category() {
		return sub_sub_category;
	}
	public void setSub_sub_category(String sub_sub_category) {
		this.sub_sub_category = sub_sub_category;
	}
	public float getCost_number() {
		return cost_number;
	}
	public void setCost_number(float cost_number) {
		this.cost_number = cost_number;
	}
	public String getCost_unit() {
		return cost_unit;
	}
	public void setCost_unit(String cost_unit) {
		this.cost_unit = cost_unit;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTrainer_avl() {
		return trainer_avl;
	}
	public void setTrainer_avl(String trainer_avl) {
		this.trainer_avl = trainer_avl;
	}
	public String getPresentation_avl() {
		return presentation_avl;
	}
	public void setPresentation_avl(String presentation_avl) {
		this.presentation_avl = presentation_avl;
	}
	public String getStu_manual_avl() {
		return stu_manual_avl;
	}
	public void setStu_manual_avl(String stu_manual_avl) {
		this.stu_manual_avl = stu_manual_avl;
	}
	public String getAct_book_avl() {
		return act_book_avl;
	}
	public void setAct_book_avl(String act_book_avl) {
		this.act_book_avl = act_book_avl;
	}
	public String getQuiz_avl() {
		return quiz_avl;
	}
	public void setQuiz_avl(String quiz_avl) {
		this.quiz_avl = quiz_avl;
	}
	public String getExec_pf_avl() {
		return exec_pf_avl;
	}
	public void setExec_pf_avl(String exec_pf_avl) {
		this.exec_pf_avl = exec_pf_avl;
	}
	public String getTarget_client() {
		return target_client;
	}
	public void setTarget_client(String target_client) {
		this.target_client = target_client;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getLast_update_person() {
		return last_update_person;
	}
	public void setLast_update_person(String last_update_person) {
		this.last_update_person = last_update_person;
	}
	public String getFunction_en_US() {
		return function_en_US;
	}
	public void setFunction_en_US(String function_en_US) {
		this.function_en_US = function_en_US;
	}
	public String getSearchDirection() {
		return searchDirection;
	}
	public void setSearchDirection(String searchDirection) {
		this.searchDirection = searchDirection;
	}
	public String getSearchNo() {
		return searchNo;
	}
	public void setSearchNo(String searchNo) {
		this.searchNo = searchNo;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchProperty() {
		return searchProperty;
	}
	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}
	public String getSearchEnable() {
		return searchEnable;
	}
	public void setSearchEnable(String searchEnable) {
		this.searchEnable = searchEnable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
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
		return "CourseVo [id=" + id + ", no=" + no + ", name=" + name
				+ ", name_en_US=" + name_en_US + ", description=" + description
				+ ", description_en_US=" + description_en_US + ", property="
				+ property + ", direction=" + direction + ", function="
				+ function + ", function_en_US=" + function_en_US + ", lock="
				+ lock + ", enable=" + enable + ", create_time=" + create_time
				+ ", create_person=" + create_person + ", update_time="
				+ update_time + ", update_person=" + update_person
				+ ", category=" + category + ", sub_category=" + sub_category
				+ ", sub_sub_category=" + sub_sub_category + ", cost_number="
				+ cost_number + ", cost_unit=" + cost_unit + ", level=" + level
				+ ", trainer_avl=" + trainer_avl + ", presentation_avl="
				+ presentation_avl + ", stu_manual_avl=" + stu_manual_avl
				+ ", act_book_avl=" + act_book_avl + ", quiz_avl=" + quiz_avl
				+ ", exec_pf_avl=" + exec_pf_avl + ", target_client="
				+ target_client + ", searchNo=" + searchNo + ", searchName="
				+ searchName + ", searchDirection=" + searchDirection
				+ ", searchProperty=" + searchProperty + ", searchEnable="
				+ searchEnable + ", searchCategory=" + searchCategory
				+ ", searchSubCategory=" + searchSubCategory
				+ ", searchSubSubCategory=" + searchSubSubCategory
				+ ", last_update_time=" + last_update_time
				+ ", last_update_person=" + last_update_person + "]";
	}

}
