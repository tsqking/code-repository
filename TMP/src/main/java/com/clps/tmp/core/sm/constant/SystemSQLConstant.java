package com.clps.tmp.core.sm.constant;

/**
 * 系统模块SQL语句
  * @ClassName: SystemSQLConstant
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 上午10:48:36
 */
public class SystemSQLConstant {

	//根据账户名去SM_PERSON表查询用户信息
	public static final String QUERY_USER_INFO = 
			"select id,role,username,password,no,name,en_name,gender,mobile,phone,email,age,birthday,education_background,"
			+ "degree,university,college,major,cet4,cet6,gpa,description,direction,contact_address,contact_postcode,home_address,"
			+ "home_postcode,photo,resume,enable,create_time,create_person,update_time,update_person "
			+ "from SM_PERSON "
			+ "where username=:username or mobile=:username";
	
	//根据用户查找菜单
	public static final String QUERY_USER_MENU = 
			"select c.id,c.name[lang] as `name`,c.url,c.level,c.parent_id,c.order "
			+ "from SM_MENU c "
			+ "join (select distinct menu_id from SM_ROLE_MENU a join SM_PERSON_ROLE b on a.role_id=b.role_id left join `SM_ROLE` c on a.`role_id`=c.`id` where c.`enable`='T' and b.user_id=:id) d "
			+ "on d.menu_id=c.id "
			+ "where c.enable='T' "
			+ "order by c.level asc,c.order asc;";
	
	/**********************************************************菜单管理 start*******************************************************************/
	//菜单分页查询
	public static final String QUERY_MENU_PAGE =
			"select a.`id`,a.`name`,a.`description`,a.`url`,a.`level`,a.`order`,a.`enable`,c.`name` `enable_name`,b.`name[lang]` `parent_id` "
			+ "FROM ( select a.`id`,case when b.`parent_id`='0' then b.`id` else b.`parent_id` end as super_parent_id, "
			+ "a.`name[lang]` `name`,a.`description[lang]` `description`,a.`url`,a.`level`,a.`order`,a.`enable`,a.`parent_id`  from SM_MENU a left join SM_MENU b on a.`parent_id`=b.`id` ) a "
			+ "left join `SM_MENU` b on a.`parent_id`=b.`id` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') c on a.`enable`=c.`value` "
			+ "[where] [order] limit [start],[number];";	
	
	//菜单分页查询
	public static final String QUERY_MENU_ALLCOUNT = 
			"SELECT count(*) allcount "
			+ "FROM ( select a.`id`,case when b.`parent_id`='0' then b.`id` else b.`parent_id` end as super_parent_id, "
			+ "a.`name`,a.`description`,a.`url`,a.`level`,a.`order`,a.`enable`,a.`parent_id`  from SM_MENU a left join SM_MENU b on a.`parent_id`=b.`id` ) a "
			+ "left join `SM_MENU` b on a.`parent_id`=b.`id` "
			+ "[where]";

	//获得菜单名根据id
	public static final String QUERY_MENU_NAME = 
			"Select `name` from SM_OPTION where `id`=:id;";
	
	//根据level查询菜单
	public static final String QUERY_MENU_BY_LEVEL = 
			"select `id` as `id`,`name[lang]` as `text` "
			+ "from SM_MENU "
			+ "where level=:level";
	
	//根据id查找菜单信息
	public static final String QUERY_MENU_BY_ID = 
			"select a.`id`,a.`name`,a.`name_en_US`,a.`description`,a.`description_en_US`,a.`url`,a.`level`,a.`order`,a.`enable`,c.`name` `enable_name`,a.`parent_id`,b.`name[lang]` `parent_name`, "
			+"a.`create_time`,a.`create_person`,a.`update_time`,a.`update_person` "
			+"FROM ( select a.`id`,case when b.`parent_id`='0' then b.`id` else b.`parent_id` end as super_parent_id, "
			+"a.`name`,a.`name_en_US`,a.`description`,a.`description_en_US`,a.`url`,a.`level`,a.`order`,a.`enable`,a.`parent_id`,a.`create_time`,a.`create_person`,a.`update_time`,a.`update_person` "	
			+"from SM_MENU a left join SM_MENU b on a.`parent_id`=b.`id` ) a "
			+"left join `SM_MENU` b on a.`parent_id`=b.`id` "
			+"left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') c on a.`enable`=c.`value` "
			+ "WHERE a.`id`=:id";
	
