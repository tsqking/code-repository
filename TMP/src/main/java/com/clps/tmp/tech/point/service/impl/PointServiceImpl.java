package com.clps.tmp.tech.point.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.point.service.PointService;
import com.clps.tmp.tech.point.vo.DetailVo;
import com.clps.tmp.tech.point.vo.PointVo;

/**
 * @author Seven
 *
 * 2015年11月3日
 */
@Service("pointService")
public class PointServiceImpl extends BaseService implements PointService {
	private static String sqlFile="tech" + File.separator + "point" + File.separator + "PointSql.xml";
	@Override
	public PageVo<PointVo> queryPointPage(PageVo<PointVo> pageVo ,String userId,String userRole) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page = null;
		String sql_count = null;
		if(Integer.parseInt(userRole)<2){//超管 & 管
			sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointPage");
			sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointPageAllCount");
		}else{//教师
			paramMap.put("teacher_id", userId);
			sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getLimitPointPage");
			sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getLimitPointPageAllCount");
		}
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
		List<PointVo> list=(List<PointVo>) this.dao.query(sql_page, paramMap, BeanPropertyRowMapper.newInstance(PointVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}
	
	@Override
	public HashMap<String,Object> addPoint(PointVo point) throws Exception {
		//插入新知识点
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(point);
		String insertPointSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertPoint");
		log.info("\nSQL:\n"+insertPointSql+"\nparam\n:"+paramSource.toString());
		String pointId=String.valueOf(this.dao.insertAndRtnKey(insertPointSql, paramSource));
		//插入技能-知识点 mapping
		String insertMapSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertSkillPointMapping");
		HashMap<String,Object> mappingParamMap=new HashMap<String,Object>();
		mappingParamMap.put("point_id", pointId);
		mappingParamMap.put("skill_id", ("".equals(point.getThird_skill()) || null==point.getThird_skill())?
										(("".equals(point.getSecond_skill()) || null==point.getSecond_skill())?point.getFirst_skill():point.getSecond_skill()):
										point.getThird_skill());
		log.info("\nSQL:\n"+insertMapSql+"\nparam\n:"+mappingParamMap.toString());
		int effect=this.dao.insert(insertMapSql, mappingParamMap);
		//返回
		HashMap<String,Object> ret=new HashMap<String,Object>();
		ret.put("effect", effect+1);
		ret.put("point_id", pointId);
		return ret;
	}

	@Override
	public int addPointDetail(DetailVo detail) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(detail);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertPointDetail");
		log.info("\nSQL:"+sql+"\nparam:"+detail.toString());
		int effect=this.dao.insert(sql, paramSource);
		return effect;
	}

	@Override
	public Map<String,Object> updateMaterialInfoByID(String pointId, String userName, FileUploadInfoVo fileVo)
			throws Exception {
		HashMap<String,Object> paramMap=getUpdateFileParamMap(pointId, userName, fileVo);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateMaterialInfoByID");
		log.info("\nSQL:"+sql+"\nparam:"+paramMap.toString());
		int effect=this.dao.update(sql, paramMap);
		effect+=updatePointUpdateInfo(paramMap);
		paramMap.put("effect", String.valueOf(effect));
		return paramMap;
	}

	@Override
	public Map<String,Object> updateTHandBookInfoByID(String pointId, String userName, FileUploadInfoVo fileVo)
			throws Exception {
		HashMap<String,Object> paramMap=getUpdateFileParamMap(pointId, userName, fileVo);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateTHandBookInfoByID");
		log.info("\nSQL:"+sql+"\nparam:"+paramMap.toString());
		int effect=this.dao.update(sql, paramMap);
		effect+=updatePointUpdateInfo(paramMap);
		paramMap.put("effect", String.valueOf(effect));
		return paramMap;
	}

	@Override
	public Map<String,Object> updateSHandBookInfoByID(String pointId, String userName, FileUploadInfoVo fileVo)
			throws Exception {
		HashMap<String,Object> paramMap=getUpdateFileParamMap(pointId, userName, fileVo);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateSHandBookInfoByID");
		log.info("\nSQL:"+sql+"\nparam:"+paramMap.toString());
		int effect=this.dao.update(sql, paramMap);
		effect+=updatePointUpdateInfo(paramMap);
		paramMap.put("effect", String.valueOf(effect));
		return paramMap;
	}

	@Override
	public Map<String,Object> updateReferenceInfoByID(String pointId, String userName, FileUploadInfoVo fileVo)
			throws Exception {
		HashMap<String,Object> paramMap=getUpdateFileParamMap(pointId, userName, fileVo);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateReferenceInfoByID");
		log.info("\nSQL:"+sql+"\nparam:"+paramMap.toString());
		int effect=this.dao.update(sql, paramMap);
		effect+=updatePointUpdateInfo(paramMap);
		paramMap.put("effect", String.valueOf(effect));
		return paramMap;
	}
	
