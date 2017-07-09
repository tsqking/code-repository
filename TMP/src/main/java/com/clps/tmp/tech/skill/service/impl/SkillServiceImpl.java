package com.clps.tmp.tech.skill.service.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.skill.service.SkillService;
import com.clps.tmp.tech.skill.vo.SkillVo;

/**
 * @author Seven
 *
 *         2015年10月19日
 */
@Service("skillService")
public class SkillServiceImpl extends BaseService implements SkillService {
	private static String sqlFile="tech" + File.separator + "skill" + File.separator + "SkillSql.xml";
    @Override
    public int addSkillData(Map<String, Object> paramMap) throws Exception {
        // 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/AddSkill");
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        // 执行sql
        int res = dao.insert(sql, paramMap);
        // 返回结果集
        return res;
    }

    @Override
    public PageVo<SkillVo> queryOptionPage(PageVo<SkillVo> pageVo) throws Exception {
        // 获取sql语句
        String sqlFind = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/FindSkill");
        log.info("SQL:\n" + sqlFind + "\nparam:");
        // 获取总条数
        String sqlFindAllcount = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/FindSkillAccount");
        log.info("SQL:\n" + sqlFindAllcount + "\nparam:");
        
        // 分页参数
        int page = pageVo.getPage();
        int limitPage = pageVo.getLimitPage();
        // 排序参数
        LinkedHashMap<String, String> sort = pageVo.getSort();
        //精确查询
        HashMap<String, String> where1 = pageVo.getWhere1();
        // 拼凑分页sql
        StringBuilder conditions = new StringBuilder();
        sqlFind = sqlFind.replace("[start]", ((page - 1) * limitPage) + "");
        sqlFind = sqlFind.replace("[number]", limitPage + "");
        conditions.append(sqlFind);

        StringBuilder conditionAllcount = new StringBuilder();  
        // 拼凑条件查询
        conditions = new StringBuilder();
        conditionAllcount = new StringBuilder();
        conditions.append(" where 1=1 ");
        conditionAllcount.append(" where 1=1 ");
        if(!(where1==null||where1.size()<=0)){
            StringBuilder whereConditionData = new StringBuilder();
            StringBuilder whereConditionPage = new StringBuilder();
            //conditions.append(" and `ENABLE`='T' and ");
            //conditionAllcount.append(" and `ENABLE`='T' and ");
            whereConditionData.append(" and (");
            whereConditionPage.append(" and (");
            for (Map.Entry<String, String> entry : where1.entrySet()) {            
                whereConditionData.append("  `");
                whereConditionData.append(entry.getKey());
                whereConditionData.append("`='");
                whereConditionData.append(entry.getValue());
                whereConditionData.append("' ");
                whereConditionData.append(" or ");
                
                whereConditionPage.append("  `");
                whereConditionPage.append(entry.getKey());
                whereConditionPage.append("`='");
                whereConditionPage.append(entry.getValue());
                whereConditionPage.append("' ");
                whereConditionPage.append(" or ");
             }   
            conditions = conditions.append(whereConditionData.substring(0,whereConditionData.length()-3));
            conditionAllcount = conditionAllcount.append(whereConditionPage.substring(0,whereConditionPage.length()-3));
            conditions.append(")");
            conditionAllcount.append(")");
        }
        
        //sql精确查找条件替换
        sqlFind = sqlFind.replace("[where]", conditions.toString());
        sqlFindAllcount = sqlFindAllcount.replace("[where]", conditionAllcount.toString());
       
        // 拼凑排序sql
        conditions = new StringBuilder();
        if (!(sort == null || sort.size() <= 0)) {
            conditions.append(" order by ");
            for (Map.Entry<String, String> entry : sort.entrySet()) {
                
                conditions.append("`" + entry.getKey() + "`");
                conditions.append(" ");
                conditions.append(entry.getValue() + ",");
            }
            conditions.deleteCharAt(conditions.length() - 1);
        } else {
            conditions.append(" order by `level` asc,`order` asc ");
        }
        sqlFind = sqlFind.replace("[order]", conditions.toString());
        sqlFind = sqlFind.replaceAll("\\[lang\\]", getLang());
        log.info("SQL：\n" + sqlFind + "\n");

        // 执行sql
        List<SkillVo> resList = (List<SkillVo>) dao.query(sqlFind, new RowMapper<SkillVo>() {
            public SkillVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                SkillVo skillVo = new SkillVo();
                skillVo.setId(rs.getInt("id"));
                skillVo.setOrder(rs.getInt("order"));
                skillVo.setName(rs.getString("name"));
                skillVo.setType(rs.getString("type"));
                skillVo.setParent_name(rs.getString("parent_name"));
                skillVo.setDescription(rs.getString("description"));
                skillVo.setLevel(rs.getString("level"));
                skillVo.setEnable(rs.getString("enable"));
                skillVo.setUpdate_time(rs.getString("update_time"));
                skillVo.setUpdate_person(rs.getString("update_person"));
                return skillVo;
            }
        });
        