	//根据id删除菜单
	public static final String DELETE_MENU_BY_ID = "DELETE FROM `SM_MENU` [where] ;";
			
	//级联查找菜单id
	public static final String CHECK_MENU_RELEVANT = 
			"select sum(`ct`) as `ct` "
			+ "from ( "
			+ "     select 'id' as `id`,count(*) as `CT` FROM `SM_ROLE_MENU` [WHERE] "
			+ ") a "
			+ "group by `id` ";
	
	//级联查找菜单id
	public static final String QUERY_MENU_ALL = 
			"select distinct id "
					+"FROM ( "
					+"   select id  "
					+"   from ( "
					+"       select a.`id`,a.`name`,a.`description`,a.`url`,a.`level`,a.`order`,a.`enable`,a.`parent_id`, "
					+"              case when b.`parent_id`='0' then b.`id` else b.`parent_id` end as super_parent_id "
					+"       from SM_MENU a left join SM_MENU b on a.`parent_id`=b.`id` " 
					+"       ) a "
					+"   where a.super_parent_id=:id "
					+"   union all "
					+"   select id from SM_MENU where parent_id=:id "
					+"   union all "
					+"   select id from SM_MENU where id=:id "
					+"   ) a  ";
	
	//更新菜单信息
	public static final String UPDATE_MENU = 
			"UPDATE `SM_MENU` SET "
			+ "`name`=:name, `description`=:description,`name_en_US`=:name_en_US, `description_en_US`=:description_en_US, `url`=:url, "
			+ "`order`=case when :order is null then 1 when cast( :order as char) ='null' then 1 when LENGTH(trim(:order))<1 then 1 else :order end, `enable`=:enable, `update_time`=:update_time, `update_person`=:update_person "
			+ "WHERE `id`=:id;";
	
	//菜单等级排序下拉框
	public static final String QUERY_MENU_ORDER = 
			"SELECT `name[lang]` `text`,`order` `id` FROM SM_MENU WHERE `level`=:level [where] ORDER BY `order` asc; ";
	
	//添加子菜单
	public static final String ADD_MENU_TO_PMENU =
			"INSERT INTO `SM_MENU` "
			+ "(`name`, `description`, `name_en_US`, `description_en_US`, `url`, `level`, `parent_id`, "
			+ "`order`, `enable`, `create_time`, `create_person`, `update_time`, `update_person`) "
			+ "VALUES "
			+ "(:name, :description, :name_en_US, :description_en_US, :url, :level, :parent_id, "
			+ "case when :order is null then 1 when cast( :order as char) ='null' then 1 when LENGTH(trim(:order))<1 then 1 else :order end, :enable, :create_time, :create_person, :update_time, :update_person);";
	
	//获取所有菜单并根据角色判断是否拥有
	public static final String QUERY_ALL_MENU_AND_CHECK_ROLE=
			"select a.`id`,a.`name[lang]` as `name`,a.`url`,a.`level`,a.`parent_id`,a.`order`, "
			+ "       case when b.`role_id` is not null then 'checked' else '' end as `description` "
			+ "from `SM_MENU` a left join (select * from`SM_ROLE_MENU` where `role_id`=:role_id ) b on a.`id`=b.`menu_id`";
	
	/**********************************************************菜单管理 end*******************************************************************/

	/**********************************************************选项框管理 start*******************************************************************/
	//根据选项组调用值查询选项
	public static final String QUERY_OPTIOIN_BY_GP_VAL = 
			"select a.`value` as `id`,a.`name[lang]` as `text` "
					+ "from SM_OPTION a left join SM_OPTION b on a.`parent_id`=b.`id` "
					+ "where 1=1 "
					+ "and a.enable='T' "
					+ "and b.`enable`='T' "
					+ "and b.`value`= :gp_value "
					+ "order by a.`order` asc";
	
