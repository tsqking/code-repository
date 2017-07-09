package com.clps.tmp.tech.skill.vo;

/**
 * @author Seven
 *
 *         2015年10月19日
 */
public class SkillVo {
    // TODO 添加技能用到属性值
    // 自增Id
    private int id;
    // 排序ID
    private int order;
    // 技能名称
    private String name;
    // 英文技能名称
    private String name_en_US;
    // 技能类型(1-技术技能，2-语言技能，3-职业技能)
    private String type;
    // 上级技能id(最高级为0) 默认为0
    private int parent_id;
    //上级技能名称
    private String parent_name;
    // 技能描述
    private String description;
    // 英文技能描述
    private String description_en_US;
    // 技能层级(1,2,3,..)
    private String level; 
    
    private String levelCopy;
   
    // 生效标识(例：T-生效，F-不生效)
    private String enable;
    // 创建时间(20150901 115959000)
    private String create_time;
    // 创建人
    private String create_person;
    // 修改时间(20150901 115959000)
    private String update_time;
    // 修改人
    private String update_person;
    //等级
    private String hiddenType;
    
    //搜索条件
    private String skillFirst;
    private String skillSecond;
    private String skillThird;
   
    public String getSkillFirst() {
        return skillFirst;
    }
    public void setSkillFirst(String skillFirst) {
        this.skillFirst = skillFirst;
    }
    public String getSkillSecond() {
        return skillSecond;
    }
    public void setSkillSecond(String skillSecond) {
        this.skillSecond = skillSecond;
    }
    public String getSkillThird() {
        return skillThird;
    }
    public void setSkillThird(String skillThird) {
        this.skillThird = skillThird;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getEnable() {
        return enable;
    }
    public void setEnable(String enable) {
        this.enable = enable;
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
	public int getParent_id() {
        return parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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
    public String getHiddenType() {
        return hiddenType;
    }
    public void setHiddenType(String hiddenType) {
        this.hiddenType = hiddenType;
    }
    public String getLevelCopy() {
        return levelCopy;
    }
    public void setLevelCopy(String levelCopy) {
        this.levelCopy = levelCopy;
    }
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public String getName_en_US() {
		return name_en_US;
	}
	public void setName_en_US(String name_en_US) {
		this.name_en_US = name_en_US;
	}
	public String getDescription_en_US() {
		return description_en_US;
	}
	public void setDescription_en_US(String description_en_US) {
		this.description_en_US = description_en_US;
	}
	@Override
	public String toString() {
		return "SkillVo [id=" + id + ", order=" + order + ", name=" + name
				+ ", name_en_US=" + name_en_US + ", type=" + type
				+ ", parent_id=" + parent_id + ", parent_name=" + parent_name
				+ ", description=" + description + ", description_en_US="
				+ description_en_US + ", level=" + level + ", levelCopy="
				+ levelCopy + ", enable=" + enable + ", create_time="
				+ create_time + ", create_person=" + create_person
				+ ", update_time=" + update_time + ", update_person="
				+ update_person + ", hiddenType=" + hiddenType
				+ ", skillFirst=" + skillFirst + ", skillSecond=" + skillSecond
				+ ", skillThird=" + skillThird + "]";
	}
}
