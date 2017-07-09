package com.clps.tmp.common.region.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.region.service.ChinaRegionService;
import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.common.util.ChinaRegionUtil;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.vo.SelectVo;

@Service("chinaRegionService")
public class ChinaRegionImpl extends BaseService implements ChinaRegionService {
	private static String sqlFile="region" + File.separator+File.separator  + "ChinaRegion.xml";
	
	@Override
	public ChinaRegionVo addChinaRegion(String fileName) throws Exception {
		// 获取sql语句
		String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/insert");
		//变量和对象的初始化
        BufferedReader reader = null;
        String tempString = null;
        String parentProvinceId = null;
        String parentCityId = null;
        ChinaRegionVo chinaRegion = new ChinaRegionVo();
        try {
        //读取文件
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    new File(fileName)), "utf8");
            reader = new BufferedReader(read);
        //数据的拆分与组装
            while ((tempString = reader.readLine()) != null) {
                tempString = tempString.replaceAll(" +", ",");
                String[] tempStringArray = tempString.trim().split(",");
                String tempStringCode = tempStringArray[0];
                String tempStringName = ChinaRegionUtil
                        .deleteBlank(tempStringArray[1]);
                Map<String,Object> rtnMap=new HashMap<String,Object>();
                if (tempStringCode.substring(2, 6).equals("0000")) {
                    parentProvinceId = tempStringCode;
                    chinaRegion.setCode(parentProvinceId);
                    chinaRegion.setName(tempStringName);
                    chinaRegion.setParentId("0");
                    
                    log.info("SQL:\n" + sql + "\nparam:" + chinaRegion.toString());
                    rtnMap.put("code", chinaRegion.getCode());
                    rtnMap.put("parentId", chinaRegion.getParentId());
                    rtnMap.put("name", chinaRegion.getName());
                    
                    int res = dao.insert(sql, rtnMap);
                    if (res < 0) {
                        throw new Exception("插入数据异常");
                    }
                    continue;
                }
                if (tempStringCode.substring(4, 6).equals("00")
                        && !tempStringCode.substring(2, 6).equals("0000")) {
                    parentCityId = tempStringCode;
                    chinaRegion.setCode(parentCityId);
                    chinaRegion.setName(tempStringName);
                    chinaRegion.setParentId(parentProvinceId);
                    log.info("SQL:\n" + sql + "\nparam:" + chinaRegion.toString());
                   
                    rtnMap.put("code", chinaRegion.getCode());
                    rtnMap.put("parentId", chinaRegion.getParentId());
                    rtnMap.put("name", chinaRegion.getName());
                    int res = dao.insert(sql, rtnMap);
                    if (res <0) {
                        throw new Exception("插入数据异常");
                    }
                    continue;
                }
                if (!tempStringCode.substring(4, 6).equals("00")
                        && !tempStringCode.substring(2, 6).equals("0000")) {
                    chinaRegion.setCode(tempStringCode);
                    chinaRegion.setName(tempStringName);
                    chinaRegion.setParentId(parentCityId);
                    log.info("SQL:\n" + sql + "\nparam:" + chinaRegion.toString());
                    rtnMap.put("code", chinaRegion.getCode());
                    rtnMap.put("parentId", chinaRegion.getParentId());
                    rtnMap.put("name", chinaRegion.getName());
                    
                    int res = dao.insert(sql, rtnMap);
                    if (res <0) {
                        throw new Exception("插入数据异常");
                    }
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
		return chinaRegion;
	}
	@Override
	public Map<String, ChinaRegionVo> findChinaRegion(Map<String, Object> param) throws Exception {
		return null;
	}
	
	@Override
	public void deleteTable() throws Exception {
		// 获取sql语句
		String sqlselect = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/selectAll");
		String sqldelete = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/delete");
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> rtnMap=dao.queryForMap(sqlselect, map);
		int res=this.dao.delete(sqldelete, rtnMap);
	}
	
	@Override
	public List<SelectVo> findRegion(Map<String, Object> paramMap) throws Exception {
		// 获取sql语句
        String sql = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/selectprovince");
        sql=sql.replaceAll("\\[lang\\]", getLang());
        log.info("SQL:\n" + sql + "\nparam:" + paramMap.toString());
        // 执行sql
        @SuppressWarnings("unchecked")
		List<SelectVo> res = (List<SelectVo>) dao.query(sql,paramMap,new RowMapper<SelectVo>(){
            @Override
            public SelectVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	SelectVo vo = new SelectVo();
            	vo.setId(rs.getString("id"));
            	vo.setText(rs.getString("text"));
                return vo;
            }
        });
        // 返回结果集
        return res;
	}
	@Override
	public List<Map<String, Object>> getAllCodeName() throws Exception {
		String sqlselect = SqlLoader.loadSqlFromXml(sqlFile, "//SQL/getAllCodeName");
		return this.dao.queryForList(sqlselect, new HashMap<String,Object>());
	}
}