	//根据选项level查询选项
	public static final String QUERY_OPTIOIN_BY_LEVEL = 
			"select `value` as `id`,`name[lang]` as `text` "
			+ "from SM_OPTION "
			+ "where `level`= :level";

	//获取所有选项组
	public static final String QUERY_OPTIOIN_GROUPS =
			"select `value` as `id`,`name[lang]` as `text` "
			+ "from `SM_OPTION` "
			+ "where `type`= '0' ";
	
	//选项框分页查询
	public static final String QUERY_OPTION_PAGE =
			"select a.`name[lang]` as `name`,d.`name` as `type`, "
			+ "     a.`value`,b.`name[lang]` as `parent_id`,a.`order`,c.`name` as `enable`,a.`create_time`,a.`create_person`, "
			+ "     a.`update_time`,a.`update_person`,a.`id`  "
			+ "from  `SM_OPTION` a left join `SM_OPTION` b on  a.`parent_id`=b.`id`  "
			+ "      left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') c on a.`enable`=c.`value` "
			+ "      left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='OPTION_TYPE') d on a.`type`=d.`value` "
			+ "[where]  [order] limit [start],[number];";
	
	//选项框分页查询
	public static final String QUERY_OPTION_ALLCOUNT =
			"select count(*) as allcount "
			+ "from  `SM_OPTION` a left join `SM_OPTION` b on  a.`parent_id`=b.`id`  "
			+ "      left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') c on a.`enable`=c.`value`  "
			+ "      left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='OPTION_TYPE') d on a.`type`=d.`value` "
			+ "[where] ";
	
	//删除选项(组)
	public static final String DELETE_OPTION =
			"delete from SM_OPTION "
			+ "where id in ( "
			+ "      select id "
			+ "      from ("
			+ "  		select a.id "
			+ "  		from SM_OPTION a "
			+ "  		where id= :id "
			+ "  		union all "
			+ "  		select a.id "
			+ "  		from SM_OPTION a left join SM_OPTION b on a.parent_id=b.id  "
			+ "  		where b.id= :id  "
			+ "      )a"
			+ ")";
	
	//查询选项 根据id
	public static final String QUERY_OPTION_BY_ID =
			"select a.`id`,a.`name`,a.`name_en_US`,a.`level`,a.`type`,a.`value`,b.`name[lang]` as parent_name,a.parent_id,a.`order`,a.`enable`,"
			+ "a.create_time,a.create_person,a.update_time,a.update_person "
			+ "from `SM_OPTION` a left join `SM_OPTION` b on a.`parent_id`=b.`id` "
			+ "where a.id=:id ";
	
	//更新选项 根据id
	public static final String UPDATE_OPTION_BY_ID =
			"update `SM_OPTION` "
			+ "set    `name`=:name,`name_en_US`=:name_en_US,`value`=:value,`order`=case when :order is null then 1 when cast( :order as char) ='null' then 1 when LENGTH(trim(:order))<1 then 1 else :order end,"
			+ "		  `enable`=:enable,`update_person`=:update_person "
			+ "       ,`update_time`=:update_time "
			+ "where  `id`=:id "
			+ "and    `update_time`=:last_update_time ";
	
	//查询是否存在相同Value的选项组
	public static final String QUERY_OPTION_GP_BY_VALUE =
			"select count(*) ct from `SM_OPTION` where `value`=:value";
	
	//查询除指定id外是否存在相同Value的选项组
	public static final String QUERY_OTHER_OPTION_GP_BY_VALUE =
			"select count(*) ct from `SM_OPTION` where `value`=:value and `id` != :id " ;
	
	//查询某个组下是否存在相同value的选项
	public static final String QUERY_OPTIONS_BY_VALUE_PID =
			"select count(*) ct from `SM_OPTION` where parent_id=:parent_id and value=:value";
	
	//查询某个组下除指定id外是否存在相同value的选项
	public static final String QUERY_OTHER_OPTIONS_BY_VALUE_PID =
			"select count(*) ct from `SM_OPTION` where parent_id=:parent_id and value=:value and `id` != :id ";
		