        sqlFindAllcount=sqlFindAllcount.replaceAll("\\[lang\\]", getLang());
        // 查询条数
        int allcount = (Integer) this.dao.queryForObject(sqlFindAllcount, Integer.class);
        // 返回结果集
        pageVo.setList(resList);
        pageVo.setAllcount(String.valueOf(allcount));
        return pageVo;
    }

    @Override
    public List<SelectVo> findOptionsByParentId(Map<String, Object> paramMap) throws Exception {
        // 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/FindMenuOption");
        sql=sql.replaceAll("\\[lang\\]", getLang());
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        // 执行sql
        List<SelectVo> res = (List<SelectVo>) dao.query(sql,paramMap,new RowMapper<SelectVo>(){
            @Override
            public SelectVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	SelectVo skillVo = new SelectVo();
            	skillVo.setId(rs.getString("id"));
            	skillVo.setText(rs.getString("text"));
                return skillVo;
            }
        });
        // 返回结果集
        return res;
    }

    @Override
    public Map<String,Object> deleteSkill(Map<String,Object> paramMap) throws Exception {
    	//返回Map
    	//code - 0/1 0标识已删除 1标识未能删除
    	//code = 0 时 effect - 删除记录数
    	//code = 1 时 existPoint - 挂载在技能下的知识点
    	Map<String,Object> rtnMap=new HashMap<String,Object>();
        //TODO 删除之前，查看其下是否有知识点挂载技能下(包括子技能)
    	String checkMappingSql=SqlLoader.loadSqlFromXml(sqlFile, "//SQL/checkMappingWhenDelete");
    	checkMappingSql=checkMappingSql.replaceAll("\\[lang\\]", getLang());
    	log.info("SQL:\n" + checkMappingSql + "\nparam:" + paramMap.toString());
		List<Map<String,Object>> pointListInSkill=this.dao.queryForList(checkMappingSql, paramMap);
		if(pointListInSkill.size()==0){//空技能，可以删除
			// 获取删除sql语句
	        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/DeleteSkill");
	        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
	        //执行sql
	        int effect=this.dao.delete(sql, paramMap);
	        rtnMap.put("code", "0");
	        rtnMap.put("effect", String.valueOf(effect));
		}else{//技能下挂载有知识点，不能删除
			StringBuffer pointSB=new StringBuffer();
			for(int i=0;i<pointListInSkill.size();i++){
				Map<String,Object> map=pointListInSkill.get(i);
				if(i!=0)
					pointSB.append("<br>");
				pointSB.append(map.get("name"));
			}
			rtnMap.put("code", "1");
			rtnMap.put("existPoint", pointSB.toString());
		}
        return rtnMap;
    }

    @Override
    public SkillVo selectSkill(Map<String, Object> paramMap) throws Exception {
        // 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/SelectSkill");
        sql=sql.replaceAll("\\[lang\\]", getLang());
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        //执行sql
        List<SkillVo> resSkill = (List<SkillVo>) this.dao.query(sql, paramMap, new RowMapper<SkillVo>(){
            public SkillVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                SkillVo skillVo = new SkillVo();
                skillVo.setId(rs.getInt("id"));
                skillVo.setOrder(rs.getInt("order"));
                skillVo.setName(rs.getString("name"));
                skillVo.setName_en_US(rs.getString("name_en_US"));
                skillVo.setType(rs.getString("type"));
                skillVo.setParent_id(rs.getInt("parent_id"));
                skillVo.setParent_name(rs.getString("parent_name"));
                skillVo.setDescription(rs.getString("description"));
                skillVo.setDescription_en_US(rs.getString("description_en_US"));
                skillVo.setLevel(rs.getString("level"));
                skillVo.setEnable(rs.getString("enable"));
                skillVo.setUpdate_time(rs.getString("update_time"));
                skillVo.setUpdate_person(rs.getString("update_person"));
                return skillVo;
            }
        });
        if(resSkill.size()==0)
        	return null;
        return resSkill.get(0);
    }

    @Override
    public int editSkill(Map<String, Object> paramMap) throws Exception {
        // 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/UpdateSkill");
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        int res = this.dao.update(sql,paramMap);
        return res;
    }
}
