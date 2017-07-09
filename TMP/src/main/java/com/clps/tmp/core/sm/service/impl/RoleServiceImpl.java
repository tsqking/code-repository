package com.clps.tmp.core.sm.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.service.RoleService;
import com.clps.tmp.core.sm.vo.OptionVo;
import com.clps.tmp.core.sm.vo.RoleVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * @author Seven
 *
 * 2015年10月19日
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService implements RoleService {

	@Override
	public PageVo<RoleVo> queryRolePage(PageVo<RoleVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page = SystemSQLConstant.QUERY_ROLE_PAGE;
		String sql_count = SystemSQLConstant.QUERY_ROLE_ALLCOUNT;
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
				conditions.append(" and a.`");
				conditions.append(entry.getKey());
				conditions.append("`='");
				conditions.append(entry.getValue());
				conditions.append("' ");
			}	
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				conditions.append(" and a.`");
				conditions.append(entry.getKey());
				conditions.append("` like '%");
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
				conditions.append(" `"+entry.getKey()+"` ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by a.`update_time` asc ");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<RoleVo> list=(List<RoleVo>) this.dao.query(sql_page, BeanPropertyRowMapper.newInstance(RoleVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int deleteRole(String id) throws Exception {
		//条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		//删除人员角色对应表
		log.info("SQL:"+SystemSQLConstant.DELDTE_PERSON_ROLE);
		int count2=this.dao.delete(SystemSQLConstant.DELDTE_PERSON_ROLE, paramMap);
		//删除菜单角色对应表
		log.info("SQL:"+SystemSQLConstant.DELETE_MENU_ROLE);
		int count3=this.dao.delete(SystemSQLConstant.DELETE_MENU_ROLE, paramMap);
		//删除角色表
		log.info("SQL:"+SystemSQLConstant.DELETE_ROLE);
		int count1=this.dao.delete(SystemSQLConstant.DELETE_ROLE, paramMap);
		return count1+count2+count3;
	}

	@Override
	public RoleVo getRoleByID(String id) throws Exception {
		//条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		//查询
		log.info("SQL:\n"+SystemSQLConstant.QUERY_ROLE_BY_ID);
		List<RoleVo> list= (List<RoleVo>) this.dao.query(SystemSQLConstant.QUERY_ROLE_BY_ID, paramMap, BeanPropertyRowMapper.newInstance(RoleVo.class));
		RoleVo role=null;
		if(list.size()!=0){
			role=list.get(0);
		}
		return role;
	}

	@Override
	public int updateRole(RoleVo role) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(role);
		log.info("SQL：\n"+SystemSQLConstant.UPDATE_ROLE_BY_ID);
		int effect=this.dao.insert(SystemSQLConstant.UPDATE_ROLE_BY_ID, paramSource);
		return effect;
	}

	@Override
	public int addRole(RoleVo role) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(role);
		log.info("SQL：\n"+SystemSQLConstant.ADD_ROLE);
		int effect=this.dao.insert(SystemSQLConstant.ADD_ROLE, paramSource);
		return effect;
	}

	@Override
	public int updateRoleMenu(String role_id, Map<String, Object>[] paramMap) throws Exception {
		// TODO delete then add
		HashMap<String,Object> roleIdParam=new HashMap<String,Object>();
		roleIdParam.put("role_id", role_id);
		//delete
		log.info("SQL:\n"+ SystemSQLConstant.DELETE_ROLE_MENU_BY_ROLE_ID);
		int effect=0;
		this.dao.delete(SystemSQLConstant.DELETE_ROLE_MENU_BY_ROLE_ID, roleIdParam);
		
		if(paramMap==null)return effect;
		//insert
		log.info("SQL:\n"+ SystemSQLConstant.INSERT_ROLE_MENU);
		int[] batch_effect=this.dao.batchUpdate(SystemSQLConstant.INSERT_ROLE_MENU,paramMap);
		for(int tmp:batch_effect)
			effect+=tmp;
		return effect;
	}

	@Override
	public PageVo<UserVo> queryRolePersonPage(PageVo<UserVo> pageVo,String roleId) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page = SystemSQLConstant.QUERY_ROLE_PERSON;
		String sql_count = SystemSQLConstant.QUERY_ROLE_PERSON_ALLCOUNT;
		sql_page = sql_page.replace(":role_id","'"+roleId+"'");
		sql_count = sql_count.replace(":role_id","'"+roleId+"'");
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
				conditions.append(" and a.`");
				conditions.append(entry.getKey());
				conditions.append("`='");
				conditions.append(entry.getValue());
				conditions.append("' ");
			}	
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				conditions.append(" and a.`");
				conditions.append(entry.getKey());
				conditions.append("` like '%");
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
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<UserVo> list=(List<UserVo>) this.dao.query(sql_page, BeanPropertyRowMapper.newInstance(UserVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int updatePersonsInRole(Map<String, Object>[] paramMap, String operation) throws Exception {
		int[] effect;
		int total=0;
		if("delete".equals(operation)){
			String deleteSQL=SystemSQLConstant.DELDTE_PERSON_IN_ROLE;
			log.info("Delete SQL:\n"+deleteSQL);
			effect=this.dao.batchUpdate(deleteSQL, paramMap);
		}else{
			String insertSQL=SystemSQLConstant.INSERT_PERSON_IN_ROLE;
			log.info("Insert SQL:\n"+insertSQL);
			effect=this.dao.batchUpdate(insertSQL, paramMap);
		}
		for(int tmp:effect)
			total+=tmp;
		return total;
	}

}