	//增加选项
	public static final String ADD_OPTION =
			"insert into `SM_OPTION`(`name`,`name_en_US`,`level`,`type`,`value`,`parent_id`,`order`,`enable`,`create_time`,`create_person`,`update_time`,`update_person`) "
			+ "values(:name,:name_en_US,:level,:type,:value,:parent_id,case when :order is null then 1 when cast( :order as char) ='null' then 1 when LENGTH(trim(:order))<1 then 1 else :order end,:enable,:create_time,:create_person,:update_time,:update_person)";
	/**********************************************************选项框管理 end*******************************************************************/
	
	/**********************************************************角色管理 start*******************************************************************/
	//查询角色分页信息
	public static final String QUERY_ROLE_PAGE =
			"select a.`id`,a.`name[lang]` as `name`,a.`description[lang]` as `description`,b.`name[lang]` as `enable`,a.`create_time`,a.`create_person`,a.`update_time`,a.`update_person` "
			+ "from `SM_ROLE` a left join (select o1.`name[lang]`,o1.`value` from `SM_OPTION`  o1 join  `SM_OPTION`  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') b  "
			+ "on a.`enable`=b.`value`  "
			+ "[where] [order] limit [start],[number]; ";
	
	//查询角色分页信息之数据总数
	public static final String QUERY_ROLE_ALLCOUNT =
			"select count(*) ct "
			+ "from `SM_ROLE` a "
			+ "[where]";
	
	//删除角色
	public static final String DELETE_ROLE =
			"delete from `SM_ROLE` where `id`=:id ";
	
	//删除角色-删除人员角色对应
	public static final String DELDTE_PERSON_ROLE =
			"delete from `SM_PERSON_ROLE` where `role_id`=:id ";
			
	//删除角色-删除菜单角色对应
	public static final String DELETE_MENU_ROLE =
			"delete from `SM_ROLE_MENU` where `role_id`=:id ";
	
	//根据id查询角色
	public static final String QUERY_ROLE_BY_ID =
			"select `id`,`name`,`name_en_US`,`description`,`description_en_US`,`enable`,`create_time`,`create_person`,`update_time`,`update_person` "
			+ "from `SM_ROLE` "
			+ "where `id`=:id ";
	
	//根据id更新角色信息
	public static final String UPDATE_ROLE_BY_ID =
			"update `SM_ROLE` "
			+ "set `name`=:name,`name_en_US`=:name_en_US,`description`=:description,`description_en_US`=:description_en_US,`enable`=:enable, "
			+ "    `update_time`=:update_time,`update_person`=:update_person "
			+ "where `id`=:id "
			+ "and `update_time`=:last_update_time ";
	//角色添加
	public static final String ADD_ROLE =
			"insert into `SM_ROLE`(`name`,`name_en_US`,`description`,`description_en_US`,`enable`,`create_time`,`create_person`,`update_time`,`update_person`) "
			+ "values(:name,:name_en_US,:description,:description_en_US,:enable,:create_time,:create_person,:update_time,:update_person) ";
	
	//根据角色id删除角色菜单
	public static final String DELETE_ROLE_MENU_BY_ROLE_ID =
			"delete from `SM_ROLE_MENU` where `role_id`=:role_id ";
	
	//插入角色菜单
	public static final String INSERT_ROLE_MENU =
			"insert into `SM_ROLE_MENU`(`role_id`,`menu_id`) values(:role_id,:menu_id) ";
	
	//查询角色人员分页信息
	public static final String QUERY_ROLE_PERSON =
			"select distinct a.`id`,a.`role`,a.`username`,a.`no`,a.`name`,a.`en_name`,a.`gender`,a.`role_name`, "
			+ "       f.`name` as `description` "
			+ "from ( "
			+ "      select a.`id`,d.`name` as `role`, "
			+ "             a.`username`,a.`no`,a.`name`,a.`en_name`, "
			+ "             e.`name` as `gender`, "
			+ "             case WHEN b.`role_have` is null then '' else b.`role_have` end as `role_name`, "
			+ "				case when c.`user_ID` IS NULL THEN '0' else '1' end as `description` "
			+ "      from `SM_PERSON` a left join(   "
			+ "               select a.`user_id`,cast(group_concat(b.`name[lang]`) as CHAR) as `role_have` "
			+ "               from (select distinct * from `SM_PERSON_ROLE`) a left join `SM_ROLE` b on a.`role_id`=b.`id` "
			+ "               group by a.user_id "
			+ "           ) b on a.`id`=b.`user_id`  "
			+ "           left join (select `user_id` from `SM_PERSON_ROLE` where `role_id`=:role_id) c on a.`id`=c.`user_id` "
			+ "           left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='USER_TYPE') d on a.`role`=d.`value`  "
			+ "           left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='SEX') e on a.`gender`=e.`value` "
			+ "   )a left join (select o1.`name[lang]` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='HAS') f on a.`description`=f.`value`  "
			+ "[where] [order] limit [start],[number] ";
	
