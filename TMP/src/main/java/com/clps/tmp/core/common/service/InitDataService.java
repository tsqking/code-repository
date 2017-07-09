package com.clps.tmp.core.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.core.common.dao.Dao;
import com.clps.tmp.core.common.util.ObjectUtil;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.question.question.vo.QuestionVo;
/**
 * 项目启动时，加载数据service
 * @author seven
 */
@Service("initDataService")
public class InitDataService extends BaseService {
	private Logger log=Logger.getLogger("");
	@Resource
	protected Dao dao;
	
	@SuppressWarnings("unchecked")
	public void loadNoData() throws Exception {
		//查询人员最大号
		StringBuffer getPersonNoSql=new StringBuffer();
		getPersonNoSql.append("select case `SuperManagerNo` when null then CONCAT('0',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                             when '0000000' then CONCAT('0',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                             when '' then CONCAT('0',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                             else cast(`SuperManagerNo` as char) end  as `SuperManagerNo`, ");
		getPersonNoSql.append("       case `ManagerNo` when null THEN CONCAT('1',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '0' THEN CONCAT('1',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '' THEN CONCAT('1',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        else `ManagerNo` end as `ManagerNo`, ");
		getPersonNoSql.append("       case `TeacherNo` when null then CONCAT('2',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '0' then CONCAT('2',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '' then CONCAT('2',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        else `TeacherNo` end as `TeacherNo`, ");
		getPersonNoSql.append("       case `StudentNo` when null then CONCAT('3',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '0' then CONCAT('3',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        when '' then CONCAT('3',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                        else `StudentNo` end as `StudentNo`, ");
		getPersonNoSql.append("       case `OthersNo` when null then CONCAT('4',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                       when '0' then CONCAT('4',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                       when '' then CONCAT('4',date_format(curdate(),'%y'),'0000') ");
		getPersonNoSql.append("                       else `OthersNo` end as `OthersNo` ");
		getPersonNoSql.append("from( ");
		getPersonNoSql.append("      select LPAD(sum( case role when 'SuperManagerNo' then max_no else 0 end),7,'0') as `SuperManagerNo`, ");
		getPersonNoSql.append("             sum( case role when 'ManagerNo' then max_no else 0 end) as `ManagerNo`, ");
		getPersonNoSql.append("             sum( case role when 'TeacherNo' then max_no else 0 end) as `TeacherNo`, ");
		getPersonNoSql.append("             sum( case role when 'StudentNo' then max_no else 0 end) as `StudentNo`, ");
		getPersonNoSql.append("             sum( case role when 'OthersNo' then max_no else 0 end) as `OthersNo` ");
		getPersonNoSql.append("      from( ");
		getPersonNoSql.append("            select case when `role`='0' then 'SuperManagerNo'  ");
		getPersonNoSql.append("                        when `role`='1' then 'ManagerNo'  ");
		getPersonNoSql.append("                        when `role`='2' then 'TeacherNo'  ");
		getPersonNoSql.append("                        when `role`='3' then 'StudentNo' ");
		getPersonNoSql.append("                        else 'OthersNo' end as `role`, ");
		getPersonNoSql.append("                   case max(`no`) when null then '0' else max(`no`) end as `max_no`,'test' as `test_c` ");
		getPersonNoSql.append("            from `SM_PERSON` ");
		getPersonNoSql.append("            where substring(`no`,2,2)=date_format(curdate(),'%y') ");
		getPersonNoSql.append("            group by `role` ");
		getPersonNoSql.append("            union  ");
		getPersonNoSql.append("            select 'UnUsed',CONCAT('0',date_format(curdate(),'%y'),'0000'),'test' ");
		getPersonNoSql.append("          )a ");
		getPersonNoSql.append("      group by a.`test_c` ");
		getPersonNoSql.append("   )b ");
		log.info("SQL:\n"+getPersonNoSql.toString());
		Map<String,Object> param=new HashMap<String,Object>();
		GenerateNextNo.setPersonNoList(this.dao.queryForMap(getPersonNoSql.toString(), param));
		//查询课程最大号
		StringBuffer getCourseNoSql=new StringBuffer();
		getCourseNoSql.append("select case when max(`no`) is null then concat('CSE',date_format(curdate(),'%y'),'000') else max(`no`) end as `max_no` from `COURSE` ");//CSE15000
		log.info("SQL:\n"+getCourseNoSql.toString());
		GenerateNextNo.setCourseNo((String)this.dao.queryForObject(getCourseNoSql.toString(), String.class));
		//查询班级最大号
		StringBuffer getClassNoSql=new StringBuffer();
		getClassNoSql.append("select case when max(`no`) is null then concat('CLS',date_format(curdate(),'%y%m'),'00') else max(`no`) end as `max_no` from `CLASS` ");//CLS150100
		log.info("SQL:\n"+getClassNoSql.toString());
		GenerateNextNo.setClassNo((String)this.dao.queryForObject(getClassNoSql.toString(), String.class));
		//查询临时用户名最大号
		StringBuffer getTempUserNameSql=new StringBuffer();
		getTempUserNameSql.append("select case when max(`username`) is null then 'tmpuser0000' else max(`username`) end as `tmpname` from `SM_PERSON` where `username` like 'tmpuser%' ");//tmpuser0001
		log.info("SQL:\n"+getTempUserNameSql.toString());
		GenerateNextNo.setTempUserName((String)this.dao.queryForObject(getTempUserNameSql.toString(), String.class));
		//查询所有的题目信息进入redis缓存
		StringBuffer getQuestionSql=new StringBuffer();
		getQuestionSql.append("select id,type,so_flag,difficulty,property,use_flag,content,answer,opt1,opt2,opt3,opt4,opt5,opt6,analysis,enable,create_time,create_person,update_time,update_person from `QUESTION`");
		RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
		redis.remove("QUESTION");;//先清除
		List<QuestionVo> questList=(List<QuestionVo>) this.dao.query(getQuestionSql.toString(), BeanPropertyRowMapper.newInstance(QuestionVo.class));
		for(QuestionVo bean:questList){
			redis.hSet("QUESTION", "Q_"+bean.getId(), ObjectUtil.serialize(bean));
		}
		log.info("所有题目存放Redis完毕");
		
	}
}
