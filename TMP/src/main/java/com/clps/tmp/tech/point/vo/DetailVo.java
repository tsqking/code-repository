package com.clps.tmp.tech.point.vo;
/**
 * @author Seven
 *
 * 2015年11月5日
 */
public class DetailVo {
	// 知识点id号
	private String point_id;
	// 讲解耗时
	private double cost;
	// 描述
	private String description;
	// 描述--英文
	private String description_en_US;
	// 教材资料名
	private String material_file_name;
	// 教材文件类型
	private String material_content_type;
	// 教材文件大小
	private String material_file_size;
	// 教材文件路径
	private String material_dir;
	// 是否部分教材 Y-是/N-否
	private String part;
	// 若为整体教材一部分，需说明从第几页至第几页（默认为空）
	private int from_page;
	// 若为整体教材一部分，需说明从第几页至第几页（默认为空）
	private int to_page;
	// 教师手册
	//private File t_handbook;
	// 教师手册名称
	private String t_handbook_file_name;
	// 教师手册文件类型
	private String t_handbook_content_type;
	// 教师手册文件大小
	private String t_handbook_file_size;
	// 教师手册文件路劲
	private String t_handbook_dir;
	// 学员手册
	//private File s_handbook;
	// 学员手册名称
	private String s_handbook_file_name;
	// 学员手册文件类型
	private String s_handbook_content_type;
	// 学员手册文件大小
	private String s_handbook_file_size;
	// 学员手册文件路劲
	private String s_handbook_dir;
	// 参考资料
	//private File reference;
	// 参考资料名称
	private String reference_file_name;
	// 参考资料文件类型
	private String reference_content_type;
	// 参考资料文件大小
	private String reference_file_size;
	// 参考资料文件路劲
	private String reference_dir;
	// 学生目标
	private String goal;
	// 学生目标 --英文
	private String goal_en_US;
	// 创建人 取用户的name属性
	private String create_person;
	// 创建时间 如"20151105 105859"
	private String create_time;
	// 更新人 取用户的name属性
	private String update_person;
	// 更新时间  如"20151105 105859"
	private String update_time;
	//****************************
	//是否教材部分 T/F
	private String part_key;
	// 更新时候用
	private String last_update_time;
	// 更新时候用
	private String last_update_person;
	//****************************
	public String getPoint_id() {
		return point_id;
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
	public String getPart_key() {
		return part_key;
	}
	public void setPart_key(String part_key) {
		this.part_key = part_key;
	}
	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	public String getMaterial_file_name() {
		return material_file_name;
	}
	public void setMaterial_file_name(String material_file_name) {
		this.material_file_name = material_file_name;
	}
	public String getMaterial_content_type() {
		return material_content_type;
	}
	public void setMaterial_content_type(String material_content_type) {
		this.material_content_type = material_content_type;
	}
	public String getMaterial_file_size() {
		return material_file_size;
	}
	public void setMaterial_file_size(String material_file_size) {
		this.material_file_size = material_file_size;
	}
	public String getMaterial_dir() {
		return material_dir;
	}
	public void setMaterial_dir(String material_dir) {
		this.material_dir = material_dir;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getFrom_page() {
		return from_page;
	}
	public void setFrom_page(int from_page) {
		this.from_page = from_page;
	}
	public int getTo_page() {
		return to_page;
	}
	public void setTo_page(int to_page) {
		this.to_page = to_page;
	}
	public String getT_handbook_file_name() {
		return t_handbook_file_name;
	}
	public void setT_handbook_file_name(String t_handbook_file_name) {
		this.t_handbook_file_name = t_handbook_file_name;
	}
	public String getT_handbook_content_type() {
		return t_handbook_content_type;
	}
	public void setT_handbook_content_type(String t_handbook_content_type) {
		this.t_handbook_content_type = t_handbook_content_type;
	}
	public String getT_handbook_file_size() {
		return t_handbook_file_size;
	}
	public void setT_handbook_file_size(String t_handbook_file_size) {
		this.t_handbook_file_size = t_handbook_file_size;
	}
	public String getT_handbook_dir() {
		return t_handbook_dir;
	}
	public void setT_handbook_dir(String t_handbook_dir) {
		this.t_handbook_dir = t_handbook_dir;
	}
	public String getS_handbook_file_name() {
		return s_handbook_file_name;
	}
	public void setS_handbook_file_name(String s_handbook_file_name) {
		this.s_handbook_file_name = s_handbook_file_name;
	}
	public String getS_handbook_content_type() {
		return s_handbook_content_type;
	}
	public void setS_handbook_content_type(String s_handbook_content_type) {
		this.s_handbook_content_type = s_handbook_content_type;
	}
	public String getS_handbook_file_size() {
		return s_handbook_file_size;
	}
	public void setS_handbook_file_size(String s_handbook_file_size) {
		this.s_handbook_file_size = s_handbook_file_size;
	}
	public String getS_handbook_dir() {
		return s_handbook_dir;
	}
	public void setS_handbook_dir(String s_handbook_dir) {
		this.s_handbook_dir = s_handbook_dir;
	}
	public String getReference_file_name() {
		return reference_file_name;
	}
	public void setReference_file_name(String reference_file_name) {
		this.reference_file_name = reference_file_name;
	}
	public String getReference_content_type() {
		return reference_content_type;
	}
	public void setReference_content_type(String reference_content_type) {
		this.reference_content_type = reference_content_type;
	}
	public String getReference_file_size() {
		return reference_file_size;
	}
	public void setReference_file_size(String reference_file_size) {
		this.reference_file_size = reference_file_size;
	}
	public String getReference_dir() {
		return reference_dir;
	}
	public void setReference_dir(String reference_dir) {
		this.reference_dir = reference_dir;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getGoal_en_US() {
		return goal_en_US;
	}
	public void setGoal_en_US(String goal_en_US) {
		this.goal_en_US = goal_en_US;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "DetailVo [point_id=" + point_id + ", cost=" + cost
				+ ", description=" + description + ", description_en_US="
				+ description_en_US + ", material_file_name="
				+ material_file_name + ", material_content_type="
				+ material_content_type + ", material_file_size="
				+ material_file_size + ", material_dir=" + material_dir
				+ ", part=" + part + ", from_page=" + from_page + ", to_page="
				+ to_page + ", t_handbook_file_name=" + t_handbook_file_name
				+ ", t_handbook_content_type=" + t_handbook_content_type
				+ ", t_handbook_file_size=" + t_handbook_file_size
				+ ", t_handbook_dir=" + t_handbook_dir
				+ ", s_handbook_file_name=" + s_handbook_file_name
				+ ", s_handbook_content_type=" + s_handbook_content_type
				+ ", s_handbook_file_size=" + s_handbook_file_size
				+ ", s_handbook_dir=" + s_handbook_dir
				+ ", reference_file_name=" + reference_file_name
				+ ", reference_content_type=" + reference_content_type
				+ ", reference_file_size=" + reference_file_size
				+ ", reference_dir=" + reference_dir + ", goal=" + goal
				+ ", goal_en_US=" + goal_en_US + ", create_person="
				+ create_person + ", create_time=" + create_time
				+ ", update_person=" + update_person + ", update_time="
				+ update_time + "]";
	}
	
}