	//查询角色人员分页信息
	public static final String QUERY_ROLE_PERSON_ALLCOUNT =
			"select count(*) ct "
			+ "from ( "
			+ "      select distinct a.`id`,d.`name` as `role`, "
			+ "             a.`username`,a.`no`,a.`name`,a.`en_name`, "
			+ "             e.`name` as `gender`,  "
			+ "             case WHEN b.`role_have` is null then '' else b.`role_have` end as `role_name`, "
			+ "				     case when c.`user_ID` IS NULL THEN '0' else '1' end as `description`  "
			+ "      from `SM_PERSON` a left join(   "
			+ "               select a.`user_id`,cast(group_concat(b.`name_en_US`) as CHAR) as `role_have` "
			+ "               from (select distinct * from `SM_PERSON_ROLE`) a left join `SM_ROLE` b on a.`role_id`=b.`id` "
			+ "               group by a.user_id "
			+ "           ) b on a.`id`=b.`user_id`  "
			+ "           left join (select `user_id` from `SM_PERSON_ROLE` where `role_id`=:role_id) c on a.`id`=c.`user_id` "
			+ "           left join (select o1.`name_en_US` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='USER_TYPE') d on a.`role`=d.`value` "
			+ "           left join (select o1.`name_en_US` as `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='SEX') e on a.`gender`=e.`value` "
			+ "   )a "
			+ "[where] ";
	
	//从某角色下移除某用户
	public static final String DELDTE_PERSON_IN_ROLE=
			"delete from `SM_PERSON_ROLE` "
			+ "where `user_id` in("
			+ "                  select `id` "
			+ "                  from `SM_PERSON` "
			+ "                  where `username` =:username"
			+ "                  ) "
			+ "and `role_id`=:role_id";
	
	//插入某用户至某角色下
	public static final String INSERT_PERSON_IN_ROLE=
			"insert into `SM_PERSON_ROLE`(`user_id`,`role_id`) "
			+ "select `id`,:role_id as `role_id` "
			+ "from `SM_PERSON` "
			+ "where username =:username ";
	
	/**********************************************************角色管理  end*******************************************************************/
			
	/**********************************************************账户管理 end*******************************************************************/
	//账户分页查询
	public static final String QUERY_USER_PAGE =
			"select a.`id`,a.`username`,a.`role`,d.`name` `role_name`,a.`[name]` `name`,a.`gender`,e.`name` `gender_name`,a.`age`,a.`mobile`,a.`email`,a.`enable`,c.`name` `enable_name` from SM_PERSON a "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') c on a.`enable`=c.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='USER_TYPE') d on a.`role`=d.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='SEX') e on a.`gender`=e.`value` "
			+ "[where] [order] limit [start],[number];";
		
		
	//账户分页查询
	public static final String QUERY_USER_ALLCOUNT = 
			"SELECT count(*) allcount FROM SM_PERSON a [where]";
		
		
	//账户详细信息查询
	public static final String QUERY_USER_DETAIL = 
			"SELECT  a.`id`,a.`exam_num`,a.`cardtype`,e.`name` `cardtype_name`,a.`cardno`,a.`role`,a.`username`,a.`password`,a.`no`,a.`name`,a.`en_name`,a.`gender`,a.`mobile`,a.`phone`,a.`email`,a.`age`,a.`birthday`,"
			+ "a.`education_background`,f.`name` `education_background_name`,a.`degree`,g.`name` `degree_name`,a.`university`"
			+ ",a.`college`,a.`major`,a.`cet4`,a.`cet6`,a.`gpa`,a.`description`,a.`direction`,a.`contact_address`,a.`contact_postcode`,a.`home_address`,a.`home_postcode`,a.`resume`,a.`enable`"
			+ ",a.`create_time`,a.`create_person`,a.`update_time`,a.`update_person`,b.`name` `enable_name`,c.`name` `role_name`,d.`name` `gender_name` FROM SM_PERSON a "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') b on a.`enable`=b.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='USER_TYPE') c on a.`role`=c.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='SEX') d on a.`gender`=d.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='CARD_TYPE') e on a.`cardtype`=e.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='EDU_BAK') f on a.`education_background`=f.`value` "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='DEGREE') g on a.`degree`=g.`value` "
			+ "WHERE a.`id`=:id;";
		