	private int updatePointUpdateInfo(HashMap<String,Object> paramMap) throws Exception {
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updatePointUpdateInfo");
		log.info("\nSQL:"+sql+"\nparam:"+paramMap.toString());
		return this.dao.update(sql, paramMap);
	}
	
	private HashMap<String,Object> getUpdateFileParamMap(String pointId, String userName, FileUploadInfoVo fileVo){
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("point_id", pointId);
		paramMap.put("file_name", fileVo==null?null:fileVo.getFileFileName());
		paramMap.put("file_type", fileVo==null?null:fileVo.getFileContentType());
		paramMap.put("file_size", fileVo==null?null:fileVo.getFileSize());
		paramMap.put("file_dir", fileVo==null?null:fileVo.getFilePath());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", userName);
		return paramMap;
	}

	@Override
	public PointVo getPointInfoByID(String pointId) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("point_id", pointId);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointInfoByID");
		sql=sql.replaceAll("\\[lang\\]",getLang());
		log.info("\nSQL:"+sql+"\nparam:"+pointId);
		List<PointVo> list=(List<PointVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(PointVo.class));
		if(list.size()==0)return null;
		else return list.get(0);
	}

	@Override
	public DetailVo getDetailInfoByID(String pointId) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("point_id", pointId);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getDetailInfoByID");
		sql=sql.replaceAll("\\[lang\\]",getLang());
		log.info("\nSQL:"+sql+"\nparam:"+pointId);
		List<DetailVo> list=(List<DetailVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(DetailVo.class));
		if(list.size()==0)return null;
		else return list.get(0);
	}

	@Override
	public int updatePointInfo(PointVo pointVo) throws Exception {
		//更新基本信息
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(pointVo);
		String updatePointSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updatePoint");
		log.info("\nSQL:\n"+updatePointSql+"\nparam\n:"+paramSource.toString());
		int effect=this.dao.update(updatePointSql, paramSource);
		//更新mapping
		String updateSkillPointMappingSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateSkillPointMapping");
		HashMap<String,Object> mappingParamMap=new HashMap<String,Object>();
		mappingParamMap.put("point_id", pointVo.getId());
		mappingParamMap.put("skill_id", ("".equals(pointVo.getThird_skill()) || null==pointVo.getThird_skill())?
										(("".equals(pointVo.getSecond_skill()) || null==pointVo.getSecond_skill())?pointVo.getFirst_skill():pointVo.getSecond_skill()):
											pointVo.getThird_skill());
		log.info("\nSQL:\n"+updateSkillPointMappingSql+"\nparam\n:"+mappingParamMap.toString());
		effect+=this.dao.update(updateSkillPointMappingSql, mappingParamMap);		
		return effect;
	}

	@Override
	public int updateDetailInfo(DetailVo detailVo) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(detailVo);
		String updateDetailSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/updateDetail");
		log.info("\nSQL:\n"+updateDetailSql+"\nparam\n:"+paramSource.toString());
		int effect=this.dao.update(updateDetailSql, paramSource);
		return effect;
	}

	@Override
	public Map<String,Object> deletePoint(String pointId) throws Exception {
		//返回Map 
		//code-'0-删除成功/1-不能删除，有课程用/2-不能删除，有教学计划用/3-不能删除，课程和计划都在用'/4-不能删除，除课程教学之外，其他的关联
		//deleteEffect-'删除影响记录数' 
		//inUseCourse-'courseNo-courseName'  
		//inUsePlan-'courseNo-courseName' 
		HashMap<String,Object> rtnMap=new HashMap<String,Object>();
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("point_id", pointId);
		//查询是否能删除--check课程是否用到
		String checkCourseSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkCourseWhenDelete");
		checkCourseSql=checkCourseSql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+checkCourseSql+"\nparam\n:"+paramMap.toString());
		List<Map<String,Object>> pointInCourseList= this.dao.queryForList(checkCourseSql, paramMap);
		//查询是否能删除--check教学计划是否用到
		String checkTeachPlanSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkTeachPlanWhenDelete");
		checkTeachPlanSql=checkTeachPlanSql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+checkTeachPlanSql+"\nparam\n:"+paramMap.toString());
		List<Map<String,Object>> pointInPlanList= this.dao.queryForList(checkTeachPlanSql, paramMap);
		
		if(pointInCourseList.size()==0 && pointInPlanList.size()==0){//没有 课程&教学计划 用到知识点
			//check教学历史、教学练习
			String checkPointRelevantSql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkOtherWhenDelete");
			log.info("SQL:\n" + checkPointRelevantSql+"\nparam\n:"+paramMap.toString());
			int checkPointRelevant=(Integer)this.dao.queryForObject(checkPointRelevantSql, paramMap, Integer.class);
			if(checkPointRelevant==0){//无关联,可以删除
				//删除教师知识点mapping
				String deleteTeacherPointMappingSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deleteTeacherPointMapping");
				log.info("\nSQL:\n"+deleteTeacherPointMappingSql+"\nparam\n:"+paramMap.toString());
				int effect4=this.dao.delete(deleteTeacherPointMappingSql, paramMap);
				//删除技能知识点mapping
				String deleteMappingSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deleteSkillPointMapping");
				log.info("\nSQL:\n"+deleteMappingSql+"\nparam\n:"+paramMap.toString());
				int effect1=this.dao.delete(deleteMappingSql, paramMap);
				//删除知识点详细
				String deleteDetailSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deletePointDetail");
				log.info("\nSQL:\n"+deleteDetailSql+"\nparam\n:"+paramMap.toString());
				int effect2=this.dao.delete(deleteDetailSql, paramMap);
				//删除知识点
				String deleteSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/deletePoint");
				log.info("\nSQL:\n"+deleteSql+"\nparam\n:"+paramMap.toString());
				int effect3=this.dao.delete(deleteSql, paramMap);
				rtnMap.put("code", "0");
				rtnMap.put("deleteEffect", String.valueOf(effect1+effect2+effect3+effect4));
			}else{
				rtnMap.put("code", "4");//有除课程&教学计划之外的关联，不能删除
			}
		}else{//有课程用到知识点
			//列出用到的课程
			boolean courseUse=false,planUse=false;
			if(pointInCourseList.size()>0){
				StringBuffer courseBf=new StringBuffer();
				for(int i=0;i<pointInCourseList.size();i++){
					Map<String,Object> map=pointInCourseList.get(i);
					if(i!=0)
						courseBf.append("<br>");
					courseBf.append(map.get("no")+" - "+map.get("name"));
				}
				courseUse=true;
				rtnMap.put("inUseCourse", courseBf.toString());
			}
			//列出用到的教学计划
			if(pointInPlanList.size()>0){
				StringBuffer courseBf=new StringBuffer();
				for(int i=0;i<pointInPlanList.size();i++){
					Map<String,Object> map=pointInPlanList.get(i);
					if(i!=0)
						courseBf.append("<br>");
					courseBf.append(map.get("no")+" - "+map.get("name"));
				}
				planUse=true;
				rtnMap.put("inUsePlan", courseBf.toString());
			}
			if(courseUse & planUse){
				rtnMap.put("code", "3");
			}else{
				if(courseUse) rtnMap.put("code", "1");
				else rtnMap.put("code", "2");
			}
		}
		return rtnMap;
	}

	@Override
	public int copyPoint(PointVo point, DetailVo sourceDetail) throws Exception {
		//插入知识点简要信息
		SqlParameterSource pointSource = new BeanPropertySqlParameterSource(point);
		String insertPointSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/copyInsertPoint");
		log.info("\nSQL:\n"+insertPointSql+"\nparam\n:"+point.toString());
		String pointId=String.valueOf(this.dao.insertAndRtnKey(insertPointSql, pointSource));
		//插入知识点详细
		String skillPath=getSkillPath(point.getFirst_skill_id(),point.getSecond_skill_id(),point.getThird_skill_id());
		sourceDetail.setDescription(point.getId());//旧的pointId
		sourceDetail.setPoint_id(pointId);//新的pointId
		String mDir=getAttachFilePath(sourceDetail.getMaterial_dir(),skillPath,pointId);
		String tDir=getAttachFilePath(sourceDetail.getT_handbook_dir(),skillPath,pointId);
		String sDir=getAttachFilePath(sourceDetail.getS_handbook_dir(),skillPath,pointId);
		String rDir=getAttachFilePath(sourceDetail.getReference_dir(),skillPath,pointId);
			//复制文件
			UploadFileUtil uploadUtil=new UploadFileUtil();
			uploadUtil.copyFile(sourceDetail.getMaterial_dir(), mDir);
			uploadUtil.copyFile(sourceDetail.getT_handbook_dir(), tDir);
			uploadUtil.copyFile(sourceDetail.getS_handbook_dir(), sDir);
			uploadUtil.copyFile(sourceDetail.getReference_dir(), rDir);
		sourceDetail.setMaterial_dir(mDir);
		sourceDetail.setT_handbook_dir(tDir);
		sourceDetail.setS_handbook_dir(sDir);
		sourceDetail.setReference_dir(rDir);
		SqlParameterSource detailSource = new BeanPropertySqlParameterSource(sourceDetail);
		String insertDetailSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/copyInsertDetail");
		log.info("\nSQL:\n"+insertDetailSql+"\nparam\n:"+sourceDetail.toString());
		int effect=this.dao.insert(insertDetailSql, detailSource);
		//插入mapping
		String insertMapSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insertSkillPointMapping");
		HashMap<String,Object> mappingParamMap=new HashMap<String,Object>();
		mappingParamMap.put("point_id", pointId);
		mappingParamMap.put("skill_id", ("".equals(point.getThird_skill_id()) || null==point.getThird_skill_id())?
										(("".equals(point.getSecond_skill_id()) || null==point.getSecond_skill_id())?point.getFirst_skill_id():point.getSecond_skill_id()):
										point.getThird_skill_id());
		log.info("\nSQL:\n"+insertMapSql+"\nparam\n:"+mappingParamMap.toString());
		effect+=this.dao.insert(insertMapSql, mappingParamMap);
		return Integer.parseInt(pointId);
	}
	
	private String getAttachFilePath(String sfilePath,String skillPath,String pointId){
		if("".equals(sfilePath) || null==sfilePath){
			return "";
		}
		sfilePath=sfilePath.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
		sfilePath=sfilePath.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
		StringBuffer path=new StringBuffer();
		String beforeSkill=sfilePath.substring(0, sfilePath.indexOf(File.separator+"PointAttach"+File.separator));
		beforeSkill+=(File.separator+"PointAttach"+File.separator);
		path.append(beforeSkill);
		path.append(skillPath);
		path.append(File.separator+pointId);
		path.append(sfilePath.substring(sfilePath.lastIndexOf(File.separator)));
		return path.toString();
	}
	
	private String getSkillPath(String f1,String f2,String f3){
		StringBuffer skillPath=new StringBuffer();
		if("".equals(f1) || null==f1)
			return "";
		skillPath.append(f1);
		if("".equals(f2) || null==f2)
			return skillPath.toString();
		skillPath.append(File.separator);
		skillPath.append(f2);
		if("".equals(f3) || null==f3)
			return skillPath.toString();
		skillPath.append(File.separator);
		skillPath.append(f3);
		return skillPath.toString();
	}

	@Override
	public List<SelectVo> getPointOptionBySkillIds(String firstSkillId,String secondSkillId,String thirdSkillId) throws Exception {
		if(firstSkillId == null && secondSkillId == null && thirdSkillId == null)return null;
		StringBuffer where=new StringBuffer();
		if(null!=firstSkillId && !"".equals(firstSkillId)){where.append(" and e.`id`= '"+firstSkillId+"'");}
		if(null!=secondSkillId && !"".equals(secondSkillId)){where.append(" and d.`id`= '"+secondSkillId+"'");}
		if(null!=thirdSkillId && !"".equals(thirdSkillId)){where.append(" and c.`id`= '"+thirdSkillId+"'");}
		//查询语句
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getPointOptionBySkillIds");
		sql=sql.replace("[condition]", where.toString());
		sql=sql.replaceAll("\\[lang\\]", getLang());
		log.info("\nSQL:\n"+sql);
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	@Override
	public void grantManageToTeacher(String teacherId, String pointId, String manage) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("teacher_id", teacherId);
		paramMap.put("point_id", pointId);
		paramMap.put("manage", manage);
		String sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/grantManagePriToTeacherSelf");
		log.info("\nSSQL:"+sql+"\nparam:"+paramMap.toString());
		this.dao.insert(sql, paramMap);
	}

	@Override
	public List<Map<String, Object>> getTopicData(Map<String, Object> paramMap)  throws Exception {
		String querySql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/downTopicData");
		StringBuilder where =new StringBuilder();
		where.append(" where 1=1 ");
		//精确
		if(paramMap.get("enable")!=null && !paramMap.get("enable").equals("")){
			where.append(" and a.`enable` = :enable ");
		}
		if(paramMap.get("first_skill")!=null && !paramMap.get("first_skill").equals("")){
			where.append(" and e.`id` = :first_skill ");
		}
		if(paramMap.get("second_skill")!=null && !paramMap.get("second_skill").equals("")){
			where.append(" and d.`id` = :second_skill ");
		}
		if(paramMap.get("third_skill")!=null && !paramMap.get("third_skill").equals("")){
			where.append(" and c.`id` = :third_skill");
		}
		//模糊
		if(paramMap.get("name")!=null && !paramMap.get("name").equals("")){
			where.append(" and a.`name` like '%"+paramMap.get("name")+"%'");
		}
		querySql=querySql.replace("[where]",where.toString());
		querySql=querySql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+querySql+"\nparam:"+paramMap.toString());
		return dao.queryForList(querySql, paramMap);
	}
}
