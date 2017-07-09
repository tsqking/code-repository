package com.clps.tmp.tech.classes.service.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.classes.service.ClassService;
import com.clps.tmp.tech.classes.vo.ClassVo;





/**
 * 班级管理  服务类实现类
 * @author Seven
 *
 * 2015年11月3日
 */
@Service("classService")
public class ClassServiceImpl extends BaseService implements ClassService {

	private static String sqlFile="tech" + File.separator + "class" + File.separator + "ClassSql.xml";

	public int addClassData(ClassVo classVo) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(classVo);
		// 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/AddClass");
        System.out.println("SQL:\n" + sql + "\nparam:" + classVo.toString());
        int res = this.dao.insert(sql, paramSource);
        // 返回结果集
        return res;
	}

	@Override
	public PageVo<ClassVo> queryClassPage(PageVo<ClassVo> pageVo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page =  SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getClassPage");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getClassPageAllCount");
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
				if("a.`name`".equals(entry.getKey())){
					conditions.append(" (a.`name[lang]` like concat('%',:name,'%') or a.`session[lang]` like concat('%',:name,'%') or concat(a.`session[lang]`,' - ',a.`name[lang]`)like concat('%',:name,'%')) ");
					paramMap.put("name",entry.getValue());
				}else{
					conditions.append(entry.getKey());
					conditions.append(" like '%");
					conditions.append(entry.getValue());
					conditions.append("%' ");
				}
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
		List<ClassVo> list=(List<ClassVo>) this.dao.query(sql_page,paramMap, BeanPropertyRowMapper.newInstance(ClassVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count,paramMap, Integer.class);
		for(ClassVo vo:list){
			vo.setUpdate_time(DateTimeUtil.databaseToSystem(vo.getUpdate_time()));
			vo.setCreate_time(DateTimeUtil.databaseToSystem(vo.getCreate_time()));
		}
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int deleteClass(String id) throws Exception {
		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        String sql=null;
        //check是否关联
        sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/CheckClassWhenDelete");
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        int checkClass=(Integer) this.dao.queryForObject(sql, paramMap, Integer.class);
        if(checkClass==0){//没有关联，可以删除
        	int effect=0;
        	//删除班级人员Mapping
        	sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/DeletePeopleInClass");
        	log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        	effect+=this.dao.delete(sql, paramMap);
        	//删除班级
        	sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/DeleteClass");
        	log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        	effect+=this.dao.delete(sql, paramMap);
        	return effect;
        }else{
        	return -1;
        }
	}
	
	@Override
	public ClassVo selectClass(Map<String, Object> paramMap) throws Exception {
		// 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/SelectClass");
        sql=sql.replaceAll("\\[lang\\]", getLang());
        System.out.println("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        //执行sql
        List<ClassVo> resSkill = (List<ClassVo>) this.dao.query(sql, paramMap, new RowMapper<ClassVo>(){
            public ClassVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                ClassVo ClassVo = new ClassVo();
                ClassVo.setId(rs.getInt("id"));
                ClassVo.setNo(rs.getString("no"));
                ClassVo.setName(rs.getString("name"));
                ClassVo.setName_en_US(rs.getString("name_en_US"));
                ClassVo.setDescription(rs.getString("description"));
                ClassVo.setDescription_en_US(rs.getString("description_en_US"));
                ClassVo.setStudent_monitor(rs.getInt("student_monitor"));
                ClassVo.setTeacher_monitor(rs.getInt("teacher_monitor"));
                ClassVo.setDirection(rs.getString("direction"));
                ClassVo.setSize(rs.getInt("size"));
                ClassVo.setSession(rs.getString("session"));
                ClassVo.setSession_en_US(rs.getString("session_en_US"));
                ClassVo.setLocation(rs.getString("location"));
                ClassVo.setEnable(rs.getString("enable"));
                ClassVo.setUpdate_time(DateTimeUtil.databaseToSystem(rs.getString("update_time")));
                ClassVo.setUpdate_person(rs.getString("update_person"));
                ClassVo.setCreate_time(DateTimeUtil.databaseToSystem(rs.getString("create_time")));
                ClassVo.setCreate_person(rs.getString("create_person"));
                return ClassVo;
            }
        });
        if(resSkill.size()==0)
        	return null;
        return resSkill.get(0);
	}

	@Override
	public int editClass(ClassVo classVo) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(classVo);
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/UpdateClass");
        log.info("SQL:\n" + sql + "\nparam:" + classVo.toString());
        int res = this.dao.update(sql,paramSource);
        return res;
	}

	public PageVo<UserVo> queryUserPage(PageVo<UserVo> pageVo,int classId) throws Exception {
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("class_id", classId);
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/QueryPerson");
		String sql_count = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/QueryAllCountPerson");
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
				if("a.`name`".equals(entry.getKey())){
					conditions.append(" and (a.`name` like concat('%',:name,'%') or a.`en_name` like concat('%',:name,'%')) ");
					paramMap.put("name", entry.getValue());
				}else{
					conditions.append(" and ");
					conditions.append(entry.getKey());
					conditions.append(" like '%");
					conditions.append(entry.getValue());
					conditions.append("%' ");
				}
			}	
		}	
		sql_page = sql_page.replace("[where]", conditions.toString());
		sql_count = sql_count.replace("[where]", conditions.toString());
		//排序
		conditions = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			conditions.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				conditions.append(" `"+entry.getKey()+"` ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by a.`id` asc ");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count+"\n"+paramMap.toString());
		//查询数据
		@SuppressWarnings("unchecked")
		List<UserVo> list=(List<UserVo>) this.dao.query(sql_page, paramMap, new RowMapper<UserVo>(){
            public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	UserVo vo = new UserVo();
            	vo.setId(rs.getString("id"));
            	vo.setNo(rs.getString("no"));
            	vo.setName(rs.getString("name"));
            	vo.setEn_name(rs.getString("en_name"));
            	vo.setGender(rs.getString("gender"));
            	vo.setMobile(rs.getString("mobile"));
            	vo.setDescription(rs.getString("description"));	
            	vo.setExam_num(rs.getString("exam_num"));	
                return vo;
            }
        });
		int allcount=(Integer) this.dao.queryForObject(sql_count, paramMap, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int setMonitorInClass(int classId,String type,String monitorNo,String updateUser,String updateTime) throws Exception {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", classId);
		paramMap.put("monitor", monitorNo);
		paramMap.put("update_time", updateTime);
		paramMap.put("update_person", updateUser);
		String sql=null;
		int exist=0;
		if("0".equals(monitorNo)){//monitorNo=0 表示取消设置
			exist=1;
		}else{
			sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkPersonNo");
			exist=(Integer) this.dao.queryForObject(sql, paramMap, Integer.class);
		}
		if(exist==0){//人员不存在
			return -1;
		}else{
			if("student".equals(type)){
				sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/setSMonitorInClass");
			}else if("teacher".equals(type)){
				sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/setTMonitorInClass");
			}
			log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
			int res = dao.update(sql, paramMap);
			// 返回结果集
			return res;
		}
	}

	@Override
	public int addPersonsInClass(Map<String, Object> paramMap) throws Exception {
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/AddPersonInClass");
		log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());		
	    int res = dao.insert(sql, paramMap);
		return res;
	}

	@Override
	public int updatePersonsInClassSize(Map<String, Object> paramMap) throws Exception {
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/UpdateClassSize");
	    log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());	
	    int res = dao.update(sql, paramMap);
		return res;
	}

	@Override
	public int deletePersonInClass(Map<String, Object> paramMap) throws Exception {
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/DeletePersonInClass");
		log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());	
		int res = dao.delete(sql, paramMap);
		return res;
	}

	@Override
	public HashMap<String, Object> batchAddUser(List<Map<String, Object>> list) throws Exception {
		HashMap<String,Object> rtn=new HashMap<String,Object>();//返回map
		StringBuffer existMobile=new StringBuffer();//存放已存在用户的手机信息
		StringBuffer userName=new StringBuffer();//搜集用户名
		int i=0;
		for(Map<String,Object> map:list){
			if(existMobile((String)map.get("mobile"))){//手机号存在，则已是系统用户
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("username", (String)map.get("mobile"));
				// 获取SQL语句
				String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/QueryEveryPerson");
				System.out.println("SQL:\n" + sql + "\nparam:" + paramMap.toString());	
				// 查询
				List<UserVo> userList = (List<UserVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(UserVo.class));
				UserVo user=userList.get(0);
				map.put("username", user.getUsername());
				map.put("exist", true);
				userName.append(user.getUsername()+";");
			}else{//注册新用户
				i++;
				//按照需求，须将手机号作为用户名，判断用户名是否存在
				if(existUserName((String)map.get("mobile"))){
					map.put("username", new GenerateNextNo().getNextTempUserName());//动态生成临时用户名
				}
				map.put("no", new GenerateNextNo().getNextPersonNo("3"));//动态生成学号
				map.put("password", SecurityHelper.DESEncrypt(genertePassWord((String)map.get("cardno"))));//动态生成密码
				userName.append(((String)map.get("username"))+";");
			}
		}
		//准备数据
		Map<String,Object>[] batchUser=new HashMap[i];
		i=0;
		for(Map<String,Object> map:list){
			if((Boolean) map.get("exist")){//记录已存在用户，到时候返回给前台
				existMobile.append(((String)map.get("mobile"))+";");
			}else{
				batchUser[i++]=map;
			}
		}
		//插入数据库
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/BatchAddPerson");
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);
		//先去除人员基本角色
		sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/BatchRemovePersonBaseRole");
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);
		//再关联人员基本角色
		sql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/BatchAddPersonRole");
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);
		
		rtn.put("existMobile", existMobile.toString());
		rtn.put("userNameList", userName.toString());
		return rtn;
	}
	/**
	 * 根据身份证号生成密码--身份证后6位
	 */
	private String genertePassWord(String cardNo)throws Exception{
		return cardNo.substring(cardNo.length()-6);
	}
	
	@Override
	public boolean existUserName(String userName) throws Exception{
		boolean exist=true;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userName", userName);
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/ExistUserName");
		
		int count=(Integer) this.dao.queryForObject(sql, paramMap, Integer.class);
		if(count==0)
			exist=false;
		return exist;
	}

	@Override
	public boolean existMobile(String mobile) throws Exception {
		boolean exist=true;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mobile", mobile);
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/ExistMobile");
		
		int count=(Integer) this.dao.queryForObject(sql, paramMap, Integer.class);
		if(count==0)
			exist=false;
		return exist;
	}

	@Override
	public void batchAddUserInClass(String [] userNames,int id) throws Exception {
		//准备数据
		Map<String,Object>[] batchUserInfo=new HashMap[userNames.length];
		for(int i=0;i<userNames.length;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("username", userNames[i]);
			map.put("class_id", id);
			batchUserInfo[i]=map;
		}
		//先清除记录
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/BatchRemovePersonFromClass");
		log.info("SQL:\n" + sql+"\n"+batchUserInfo.toString());
		this.dao.batchUpdate(sql, batchUserInfo);
		//再插入数据库
		sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/BatchAddPersonInClass");
		log.info("SQL:\n" + sql+"\n"+batchUserInfo.toString());
		this.dao.batchUpdate(sql, batchUserInfo);
	}
	
}