	//查找账户图片
	public static final String QUERY_USER_PHOTO = 
			"SELECT `photo` FROM SM_PERSON WHERE `id`=:id;";
	
	//更新用户基本数据
	public static final String UPDATE_USER_INFO = 
			"UPDATE `SM_PERSON` SET "
			+ "[role] `username`=:username, `password`=:password, `name`=:name, `en_name`=:en_name, `cardtype`=:cardtype, `cardno`=:cardno," 
			+ "`gender`=:gender, `mobile`=:mobile, `phone`=:phone, `email`=:email, `age`=case when :age is null then 0 when cast( :age as char) ='null' then 0 when LENGTH(trim(:age))<1 then 0 else :age end," 
			+ "`birthday`=:birthday, `education_background`=:education_background," 
			+ "`degree`=:degree, `university`=:university, `college`=:college," 
			+ "`major`=:major, `cet4`=case when :cet4 is null then 0 when cast( :cet4 as char) ='null' then 0 when LENGTH(trim(:cet4))<1 then 0 else :cet4 end, "
			+ "`cet6`=case when :cet6 is null then 0 when cast( :cet6 as char) ='null' then 0 when LENGTH(trim(:cet6))<1 then 0 else :cet6 end, "
			+ "`gpa`=case when :gpa is null then 0 when cast( :gpa as char) ='null' then 0 when LENGTH(trim(:gpa))<1 then 0 else :gpa end, `description`=:description," 
			+ "`direction`=:direction, `contact_address`=:contact_address, `contact_postcode`=:contact_postcode," 
			+ "`home_address`=:home_address, `home_postcode`=:home_postcode, `exam_num`=:exam_num," 
			+ "`resume`=:resume, `enable`=:enable, `update_time`=:update_time, `update_person`=:update_person "
			+ "WHERE `id`=:id;";
	
	//更新用户头像
	public static final String UPDATE_USER_PHOTO = 
			"UPDATE `SM_PERSON` SET `photo`=:photo WHERE `id`=:id;";
	
	//更新用户头像
	public static final String ADD_USER_INFO = 
			"INSERT INTO `SM_PERSON` (" 
			+ "`role`, `username`, `password`, `no`, `name`, `en_name`," 
			+ "`gender`, `mobile`, `phone`, `email`, `age`, `birthday`,"  
			+ "`education_background`, `degree`, `university`,"  
			+ "`college`, `major`, `cet4`, `cet6`, `gpa`, `description`,"  
			+ "`direction`, `contact_address`, `contact_postcode`,"  
			+ "`home_address`, `home_postcode`, `resume`,"  
			+ "`enable`, `create_time`, `create_person`,"  
			+ "`update_time`, `update_person`, `photo`, `cardtype`, `cardno`, `exam_num`) VALUES (" 
			+ ":role , :username , :password , :no , :name ,"  
			+ ":en_name , :gender , :mobile , :phone , :email ,"  
			+ "case when :age is null then 0 when cast( :age as char) ='null' then 0 when LENGTH(trim(:age))<1 then 0 else :age end , :birthday , :education_background , :degree , :university ,"  
			+ ":college , :major , case when :cet4 is null then 0 when cast( :cet4 as char) ='null' then 0 when LENGTH(trim(:cet4))<1 then 0 else :cet4 end , "
			+ "case when :cet6 is null then 0 when cast( :cet6 as char) ='null' then 0 when LENGTH(trim(:cet6))<1 then 0 else :cet6 end , "
			+ "case when :gpa is null then 0 when cast( :gpa as char) ='null' then 0 when LENGTH(trim(:gpa))<1 then 0 else :gpa end ,"  
			+ ":description , :direction , :contact_address , :contact_postcode , :home_address ,"  
			+ ":home_postcode , :resume , :enable , :create_time , :create_person ,"  
			+ ":update_time , :update_person, :photo, :cardtype, :cardno, :exam_num );";

