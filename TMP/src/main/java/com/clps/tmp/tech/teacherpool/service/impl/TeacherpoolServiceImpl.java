package com.clps.tmp.tech.teacherpool.service.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.point.vo.PointVo;
import com.clps.tmp.tech.teacherpool.service.TeacherpoolService;
import com.clps.tmp.tech.teacherpool.vo.HistoryVo;
import com.clps.tmp.tech.teacherpool.vo.TeacherpoolVo;

/**
 * 教师业务
 * 
 * 
 */
@Service("teapoolService")
public class TeacherpoolServiceImpl extends BaseService implements TeacherpoolService {
	private static String sqlFile="tech" + File.separator + "teacherpool" + File.separator + "TeacherpoolSql.xml";
	private static String forPlanSqlFile="tech" + File.separator + "teacherpool" + File.separator + "teacherToPool" + File.separator + "TeacherToPoolSql.xml";
	@Override
	public PageVo<TeacherpoolVo> queryTeacherpoolPage(PageVo<TeacherpoolVo> pageVo) throws Exception{
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getTeacherpoolPage");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getTeacherpoolPageAllCount");
		//条件
		StringBuilder conditions = new StringBuilder();
		//起始条数
		sql_page = sql_page.replace("[start]", ((page-1)*limitPage)+"");
		//每页条数
		sql_page = sql_page.replace("[number]", limitPage+"");
		//精确查询
		conditions.append(" where 1=1 ");
		if(!(where1==null||where1.size()<=0)){
			/*for (Map.Entry<String, String> entry : where1.entrySet()) {
				conditions.append(" and ");
				conditions.append(entry.getKey());
				conditions.append(" ='");
				conditions.append(entry.getValue());
				conditions.append("' ");
			}	*/
			boolean flag = false;
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				if (!flag) {
					conditions.append(" and ");
				} else {
					flag = true;
				}
				conditions.append(entry.getKey());
				conditions.append("='");
				conditions.append(entry.getValue());
				conditions.append("'");
			}
		} else {
			conditions.append(" ");
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			/*for (Map.Entry<String, String> entry : where2.entrySet()) {
				conditions.append(" and ");
				conditions.append(entry.getKey());
				conditions.append(" like '%");
				conditions.append(entry.getValue());
				conditions.append("%' ");
			}*/
			boolean flag=false;
			if (where1 == null || where1.size() <= 0) {
				conditions.append(" and ");
			} else {
				conditions.append(" and ");
			}
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				if (flag) {
					conditions.append(" and ");
				} else {
					flag = true;
				}
				conditions.append(entry.getKey());
				conditions.append(" like '%");
				conditions.append(entry.getValue());
				conditions.append("%'");
			}
		} else {
			conditions.append(" ");
		}
		sql_page = sql_page.replace("[where]", conditions.toString());
		sql_count = sql_count.replace("[where]", conditions.toString());
		//排序
		conditions = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			conditions.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				conditions.append(" `"+entry.getKey()+"`");
				conditions.append(" ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by `update_time` desc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		sql_page=sql_page.replaceAll("\\[lang\\]", getLang());
		//国际化语言切换
		if(getLang().equals("")){
			//中文
			sql_page=sql_page.replaceAll("\\[name\\]", "name");
		}else{
			//英文
			sql_page=sql_page.replaceAll("\\[name\\]", "en_name");
		}
		//sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<TeacherpoolVo> list=(List<TeacherpoolVo>) this.dao.query(sql_page, BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}
	//@Override
	/*public int addTeacher(TeacherpoolVo tepVo) throws Exception {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(tepVo);
		String insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacher");
		log.info("\nSQL:\n"+insertSql+"\nparam\n:"+paramSource.toString());
		return this.dao.insert(insertSql, paramSource);
	}
*/
	
	@Override
	public int deleteTeacher(String nbr) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("user_id", nbr);
		String checkUerSql=SystemSQLConstant.CHECK_USER_RELEVANT;
		log.info("\nSQL:\n"+checkUerSql+ "\nparam:"+paramMap.toString());
		int check=(Integer) this.dao.queryForObject(checkUerSql, paramMap, Integer.class);
		if(check==0){//无关联，可以删除
			//删除用户所有角色
			String delUserRole=SystemSQLConstant.DELETE_USER_ALLROLES;
			log.info("\nSQL:\n" +delUserRole+"\nparam :"+paramMap.toString());
			this.dao.delete(delUserRole, paramMap);
			//删除用户
			String delUserSql=SystemSQLConstant.DELETE_USER_INFO;
			log.info("\nSQL:\n" +delUserSql+"\nparam:"+paramMap.toString());
			this.dao.delete(delUserSql, paramMap);
			return 1;//删除成功
		}else{
			return -1;//有关联，不能删除
		}
	}
	@SuppressWarnings("static-access")
	@Override
	public TeacherpoolVo detailTeacehr(String nbr) throws Exception{
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("nbr", nbr);
			String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getTeacherByNbr");
			sql=sql.replaceAll("\\[lang\\]", getLang());
			//国际化语言切换
			if(getLang().equals("")){
				//中文
				sql=sql.replaceAll("\\[name\\]", "name");
			}else{
				//英文
				sql=sql.replaceAll("\\[name\\]", "en_name");
			}

			log.info("\nSQL:\n"+sql+"\nparam\n:"+paramMap.toString());
			List<TeacherpoolVo> list=(List<TeacherpoolVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
			List<TeacherpoolVo> list2 = new ArrayList<TeacherpoolVo>();
			DateTimeUtil at = new DateTimeUtil();
			for (TeacherpoolVo t : list) {
				t.setCreate_time(at.databaseToSystem(t.getCreate_time()));
				t.setUpdate_time(at.databaseToSystem(t.getUpdate_time()));
				t.setBirthday(at.databaseToSystem(t.getBirthday()));
				list2.add(t);
			}
			if(list2.size()==0) return null;
			return list2.get(0);
		}
	/*@Override
	public int updateTeacher(TeacherpoolVo tepVo) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(tepVo);
		String updateSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateTeacher");
		log.info("\nSQL:\n"+updateSql+"\nparam\n:"+paramSource.toString());
		return this.dao.update(updateSql, paramSource);
	}*/
//	@Override
//	public int addTeacher(TeacherpoolVo tepVo) throws Exception {
//		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(tepVo);
//		String insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacher");
//		log.info("\nSQL:\n"+insertSql+"\nparam\n:"+paramSource.toString());
//		return this.dao.insert(insertSql, paramSource);
//	}

	@Override
	public boolean updateTeacher(TeacherpoolVo tepVo, boolean flag)
			throws Exception {
			// TODO Auto-generated method stub
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("nbr", tepVo.getNbr());
			if (true) {
				paramMap.put("role", tepVo.getRole());
			}
			paramMap.put("username", tepVo.getUsername());
			paramMap.put("password", tepVo.getPassword());
			paramMap.put("name", tepVo.getName());
			paramMap.put("en_name", tepVo.getEn_name());
			paramMap.put("gender", tepVo.getGender());
			paramMap.put("mobile", tepVo.getMobile());
			paramMap.put("phone", tepVo.getPhone());
			paramMap.put("email", tepVo.getEmail());
			paramMap.put("age", tepVo.getAge());
			paramMap.put("birthday", tepVo.getBirthday());
			paramMap.put("education_background", tepVo.getEducation_background());
			paramMap.put("degree", tepVo.getDegree());
			paramMap.put("university", tepVo.getUniversity());
			paramMap.put("college", tepVo.getCollege());
			paramMap.put("major", tepVo.getMajor());
			paramMap.put("cet4", tepVo.getCet4());
			paramMap.put("cet6", tepVo.getCet6());
			paramMap.put("teacType", tepVo.getTeacType());
			paramMap.put("gpa", tepVo.getGpa());
			paramMap.put("description", tepVo.getDescription());
			paramMap.put("direction", tepVo.getDirection());
			paramMap.put("contact_address", tepVo.getContact_address());
			paramMap.put("contact_postcode", tepVo.getContact_postcode());
			paramMap.put("home_address", tepVo.getHome_address());
			paramMap.put("home_postcode", tepVo.getHome_postcode());
			paramMap.put("resume", tepVo.getResume());
			paramMap.put("enable", tepVo.getEnable());
			paramMap.put("update_time", tepVo.getUpdate_time());
			paramMap.put("update_person", tepVo.getUpdate_person());
			String updateSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateTeacher");
			log.info("SQL:\n" + updateSql);
			int re = this.dao.update(updateSql, paramMap);
			if (re != 0) {
				return true;
			} else {
				return false;
			}
		}


	@SuppressWarnings("static-access")
	@Override
	public PageVo<TeacherpoolVo> queryHistory(PageVo<TeacherpoolVo> pageVo,String nbr)
			throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		//HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getHistory");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getHistoryAllCount");
		
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("teacher_id", nbr);
		//条件
		StringBuilder conditions = new StringBuilder();
		//起始条数
		sql_page = sql_page.replace("[start]", ((page-1)*limitPage)+"");
		//每页条数
		sql_page = sql_page.replace("[number]", limitPage+"");
		//模糊查询
		sql_page = sql_page.replace("[where]", conditions.toString());
		sql_count = sql_count.replace("[where]", conditions.toString());
		//排序
		conditions = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			conditions.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				conditions.append(" `"+entry.getKey()+"`");
				conditions.append(" ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by `update_time` desc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<TeacherpoolVo> list=(List<TeacherpoolVo>) this.dao.query(sql_page,paramMap, BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
		List<TeacherpoolVo> list2 = new ArrayList<TeacherpoolVo>();
		DateTimeUtil at = new DateTimeUtil();
		for (TeacherpoolVo t : list) {
			t.setStart_time(at.databaseToSystem(t.getStart_time()));
			t.setEnd_time(at.databaseToSystem(t.getEnd_time()));
			t.setCreate_time(at.databaseToSystem(t.getCreate_time()));
			t.setUpdate_time(at.databaseToSystem(t.getUpdate_time()));
			t.setConfirmTime(at.databaseToSystem(t.getConfirmTime()));
			list2.add(t);
		}
		int allcount=(Integer) this.dao.queryForObject(sql_count,paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list2);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public PageVo<PointVo> querySkillPage(PageVo<PointVo> pageVo)
			throws Exception {
		//取出数据
				int page = pageVo.getPage();
				int limitPage = pageVo.getLimitPage();
				HashMap<String, String> where1 = pageVo.getWhere1();
				HashMap<String, String> where2 = pageVo.getWhere2();
				LinkedHashMap<String, String> sort = pageVo.getSort();
				//获取SQL语句
				String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getSkillPage");
				String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getSkillPageAllCount");
				//条件
				StringBuilder conditions = new StringBuilder();
				//起始条数
				sql_page = sql_page.replace("[start]", ((page-1)*limitPage)+"");
				//每页条数
				sql_page = sql_page.replace("[number]", limitPage+"");
				//精确查询
				if(!(where1==null||where1.size()<=0)){
					for (Map.Entry<String, String> entry : where1.entrySet()) {
						conditions.append(" and ");
						conditions.append(entry.getKey());
						conditions.append(" ='");
						conditions.append(entry.getValue());
						conditions.append("' ");
					}	
				}
				sql_page = sql_page.replace("[where]", conditions.toString());
				sql_count = sql_count.replace("[where]", conditions.toString());
				//排序
				conditions = new StringBuilder();
				if(!(sort==null||sort.size()<=0)){
					conditions.append(" order by ");
					for (Map.Entry<String, String> entry : sort.entrySet()) {
						conditions.append(" `"+entry.getKey()+"`");
						conditions.append(" ");
						conditions.append(entry.getValue()+",");
					}
					conditions.deleteCharAt(conditions.length()-1);
				}else{
					conditions.append(" order by e.`order` asc,d.`order` asc,c.`order` asc ");
				}
				sql_page = sql_page.replace("[order]", conditions.toString());	
				
				sql_page=sql_page.replaceAll("\\[lang\\]", getLang());
				//国际化语言切换
				if(getLang().equals("")){
					//中文
					sql_page=sql_page.replaceAll("\\[name\\]", "name");
				}else{
					//英文
					sql_page=sql_page.replaceAll("\\[name\\]", "en_name");
				}	
				sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
				log.info("SQL：\n"+sql_page+"\n"+sql_count);
				//其他自定义条件
				Map<String,Object> paramMap=new HashMap<String,Object>();
				for(Map.Entry<String, String> entry: pageVo.getOther().entrySet() ){
					paramMap.put(entry.getKey(), entry.getValue());
				}
				log.info("param:"+paramMap.toString());
				//查询数据
				List<PointVo> list=(List<PointVo>) this.dao.query(sql_page, paramMap, BeanPropertyRowMapper.newInstance(PointVo.class));
				int allcount=(Integer) this.dao.queryForObject(sql_count,  paramMap, Integer.class);
				//汇总数据
				pageVo.setList(list);
				pageVo.setAllcount(String.valueOf(allcount));
				return pageVo;
			}

	@Override
	public int addTeacSkillPoint(String teacherId, String pointId, String skillId ,String grant)
			throws Exception {
			log.info("技能ID:"+skillId+" 知识点ID:"+pointId);
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			String insertSql=null, deleteSql=null;
			if(pointId==null || "".equals(pointId)){//按照技能添加
				paramMap.put("skill_id", skillId);
				paramMap.put("nbr", teacherId);
				paramMap.put("grant", grant);
				deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delTeacherSkill");
				insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacherSkill");
			}else{//按照知识点添加
				paramMap.put("point_id",pointId);
				paramMap.put("nbr", teacherId);
				paramMap.put("grant", grant);
				deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delTeacherPoint");
				insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertSkillPoint");
			}
			log.info("\nSQL:\n"+deleteSql+"\n"+insertSql+"\nparam\n:"+paramMap.toString());
			this.dao.delete(deleteSql, paramMap);
			int effect2=this.dao.insert(insertSql, paramMap);
			return effect2;
		}

	@Override
	public int removeTeacherSkill(String nbr, String skillId) throws Exception {
			log.info("技能ID:"+skillId);
			String deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delTeacherSkill");
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("skill_id", skillId);
			paramMap.put("nbr", nbr);
			log.info("\nSQL:\n"+deleteSql+"\nparam\n:"+paramMap.toString());
			return this.dao.delete(deleteSql, paramMap);
		}

	@Override
	public PageVo<PointVo> queryPointPage(PageVo<PointVo> pageVo, String nbr,
			String skillId) throws Exception {
		log.info("技能ID:"+skillId);
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointPage");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointPageAllCount");
		//条件
		StringBuilder conditions = new StringBuilder();
		//起始条数
		sql_page = sql_page.replace("[start]", ((page-1)*limitPage)+"");
		//每页条数
		sql_page = sql_page.replace("[number]", limitPage+"");
		//排序
		conditions = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			conditions.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				conditions.append(" `"+entry.getKey()+"`");
				conditions.append(" ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by a.`order` asc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("skill_id", skillId);
		paramMap.put("nbr", nbr);
		//查询数据
		List<PointVo> list=(List<PointVo>) this.dao.query(sql_page, paramMap,BeanPropertyRowMapper.newInstance(PointVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int managePoint(HashMap<String, Object> paramMap, String description)
			throws Exception {
		String insertsql=null,deleteSql=null;
		if("add".equals(description)){
			insertsql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacherPoint");
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delTeacherPoint");
			this.dao.delete(deleteSql, paramMap);
			this.dao.insert(insertsql, paramMap);
		}else{
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delTeacherPoint");
			this.dao.delete(deleteSql, paramMap);
		}
		return 0;
	}

	@Override
	public LinkedList<HashMap<String, Object>> reviewTeacher(String nbr)
			throws Exception {
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("nbr", nbr);
			String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/reviewTeacher");
			querySql=querySql.replaceAll("\\[lang\\]", getLang());
			log.info("\nSQL:\n"+querySql+"\nparam\n:"+paramMap.toString());
			List<Map<String,Object>> resultlist=this.dao.queryForList(querySql, paramMap);
			LinkedList<HashMap<String,Object>> list=new LinkedList<HashMap<String,Object>>();
			for(int i=0;i<resultlist.size();i++){
				HashMap<String,Object> map=(HashMap<String, Object>) resultlist.get(i);
				setTeacherList(list,map);
			}
			return list;
		}
	///////////////////////////////////
	/**
	 * 生成课程知识点列表<br>将Map记录存放list中
	 * 2015年11月23日 Seven
	 */
	private void setTeacherList(LinkedList<HashMap<String,Object>> list, HashMap<String,Object> map){
		if(!checkExist(list,true,(String) map.get("first_id"))){//一级 不存在，添加
			add(list,true,null,map);
		}
		if(!checkExist(list,true,(String) map.get("second_id"))){//二级 不存在，添加
			add(list,true,(String) map.get("first_id"),map);
		}
		if(!checkExist(list,true,(String) map.get("third_id"))){//三级 不存在，添加
			add(list,true,(String) map.get("second_id"),map);
		}
		if(!checkExist(list,false,(String) map.get("point_id"))){//四级(知识点) 不存在，添加
			String skill=("".equals((String) map.get("third_id")) || null==(String) map.get("third_id"))?
						 (("".equals((String) map.get("second_id")) || null==(String) map.get("second_id"))?(String) map.get("first_id"):(String) map.get("second_id")):
							 (String) map.get("third_id");
			add(list,false,skill,map);
		}
	}
	/**
	 * 添加数据
	 * 
	 */
	private void add(LinkedList<HashMap<String,Object>> l1, boolean isSkill, String pId,HashMap<String,Object> map){
		if(pId==null){
			HashMap<String,Object> l1_mapRec=new HashMap<String,Object>();
			l1_mapRec.put("id", map.get("first_id"));
			l1_mapRec.put("text", map.get("first"));
			l1_mapRec.put("tags", new LinkedList<String>());
			l1_mapRec.put("nodes", new LinkedList<HashMap<String,Object>>());
			l1.add(l1_mapRec);
			return;
		}
		for(int i=0;i<l1.size();i++){
			HashMap<String,Object> l1_mapRec=l1.get(i);
			if(l1_mapRec.get("nodes")==null){//尾节点
				continue;
			}
			if(pId.equals(l1_mapRec.get("id"))){
				LinkedList<HashMap<String,Object>> l2=(LinkedList<HashMap<String, Object>>) l1_mapRec.get("nodes");
				HashMap<String,Object> mapRec=new HashMap<String,Object>();
				if(isSkill){
					mapRec.put("id", map.get("second_id"));
					mapRec.put("text", map.get("second"));
					mapRec.put("nodes", new LinkedList<HashMap<String,Object>>());
				}else{
					mapRec.put("id", map.get("point_id"));
					mapRec.put("text", map.get("point"));
				}
				mapRec.put("tags", new LinkedList<String>());
				l2.add(mapRec);
				return;
			}else{
				LinkedList<HashMap<String,Object>> l2=(LinkedList<HashMap<String, Object>>) l1_mapRec.get("nodes");
				for(int j=0;j<l2.size();j++){
					HashMap<String,Object> l2_mapRec=l2.get(j);
					if(l2_mapRec.get("nodes")==null){//尾节点
						continue;
					}
					if(pId.equals(l2_mapRec.get("id"))){
						LinkedList<HashMap<String,Object>> l3=(LinkedList<HashMap<String, Object>>) l2_mapRec.get("nodes");
						HashMap<String,Object> mapRec=new HashMap<String,Object>();
						if(isSkill){
							mapRec.put("id", map.get("third_id"));
							mapRec.put("text", map.get("third"));
							mapRec.put("nodes", new LinkedList<HashMap<String,Object>>());
						}else{
							mapRec.put("id", map.get("point_id"));
							mapRec.put("text", map.get("point"));
						}
						mapRec.put("tags", new LinkedList<String>());
						l3.add(mapRec);
						return;
					}else{
						LinkedList<HashMap<String,Object>> l3=(LinkedList<HashMap<String, Object>>) l2_mapRec.get("nodes");
						for(int k=0;k<l3.size();k++){
							HashMap<String,Object> l3_mapRec=l3.get(k);
							if(l3_mapRec.get("nodes")==null){//尾节点
								continue;
							}
							if(pId.equals(l3_mapRec.get("id")) && !isSkill){
								LinkedList<HashMap<String,Object>> l4=(LinkedList<HashMap<String, Object>>) l3_mapRec.get("nodes");
								HashMap<String,Object> mapRec=new HashMap<String,Object>();
								mapRec.put("id", map.get("point_id"));
								mapRec.put("text", map.get("point"));
								mapRec.put("tags", new LinkedList<String>());
								l4.add(mapRec);
								return;
							}
						}
					}
				}
			}
		}
	}
	/**
	 * 查看sourceId是否已经存在于list中
	 * 
	 */
	private boolean checkExist(LinkedList<HashMap<String,Object>> l1,boolean isSkill,String sourceId){
		if(sourceId==null) return true;
		for(int i=0;i<l1.size();i++){
			HashMap<String,Object> l1_mapRec=l1.get(i);
			if(sourceId.equals(l1_mapRec.get("id")) && isSkill && l1_mapRec.get("nodes")!=null)
				return true;
			LinkedList<HashMap<String,Object>> l2=(LinkedList<HashMap<String, Object>>) l1_mapRec.get("nodes");
			int l2_length=(l2==null?0:l2.size());
			for(int j=0;j<l2_length;j++){
				HashMap<String,Object> l2_mapRec=l2.get(j);
				if(sourceId.equals(l2_mapRec.get("id")) && isSkill && l2_mapRec.get("nodes")!=null)
					return true;
				LinkedList<HashMap<String,Object>> l3=(LinkedList<HashMap<String, Object>>) l2_mapRec.get("nodes");
				int l3_length=(l3==null?0:l3.size());
				for(int k=0;k<l3_length;k++){
					HashMap<String,Object> l3_mapRec=l3.get(k);
					if(sourceId.equals(l3_mapRec.get("id")) && isSkill && l3_mapRec.get("nodes")!=null)
						return true;
					LinkedList<HashMap<String,Object>> l4=(LinkedList<HashMap<String, Object>>) l3_mapRec.get("nodes");
					int l4_length=(l4==null?0:l4.size());
					for(int t=0;t<l4_length;t++){
						HashMap<String,Object> l4_mapRec=l4.get(t);
						if(sourceId.equals(l4_mapRec.get("id")) && !isSkill)
							return true;
					}
				}
			}
		}
		return false;
	}
///////////////////////////////////
	@Override
	public String queryTeacherPhoto(String nbr) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nbr", nbr);
		 String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/queryTeacherPhoto");
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<TeacherpoolVo> list = (List<TeacherpoolVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
		if (list != null) {
			return list.get(0).getPhoto();
		}
		return null;
	}

	@Override
	public boolean updateTeacherPhoto(String photo, String nbr)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("photo", photo);
		paramMap.put("nbr", nbr);
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateTeacherPhoto");
		log.info("SQL:\n" + sql);
		int re = this.dao.update(sql, paramMap);
		if (re != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addTeacher(TeacherpoolVo tepVo, UserVo userNow)
			throws Exception {
		// TODO Auto-generated method stub
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("role", tepVo.getRole());
				paramMap.put("username", tepVo.getUsername());
				SecurityHelper sh = new SecurityHelper();
				paramMap.put("password", sh.DESEncrypt(tepVo.getPassword()));
				GenerateNextNo generate=new GenerateNextNo();
				paramMap.put("no", generate.getNextPersonNo(tepVo.getRole()));
				paramMap.put("name", tepVo.getName());
				paramMap.put("en_name", tepVo.getEn_name());
				paramMap.put("gender", tepVo.getGender());
				paramMap.put("mobile", tepVo.getMobile());
				paramMap.put("teacType", tepVo.getTeacType());
				paramMap.put("phone", tepVo.getPhone());
				paramMap.put("email", tepVo.getEmail());
				paramMap.put("age", tepVo.getAge());
				paramMap.put("birthday", tepVo.getBirthday().replaceAll("-", ""));
				paramMap.put("education_background", tepVo.getEducation_background());
				paramMap.put("degree", tepVo.getDegree());
				paramMap.put("university", tepVo.getUniversity());
				//paramMap.put("college", tepVo.getCollege());
				paramMap.put("major", tepVo.getMajor());
				//paramMap.put("cet4", tepVo.getCet4());
				//paramMap.put("cet6", tepVo.getCet6());
				//paramMap.put("gpa", tepVo.getGpa());
				paramMap.put("cardNbr", tepVo.getCardNbr());
				paramMap.put("cardType", tepVo.getCardType());
				paramMap.put("description", tepVo.getDescription());
				paramMap.put("direction", tepVo.getDirection());
				paramMap.put("contact_address", tepVo.getContact_address());
				paramMap.put("contact_postcode", tepVo.getContact_postcode());
				paramMap.put("home_address", tepVo.getHome_address());
				paramMap.put("home_postcode", tepVo.getHome_postcode());
				//paramMap.put("resume", tepVo.getResume());
				paramMap.put("enable", tepVo.getEnable());
				paramMap.put("create_time", DateTimeUtil.nowToDatabase());
				paramMap.put("create_person", userNow.getName());
				paramMap.put("update_time", DateTimeUtil.nowToDatabase());
				paramMap.put("update_person", userNow.getName());
				paramMap.put("photo", tepVo.getPhoto());
				String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacher");
				log.info("SQL:\n" + sql);
				int re = this.dao.insert(sql, paramMap);
				if (re != 0) {
					//获取用户id
					sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getUserId");
					int userId=(Integer)this.dao.queryForObject(sql, paramMap, Integer.class);
					//插入角色
					sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertTeacherRole");
					paramMap=null;
					HashMap<String,Object> paramMap2=new HashMap<String,Object>();
					paramMap2.put("user_id", userId);
					this.dao.insert(sql, paramMap2);
					return true;
				} else {
					return false;
				}
			}
	/**
	 * 检查数据的唯一
	 */
	@Override
	public int checkData(String name, String mobile) throws Exception {
		
			// 搜索条件
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 获取SQL语句
			String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/queryDataOnly");
			StringBuilder sb = new StringBuilder();
			sb.append(" where ");
			if(name!=null){
				sb.append("`username`=\"").append(name).append("\"");
			}else{
				sb.append("`mobile`=\"").append(mobile).append("\"");
			}	
			sql = sql.replace("[where]", sb.toString());
			// 查询
			@SuppressWarnings("unchecked")
			List<TeacherpoolVo> list = (List<TeacherpoolVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
			// 返回
			if(list==null||list.size()==0){
				//出错!
				return 0;
			}else{
				String s = list.get(0).getNumber();
				if(s.equals("0")){
					//成功!
					return 1;
				}else{
					//重复
					return 2;
				}
			}	
			
		}

	@Override
	public boolean reVokeTeacher(String nbr,String pointId) throws Exception {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("nbr", nbr);
		paramMap.put("pointId", pointId);
		String updateSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/revokeManage");
		log.info("SQL:\n"+updateSql);
		int r=this.dao.update(updateSql, paramMap);
		if(r!=0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addManage(String nbr, String pointId) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("nbr", nbr);
		map.put("pointId", pointId);
		String updateSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/addManage");
		log.info("SQL:\n"+updateSql);
		int rr=this.dao.update(updateSql, map);
		if(rr!=0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public PageVo<TeacherpoolVo> queryTeachersBySpecialPoint(String pointId, String starttime,String endtime, PageVo<TeacherpoolVo> pageVo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();//参数
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(forPlanSqlFile, "//SQL/getTeachersBySpecialPoint");
		String sql_count = SqlLoader.loadSqlFromXml(forPlanSqlFile, "//SQL/getTeachersBySpecialPointCount");
		//条件
		StringBuilder conditions = new StringBuilder();
		//起始条数
		sql_page = sql_page.replace("[start]", ((page-1)*limitPage)+"");
		//每页条数
		sql_page = sql_page.replace("[number]", limitPage+"");
		//精确查询
		conditions.append(" where 1=1 ");
		if(!(where1==null||where1.size()<=0)){
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				conditions.append(" and ");
				conditions.append(entry.getKey());
				conditions.append(" ='");
				conditions.append(entry.getValue());
				conditions.append("' ");
			}	
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			boolean judgeName=false;
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				if(!entry.getKey().equals("b.`name`") && !entry.getKey().equals("b.`en_name`")){
					conditions.append(" and ");
					conditions.append(entry.getKey());
					conditions.append(" like '%");
					conditions.append(entry.getValue());
					conditions.append("%' ");
				}else{
					judgeName=true;
				}
			}
			if(judgeName){
				conditions.append(" and ( b.`name` ");
				conditions.append(" like '%");
				conditions.append(where2.get("b.`name`"));
				conditions.append("%' or ");
				
				conditions.append(" b.`en_name` ");
				conditions.append(" like '%");
				conditions.append(where2.get("b.`en_name`"));
				conditions.append("%') ");
			}
		}
		sql_page = sql_page.replace("[where]", conditions.toString());
		sql_count = sql_count.replace("[where]", conditions.toString());
		//排序
		conditions = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			conditions.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				conditions.append(" `"+entry.getKey()+"`");
				conditions.append(" ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by `no` asc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		paramMap.put("point_id", pointId);
		paramMap.put("starttime", starttime);
		paramMap.put("endtime", endtime);
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count+"\nparam:"+paramMap.toString());
		
		//查询数据
		List<TeacherpoolVo> list=(List<TeacherpoolVo>) this.dao.query(sql_page,paramMap, BeanPropertyRowMapper.newInstance(TeacherpoolVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public void recordTechHistory(HistoryVo histVo) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(histVo);
		//先remove可能相同的
		String sql=SqlLoader.loadSqlFromXml(forPlanSqlFile, "//SQL/removeSameTeachHist");
		log.info("\nSQL:"+sql+"\nparam:"+histVo.toString());
		this.dao.delete(sql, paramSource);
		//插入
		sql=SqlLoader.loadSqlFromXml(forPlanSqlFile, "//SQL/insertTeachHist");
		log.info("\nSQL:"+sql+"\nparam:"+histVo.toString());
		this.dao.insert(sql, paramSource);
	}

	
}