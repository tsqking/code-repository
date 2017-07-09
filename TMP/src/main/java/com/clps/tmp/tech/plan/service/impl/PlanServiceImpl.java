package com.clps.tmp.tech.plan.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.util.SerializeUtil;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.PlanService;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.PlanSnapVo;

/**
 * 教学计划管理
 * 
 * @ClassName: PlanServiceImpl
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年12月3日 下午2:25:40
 */
@Service("planService")
public class PlanServiceImpl extends BaseService implements PlanService {

	private static String sqlFile = "tech" + File.separator + "plan"
			+ File.separator + "PlanSql.xml";

	/**
	 * 教学计划管理分页查询 queryUserPage(这里用一句话描述这个方法的作用) TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * @Title: queryUserPage
	 * @Description: Comsys-liuchen
	 * @param @param pv
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return PageVo<UserVo> 返回类型
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@Override
	public PageVo<PlanVo> queryPlanPage(PageVo<PlanVo> pv) throws Exception {
		// TODO Auto-generated method stub
		// 取出数据
		int page = pv.getPage();
		int limitPage = pv.getLimitPage();
		HashMap<String, String> where1 = pv.getWhere1();
		HashMap<String, String> where2 = pv.getWhere2();
		HashMap<String, String> dateTime = pv.getDateTime();
		LinkedHashMap<String, String> sort = pv.getSort();
		// 获取SQL语句
		String sql1 = SqlLoader
				.loadSqlFromXml(sqlFile, "//SQL/query_plan_page");
		String sql2 = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_plan_allcount");
		// 条件
		StringBuilder sb = new StringBuilder();
		// 起始条数
		sql1 = sql1.replace("[start]", ((page - 1) * limitPage) + "");
		// 每页条数
		sql1 = sql1.replace("[number]", limitPage + "");
		// 搜索条件
		sb = new StringBuilder();
		// 精确查询
		if (!(where1 == null || where1.size() <= 0)) {
			boolean flag = false;
			sb.append(" where ");
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				if (flag) {
					sb.append(" and ");
				} else {
					flag = true;
				}
				sb.append("a.`");
				sb.append(entry.getKey());
				sb.append("`='");
				sb.append(entry.getValue());
				sb.append("'");
			}
		} else {
			sb.append(" ");
		}
		// 模糊查询
		if (!(where2 == null || where2.size() <= 0)) {
			boolean flag = false;
			if (where1 == null || where1.size() <= 0) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				if (flag) {
					sb.append(" and ");
				} else {
					flag = true;
				}
				sb.append("a.`");
				sb.append(entry.getKey());
				sb.append("` like '%");
				sb.append(entry.getValue());
				sb.append("%'");
			}
		} else {
			sb.append(" ");
		}
		// 时间范围
		// (a.`create_time`>='20151010 121212000' and a.`create_time`<='20151019
		// 221212000')
		// 2015-10-19 00:00:00 ~ 2015-10-19 23:59:59
		if (!(dateTime == null || dateTime.size() <= 0)) {
			boolean flag = false;
			if (((where1 == null || where1.size() <= 0) && (where2 == null || where2
					.size() <= 0))) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			for (Map.Entry<String, String> entry : dateTime.entrySet()) {
				if (flag) {
					sb.append(" and ");
				} else {
					flag = true;
				}
				String start = DateTimeUtil.systemToDatabase(entry.getValue()
						.split("~")[0].trim());
				String end = DateTimeUtil.systemToDatabase(entry.getValue()
						.split("~")[1].trim());
				sb.append(" (a.`");
				sb.append(entry.getKey());
				sb.append("`>='");
				sb.append(start);
				sb.append("' and  a.`");
				sb.append(entry.getKey());
				sb.append("`<='");
				sb.append(end);
				sb.append("') ");
			}
		} else {
			sb.append(" ");
		}
		sql1 = sql1.replace("[where]", sb.toString());
		sql2 = sql2.replace("[where]", sb.toString());
		// 排序
		sb = new StringBuilder();
		if (!(sort == null || sort.size() <= 0)) {
			boolean flag = false;
			sb.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				if (flag) {
					sb.append(",");
				}
				String col = entry.getKey();
				if (col.equals("course_id_name")) {
					col = "course_id";
				} else if (col.equals("class_id_name")) {
					col = "class_id";
				}
				sb.append("a.`" + col + "`");
				sb.append(" ");
				sb.append(entry.getValue());
			}
		} else {
			sb.append(" order by a.`update_time` desc,a.`create_time` desc ");
		}
		sql1 = sql1.replace("[order]", sb.toString());
		sql1=sql1.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n" + sql1 + "\n" + sql2);
		// 查询数据		
		@SuppressWarnings({ "unchecked" })
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql1,
				BeanPropertyRowMapper.newInstance(PlanVo.class));
		// 处理日期格式
		List<PlanVo> list3 = new ArrayList<PlanVo>();
		DateTimeUtil at = new DateTimeUtil();
		for (PlanVo p : list) {
			p.setStart_time(at.databaseToSystem(p.getStart_time()));
			p.setEnd_time(at.databaseToSystem(p.getEnd_time()));
			p.setCreate_time(at.databaseToSystem(p.getCreate_time()));
			p.setUpdate_time(at.databaseToSystem(p.getUpdate_time()));
			list3.add(p);
		}
		// 查询总条数
		@SuppressWarnings({ "unchecked" })
		List<PlanVo> list2 = (List<PlanVo>) this.dao.query(sql2,
				BeanPropertyRowMapper.newInstance(PlanVo.class));
		String allcount = list2.get(0).getAllcount();
		// 汇总数据
		pv.setList(list3);
		pv.setAllcount(allcount);
		return pv;
	}

	/**
	 * 课程下拉框
	 */
	@Override
	public List<SelectVo> getAllCourseName_Id() throws Exception {
		// TODO Auto-generated method stub
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_allcourse_name_id");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);		
		@SuppressWarnings("unchecked")
		List<SelectVo> list = (List<SelectVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	/**
	 * 班级下拉框
	 */
	@Override
	public List<SelectVo> getAllClassName_Id() throws Exception {
		// TODO Auto-generated method stub
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_allclass_name_id");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<SelectVo> list = (List<SelectVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	/**
	 * 计划知识点列表
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public ArrayList<TreeViewVo> getPlanPoint(String planVo, PlanVo vo,
			String courseId,boolean f) throws Exception {
		// TODO Auto-generated method stub
		DateTimeUtil at = new DateTimeUtil();
		String t = null;
		// 现在开始的时间
		String t1 = vo.getStart_time();
		// 现在结束的时间
		String t2 = vo.getEnd_time();
		// 原来开始的时间
		String t3 = vo.getStarttime();
		// 原来结束的时间
		String t4 = vo.getEndtime();
		double l = 1;
		if (!t1.equals("null") && !t2.equals("null") && !t3.equals("null")
				&& !t4.equals("null")) {
			t = t1;
			double l1 = l * (Integer) at.getHours2(t1, t2).get(2);
			double l2 = l * (Integer) at.getHours2(t3, t4).get(2);
			l = l1 / l2;
		}
		// 1.搜索数据
		// 搜索条件
		/*
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("course_id", courseId);
		paramMap.put("teach_plan_id", planVo);
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_plan_point_skill_list");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		*/
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planVo);
		String lang=getLang();
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_teach_plan_tree_snap");
		sql=sql.replaceAll("\\[lang\\]", lang);
		log.info("SQL:\n" + sql);
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		HashMap<String,PlanVo> map2 = new HashMap<String,PlanVo>();
		String ser = list.get(0).getTree_snap();
		List<PlanSnapVo> list1 = (ArrayList<PlanSnapVo>)SerializeUtil.unserialize(ser);
		//*****start
		StringBuffer pointsBuffer=new StringBuffer();
		for(int i=0;i<list1.size();i++){
			PlanSnapVo snapBean=list1.get(i);
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
		sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_detail_plan_tree_snap");
		//国际化语言切换
		sql = sql.replaceAll("\\[lang\\]", lang);
		if(lang.equals("")){ //中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{ //英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		sql=sql.replaceAll("\\[point_ids\\]", pointsBuffer.toString());
		List<PlanVo> listTem = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		for(int i=0;i<listTem.size();i++){
			PlanVo pTem = listTem.get(i);
			map2.put(pTem.getPoint_id(), pTem);
		}
		//*****end
		// 2.整理数据
		if (list1.size() > 0) {
			HashMap<String,TreeViewVo> map11 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map22 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map33 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map44 = new HashMap<String,TreeViewVo>();
			//一级技能
			for (PlanSnapVo v1 : list1) {
				String id1 = v1.getFirst_id();
				if(!map11.containsKey(id1)){
					map11.put(id1, new TreeViewVo(v1.getFirst_id(),v1.getFirst()));
				}		
			}
			//二级技能
			for (PlanSnapVo v1 : list1) {
				String id2 = v1.getSecond_id();
				if(id2!=null&&!map22.containsKey(id2)){
					map22.put(id2, new TreeViewVo(v1.getSecond_id(),v1.getSecond()));
				}		
			}
			//三级技能
			for (PlanSnapVo v1 : list1) {
				String id3 = v1.getThird_id();
				if(id3!=null&&!map33.containsKey(id3)){
					map33.put(id3, new TreeViewVo(v1.getThird_id(),v1.getThird()));
				}		
			}
			//知识点
			for (PlanSnapVo v1 : list1) {			
				String id4 = v1.getPoint_id();
				// point
				// point
				//切换数据
				PlanVo pvo = map2.get(v1.getPoint_id());
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("teacher_id", pvo.getTeacher_id());
				map.put("course_id", courseId);
				map.put("teacher_name", pvo.getTeacher_name());
				map.put("point", pvo.getPoint());
				map.put("point_id", pvo.getPoint_id());			
				map.put("finish_state", pvo.getFinish_state());
				map.put("finish_state_name", pvo.getFinish_state_name());
				map.put("id", planVo);			
				// 计算时间改变
				if (t != null && !f) {
					//重新计算,根据时间长度
					double cost = Double.parseDouble(pvo.getCost()) * 3600 * 1000;
					cost = cost * l;
					ArrayList<Date> al = at.addMSec(t, (long) cost);
					map.put("starttime", at.toSystem(al.get(0)));					
					t = at.toSystem(al.get(1));
					map.put("endtime", t);
				} else {
					//判断是否要重新计算时间
					if(f){
						//重新计算
						if(t==null){
							t = at.databaseToSystem(pvo.getStarttime());
						}
						double cost = Double.parseDouble(pvo.getCost()) * 3600 * 1000;
						ArrayList<Date> al = at.addMSec(t, (long) cost);
						map.put("starttime", at.toSystem(al.get(0)));	
						t = at.toSystem(al.get(1));
						map.put("endtime", t);
					}else{
						//直接取出数据库中详细信息-不重新计算
						map.put("starttime", at.databaseToSystem(pvo.getStarttime()));
						map.put("endtime", at.databaseToSystem(pvo.getEndtime()));
					}				
				}
				// 计算时长
				ArrayList<Object> alTime = at.getHours2(map.get("starttime"), map.get("endtime"));
				map.put("hour", alTime.get(0)+"");
				map.put("min", alTime.get(1)+"");
				map44.put(id4, new TreeViewVo(pvo.getPoint_id(), v1.getPoint() , map, true));			
			}
			//3-4层关系
			ArrayList<String> tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : list1) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				String id4 = v1.getPoint_id();
				if(id2!=null&id3!=null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map33.get(id3).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map33.get(id3).setNodes(alv);
						tmp.add(id4);
					}		
				}
				if(id2!=null&id3==null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map22.get(id2).setNodes(alv);
						tmp.add(id4);
					}
				}
				if(id2==null&id3==null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map44.get(id4));
						map11.get(id1).setNodes(alv);
						tmp.add(id4);
					}		
				}	
			}
			//2-3层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : list1) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				if(id3!=null){
					if(id2!=null){
						if(!tmp.contains(id3)){
							ArrayList<TreeViewVo> alv = map22.get(id2).getNodes();
							if(alv ==  null){
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map22.get(id2).setNodes(alv);
							tmp.add(id3);
						}
					}else{
						if(!tmp.contains(id3)){
							ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
							if(alv ==  null){
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map33.get(id3));
							map11.get(id1).setNodes(alv);
							tmp.add(id3);
						}
					}
				}
			}
			//1-2层关系
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : list1) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				if(id2!=null){
					if(!tmp.contains(id2)){
						ArrayList<TreeViewVo> alv = map11.get(id1).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map22.get(id2));
						map11.get(id1).setNodes(alv);
						tmp.add(id2);
					}
					
				}
			}
			//1
			ArrayList<TreeViewVo> al = new ArrayList<TreeViewVo>();
			tmp = new ArrayList<String>();
			for (PlanSnapVo v1 : list1) {
				String id1 = v1.getFirst_id();
				if(!tmp.contains(id1)){
					al.add(map11.get(id1)); 
					tmp.add(id1);
				}	
			}
			return al;	
		} else {
			return null;
		}
	}

	/**
	 * 获取所有老师的的id和名字的下拉框
	 */
	@Override
	public List<SelectVo> getAllTeacherSelect() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_teacher_select");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		//国际化语言切换
		if(getLang().equals("")){
			//中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{
			//英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<SelectVo> list = (List<SelectVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(SelectVo.class));
		if (list != null) {
			return list;
		}
		return null;
	}

	/**
	 * 修改起始时间
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public boolean updatePlanStartTime(PlanVo vo, UserVo userNow)
			throws Exception {
		// TODO Auto-generated method stub
		// 0.提取数据
		DateTimeUtil at = new DateTimeUtil();
		// 现在开始的时间
		String t1 = at.systemToDatabase(vo.getStart_time());
		// 现在结束的时间
		String t2 = at.systemToDatabase(vo.getEnd_time());
		// 原来开始的时间
		String t3 = at.systemToDatabase(vo.getStarttime());
		// 原来结束的时间
		String t4 = at.systemToDatabase(vo.getEndtime());
		// id
		String id = vo.getId();
		// courseId
		//String courseId = vo.getCourse_id();
		String t = null;
		double l = 1;
		t = t1;
		double l1 = l * (Integer) at.getHours2(t1, t2).get(2);
		double l2 = l * (Integer) at.getHours2(t3, t4).get(2);
		l = l1 / l2;
		// 1.查出知识点的列表和当前时间
		/*
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teach_plan_id", id);
		paramMap.put("course_id", courseId);
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_plan_pointid_order");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(PlanVo.class));
		*/
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_teach_plan_tree_snap");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		HashMap<String,PlanVo> map2 = new HashMap<String,PlanVo>();
		String ser = list.get(0).getTree_snap();
		ArrayList<PlanSnapVo> list1 = (ArrayList<PlanSnapVo>)SerializeUtil.unserialize(ser);
		//*****start
		StringBuffer pointsBuffer=new StringBuffer();
		String lang=getLang();
		for(int i=0;i<list1.size();i++){
			PlanSnapVo snapBean=list1.get(i);
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
		sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_detail_plan_tree_snap");
		//国际化语言切换
		sql = sql.replaceAll("\\[lang\\]", lang);
		if(lang.equals("")){ //中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{ //英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		sql=sql.replaceAll("\\[point_ids\\]", pointsBuffer.toString());
		List<PlanVo> listTem = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		for(int i=0;i<listTem.size();i++){
			PlanVo pTem = listTem.get(i);
			map2.put(pTem.getPoint_id(), pTem);
		}
		//*****end
		// 2.循环修改知识点的时间
		for (PlanSnapVo p : list1) {
			PlanVo pv = map2.get(p.getPoint_id());
			// 修改数据
			String point_id = pv.getPoint_id();
			// 执行
			paramMap = new HashMap<String, Object>();
			paramMap.put("teach_plan_id", id);
			paramMap.put("point_id", point_id);
			// 计算时间改变			
			double cost = Double.parseDouble(pv.getCost()) * 3600 * 1000;
			cost = cost * l;
			ArrayList<Date> al = at.addMSec(t, (long)cost);
			paramMap.put("starttime", at.toDatabase(al.get(0)));
			t = at.toDatabase(al.get(1));
			paramMap.put("endtime", t);
			// 时间长度截取2位精度
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			paramMap.put("cost", df.format((cost / (3600 * 1000))));
			sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/update_plan_point");
			sql=sql.replaceAll("\\[lang\\]", getLang());
			log.info("SQL:\n" + sql);
			int re = this.dao.update(sql, paramMap);
			if (re == 0) {
				return false;
			}
		}
		// 3.修改计划总时间
		paramMap = new HashMap<String, Object>();
		paramMap.put("start_time", at.systemToDatabase(t1));
		paramMap.put("end_time", at.systemToDatabase(t));
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", userNow.getName());
		paramMap.put("id", id);
		sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/update_teach_plan");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		int re = this.dao.update(sql, paramMap);
		if (re == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 计划知识点列表
	 */
	@SuppressWarnings("static-access")
	@Override
	public ArrayList<TreeViewVo> getPlanPoint2(String starttime,
			String end_time, String endtime, String courseId) throws Exception {
		// TODO Auto-generated method stub
		// 1.搜索数据
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("course_id", courseId);
		String lang=getLang();
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_plan_point_skill_list_course");
		sql=sql.replaceAll("\\[lang\\]", lang);
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(PlanVo.class));
		// 1.1.时间
		String t1 = starttime;
		// 2.整理数据
		if (list.size() > 0) {
			HashMap<String,TreeViewVo> map1 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map2 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map3 = new HashMap<String,TreeViewVo>();
			HashMap<String,TreeViewVo> map4 = new HashMap<String,TreeViewVo>();
			//一级技能
			for (PlanVo v1 : list) {
				String id1 = v1.getFirst_id();
				if(!map1.containsKey(id1)){
					map1.put(id1, new TreeViewVo(v1.getFirst_id(), lang.equals("")?v1.getFirst():v1.getFirst_en_US()));
				}		
			}
			//二级技能
			for (PlanVo v1 : list) {
				String id2 = v1.getSecond_id();
				if(id2!=null&&!map2.containsKey(id2)){
					map2.put(id2, new TreeViewVo(v1.getSecond_id(), lang.equals("")?v1.getSecond():v1.getSecond_en_US()));
				}		
			}
			//三级技能
			for (PlanVo v1 : list) {
				String id3 = v1.getThird_id();
				if(id3!=null&&!map3.containsKey(id3)){
					map3.put(id3, new TreeViewVo(v1.getThird_id(), lang.equals("")?v1.getThird():v1.getThird_en_US()));
				}		
			}
			//知识点
			for (PlanVo v1 : list) {			
				String id4 = v1.getPoint_id();
				// point
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("point", v1.getPoint());
				map.put("point_id", v1.getPoint_id());
				DateTimeUtil at = new DateTimeUtil();
				// 判断时间有没有压缩
				double cost = Double.parseDouble(v1.getCost()) * 3600 * 1000;
				if (!end_time.equals("null") && !endtime.equals("null")) {
					//压缩过
					double l = 1;
					// 计算压缩比
					double l1 = l
							* (Integer) at.getHours2(starttime, endtime).get(2);
					double l2 = l
							* (Integer) at.getHours2(starttime, end_time).get(2);
					l = l1 / l2;
					cost = cost * l;
					//传入压缩比
					map.put("cost", l+"");
				}else{
					map.put("cost", "1");
				}
				ArrayList<Date> al = at.addMSec(t1, (long) cost);
				map.put("starttime", at.toSystem(al.get(0)));			
				t1 = at.toSystem(al.get(1));
				map.put("endtime", t1);
				// 计算时长
				ArrayList<Object> alTime = at.getHours2(map.get("starttime"), map.get("endtime"));
				map.put("hour", alTime.get(0)+"");
				map.put("min", alTime.get(1)+"");
				map4.put(id4, new TreeViewVo(v1.getPoint_id(), lang.equals("")?v1.getPoint():v1.getPoint_en_US(),map, true));			
			}
			//3-4层关系
			ArrayList<String> tmp = new ArrayList<String>();
			for (PlanVo v1 : list) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				String id4 = v1.getPoint_id();
				if(id2!=null&id3!=null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map3.get(id3).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map4.get(id4));
						map3.get(id3).setNodes(alv);
						tmp.add(id4);
					}		
				}
				if(id2!=null&id3==null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map2.get(id2).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map4.get(id4));
						map2.get(id2).setNodes(alv);
						tmp.add(id4);
					}
				}
				if(id2==null&id3==null){
					if(!tmp.contains(id4)){
						ArrayList<TreeViewVo> alv = map1.get(id1).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map4.get(id4));
						map1.get(id1).setNodes(alv);
						tmp.add(id4);
					}		
				}	
			}
			//2-3层关系
			tmp = new ArrayList<String>();
			for (PlanVo v1 : list) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				String id3 = v1.getThird_id();
				if(id3!=null){
					if(id2!=null){
						if(!tmp.contains(id3)){
							ArrayList<TreeViewVo> alv = map2.get(id2).getNodes();
							if(alv ==  null){
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map3.get(id3));
							map2.get(id2).setNodes(alv);
							tmp.add(id3);
						}
					}else{
						if(!tmp.contains(id3)){
							ArrayList<TreeViewVo> alv = map1.get(id1).getNodes();
							if(alv ==  null){
								alv = new ArrayList<TreeViewVo>();
							}
							alv.add(map3.get(id3));
							map1.get(id1).setNodes(alv);
							tmp.add(id3);
						}
					}
				}
			}
			//1-2层关系
			tmp = new ArrayList<String>();
			for (PlanVo v1 : list) {
				String id1 = v1.getFirst_id();
				String id2 = v1.getSecond_id();
				if(id2!=null){
					if(!tmp.contains(id2)){
						ArrayList<TreeViewVo> alv = map1.get(id1).getNodes();
						if(alv ==  null){
							alv = new ArrayList<TreeViewVo>();
						}
						alv.add(map2.get(id2));
						map1.get(id1).setNodes(alv);
						tmp.add(id2);
					}
					
				}
			}
			//1
			ArrayList<TreeViewVo> al = new ArrayList<TreeViewVo>();
			tmp = new ArrayList<String>();
			for (PlanVo v1 : list) {
				String id1 = v1.getFirst_id();
				if(!tmp.contains(id1)){
					al.add(map1.get(id1)); 
					tmp.add(id1);
				}	
			}
			return al;
		}else {
			return null;
		}		
	}

	/**
	 * 添加教学计划
	 */
	@SuppressWarnings("static-access")
	@Override
	public PlanVo addPlan(PlanVo vo, UserVo userNow) throws Exception {
		// TODO Auto-generated method stub
		// 提取数据
		String courseId = vo.getCourse_id();
		// 用户开始时间
		String starttime = vo.getStart_time();
		// 用户结束时间
		//String endtime = vo.getEnd_time();
		// 原计划结束时间
		//String oldEndTime = vo.getEndtime();
		// 计算时长压缩比
		DateTimeUtil at = new DateTimeUtil();
		double l = Double.parseDouble(vo.getCost());
		log.info("压缩比："+l);
		// 新建教学计划
		vo.setCreate_time(DateTimeUtil.nowToDatabase());
		vo.setCreate_person(userNow.getName());
		vo.setUpdate_time(DateTimeUtil.nowToDatabase());
		vo.setUpdate_person(userNow.getName());
		vo.setStart_time(DateTimeUtil.systemToDatabase(vo.getStart_time()));
		vo.setEnd_time(DateTimeUtil.systemToDatabase(vo.getEnd_time()));	
		// 查询知识点列表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("course_id", courseId);
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_plan_point_skill_list_course");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<PlanSnapVo> list = (List<PlanSnapVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(PlanSnapVo.class));
		//序列化对象
		String tree_snap = SerializeUtil.serialize(list);
		log.info("SQL:\n序列化长度:" + tree_snap.length());
		vo.setTree_snap(tree_snap);
		//保存
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		String insertSql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/add_plan");
		insertSql=insertSql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n" + insertSql + "\nparam\n:" + paramSource.toString());
		int planId = this.dao.insertAndRtnKey(insertSql, paramSource);
		// 遍历知识点
		String t = DateTimeUtil.systemToDatabase(starttime);
		for (PlanSnapVo psv : list) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("teach_plan_id", planId);
			paramMap.put("point_id", psv.getPoint_id());
			// 计算结束时间
			double cost = Double.parseDouble(psv.getCost()) * 3600 * 1000;
			cost = cost * l;
			ArrayList<Date> al = at.addMSec(t, (long) cost);
			paramMap.put("starttime", at.toDatabase(al.get(0)));
			t = at.toDatabase(al.get(1));
			paramMap.put("endtime", t);
			paramMap.put("finish_state", "4");
			// 时间长度截取2位精度
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			ArrayList<Object> alTime = at.getHours2(at.toDatabase(al.get(0)), t);
			paramMap.put("cost", DateTimeUtil.getCost(String.valueOf(alTime.get(0)), String.valueOf(alTime.get(1))));
			sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/add_plan_detail");
			sql=sql.replaceAll("\\[lang\\]", getLang());
			log.info("SQL:\n" + sql);
			this.dao.insert(sql, paramMap);
		}
		//修改回系统时间格式
		vo.setStart_time(DateTimeUtil.databaseToSystem(vo.getStart_time()));
		vo.setEnd_time(DateTimeUtil.databaseToSystem(vo.getEnd_time()));
		vo.setId(planId + "");
		return vo;
	}

	/**
	 * 删除教学计划
	 */
	@Override
	public boolean planDel(String planId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planId);
		paramMap.put("teach_plan_id", planId);
		// 删除详细计划
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delete_plan_detail");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		this.dao.delete(sql, paramMap);
		// 删除教学计划
		sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delete_plan");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		this.dao.delete(sql, paramMap);
		return true;
	}

	/**
	 * 获取知识点完成状态
	 */
	@Override
	public List<SelectVo> getPoint_Finish_state() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = SqlLoader.loadSqlFromXml(sqlFile,
				"//SQL/query_point_finish_status");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<SelectVo> list = (List<SelectVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(SelectVo.class));
		if (list != null) {
			return list;
		}
		return null;
	}

	/**
	 * 修改详细知识点的信息
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public HashMap<String,String> updatePointDetail(PlanVo vo, UserVo userNow)
			throws Exception {
		// TODO Auto-generated method stub
		String id = vo.getId();
		String point_id = vo.getPoint_id();
		String hours = vo.getHours();
		String mins = vo.getMins();
		String teacher_id = vo.getTeacher_id();
		String finish_state = vo.getFinish_state();
		String t0 = null;
		String t = null;
		// 1.查询知识点列表
		// 搜索条件
		/*
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teach_plan_id", id);
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_plan_point_skill_list");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		*/
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		// 查询语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_teach_plan_tree_snap");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		List<PlanVo> list = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		HashMap<String,PlanVo> map2 = new HashMap<String,PlanVo>();
		String ser = list.get(0).getTree_snap();
		List<PlanSnapVo> list1 = (ArrayList<PlanSnapVo>)SerializeUtil.unserialize(ser);
		//*****start
		StringBuffer pointsBuffer=new StringBuffer();
		String lang=getLang();
		for(int i=0;i<list1.size();i++){
			PlanSnapVo snapBean=list1.get(i);
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
		sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/query_detail_plan_tree_snap");
		//国际化语言切换
		sql = sql.replaceAll("\\[lang\\]", lang);
		if(lang.equals("")){ //中文
			sql=sql.replaceAll("\\[name\\]", "name");
		}else{ //英文
			sql=sql.replaceAll("\\[name\\]", "en_name");
		}
		sql=sql.replaceAll("\\[point_ids\\]", pointsBuffer.toString());
		List<PlanVo> listTem = (List<PlanVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(PlanVo.class));
		for(int i=0;i<listTem.size();i++){
			PlanVo pTem = listTem.get(i);
			map2.put(pTem.getPoint_id(), pTem);
		}
		//*****end
		// 2.循环遍历知识点,到目标之后开始修改
		boolean f = false;
		DateTimeUtil at = new DateTimeUtil();
		for(PlanSnapVo psv : list1){
			if(psv.getPoint_id().equals(point_id)){
				f = true;
			}
			PlanVo p = map2.get(psv.getPoint_id());
			if(f){
				//修改时间
				paramMap = new HashMap<String, Object>();	
				if(p.getPoint_id().equals(point_id)){
					ArrayList<Date> al = at.addMSec(p.getStarttime(), (Long.parseLong(hours)*60+Long.parseLong(mins))*60*1000); 			
					paramMap.put("starttime", at.toDatabase(al.get(0)));
					paramMap.put("endtime", at.toDatabase(al.get(1)));
					java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
					paramMap.put("cost", df.format((Double.parseDouble(hours)*60+Double.parseDouble(mins))/60));
					paramMap.put("teacher_id", teacher_id);
					paramMap.put("finish_state", finish_state);
					t0 = p.getStarttime();
					t = (String)paramMap.get("endtime");
				}else{
					ArrayList<Date> al = at.addMSec(t, (long)(Double.parseDouble(p.getCost())*3600*1000)); 			
					paramMap.put("starttime", at.toDatabase(al.get(0)));
					paramMap.put("endtime", at.toDatabase(al.get(1)));		
					paramMap.put("cost", p.getCost());
					paramMap.put("teacher_id", p.getTeacher_id());
					paramMap.put("finish_state", p.getFinish_state());
					t = (String)paramMap.get("endtime");
				}			
				paramMap.put("teach_plan_id", id);
				paramMap.put("point_id", p.getPoint_id());
				sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/update_plan_point2");
				sql=sql.replaceAll("\\[lang\\]", getLang());
				log.info("SQL:\n" + sql);
				this.dao.update(sql, paramMap);			
			}
		}
		// 3.修改整个计划的结束时间
		if(f){
			paramMap = new HashMap<String, Object>();	
			paramMap.put("end_time", t);
			paramMap.put("update_time", at.nowToDatabase());
			paramMap.put("update_person", userNow.getName());
			paramMap.put("id", id);
			sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/update_teach_plan2");
			sql=sql.replaceAll("\\[lang\\]", getLang());
			log.info("SQL:\n" + sql);
			this.dao.update(sql, paramMap);		
		}
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("starttime", at.databaseToSystem(t0));
		map.put("endtime", at.databaseToSystem(t));
		return map;
	}

	/**
	 * 更新教学计划最后修改时间
	 */
	@Override
	public void updateTeachPlanUpdateTime(String planId, UserVo userNow)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> paramMap = new HashMap<String, Object>();	
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", userNow.getName());
		paramMap.put("id", planId);
		String sql = SqlLoader.loadSqlFromXml(sqlFile,"//SQL/update_teach_plan_update_time");
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		this.dao.update(sql, paramMap);			
	}

	
	public static <T> T deepCopy(T src) throws IOException, ClassNotFoundException{     
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();     
        ObjectOutputStream out = new ObjectOutputStream(byteOut);     
        out.writeObject(src);       
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());     
        ObjectInputStream in =new ObjectInputStream(byteIn);     
        @SuppressWarnings("unchecked")
		T dest = (T) in.readObject();     
        return dest;     
    } 
}