	//check人员所有关联
	public static final String CHECK_USER_RELEVANT = 
			"select sum(`CT`) as `ct` "
			+ "from ( "
			//+ "       select 'id' as `id`,count(*) as `CT` FROM `SM_PERSON_ROLE` WHERE `user_id`=:user_id "//人员角色对应
			//+ "       union all "
			+ "       select 'id' as `id`,count(*) as `CT` FROM `CLASS_STUDENT` WHERE `student_id`=:user_id "//人员班级对应
			+ "       union all "
			+ "       select 'id' as `id`,count(*) as `CT` FROM `GENERAL_MATUAL_EVAL` WHERE `student_id`=:user_id or `teacher_id`=:user_id "//总评互评
			+ "       union all "
			+ "       select 'id' as `id`,count(*) as `CT` FROM `PROCESS_EVAL` WHERE `student_id`=:user_id or `teacher_id`=:user_id "//过程评价
			+ "       union all "
			+ "       select 'id' as `id`,count(*) as `CT` FROM `TEACH_HIST` WHERE `teacher_id`=:user_id "//教学历史
			+ "       union all "
			+ "       select 'id' as `id`,count(*) as `CT` FROM `TEACHER_POINT` WHERE `teacher_id`=:user_id "//教师技能
			+ ") a "
			+ "group by `id` ";
	
	//删除人员
	public static final String DELETE_USER_INFO = 
			"DELETE FROM `SM_PERSON` WHERE `id`=:user_id;";
	
	//角色管理分页
	public static final String QUERY_USER_ROLE_PAGE =
			"select a.`id`,a.`name[lang]` `name`,a.`description[lang]` `description`,a.`enable`,a.`create_time`,a.`create_person`,a.`update_time`,a.`update_person`,b.`name` `enable_name` from SM_ROLE a "
			+ "left join (select o1.`name[lang]` `name`,o1.`value` from SM_OPTION  o1 join  SM_OPTION  o2  on o1.`parent_id`=o2.`id` where  o2.`value`='STATUS') b on a.`enable`=b.`value` "
			+ "[where] [order] limit [start],[number];";
	
	//角色总条数
	public static final String QUERY_USER_ROLE_ALLCOUNT = 
			"SELECT count(*) allcount FROM SM_ROLE a [where]";
	
	//获取用户的所有角色
	public static final String QUERY_USER_ROLES = 
			"select `role_id` `id` from `SM_PERSON_ROLE` where `user_id` = :id;";
	
	//去除所有角色
	public static final String DELETE_USER_ALLROLES = 
			"DELETE FROM `SM_PERSON_ROLE` WHERE `user_id`=:user_id;";
	
	//增加角色
	public static final String ADD_USER_ROLE = 
			"INSERT INTO `SM_PERSON_ROLE` ( `user_id` , `role_id` ) VALUES ( :userId , :roleId );";
	
	//去除角色
	public static final String DELETE_USER_ROLE = 
			"DELETE FROM `SM_PERSON_ROLE` WHERE `user_id`=:userId and `role_id`=:roleId;";
	
	//验证用户名是否已用
	public static final String EXIST_USERNAME = 
			"select count(*) as ct from `SM_PERSON` where username=:userName ";
	
	//验证用户名是否已用
	public static final String EXIST_MOBILE = 
			"select count(*) as ct from `SM_PERSON` where mobile=:mobile ";
	
