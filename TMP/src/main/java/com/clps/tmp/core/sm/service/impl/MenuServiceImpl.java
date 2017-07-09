package com.clps.tmp.core.sm.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.vo.MenuVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.service.MenuService;

/**
 * 菜单 service实现类
  * @ClassName: MenuServiceImpl
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 上午9:48:09
 */
@Service("menuService")
public class MenuServiceImpl extends BaseService implements MenuService {
	

	/**
	 * 查询用户的菜单
	 */
	@Override
	public List<MenuVo> queryUserMenu(UserVo user) throws Exception {
		// TODO Auto-generated method stub
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", user.getId());
		//获取SQL语句
		String sql = SystemSQLConstant.QUERY_USER_MENU;
		sql=sql.replaceAll("\\[lang\\]", getLang());
		//查询
		@SuppressWarnings("unchecked")
		List<MenuVo> list = (List<MenuVo>) this.dao.query(sql, paramMap,
			BeanPropertyRowMapper.newInstance(MenuVo.class));
		//返回
		return list;
	}

	
	/**
	 * 菜单分页查询
	 */
	@Override
	public PageVo<MenuVo> queryMenuPage(PageVo<MenuVo> pv)throws Exception {
		// TODO Auto-generated method stub
		//取出数据
		int page = pv.getPage();
		int limitPage = pv.getLimitPage();
		HashMap<String, String> where1 = pv.getWhere1();
		HashMap<String, String> where2 = pv.getWhere2();
		LinkedHashMap<String, String> sort = pv.getSort();
		//获取SQL语句
		String sql1 = SystemSQLConstant.QUERY_MENU_PAGE;
		String sql2 = SystemSQLConstant.QUERY_MENU_ALLCOUNT;
		//条件
		StringBuilder sb = new StringBuilder();
		//起始条数
		sql1 = sql1.replace("[start]", ((page-1)*limitPage)+"");
		//每页条数
		sql1 = sql1.replace("[number]", limitPage+"");
		//搜索条件
		sb = new StringBuilder();
		//精确查询
		if(!(where1==null||where1.size()<=0)){
			boolean flag = false;
			sb.append(" where ");
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				if(flag){
					sb.append(" and ");
				}else{
					flag = true;
				}
				sb.append("a.`");
				if(entry.getKey().equalsIgnoreCase("parent_id"))
					sb.append("super_parent_id");
				else
					sb.append(entry.getKey());
				sb.append("`='");
				sb.append(entry.getValue());
				sb.append("'");
			}	
		}else{
			sb.append(" ");
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			boolean flag = false;
			if(where1==null||where1.size()<=0){
				sb.append(" where ");
			}else{
				sb.append(" and ");
			}
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				if(flag){
					sb.append(" and ");
				}else{
					flag = true;
				}
				sb.append("a.`");
				sb.append(entry.getKey());
				sb.append("` like '%");
				sb.append(entry.getValue());
				sb.append("%'");
			}	
		}else{
			sb.append(" ");
		}	
		sql1 = sql1.replace("[where]", sb.toString());
		sql2 = sql2.replace("[where]", sb.toString());
		//排序
		sb = new StringBuilder();
		if(!(sort==null||sort.size()<=0)){
			boolean flag = false;
			sb.append(" order by ");
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				if(flag){
					sb.append(",");
				}
				String col = entry.getKey();
				if(col.equals("enable_name")){
					col = "enable";
				}		
				sb.append("a.`"+col+"` ");
				sb.append(entry.getValue());
			}	
		}else{
			sb.append(" order by a.`level` asc,a.`order` asc ");
		}
		sql1 = sql1.replace("[order]", sb.toString());	
		//国际化
		sql1 = sql1.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n"+sql1+"\n"+sql2);
		//查询数据
		@SuppressWarnings({ "unchecked"})
		List<MenuVo> list = (List<MenuVo>) this.dao.query(sql1,BeanPropertyRowMapper.newInstance(MenuVo.class));
		//查询总条数
		@SuppressWarnings({ "unchecked"})
		List<MenuVo> list2 = (List<MenuVo>) this.dao.query(sql2,BeanPropertyRowMapper.newInstance(MenuVo.class));
		String allcount = list2.get(0).getAllcount();
		//汇总数据
		pv.setList(list);
		pv.setAllcount(allcount);
		return pv;
	}


	/**
	 * 获得菜单名
	 */
	@Override
	public String queryMenuNameById(String menuId) throws Exception {
		// TODO Auto-generated method stub
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", menuId);
		//获取SQL语句
		String sql = SystemSQLConstant.QUERY_MENU_NAME;
		//查询
		@SuppressWarnings("unchecked")
		List<MenuVo> list = (List<MenuVo>) this.dao.query(sql, paramMap,
			BeanPropertyRowMapper.newInstance(MenuVo.class));
		//返回
		if(list.size()==0){
			return null;
		}else{
			return list.get(0).getName();
		}
	}

	/**
	 * 根据level获取菜单，用于下拉框
	 */
	@Override
	public List<SelectVo> getMenuByLevel(String level) throws Exception {
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("level", level);
		//查询语句
		String sql=SystemSQLConstant.QUERY_MENU_BY_LEVEL;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		@SuppressWarnings("unchecked")
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	/**
	 * 根据id级联删除菜单
	 */
	@Override
	public boolean deleteMenu(String menuId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", menuId);
		String sql=null;
		sql=SystemSQLConstant.QUERY_MENU_ALL;
		log.info("SQL:\n"+sql);
		@SuppressWarnings("unchecked")
		List<MenuVo> list=(List<MenuVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(MenuVo.class));
		//id
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		sb.append(" WHERE ");
		for(MenuVo m : list){
			if(flag){
				sb.append(" or ");
			}else{
				flag = true;
			}
			sb.append(" `id`='");
			sb.append(m.getId());
			sb.append("' ");
		}
		//check用户所有关联
		String checkMenuRelevantSql = SystemSQLConstant.CHECK_MENU_RELEVANT;
		String checkMenuWhere=sb.toString().replaceAll("`id`", "`menu_id`");
		checkMenuRelevantSql=checkMenuRelevantSql.replace("[WHERE]", checkMenuWhere);
		log.info("SQL:\n" + checkMenuRelevantSql);
		int checkMenuRelevant=(Integer)this.dao.queryForObject(checkMenuRelevantSql, paramMap, Integer.class);
		if(checkMenuRelevant==0){//无关联,可以删除
			//删除
			sql = SystemSQLConstant.DELETE_MENU_BY_ID;
			sql = sql.replace("[where]", sb.toString());	
			this.dao.delete(sql,new HashMap<String,Object>());
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据id查找菜单信息
	 */
	@Override
	public MenuVo detailMenu(String menuId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", menuId);
		String sql=SystemSQLConstant.QUERY_MENU_BY_ID;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		@SuppressWarnings("unchecked")
		List<MenuVo> list=(List<MenuVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(MenuVo.class));
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 更新菜单信息
	 */
	@Override
	public boolean updateMenu(MenuVo mv,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", mv.getName());
		paramMap.put("description", mv.getDescription());
		paramMap.put("name_en_US", mv.getName_en_US());
		paramMap.put("description_en_US", mv.getDescription_en_US());
		paramMap.put("url", mv.getUrl());
		paramMap.put("order", mv.getOrder());
		paramMap.put("enable", mv.getEnable());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		paramMap.put("id", mv.getId());
		String sql=SystemSQLConstant.UPDATE_MENU;
		log.info("SQL:\n"+sql);
		int re = this.dao.update(sql,paramMap);
		if(re!=0){
			return true;
		}else{
			return false;
		}	
	}


	/**
	 * 同等级菜单排序下拉框
	 */
	@Override
	public List<SelectVo> queryMenuOrderByLevel(String level,String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("level", level);
		String sql=SystemSQLConstant.QUERY_MENU_ORDER;
		if(!level.equals("1")){
			sql = sql.replace("[where]", " and `parent_id`='"+id+"' ");	
		}else{
			sql = sql.replace("[where]", "");	
		}	
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		@SuppressWarnings("unchecked")
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(SelectVo.class));
		if(list!=null){
			return list;
		}
		return null;
	}


	/**
	 * 添加菜单
	 */
	@Override
	public boolean addMenuToParentMenu(MenuVo mv,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		String pid = mv.getParent_id();
		if(pid==null||pid.equals("")){
			pid=null;
		}
		if(pid==null){
			//一级菜单
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("name", mv.getName()==null? "New Menu" : mv.getName());
			paramMap.put("name_en_US", mv.getName_en_US()==null? "New Menu" : mv.getName_en_US());
			paramMap.put("description", mv.getDescription()==null? "No Description" : mv.getDescription());
			paramMap.put("description_en_US", mv.getDescription_en_US()==null? "No Description" : mv.getDescription_en_US());
			paramMap.put("level", "1");
			paramMap.put("url", mv.getUrl()==null? "" : mv.getUrl());
			paramMap.put("parent_id", pid==null? "0":pid);
			paramMap.put("order", mv.getOrder()==null? "0":Integer.parseInt(mv.getOrder())+1);
			paramMap.put("enable", mv.getEnable()==null? "F":mv.getEnable());
			paramMap.put("create_time", DateTimeUtil.nowToDatabase());
			paramMap.put("create_person", user.getName());
			paramMap.put("update_time", DateTimeUtil.nowToDatabase());
			paramMap.put("update_person", user.getName());		
			String sql=SystemSQLConstant.ADD_MENU_TO_PMENU;
			sql = sql.replaceAll("\\[lang\\]", getLang());
			int re = this.dao.update(sql,paramMap);
			if(re!=0){
				return true;
			}else{
				return false;
			}
		}else{
			//子菜单
			MenuVo pm = this.detailMenu(pid);
			if(pm!=null){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("name", mv.getName()==null? "New Menu" : mv.getName());
				paramMap.put("name_en_US", mv.getName_en_US()==null? "New Menu" : mv.getName_en_US());
				paramMap.put("description", mv.getDescription()==null? "No Description" : mv.getDescription());
				paramMap.put("description_en_US", mv.getDescription_en_US()==null? "No Description" : mv.getDescription_en_US());
				paramMap.put("level", pm.getLevel()==null? "2":Integer.parseInt(pm.getLevel())+1);
				paramMap.put("url", mv.getUrl()==null? "" : mv.getUrl());
				paramMap.put("parent_id", mv.getParent_id()==null? "0":mv.getParent_id());
				paramMap.put("order", (mv.getOrder()==null||mv.getOrder().equals(""))? "0":Integer.parseInt(mv.getOrder())+1);
				paramMap.put("enable",(mv.getEnable()==null||mv.getEnable().equals(""))? "F":mv.getEnable());
				paramMap.put("create_time", DateTimeUtil.nowToDatabase());
				paramMap.put("create_person", user.getName());
				paramMap.put("update_time", DateTimeUtil.nowToDatabase());
				paramMap.put("update_person", user.getName());
				String sql=SystemSQLConstant.ADD_MENU_TO_PMENU;
				sql = sql.replaceAll("\\[lang\\]", getLang());
				int re = this.dao.update(sql,paramMap);
				if(re!=0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}			
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuVo> queryMenuAndChkRole(String roleId) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("role_id", roleId);
		//获取数据
		String sql=SystemSQLConstant.QUERY_ALL_MENU_AND_CHECK_ROLE.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		List<MenuVo> list=(List<MenuVo>) this.dao.query(sql, paramMap, BeanPropertyRowMapper.newInstance(MenuVo.class));
		return list;
	}
}
