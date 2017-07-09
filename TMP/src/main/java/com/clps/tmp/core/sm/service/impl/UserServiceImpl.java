package com.clps.tmp.core.sm.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.service.UserService;

/**
 * 用户管理 service实现类
 * 
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年10月9日 上午9:48:09
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

	/**
	 * 查询账户信息,根据账户
	 */
	@Override
	public UserVo queryUserInfo(UserVo user) throws Exception {
		// TODO Auto-generated method stub
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", user.getUsername());
		// 获取SQL语句
		String sql = SystemSQLConstant.QUERY_USER_INFO;
		// 查询
		@SuppressWarnings("unchecked")
		List<UserVo> list = (List<UserVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		// 返回
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 账户管理
	 */
	@Override
	public PageVo<UserVo> queryUserPage(PageVo<UserVo> pv) throws Exception {
		// TODO Auto-generated method stub
		// 取出数据
		int page = pv.getPage();
		int limitPage = pv.getLimitPage();
		HashMap<String, String> where1 = pv.getWhere1();
		HashMap<String, String> where2 = pv.getWhere2();
		HashMap<String, String> dateTime = pv.getDateTime();
		LinkedHashMap<String, String> sort = pv.getSort();
		// 获取SQL语句
		String sql1 = SystemSQLConstant.QUERY_USER_PAGE;
		String sql2 = SystemSQLConstant.QUERY_USER_ALLCOUNT;
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
				if (col.equals("gender_name")) {
					col = "gender";
				} else if (col.equals("role_name")) {
					col = "role";
				} else if (col.equals("enable_name")) {
					col = "enable";
				}
				sb.append("a.`" + col + "`");
				sb.append(" ");
				sb.append(entry.getValue());
			}
		} else {
			sb.append(" order by a.`username` asc,a.`role` asc ");
		}
		sql1 = sql1.replace("[order]", sb.toString());
		sql1 = sql1.replaceAll("\\[lang\\]", getLang());
		//国际化语言切换
		if(getLang().equals("")){
			//中文
			sql1=sql1.replaceAll("\\[name\\]", "name");
		}else{
			//英文
			sql1=sql1.replaceAll("\\[name\\]", "en_name");
		}
		log.info("SQL：\n" + sql1 + "\n" + sql2);
		// 查询数据
		@SuppressWarnings({ "unchecked" })
		List<UserVo> list = (List<UserVo>) this.dao.query(sql1,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		// 查询总条数
		@SuppressWarnings({ "unchecked" })
		List<UserVo> list2 = (List<UserVo>) this.dao.query(sql2,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		String allcount = list2.get(0).getAllcount();
		// 汇总数据
		pv.setList(list);
		pv.setAllcount(allcount);
		return pv;
	}

	/**
	 * 用户详细信息查询
	 */
	@Override
	public UserVo detailUser(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		String sql = SystemSQLConstant.QUERY_USER_DETAIL;
		sql = sql.replaceAll("\\[lang\\]", getLang());
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<UserVo> list = (List<UserVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取账户头像
	 */
	@Override
	public String queryUserPhoto(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		String sql = SystemSQLConstant.QUERY_USER_PHOTO;
		log.info("SQL:\n" + sql);
		@SuppressWarnings("unchecked")
		List<UserVo> list = (List<UserVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		if (list != null) {
			return list.get(0).getPhoto();
		}
		return null;
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUserInfo(UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", user.getId());
		if(user.getRole()!=null){
			paramMap.put("role", user.getRole());
		}
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		paramMap.put("name", user.getName());
		paramMap.put("en_name", user.getEn_name());
		paramMap.put("gender", user.getGender());
		paramMap.put("mobile", user.getMobile());
		paramMap.put("phone", user.getPhone());
		paramMap.put("email", user.getEmail());
		paramMap.put("age", user.getAge());
		paramMap.put("birthday", user.getBirthday());
		paramMap.put("education_background", user.getEducation_background());
		paramMap.put("degree", user.getDegree());
		paramMap.put("university", user.getUniversity());
		paramMap.put("college", user.getCollege());
		paramMap.put("major", user.getMajor());
		paramMap.put("cet4", user.getCet4());
		paramMap.put("cet6", user.getCet6());
		paramMap.put("gpa", user.getGpa());
		paramMap.put("description", user.getDescription());
		paramMap.put("direction", user.getDirection());
		paramMap.put("contact_address", user.getContact_address());
		paramMap.put("contact_postcode", user.getContact_postcode());
		paramMap.put("home_address", user.getHome_address());
		paramMap.put("home_postcode", user.getHome_postcode());
		paramMap.put("resume", user.getResume());
		paramMap.put("enable", user.getEnable());
		paramMap.put("update_time", user.getUpdate_time());
		paramMap.put("update_person", user.getUpdate_person());
		paramMap.put("cardtype", user.getCardtype());
		paramMap.put("cardno", user.getCardno());
		paramMap.put("exam_num", user.getExam_num());
		String sql = SystemSQLConstant.UPDATE_USER_INFO;
		if(user.getRole()!=null){
			sql = sql.replaceAll("\\[role\\]", "`role`=:role,");
		}else{
			sql = sql.replaceAll("\\[role\\]", "");
		}
		log.info("SQL:\n" + sql);
		int re = this.dao.update(sql, paramMap);
		if (re != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新用户文档
	 */
	@Override
	public boolean updateUserPhoto(String photo, String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("photo", photo);
		paramMap.put("id", id);
		String sql = SystemSQLConstant.UPDATE_USER_PHOTO;
		log.info("SQL:\n" + sql);
		int re = this.dao.update(sql, paramMap);
		if (re != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新建用户
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean addUserInfo(UserVo user, UserVo userNow) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role", user.getRole());
		paramMap.put("username", user.getUsername());
		SecurityHelper sh = new SecurityHelper();
		paramMap.put("password", sh.DESEncrypt(user.getPassword()));
		GenerateNextNo generate=new GenerateNextNo();
		paramMap.put("no", generate.getNextPersonNo(user.getRole()));
		paramMap.put("name", user.getName());
		paramMap.put("en_name", user.getEn_name());
		paramMap.put("gender", user.getGender());
		paramMap.put("mobile", user.getMobile());
		paramMap.put("phone", user.getPhone());
		paramMap.put("email", user.getEmail());
		paramMap.put("age", user.getAge());
		paramMap.put("birthday", user.getBirthday().replaceAll("-", ""));
		paramMap.put("education_background", user.getEducation_background());
		paramMap.put("degree", user.getDegree());
		paramMap.put("university", user.getUniversity());
		paramMap.put("college", user.getCollege());
		paramMap.put("major", user.getMajor());
		paramMap.put("cet4", user.getCet4());
		paramMap.put("cet6", user.getCet6());
		paramMap.put("gpa", user.getGpa());
		paramMap.put("description", user.getDescription());
		paramMap.put("direction", user.getDirection());
		paramMap.put("contact_address", user.getContact_address());
		paramMap.put("contact_postcode", user.getContact_postcode());
		paramMap.put("home_address", user.getHome_address());
		paramMap.put("home_postcode", user.getHome_postcode());
		paramMap.put("resume", user.getResume());
		paramMap.put("enable", user.getEnable());
		paramMap.put("create_time", DateTimeUtil.nowToDatabase());
		paramMap.put("create_person", userNow.getName());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", userNow.getName());
		paramMap.put("photo", user.getPhoto());
		paramMap.put("cardtype", user.getCardtype());
		paramMap.put("cardno", user.getCardno());
		paramMap.put("exam_num", user.getExam_num());
		//插入用户
		String sql = SystemSQLConstant.ADD_USER_INFO;
		log.info("SQL:\n" + sql);
		int re = this.dao.insert(sql, paramMap);
		if (re != 0) {
			//关联基本角色
			sql = SystemSQLConstant.BATCH_ADD_USER_BASE_ROLE;
			log.info("SQL:\n" + sql);
			this.dao.insert(sql, paramMap);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean deleteUser(UserVo user) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String id = user.getId();
		paramMap.put("user_id", id);
		//check用户所有关联
		String checkUserRelevantSql = SystemSQLConstant.CHECK_USER_RELEVANT;
		log.info("SQL:\n" + checkUserRelevantSql+"\nparam:"+paramMap.toString());
		int checkUserRelevant=(Integer)this.dao.queryForObject(checkUserRelevantSql, paramMap, Integer.class);
		if(checkUserRelevant==0){//无关联,可以删除
			//删除用户所有角色
			String delUserRole=SystemSQLConstant.DELETE_USER_ALLROLES;
			log.info("SQL:\n" + delUserRole+"\nparam:"+paramMap.toString());
			this.dao.delete(delUserRole, paramMap);
			//删除人员
			String deletePersonSql = SystemSQLConstant.DELETE_USER_INFO;
			log.info("SQL:\n" + deletePersonSql+"\nparam:"+paramMap.toString());
			this.dao.delete(deletePersonSql, paramMap);
			return true;
		}else{//不能删除
			return false;
		}
	}

	/**
	 * 角色管理
	 */
	@Override
	public PageVo<UserVo> queryRolePage(PageVo<UserVo> pv) throws Exception {
		// TODO Auto-generated method stub
		// 取出数据
		int page = pv.getPage();
		int limitPage = pv.getLimitPage();
		HashMap<String, String> where1 = pv.getWhere1();
		HashMap<String, String> where2 = pv.getWhere2();
		HashMap<String, String> other = pv.getOther();
		HashMap<String, String> dateTime = pv.getDateTime();
		LinkedHashMap<String, String> sort = pv.getSort();
		// 获取SQL语句
		String sql1 = SystemSQLConstant.QUERY_USER_ROLE_PAGE;
		String sql2 = SystemSQLConstant.QUERY_USER_ROLE_ALLCOUNT;
		// 条件
		StringBuilder sb = new StringBuilder();
		// 起始条数
		sql1 = sql1.replace("[start]", ((page - 1) * limitPage) + "");
		// 每页条数
		sql1 = sql1.replace("[number]", limitPage + "");
		// 搜索条件
		sb = new StringBuilder();
		sb.append(" where 1=1 and a.`id`<>'1' ");
		// 精确查询
		if (!(where1 == null || where1.size() <= 0)) {
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				sb.append(" and ");
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
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				sb.append(" and ");
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
			for (Map.Entry<String, String> entry : dateTime.entrySet()) {
				sb.append(" and ");
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
		// 自定义条件
		if (!(other == null || other.size() <= 0)) {
			boolean flag = false;
			if (((where1 == null || where1.size() <= 0)
					&& (where2 == null || where2.size() <= 0) && (dateTime == null || dateTime
					.size() <= 0))) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			for (Map.Entry<String, String> entry : other.entrySet()) {
				if (flag) {
					sb.append(" and ");
				} else {
					flag = true;
				}
				if (entry.getKey().equals("has")) {
					String id = entry.getValue().split("-")[0];
					String has = entry.getValue().split("-")[1];
					ArrayList<String> al = this.queryUserRoles(id);
					StringBuilder tem = new StringBuilder();
					sb.append(" ( ");
					for (String s : al) {
						if ((has+"").equals("0")) {
							// 没有
							tem.append("and  a.`id`!=");
						} else {
							tem.append("or   a.`id`=");
						}
						tem.append(s);
						tem.append(" ");
					}
					sb.append(tem.toString().substring(4));
					sb.append(" ) ");
				}
			}
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
				if (col.equals("enable_name")) {
					col = "enable";
				} else if (col.equals("")) {
					col = "id";
				}
				sb.append("a.`" + col + "`");
				sb.append(" ");
				sb.append(entry.getValue());
			}
		} else {
			sb.append(" ");
		}
		sql1 = sql1.replace("[order]", sb.toString());
		sql1 = sql1.replaceAll("\\[lang\\]", getLang());
		log.info("SQL：\n" + sql1 + "\n" + sql2);
		// 查询数据
		@SuppressWarnings({ "unchecked" })
		List<UserVo> list = (List<UserVo>) this.dao.query(sql1,
				new RowMapper<UserVo>() {
					public UserVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserVo bean = new UserVo();
						bean.setId(rs.getString("id"));
						bean.setName(rs.getString("name"));
						bean.setDescription(rs.getString("description"));
						bean.setEnable(rs.getString("enable"));
						bean.setCreate_time(DateTimeUtil.databaseToSystem(rs
								.getString("create_time")));
						bean.setCreate_person(rs.getString("create_person"));
						bean.setUpdate_time(DateTimeUtil.databaseToSystem(rs
								.getString("update_time")));
						bean.setUpdate_person(rs.getString("update_person"));
						bean.setEnable_name(rs.getString("enable_name"));
						return bean;
					}
				});
		// 查询总条数
		@SuppressWarnings({ "unchecked" })
		List<UserVo> list2 = (List<UserVo>) this.dao.query(sql2,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		String allcount = list2.get(0).getAllcount();
		// 汇总数据
		pv.setList(list);
		pv.setAllcount(allcount);
		return pv;
	}

	/**
	 * 查询用户的所有角色的id
	 */
	@Override
	public ArrayList<String> queryUserRoles(String id) throws Exception {
		// TODO Auto-generated method stub
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		// 获取SQL语句
		String sql = SystemSQLConstant.QUERY_USER_ROLES;
		// 查询
		@SuppressWarnings("unchecked")
		List<UserVo> list = (List<UserVo>) this.dao.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(UserVo.class));
		// 返回
		ArrayList<String> li = new ArrayList<String>();
		for (UserVo uv : list) {
			li.add(uv.getId());
		}
		return li;
	}

	/**
	 * 增加-去除角色
	 */
	@Override
	public boolean updateUserRole(String userId, String roleId, boolean type)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("roleId", roleId);
		String sql = "";
		if (type) {
			// +
			sql = SystemSQLConstant.ADD_USER_ROLE;
		} else {
			// -
			sql = SystemSQLConstant.DELETE_USER_ROLE;
		}
		log.info("SQL:\n" + sql);
		int re = this.dao.delete(sql, paramMap);
		if (re != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 去除所有角色
	 */
	@Override
	public boolean deleteUserAllRole(String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", userId);
		String sql = SystemSQLConstant.DELETE_USER_ALLROLES;
		log.info("SQL:\n" + sql);
		this.dao.delete(sql, paramMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Object> batchAddUser(List<Map<String, Object>> list) throws Exception {
		HashMap<String,Object> rtn=new HashMap<String,Object>();//返回map
		StringBuffer existMobile=new StringBuffer();//存放已存在用户的手机信息
		StringBuffer userName=new StringBuffer();//搜集用户名
		int i=0;
		for(Map<String,Object> map:list){
			if(existMobile((String)map.get("mobile"))){//手机号存在，则已是系统用户
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("username", (String)map.get("mobile"));
				// 获取SQL语句
				String sql = SystemSQLConstant.QUERY_USER_INFO;
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
			if((boolean) map.get("exist")){//记录已存在用户，到时候返回给前台
				existMobile.append(((String)map.get("mobile"))+";");
			}else{
				batchUser[i++]=map;
			}
		}
		//插入数据库
		String sql = SystemSQLConstant.BATCH_ADD_USER;
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);
		//先去除人员基本角色
		/*sql = SystemSQLConstant.BATCH_REMOVE_USER_BASE_ROLE;
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);*/
		//关联人员角色
		sql = SystemSQLConstant.BATCH_ADD_USER_BASE_ROLE;
		log.info("SQL:\n" + sql);
		this.dao.batchUpdate(sql, batchUser);
		
		rtn.put("existMobile", existMobile.toString());
		rtn.put("userNameList", userName.toString());
		return rtn;
	}
	
	/**
	 * 根据身份证号生成密码--身份证后6位
	 * 2015年12月2日 Seven
	 */
	private String genertePassWord(String cardNo)throws Exception{
		return cardNo.substring(cardNo.length()-6);
	}
	
	@Override
	public boolean existUserName(String userName) throws Exception{
		boolean exist=true;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userName", userName);
		int count=(int) this.dao.queryForObject(SystemSQLConstant.EXIST_USERNAME, paramMap, Integer.class);
		if(count==0)
			exist=false;
		return exist;
	}

	@Override
	public boolean existMobile(String mobile) throws Exception {
		boolean exist=true;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mobile", mobile);
		int count=(int) this.dao.queryForObject(SystemSQLConstant.EXIST_MOBILE, paramMap, Integer.class);
		if(count==0)
			exist=false;
		return exist;
	}

	/**
	 * 检查数据的唯一
	 */
	@Override
	public int checkData(String userName, String phone) throws Exception {
		// TODO Auto-generated method stub
		// 搜索条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 获取SQL语句
		String sql = SystemSQLConstant.QUERY_DATA_ONLYONE;
		StringBuilder sb = new StringBuilder();
		sb.append(" where ");
		if(userName!=null){
			sb.append("`username`=\"").append(userName).append("\"");
		}else{
			sb.append("`mobile`=\"").append(phone).append("\"");
		}	
		sql = sql.replace("[where]", sb.toString());
		// 查询
		@SuppressWarnings("unchecked")
		List<UserVo> list = (List<UserVo>) this.dao.query(sql, paramMap,BeanPropertyRowMapper.newInstance(UserVo.class));
		// 返回
		if(list==null||list.size()==0){
			//出错!
			return 0;
		}else{
			String s = list.get(0).getNumber();
			if(s.equals("0")){
				//成功!
				return 1;
			}else{
				//重复
				return 2;
			}
		}		
	}
}