	//批量插入用户
	public static final String BATCH_ADD_USER = 
			"INSERT INTO `SM_PERSON` (" 
			+ "`role`, `username`, `password`, `no`, `name`, `en_name`," 
			+ "`gender`, `mobile`, `phone`, `email`, `age`, `birthday`,"  
			+ "`education_background`, `degree`, `university`,"  
			+ "`college`, `major`, `cet4`, `cet6`, `gpa`, "  
			+ "`contact_address`, `contact_postcode`,"  
			+ "`home_address`, `home_postcode`, `cardtype`, `cardno`,"  
			+ "`enable`, `create_time`, `create_person`,"  
			+ "`update_time`, `update_person`,`exam_num`) VALUES (" 
			+ ":role , :username , :password , :no , :name ,"  
			+ ":en_name , :gender , :mobile , :phone , :email ,"  
			+ "case when :age is null then 0 when cast( :age as char) ='null' then 0 when LENGTH(trim(:age))<1 then 0 else :age end , :birthday , :education_background , :degree , :university ,"  
			+ ":college , :major , case when :cet4 is null then 0 when cast( :cet4 as char) ='null' then 0 when LENGTH(trim(:cet4))<1 then 0 else :cet4 end , "
			+ "case when :cet6 is null then 0 when cast( :cet6 as char) ='null' then 0 when LENGTH(trim(:cet6))<1 then 0 else :cet6 end , "
			+ "case when :gpa is null then 0 when cast( :gpa as char) ='null' then 0 when LENGTH(trim(:gpa))<1 then 0 else :gpa end ,"  
			+ ":contact_address , :contact_postcode , :home_address ,"  
			+ ":home_postcode , :cardtype, :cardno, :enable , :create_time , :create_person ,"  
			+ ":update_time , :update_person, :exam_num);";
	
	//去除人员基本角色
	public static final String BATCH_REMOVE_USER_BASE_ROLE = 
			"delete from `SM_PERSON_ROLE` where (`role_id`,`user_id`) in "
			+ "(select getBaseRole('3') as role_id,`id` as user_id  "
			+ "from `SM_PERSON` "
			+ "where `username`=:username) ";
	
	//关联人员对应角色(0-超级管理员 1-管理员 2-教师 3-学生)
	public static final String BATCH_ADD_USER_BASE_ROLE = 
			"insert into `SM_PERSON_ROLE`(`role_id`,`user_id`) "
			+ "select getBaseRole(:role) as role_id,`id` as user_id  "
			+ "from `SM_PERSON` "
			+ "where `username`=:username ";
	
	//检查数据的唯一性
	public static final String QUERY_DATA_ONLYONE = 
			"SELECT count(*) `number` FROM SM_PERSON [where]";
	
	/**********************************************************账户管理 end*******************************************************************/
	
	/********************************************************假期Date管理 start***************************************************************/
	//获取某个月及其前后两个月的假期
	public static final String QUERY_HOLIDAYS_BY_NEAR_MONTH = 
			"select concat_ws('-',substring(`date`,1,4),substring(`date`,5,2),substring(`date`,7,2)) as `date` "
			+ "from `CALENDAR` "
			+ "where 1=1 "
			+ "and (date like :last_month or date like :month or date like :next_month) "
			+ "and holiday='Y' ";
	
	//设置某一天是否为假期  
	public static final String SET_HOLIDAY = 
			"update `CALENDAR` set holiday=:holiday where date=:date ";
	
	//填充日历  直至date止 同时date这一天被设置为假期
	public static final String FILL_CALENDAR = 
			"call fill_calendar(:date);";
	
	//查询接下来两个工作日
	public static final String QUERY_NEXT_TWO_WORKDAY =
			"select `date` "
			+ "from `CALENDAR` "
			+ "where `holiday`='N' "
			+ "and `date`>=:date "
			+ "order by `date` asc "
			+ "limit :start,2 ";
	
	//查询接下来两个工作日
	public static final String QUERY_WORKDAY_INTERVAL =
			"select count(*)-1 as `ct` "
			+ "from `CALENDAR` "
			+ "where `holiday`='N'  "
			+ "and `date`>=:startday  "
			+ "and `date`<=:endday "
			+ "order by `date` asc ";
	/*********************************************************假期Date管理 end****************************************************************/
	
}
