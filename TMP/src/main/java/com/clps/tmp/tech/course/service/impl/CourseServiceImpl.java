package com.clps.tmp.tech.course.service.impl;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.course.service.CourseService;
import com.clps.tmp.tech.course.vo.CourseVo;
import com.clps.tmp.tech.point.vo.PointVo;
/**
 * 课程业务
 * @author Seven
 * 2015年11月16日
 */
@Service("courseService")
public class CourseServiceImpl extends BaseService implements CourseService {
	private static String sqlFile="tech" + File.separator + "course" + File.separator + "CourseSql.xml";
	@Override
	public PageVo<CourseVo> queryCoursePage(PageVo<CourseVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		HashMap<String, String> other = pageVo.getOther();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getCoursePage");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getCoursePageAllCount");
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
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				conditions.append(" and ");
				conditions.append(entry.getKey());
				conditions.append(" like '%");
				conditions.append(entry.getValue());
				conditions.append("%' ");
			}
		}
		//其他条件 in
		if(!(other==null||other.size()<=0)){
			for (Map.Entry<String, String> entry : other.entrySet()) {
				conditions.append(" and ");
				conditions.append(entry.getKey());
				conditions.append(" in (");
				String[] value=entry.getValue().split(",");
				for(int ii=0;ii<value.length;ii++){
					if(ii==0){
						conditions.append("'"+value[ii].trim()+"'");
					}else{
						conditions.append(",'"+value[ii].trim()+"'");
					}
				}
				conditions.append(") ");
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
			conditions.append(" order by `update_time` desc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<CourseVo> list=(List<CourseVo>) this.dao.query(sql_page, BeanPropertyRowMapper.newInstance(CourseVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}
	
	@Override
	public int addCourse(CourseVo course) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(course);
		String insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertCourse");
		log.info("\nSQL:\n"+insertSql+"\nparam\n:"+paramSource.toString());
		return this.dao.insert(insertSql, paramSource);
	}

	@Override
	public CourseVo getCourseById(String id) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/queryCourse");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+sql+"\nparam\n:"+paramMap.toString());
		List<CourseVo> list=(List<CourseVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(CourseVo.class));
		for(int i=0;i<list.size();i++){
			CourseVo bean=list.get(i);
			bean.setLast_update_time(bean.getUpdate_time());
			bean.setLast_update_person(bean.getUpdate_person());
			bean.setCreate_time(DateTimeUtil.databaseToSystem(bean.getCreate_time()));
			bean.setUpdate_time(DateTimeUtil.databaseToSystem(bean.getUpdate_time()));
		}
		if(list.size()==0) return null;
		return list.get(0);
	}

	@Override
	public int editCourse(CourseVo course) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(course);
		String updateSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateCourse");
		log.info("\nSQL:\n"+updateSql+"\nparam\n:"+paramSource.toString());
		return this.dao.update(updateSql, paramSource);
	}

	@Override
	public int deleteCourse(String id) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		
		//删除之前check是否被教学计划使用
		String checkCourseSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkTeachPlanWhenDelete");
		log.info("\nSQL:\n"+checkCourseSql+"\nparam\n:"+paramMap.toString());
		int inPlanUseCount=(Integer) this.dao.queryForObject(checkCourseSql, paramMap, Integer.class);
		if(inPlanUseCount==0){//没有教学计划使用，可以删除
			//删除之前check是否被教学计划使用
			String checkOtherSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkOtherWhenDelete");
			log.info("\nSQL:\n"+checkOtherSql+"\nparam\n:"+paramMap.toString());
			int inOtherUseCount=(Integer) this.dao.queryForObject(checkOtherSql, paramMap, Integer.class);
			if(inOtherUseCount==0){//没有除教学计划之外的其他关联，可以删除
				//删除课程-知识点Mapping
				String delMappingSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deleteCoursePointMapping");
				log.info("\nSQL:\n"+delMappingSql+"\nparam\n:"+paramMap.toString());
				int effect2=this.dao.delete(delMappingSql, paramMap);
				
				//删除课程
				String delCourseSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deleteCourse");
				log.info("\nSQL:\n"+delCourseSql+"\nparam\n:"+paramMap.toString());
				int effect1=this.dao.delete(delCourseSql, paramMap);
				
				return effect1+effect2;
			}else{//还有除教学计划之外的其他关联，可以删除
				return -2;
			}
		}else{//有教学计划使用到此课程，不允许删除
			return -1;
		}
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
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
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
	public int addSkillPoint(String courseId, String pointId, String skillId) throws Exception {
		log.info("技能ID:"+skillId+" 知识点ID:"+pointId);
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		String insertSql=null, deleteSql=null;
		if(pointId==null || "".equals(pointId)){//按照技能添加
			paramMap.put("skill_id", skillId);
			paramMap.put("course_id", courseId);
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delCourseSkill");
			insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertCourseSkill");
		}else{//按照知识点添加
			paramMap.put("point_id", pointId);
			paramMap.put("course_id", courseId);
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delCoursePoint");
			insertSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertCoursePoint");
		}
		log.info("\nSQL:\n"+deleteSql+"\n"+insertSql+"\nparam\n:"+paramMap.toString());
		int effect1=this.dao.delete(deleteSql, paramMap);
		int effect2=this.dao.insert(insertSql, paramMap);
		return effect2;
	}

	@Override
	public int removeCourseSkill(String courseId, String skillId) throws Exception {
		log.info("技能ID:"+skillId);
		String deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delCourseSkill");
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("skill_id", skillId);
		paramMap.put("course_id", courseId);
		log.info("\nSQL:\n"+deleteSql+"\nparam\n:"+paramMap.toString());
		return this.dao.delete(deleteSql, paramMap);
	}

	@Override
	public PageVo<PointVo> queryPointPage(PageVo<PointVo> pageVo, String courseId, String skillId) throws Exception {
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
		paramMap.put("course_id", courseId);
		//查询数据
		List<PointVo> list=(List<PointVo>) this.dao.query(sql_page, paramMap,BeanPropertyRowMapper.newInstance(PointVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int managePoint(HashMap<String, Object> paramMap, String operation)
			throws Exception {
		String insertsql=null,deleteSql=null;
		if("add".equals(operation)){
			insertsql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertCoursePoint");
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delCoursePoint");
			this.dao.delete(deleteSql, paramMap);
			this.dao.insert(insertsql, paramMap);
		}else{
			deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delCoursePoint");
			this.dao.delete(deleteSql, paramMap);
		}
		return 0;
	}

	@Override
	public LinkedList<HashMap<String, Object>> reviewCourse(String courseId) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("course_id", courseId);
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/reviewCourse");
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+querySql+"\nparam\n:"+paramMap.toString());
		List<Map<String,Object>> resultlist=this.dao.queryForList(querySql, paramMap);
		LinkedList<HashMap<String,Object>> list=new LinkedList<HashMap<String,Object>>();
		for(int i=0;i<resultlist.size();i++){
			HashMap<String,Object> map=(HashMap<String, Object>) resultlist.get(i);
			setCourseList(list,map);
		}
		return list;
	}
	/**
	 * 生成课程知识点列表<br>将Map记录存放list中
	 * 2015年11月23日 Seven
	 */
	private void setCourseList(LinkedList<HashMap<String,Object>> list, HashMap<String,Object> map){
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
	 * 2015年11月23日 Seven
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
	 * 2015年11月23日 Seven
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
				if(sourceId.equals(l2_mapRec.get("id")) && isSkill  && l2_mapRec.get("nodes")!=null)
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

	@Override
	public List<SelectVo> getCourseOption(HashMap<String, Object> paramMap) throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getCourseOption");
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+querySql+"\nparam:\n"+paramMap.toString());
		return (List<SelectVo>) this.dao.query(querySql, paramMap, BeanPropertyRowMapper.newInstance(SelectVo.class));
	}

	@Override
	public CourseVo queryCourseByNo(String courseNo) throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getCourseByNo");
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("no", courseNo);
		List<CourseVo> list=(List<CourseVo>) this.dao.query(querySql, paramMap, BeanPropertyRowMapper.newInstance(CourseVo.class));
		if(list.size()==0)return null;
		return list.get(0);
	}

	@Override
	public CourseVo queryCourseById(String courseId) throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getCourseById");
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", courseId);
		List<CourseVo> list=(List<CourseVo>) this.dao.query(querySql, paramMap, BeanPropertyRowMapper.newInstance(CourseVo.class));
		if(list.size()==0)return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getSubCategory() throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getSubCategory");
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		List<SelectVo> list=(List<SelectVo>) this.dao.query(querySql, new RowMapper<SelectVo>(){
			@Override
			public SelectVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SelectVo bean=new SelectVo();
				bean.setId(rs.getString("id"));
				bean.setText(rs.getString("text"));
				return bean;
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getSubSubCategory() throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getSubSubCategory");
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		List<SelectVo> list=(List<SelectVo>) this.dao.query(querySql, new RowMapper<SelectVo>(){
			@Override
			public SelectVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SelectVo bean=new SelectVo();
				bean.setId(rs.getString("id"));
				bean.setText(rs.getString("text"));
				return bean;
			}
		});
		return list;
	}

	@Override
	public List<Map<String, Object>> getCourseData(Map<String, Object> paramMap) throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/downloadCourseData");
		StringBuilder where =new StringBuilder();
		where.append(" where 1=1 ");
		//精确
		if(paramMap.get("dir")!=null && !paramMap.get("dir").equals("")){
			where.append(" and aa.`direction` = :dir ");
		}
		if(paramMap.get("prop")!=null && !paramMap.get("prop").equals("")){
			where.append(" and aa.`property` = :prop ");
		}
		if(paramMap.get("category")!=null && !paramMap.get("category").equals("")){
			where.append(" and aa.`category` = :category ");
		}
		if(paramMap.get("enable")!=null && !paramMap.get("enable").equals("")){
			where.append(" and aa.`enable` = :enable");
		}
		//模糊
		if(paramMap.get("no")!=null && !paramMap.get("no").equals("")){
			where.append(" and aa.`no` like '%"+paramMap.get("no")+"%'");
		}
		if(paramMap.get("name")!=null && !paramMap.get("name").equals("")){
			where.append(" and aa.`name` like '%"+paramMap.get("name")+"%'");
		}
		//其他 in
		if(paramMap.get("scatrgory")!=null && !paramMap.get("scatrgory").equals("")){
			where.append(" and e.`id` in (");
			String[] value=((String)paramMap.get("scatrgory")).split(",");
			for(int ii=0;ii<value.length;ii++){
				if(ii==0){
					where.append("'"+value[ii].trim()+"'");
				}else{
					where.append(",'"+value[ii].trim()+"'");
				}
			}
			where.append(") ");
		}
		if(paramMap.get("sscategory")!=null && !paramMap.get("sscategory").equals("")){
			where.append(" and d.`id` in (");
			String[] value=((String)paramMap.get("sscategory")).split(",");
			for(int ii=0;ii<value.length;ii++){
				if(ii==0){
					where.append("'"+value[ii].trim()+"'");
				}else{
					where.append(",'"+value[ii].trim()+"'");
				}
			}
			where.append(") ");
		}
		querySql=querySql.replace("[where]",where.toString());
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+querySql+"\nparam:"+paramMap.toString());
		return dao.queryForList(querySql, paramMap);
	}
}
