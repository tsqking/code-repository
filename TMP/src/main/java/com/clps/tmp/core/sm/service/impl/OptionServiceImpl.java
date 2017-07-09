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
import com.clps.tmp.core.sm.service.OptionService;
import com.clps.tmp.core.sm.vo.OptionVo;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Seven
 *
 * 2015年10月13日
 */
@Service("optionService")
public class OptionServiceImpl extends BaseService implements OptionService  {
	/**
	 * 根据选项组调用值查询选项
	 * @throws Exception 
	 */
	@Override
	public List<SelectVo> getOptionsByGPVal(String value) throws Exception {
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("gp_value", value);
		//查询语句
		String sql=SystemSQLConstant.QUERY_OPTIOIN_BY_GP_VAL;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	/**
	 * 根据选项组调用值查询选项,返回List&lt;Map>形式
	 * @throws Exception 
	 */
	@Override
	public List<Map<String,Object>> getOptionMapByGPVal(String value) throws Exception {
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("gp_value", value);
		//查询语句
		String sql=SystemSQLConstant.QUERY_OPTIOIN_BY_GP_VAL;
		sql = sql.replaceAll("\\[lang\\]", "");
		log.info("SQL:\n"+sql);
		return this.dao.queryForList(sql, paramMap);
	}
	
	@Override
	public List<SelectVo> getOptionsByLevel(String level) throws Exception {
		//搜索条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("level", level);
		//查询语句
		String sql=SystemSQLConstant.QUERY_OPTIOIN_BY_LEVEL;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,paramMap,BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}
	
	@Override
	public List<SelectVo> getOptionGroups() throws Exception {
		//查询语句
		String sql=SystemSQLConstant.QUERY_OPTIOIN_GROUPS;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n"+sql);
		List<SelectVo> list=(List<SelectVo>) this.dao.query(sql,BeanPropertyRowMapper.newInstance(SelectVo.class));
		return list;
	}

	@Override
	public PageVo<OptionVo> queryOptionPage(PageVo<OptionVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//获取SQL语句
		String sql_page = SystemSQLConstant.QUERY_OPTION_PAGE;
		String sql_count = SystemSQLConstant.QUERY_OPTION_ALLCOUNT;
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
				if(entry.getKey().equals("b.`value`")){
					conditions.append(" and (");
					conditions.append(entry.getKey());
					conditions.append("='");
					conditions.append(entry.getValue());
					conditions.append("' or a.`value`='"+entry.getValue()+"') ");
					continue;
				}
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
				conditions.append("a.`"+entry.getKey()+"`");
				conditions.append(" ");
				conditions.append(entry.getValue()+",");
			}
			conditions.deleteCharAt(conditions.length()-1);
		}else{
			conditions.append(" order by a.`level` asc,a.`order` asc ");
		}
		sql_page = sql_page.replace("[order]", conditions.toString());	
		
		log.info("[lang]:"+getLang());
		log.info("转换前\nSQL：\n"+sql_page+"\n"+sql_count);
		sql_page = sql_page.replaceAll("\\[lang\\]", getLang());	
		sql_count = sql_count.replaceAll("\\[lang\\]", getLang());	
		log.info("SQL：\n"+sql_page+"\n"+sql_count);
		//查询数据
		List<OptionVo> list=(List<OptionVo>) this.dao.query(sql_page, BeanPropertyRowMapper.newInstance(OptionVo.class));
		int allcount=(Integer) this.dao.queryForObject(sql_count, Integer.class);
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(allcount));
		return pageVo;
	}

	@Override
	public int deleteOption(String id) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		//执行删除
		int count=this.dao.delete(SystemSQLConstant.DELETE_OPTION, paramMap);
		return count;
	}

	@Override
	public OptionVo getOptionByID(String id) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		//执行查询
		List<OptionVo> list=(List<OptionVo>) this.dao.query(SystemSQLConstant.QUERY_OPTION_BY_ID.replaceAll("\\[lang\\]", getLang()), paramMap,BeanPropertyRowMapper.newInstance(OptionVo.class));
		OptionVo option=null;
		if(list.size()!=0){
			option=list.get(0);
		}
		return option;
	}

	@Override
	public int updateOption(OptionVo option) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(option);
		log.info("SQL：\n"+SystemSQLConstant.UPDATE_OPTION_BY_ID);
		int effect=this.dao.insert(SystemSQLConstant.UPDATE_OPTION_BY_ID, paramSource);
		return effect;
	}

	@Override
	public int addOption(OptionVo option) throws Exception {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(option);
		log.info("SQL：\n"+SystemSQLConstant.ADD_OPTION);
		int effect=this.dao.insert(SystemSQLConstant.ADD_OPTION, paramSource);
		return effect;
	}

	@Override
	public int checkOptionGroupByVal(String value) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("value", value);
		//查询
		log.info("\nSQL:"+SystemSQLConstant.QUERY_OPTION_GP_BY_VALUE+"\nParam:"+paramMap.toString());
		int count=(int) this.dao.queryForObject(SystemSQLConstant.QUERY_OPTION_GP_BY_VALUE, paramMap, Integer.class);
		return count;
	}
	
	@Override
	public int checkOptionGroupByVal(String value, String excludeId) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("value", value);
		paramMap.put("id", excludeId);
		//查询
		log.info("\nSQL:"+SystemSQLConstant.QUERY_OTHER_OPTION_GP_BY_VALUE+"\nParam:"+paramMap.toString());
		int count=(int) this.dao.queryForObject(SystemSQLConstant.QUERY_OTHER_OPTION_GP_BY_VALUE, paramMap, Integer.class);
		return count;
	}

	@Override
	public int checkOptionsByValPid(String value, String parent_id) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("value", value) ;
		paramMap.put("parent_id", parent_id) ;
		//查询
		log.info("\nSQL:"+SystemSQLConstant.QUERY_OPTIONS_BY_VALUE_PID+"\nParam:"+paramMap.toString());
		int count=(int) this.dao.queryForObject(SystemSQLConstant.QUERY_OPTIONS_BY_VALUE_PID, paramMap, Integer.class);
		return count;
	}

	@Override
	public int checkOptionsByValPid(String value, String parent_id, String excludeId) throws Exception {
		//搜索条件
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("value", value) ;
		paramMap.put("parent_id", parent_id) ;
		paramMap.put("id", excludeId) ;
		//查询
		log.info("\nSQL:"+SystemSQLConstant.QUERY_OTHER_OPTIONS_BY_VALUE_PID+"\nParam:"+paramMap.toString());
		int count=(int) this.dao.queryForObject(SystemSQLConstant.QUERY_OTHER_OPTIONS_BY_VALUE_PID, paramMap, Integer.class);
		return count;
	}

	
}
