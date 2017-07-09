package com.clps.tmp.tech.plan.service.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.util.SerializeUtil;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.plan.service.PersonalPlanService;
import com.clps.tmp.tech.plan.vo.PlanSnapVo;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;

@Service("personalPlanService")
public class PersonalPlanServiceImpl extends BaseService implements PersonalPlanService {
	private static String sqlFile = "tech" + File.separator + "plan" + File.separator + "PersonalPlanSql.xml";
	@Override
	public PageVo<PlanVo> getTeachPlanTable(PageVo<PlanVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		HashMap<String, String> other = pageVo.getOther();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/get_teach_plan_table_data");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/get_teach_plan_data_count");
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
		//其他条件
		if(!(other==null||other.size()<=0)){
			for (Map.Entry<String, String> entry : other.entrySet()) {
				paramMap.put(entry.getKey(), entry.getValue());
			}
		}
		sql_page = sql_page.replace("[conditions]", conditions.toString());
		sql_count = sql_count.replace("[conditions]", conditions.toString());
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
			conditions.append(" order by `start_time` desc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<PlanVo> list=(List<PlanVo>) this.dao.query(sql_page, paramMap, BeanPropertyRowMapper.newInstance(PlanVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//时间格式转换
		for(PlanVo bean:list){
			bean.setStart_time(DateTimeUtil.databaseToSystem(bean.getStart_time()));
			bean.setEnd_time(DateTimeUtil.databaseToSystem(bean.getEnd_time()));
		}
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}
	@Override
	public ArrayList<TreeViewVo> getTeachPlan(String techerId,String planId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teach_plan_id", planId);
		// 查询语句
		String getSnapTreeSql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_teach_plan_tree_snap");
		log.info("SQL:\n" + getSnapTreeSql + "\nparam:"+paramMap.toString());
		String snapTreeStr = (String) this.dao.queryForObject(getSnapTreeSql, paramMap, String.class);
		//获取Tree Snap反序列化结果
		List<PlanSnapVo> snapTreeList = (ArrayList<PlanSnapVo>)SerializeUtil.unserialize(snapTreeStr);
		//获取涉及的知识点信息 存在pointsMap中
		//*****start
		HashMap<String,PlanVo> pointsMap = new HashMap<String,PlanVo>();
		StringBuffer pointsBuffer=new StringBuffer();
		String lang=getLang();
		for(int i=0;i<snapTreeList.size();i++){
			PlanSnapVo snapBean=snapTreeList.get(i);
			//国际化语言切换
			if(!lang.equals("")){ //英文
				snapBean.setFirst(snapBean.getFirst_en_US());
				snapBean.setSecond(snapBean.getSecond_en_US());
				snapBean.setThird(snapBean.getThird_en_US());
				snapBean.setPoint(snapBean.getPoint_en_US());
			}
			if(i==0)
				pointsBuffer.append("'"+snapBean.getPoint_id()+"'");
			else
				pointsBuffer.append(",'"+snapBean.getPoint_id()+"'");
		}
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_detail_plan_tree_snap");
		//国际化语言切换
		sql = sql.replaceAll("\\[lang\\]", lang);
		if(lang.equals("")){ //中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{ //英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		sql=sql.replaceAll("\\[point_ids\\]", pointsBuffer.toString());
		paramMap.put("teacher_id", techerId);
		log.info("SQL:\n" + sql + "\nparam:"+paramMap.toString());
		List<PlanVo> listTem = (List<PlanVo>) this.dao.query(sql, paramMap,new RowMapper(){
			@Override
			public PlanVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlanVo bean=new PlanVo();
				bean.setTeach_plan_id(rs.getString("teach_plan_id"));
				bean.setPoint_id(rs.getString("point_id"));
				bean.setPoint(rs.getString("point"));
				bean.setStarttime(rs.getString("starttime"));
				bean.setEndtime(rs.getString("endtime"));
				bean.setCost(rs.getString("cost"));
				bean.setTeacher_id(rs.getString("teacher_id"));
				bean.setTeacher_name(rs.getString("teacher_name"));
				bean.setFinish_state(rs.getString("finish_state"));
				bean.setFinish_state_name(rs.getString("finish_state_name"));
				bean.setInfo(rs.getString("info"));
				bean.setCourse_id(rs.getString("course_id"));
				return bean;
			}
			
		});
		for(int i=0;i<listTem.size();i++){
			PlanVo pTem = listTem.get(i);
			pointsMap.put(pTem.getPoint_id(), pTem);
		}
		//*****end
		// 2.整理数据
		if (snapTreeList.size() > 0) {
			HashMap<String, TreeViewVo> map11 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map22 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map33 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map44 = new HashMap<String, TreeViewVo>();
			// 一级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				if (!map11.containsKey(id1)) {
					map11.put(id1, new TreeViewVo(v1.getFirst_id(), v1.getFirst()));
				}
			}
			// 二级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id2 = v1.getSecond_id();
				if (id2 != null && !map22.containsKey(id2)) {
					map22.put(id2, new TreeViewVo(v1.getSecond_id(), v1.getSecond()));
				}
			}
			// 三级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id3 = v1.getThird_id();
				if (id3 != null && !map33.containsKey(id3)) {
					map33.put(id3, new TreeViewVo(v1.getThird_id(), v1.getThird()));
				}
			}
			// 知识点
			for (PlanSnapVo v1 : snapTreeList) {
				String id4 = v1.getPoint_id();
				// point
				//切换数据
				PlanVo pvo = pointsMap.get(v1.getPoint_id());
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("teacher_id", pvo.getTeacher_id());
				map.put("teacher_name", pvo.getTeacher_name());
				map.put("point", pvo.getPoint());
				map.put("point_id", pvo.getPoint_id());			
				map.put("starttime", DateTimeUtil.databaseToSystem(pvo.getStarttime(),false));			
				map.put("endtime", DateTimeUtil.databaseToSystem(pvo.getEndtime(),false));			
				map.put("cost", pvo.getCost());	
				Map<String,Object> costMap=DateTimeUtil.getHourMins(Double.valueOf(pvo.getCost()));
				map.put("hour", (String)costMap.get("Hours"));			
				map.put("min", (String)costMap.get("Mins"));			
				map.put("finish_state", pvo.getFinish_state());
				map.put("finish_state_name", pvo.getFinish_state_name());
				map.put("teach_plan_id", planId);	
				map.put("info", pvo.getInfo());	
				map.put("course_id", pvo.getCourse_id());	
				map44.put(id4, new TreeViewVo(pvo.getPoint_id(), v1.getPoint(), map, true));
			}
			// 3-4层关系
			ArrayList<String> tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				String id4 = v1.getPoint_id();
				if (id2 != null & id3 != null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map33.get(id3).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map33.get(id3).setNodes(alv);
						tmp.add(id4);
					}
				}
				if (id2 != null & id3 == null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map22.get(id2).setNodes(alv);
						tmp.add(id4);
					}
				}
				if (id2 == null & id3 == null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map11.get(id1).setNodes(alv);
						tmp.add(id4);
					}
				}
			}
			// 2-3层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				if (id3 != null) {
					if (id2 != null) {
						if (!tmp.contains(id3)) {
							ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
							if (alv == null) {
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map22.get(id2).setNodes(alv);
							tmp.add(id3);
						}
					} else {
						if (!tmp.contains(id3)) {
							ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
							if (alv == null) {
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map11.get(id1).setNodes(alv);
							tmp.add(id3);
						}
					}
				}
			}
			// 1-2层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				if (id2 != null) {
					if (!tmp.contains(id2)) {
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map22.get(id2));
						map11.get(id1).setNodes(alv);
						tmp.add(id2);
					}

				}
			}
			// 1
			ArrayList<TreeViewVo> al = new ArrayList<TreeViewVo>();
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				if (!tmp.contains(id1)) {
					al.add(map11.get(id1));
					tmp.add(id1);
				}
			}
			return al;
		} else {
			return null;
		}
	}
	@Override
	public int updateTeachState(String planId, String pointId, String userId, String state) throws Exception {
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/update_teach_state");
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("finish_state", state);
		paramMap.put("plan_id", planId);
		paramMap.put("point_id", pointId);
		paramMap.put("teacher_id", userId);
		return this.dao.update(sql, paramMap);
	}
	@Override
	public ArrayList<EvalVo> getProcEvalList(EvalVo procVo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("course_id", procVo.getCourse_id());
		paramMap.put("class_id", procVo.getClass_id());
		paramMap.put("point_id", procVo.getPoint_id());
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getProcEvalList");
		log.info("SQL:"+sql+"\nParam:"+paramMap.toString());
		ArrayList<EvalVo> list=(ArrayList<EvalVo>) this.dao.query(sql, paramMap, new RowMapper(){
			@Override
			public EvalVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				EvalVo bean=new EvalVo();
				bean.setUser_id(rs.getString("user_id"));
				bean.setUser_name(rs.getString("user_name"));
				bean.setUser_en_name(rs.getString("user_en_name"));
				bean.setSign(rs.getString("sign"));
				bean.setScore(rs.getInt("score"));
				bean.setComment(rs.getString("comment"));
				bean.setFlag(rs.getString("flag"));
				return bean;
			}
		});
		return list;
	}
	@Override
	public int saveProcEval(EvalVo procVo) throws Exception {
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/saveProcEval");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(procVo);
		log.info("SQL:"+sql+"\nParam:"+procVo.toString());
		return this.dao.insert(sql, paramSource);
	}
	@Override
	public PageVo<PlanVo> getLearnPlanTable(PageVo<PlanVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		HashMap<String, String> other = pageVo.getOther();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/get_leran_plan_table_data");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/get_leran_plan_data_count");
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
		//其他条件
		if(!(other==null||other.size()<=0)){
			for (Map.Entry<String, String> entry : other.entrySet()) {
				paramMap.put(entry.getKey(), entry.getValue());
			}
		}
		sql_page = sql_page.replace("[conditions]", conditions.toString());
		sql_count = sql_count.replace("[conditions]", conditions.toString());
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
			conditions.append(" order by `start_time` desc");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count+"\nparam:"+paramMap.toString());
		//查询数据
		List<PlanVo> list=(List<PlanVo>) this.dao.query(sql_page, paramMap, BeanPropertyRowMapper.newInstance(PlanVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//时间格式转换
		for(PlanVo bean:list){
			bean.setStart_time(DateTimeUtil.databaseToSystem(bean.getStart_time()));
			bean.setEnd_time(DateTimeUtil.databaseToSystem(bean.getEnd_time()));
		}
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}
	@Override
	public ArrayList<TreeViewVo> getLearnPlan(String planId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teach_plan_id", planId);
		// 查询语句
		String getSnapTreeSql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_teach_plan_tree_snap");
		log.info("SQL:\n" + getSnapTreeSql + "\nparam:"+paramMap.toString());
		String snapTreeStr = (String) this.dao.queryForObject(getSnapTreeSql, paramMap, String.class);
		//获取Tree Snap反序列化结果
		List<PlanSnapVo> snapTreeList = (ArrayList<PlanSnapVo>)SerializeUtil.unserialize(snapTreeStr);
		//获取涉及的知识点信息 存在pointsMap中
		//*****start
		HashMap<String,PlanVo> pointsMap = new HashMap<String,PlanVo>();
		StringBuffer pointsBuffer=new StringBuffer();
		String lang=getLang();
		for(int i=0;i<snapTreeList.size();i++){
			PlanSnapVo snapBean=snapTreeList.get(i);
			//国际化语言切换
			if(!lang.equals("")){ //英文
				snapBean.setFirst(snapBean.getFirst_en_US());
				snapBean.setSecond(snapBean.getSecond_en_US());
				snapBean.setThird(snapBean.getThird_en_US());
				snapBean.setPoint(snapBean.getPoint_en_US());
			}
			if(i==0)
				pointsBuffer.append("'"+snapBean.getPoint_id()+"'");
			else
				pointsBuffer.append(",'"+snapBean.getPoint_id()+"'");
		}
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_detail_plan_tree_snap_student");
		//国际化语言切换
		sql = sql.replaceAll("\\[lang\\]", lang);
		if(lang.equals("")){ //中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{ //英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		sql=sql.replaceAll("\\[point_ids\\]", pointsBuffer.toString());
		log.info("SQL:\n" + sql + "\nparam:"+paramMap.toString());
		List<PlanVo> listTem = (List<PlanVo>) this.dao.query(sql, new RowMapper(){
			@Override
			public PlanVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlanVo bean=new PlanVo();
				bean.setTeach_plan_id(rs.getString("teach_plan_id"));
				bean.setPoint_id(rs.getString("point_id"));
				bean.setPoint(rs.getString("point"));
				bean.setStarttime(rs.getString("starttime"));
				bean.setEndtime(rs.getString("endtime"));
				bean.setCost(rs.getString("cost"));
				bean.setTeacher_id(rs.getString("teacher_id"));
				bean.setTeacher_name(rs.getString("teacher_name"));
				bean.setFinish_state(rs.getString("finish_state"));
				bean.setFinish_state_name(rs.getString("finish_state_name"));
				return bean;
			}
			
		});//BeanPropertyRowMapper.newInstance(PlanVo.class)
		for(int i=0;i<listTem.size();i++){
			PlanVo pTem = listTem.get(i);
			pointsMap.put(pTem.getPoint_id(), pTem);
		}
		//*****end
		// 2.整理数据
		if (snapTreeList.size() > 0) {
			HashMap<String, TreeViewVo> map11 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map22 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map33 = new HashMap<String, TreeViewVo>();
			HashMap<String, TreeViewVo> map44 = new HashMap<String, TreeViewVo>();
			// 一级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				if (!map11.containsKey(id1)) {
					map11.put(id1, new TreeViewVo(v1.getFirst_id(), v1.getFirst()));
				}
			}
			// 二级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id2 = v1.getSecond_id();
				if (id2 != null && !map22.containsKey(id2)) {
					map22.put(id2, new TreeViewVo(v1.getSecond_id(), v1.getSecond()));
				}
			}
			// 三级技能
			for (PlanSnapVo v1 : snapTreeList) {
				String id3 = v1.getThird_id();
				if (id3 != null && !map33.containsKey(id3)) {
					map33.put(id3, new TreeViewVo(v1.getThird_id(), v1.getThird()));
				}
			}
			// 知识点
			for (PlanSnapVo v1 : snapTreeList) {
				String id4 = v1.getPoint_id();
				// point
				//切换数据
				PlanVo pvo = pointsMap.get(v1.getPoint_id());
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("teacher_id", pvo.getTeacher_id());
				map.put("teacher_name", pvo.getTeacher_name());
				map.put("point", pvo.getPoint());
				map.put("point_id", pvo.getPoint_id());			
				map.put("starttime", DateTimeUtil.databaseToSystem(pvo.getStarttime(),false));			
				map.put("endtime", DateTimeUtil.databaseToSystem(pvo.getEndtime(),false));			
				map.put("cost", pvo.getCost());	
				Map<String,Object> costMap=DateTimeUtil.getHourMins(Double.valueOf(pvo.getCost()));
				map.put("hour", (String)costMap.get("Hours"));			
				map.put("min", (String)costMap.get("Mins"));			
				map.put("finish_state", pvo.getFinish_state());
				map.put("finish_state_name", pvo.getFinish_state_name());
				map.put("teach_plan_id", planId);	
				map44.put(id4, new TreeViewVo(pvo.getPoint_id(), v1.getPoint(), map, true));
			}
			// 3-4层关系
			ArrayList<String> tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				String id4 = v1.getPoint_id();
				if (id2 != null & id3 != null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map33.get(id3).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map33.get(id3).setNodes(alv);
						tmp.add(id4);
					}
				}
				if (id2 != null & id3 == null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map22.get(id2).setNodes(alv);
						tmp.add(id4);
					}
				}
				if (id2 == null & id3 == null) {
					if (!tmp.contains(id4)) {
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map11.get(id1).setNodes(alv);
						tmp.add(id4);
					}
				}
			}
			// 2-3层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				if (id3 != null) {
					if (id2 != null) {
						if (!tmp.contains(id3)) {
							ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
							if (alv == null) {
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map22.get(id2).setNodes(alv);
							tmp.add(id3);
						}
					} else {
						if (!tmp.contains(id3)) {
							ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
							if (alv == null) {
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map11.get(id1).setNodes(alv);
							tmp.add(id3);
						}
					}
				}
			}
			// 1-2层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				if (id2 != null) {
					if (!tmp.contains(id2)) {
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if (alv == null) {
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map22.get(id2));
						map11.get(id1).setNodes(alv);
						tmp.add(id2);
					}

				}
			}
			// 1
			ArrayList<TreeViewVo> al = new ArrayList<TreeViewVo>();
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : snapTreeList) {
				String id1 = v1.getFirst_id();
				if (!tmp.contains(id1)) {
					al.add(map11.get(id1));
					tmp.add(id1);
				}
			}
			return al;
		} else {
			return null;
		}
	}
	@Override
	public ArrayList<EvalVo> getFinalEvalStuList(EvalVo procVo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("course_id", procVo.getCourse_id());
		paramMap.put("class_id", procVo.getClass_id());
		paramMap.put("teacher_id", procVo.getTeacher_id());
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getFinalEvalStuList");
		log.info("SQL:"+sql+"\nParam:"+paramMap.toString());
		ArrayList<EvalVo> list=(ArrayList<EvalVo>) this.dao.query(sql, paramMap, new RowMapper(){
			@Override
			public EvalVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				EvalVo bean=new EvalVo();
				bean.setUser_id(rs.getString("user_id"));
				bean.setUser_name(rs.getString("user_name"));
				bean.setUser_en_name(rs.getString("user_en_name"));
				bean.setScore(rs.getInt("score"));
				bean.setComment(rs.getString("comment"));
				bean.setFlag(rs.getString("flag"));
				return bean;
			}
		});
		return list;
	}
	@Override
	public int saveFinalEval(EvalVo procVo) throws Exception {
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/saveFinalEval");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(procVo);
		log.info("SQL:"+sql+"\nParam:"+procVo.toString());
		return this.dao.insert(sql, paramSource);
	}
	@Override
	public HashMap<String, Object> getStuRefEval(EvalVo procVo,String planId) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		HashMap<String,Object> rtnMap=new HashMap<String,Object>();
		paramMap.put("course_id", procVo.getCourse_id());
		paramMap.put("class_id", procVo.getClass_id());
		paramMap.put("teacher_id", procVo.getTeacher_id());
		paramMap.put("student_id", procVo.getStudent_id());
		paramMap.put("teach_plan_id", planId);
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getRefEval_avgScore");
		List<Map<String,Object>> list1=this.dao.queryForList(sql, paramMap);
		if(list1.size()==0){
			rtnMap.put("avg_score", 0);
		}else{
			rtnMap.put("avg_score", (list1.get(0)).get("score"));
		}
		sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getRefEval_attend");
		List<Map<String,Object>> list2=this.dao.queryForList(sql, paramMap);
		if(list2.size()==0){
			rtnMap.put("full", 0);
			rtnMap.put("half", 0);
			rtnMap.put("holiday", 0);
			rtnMap.put("absence", 0);
			rtnMap.put("record", 0);
		}else{
			rtnMap.put("full", (list2.get(0)).get("full"));
			rtnMap.put("half", (list2.get(0)).get("half"));
			rtnMap.put("holiday",(list2.get(0)).get("holiday"));
			rtnMap.put("absence", (list2.get(0)).get("absence"));
			rtnMap.put("record", (list2.get(0)).get("record"));
		}
		sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getRefEval_ToBe");
		rtnMap.put("tobe", (Integer)this.dao.queryForObject(sql, paramMap, Integer.class));
		return rtnMap;
	}
	@Override
	public List<SelectVo> getTeachersByPlan(String planId) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("teach_plan_id", planId);
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getTeachersByPlan");
		log.info("SQL:\n"+sql+"\nparam："+paramMap.toString());
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql, paramMap, new RowMapper(){
			@Override
			public SelectVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SelectVo bean=new SelectVo();
				bean.setId(rs.getString("id"));
				bean.setText(rs.getString("name"));
				return bean;
			}
		});
		return list;
	}
	@Override
	public HashMap<String, Object> getStuEvalTeacherRS(EvalVo procVo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("teacher_id", procVo.getTeacher_id());
		paramMap.put("student_id", procVo.getStudent_id());
		paramMap.put("course_id", procVo.getCourse_id());
		paramMap.put("class_id", procVo.getClass_id());
		String sql =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getStuEvalTeacherRS");
		List<Map<String,Object>> list=this.dao.queryForList(sql, paramMap);
		if(list.size()==0)return null;
		else return (HashMap<String, Object>) list.get(0);
	}
	
}
