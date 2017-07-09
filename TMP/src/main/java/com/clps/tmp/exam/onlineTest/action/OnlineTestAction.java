package com.clps.tmp.exam.onlineTest.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.StringUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.ObjectUtil;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.UserService;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.exam.onlineTest.service.OnlineTestService;
import com.clps.tmp.exam.onlineTest.vo.OnlineTestVo;
import com.clps.tmp.question.paper.vo.SectionQuestionVo;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.opensymphony.xwork2.ModelDriven;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * 在线考试系统模块代码
 * @author david.zhang
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/onlineTest")
@Controller
@Scope("prototype")
@Action("online")
@Results({ @Result(name = "toWelcomeTestPage", location = "welcomeTest.jsp"),
		@Result(name = "toExamQuestionPage", location = "examPage.jsp"),
		@Result(name = "toExamFinishPage", location = "examFinish.jsp"),
		@Result(name = "toExamLeavePromptPage", location = "examLeavePrompt.jsp"),
		@Result(name = "toExamLoginPage", location = "examLogin.jsp"), 
		@Result(name = "toExamWaitPage", location = "examWait.jsp"), 
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) 
		})
public class OnlineTestAction extends BaseAction implements ModelDriven {
	private OnlineTestVo ontestVo;
	private SectionQuestionVo secVo;
	private QuestionVo questionVo;
	private HashMap<String, Object> resultMap;
	Map<String, Object> paperMap = new HashMap<String, Object>();
	private static int hours; // 时

	private static int minutes; // 分

	private static int seconds; // 秒
	RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
	@Resource
	private OnlineTestService ontestService;
	@Resource
	private UserService userService;

	@Override
	public Object getModel() {
		if (ontestVo == null) {
			ontestVo = new OnlineTestVo();
		}
		return ontestVo;
	}


	public OnlineTestVo getOntestVo() {
		return ontestVo;
	}

	public void setOntestVo(OnlineTestVo ontestVo) {
		this.ontestVo = ontestVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public SectionQuestionVo getSecVo() {
		return secVo;
	}

	public void setSecVo(SectionQuestionVo secVo) {
		this.secVo = secVo;
	}
	/**
	 * 跳转到在线考试登录页面（教学计划）
	 */
	public String toExamLoginPage(){
		return "toExamLoginPage";
	}
	
	/**
	 * 跳转到在线考试等待页面
	 */
	public String toExamWaitPage(){
		return "toExamWaitPage";
	}
	/**
	 * 跳转在线测试页面
	 */
	public String toWelcomeTestPage() {
		return "toWelcomeTestPage";
	}
	/**
	 * 跳转在线考试提示页面
	 */
	public String toExamLeavePromptPage(){
		return "toExamLeavePromptPage";
	}
	
	
	/**
	 * 跳转到考试完成页面
	 */
	public String toExamFinishPage() {
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		String paperId = (String) paramMap.get("paperId");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		redis.hDel("markList", paperKey);
		redis.hDel("qustFinishFlag", paperKey);
		return "toExamFinishPage";
	}
	
	/**
	 * 登录页面处理
	 */
	public String loginExamSystem() throws Exception{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> map = new HashMap<String, Object>();
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		//获取参数计划ID和试卷ID
		String planId=(String) paramMap.get("planId");
		String paperNo=(String)paramMap.get("paperNo");
		String paperId = String.valueOf(ontestService.findPaperInfoByNo(paperNo).getId());
		AjaxReturnInfo rtn=null;
		//根据账户名取出用户信息
		UserVo user=new UserVo();
		user.setUsername((String)paramMap.get("username"));
		user.setPassword((String)paramMap.get("password"));
		UserVo user2 = userService.queryUserInfo(user);
		if(user2==null){
			//没有用户信息
			rtn=AjaxReturnInfo.success("1111");		
		}else{
			SecurityHelper sh = new SecurityHelper();
			if(!sh.DESEncrypt(user.getPassword()).equals(user2.getPassword())){
				//密码不正确
				rtn=AjaxReturnInfo.success("2222");
			}else if("F".equals(user2.getEnable())){
				//用户被禁用
				rtn=AjaxReturnInfo.success("3333");
			}else{
				//登录成功
				rtn=AjaxReturnInfo.success("0000");	
				//塞入用户信息到session
				this.getSession().put(SystemConstant.USER, user2);
				//塞入登录时间
				this.getSession().put(SystemConstant.LOGINTIME, StringUtil.getDate(new Date(),null));
				//塞入账户
				this.getSession().put(SystemConstant.USERNAME, user2.getUsername());
				//用户语言环境  初始化为zh_CN
				Locale locale=(Locale) this.session.get("WW_TRANS_I18N_LOCALE");
				if(locale!=null){
					this.session.put(SystemConstant.LANG, locale.toString());//zh_CN/en_US
				}else{
					this.session.put(SystemConstant.LANG, "en_US");
				}
				//写入访问者IP
				// 获取访问者IP
				String ip = this.httpRequest.getHeader("x-forwarded-for");
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getRemoteAddr();
				}	
				this.getSession().put(SystemConstant.IP_ADDRESS,ip);
				//传入用户类别  1-招生  2-教学
				this.getSession().put(SystemConstant.USER_TYPE, "2");//当前此种方式设定为教学，以后校招的会有校招登陆模块
				
				//登录成功后判断该考生是否已经考过这张试卷
				paramMap.put("userId",user2.getId());
				String hasTestedFlag=ontestService.judgeWhetherTest(paramMap);
				rtn.add("hasTestedFlag", hasTestedFlag);
				rtn.add("paperId", paperId);
				if("0".equals(hasTestedFlag)){
					paramMap.put("planId", planId);
					paramMap.put("paperNo", paperNo);
					map=ontestService.findPlanPaperInfo(paramMap);
					String paperStartTime=(String) map.get("paperStartTime");
					String paperEndTime=(String) map.get("paperEndTime");
					Date nowTime = new Date();
					map.put("nowTime", DateTimeUtil.nowToSystem());
 					Date start = sdf.parse(DateTimeUtil.databaseToSystem(paperStartTime));
					Date end = sdf.parse(DateTimeUtil.databaseToSystem(paperEndTime));
					//判断当前时间是否在考试时间内（0：在考试期间；1：未到考试时间；2：考试已结束）
					if(nowTime.after(start) && nowTime.before(end)){
						rtn.add("duringExamTimeFlag","0");
					}else if(nowTime.before(start)){
						rtn.add("duringExamTimeFlag","1");
					}else if(nowTime.after(end)){
						rtn.add("duringExamTimeFlag","2");
					}
					rtn.add("planPaperInfo",map);
				}
			}	
		}
		
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 获取试卷信息（考试时间，试卷题目数，试卷名称）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPaperInfo() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);

		// 获取试卷信息
		map = ontestService.findPaperById(paramMap);
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		map.put("userName", userNow.getName()+" , "+userNow.getEn_name());
		map.put("userId", userNow.getId());
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("true");
		rtn.add("leaveLimitTotal",map.get("leaveLimitTotal"));
		rtn.add("totalTime", map.get("totalTime"));
		rtn.add("paperName", map.get("paperName"));
		rtn.add("totalItem", map.get("totalItem"));
		rtn.add("instruction", map.get("instruction"));
		rtn.add("userName", map.get("userName"));
		rtn.add("userId", map.get("userId"));
		rtn.add("property", map.get("property"));
		
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 跳转到考试题目页面
	 */
	public String toExamQuestionPage() {
		return "toExamQuestionPage";
	}

	/**
	 * 根据试卷id或no获取试题（包括考试倒计时的计算）<br>
	 * 刚进入页面时候调用
	 */
	@SuppressWarnings("unchecked")
	public String findSubject() throws Exception {
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("");
		HashMap<String, Object> map = new HashMap<String, Object>();
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		List ml=new ArrayList();
		List qfFlag=new ArrayList();
		String qustId = null;
		int seconds=0;
		int totalTime=0;
		//考生开始答卷时间
		String startTime = DateTimeUtil.nowToDatabase();
		//试卷结束时间
		String paperEndTime=(String) paramMap.get("endTime");
		//试卷Id
		String paperId = (String) paramMap.get("paperId");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String thisAnswer = null;
		//Redis-试卷答题卡标志key
		String paperAnswerKey = "ANSWER" + "_" + userId + "_" + paperId;
		//Redis-试卷标志key
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		String usertype=(String) session.get(SystemConstant.USER_TYPE);
		@SuppressWarnings("unused")
		String leavehour="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse(DateTimeUtil.databaseToSystem(startTime));
		Date paperEnd=sdf.parse(DateTimeUtil.databaseToSystem(paperEndTime));
		//如果redis中没有key为paperkey的paperinfo信息，做如下处理
		if (!redis.hExists("PAPER_INFO", paperKey)) {
			paperMap.put("paperId", paperId);
			paperMap.put("userId", userId);
			paperMap.put("startTime", startTime);
			paperMap.put("userType", usertype);
			paperMap.put("leavelimit", 0);
			redis.hSet("PAPER_INFO", paperKey, ObjectUtil.serialize(paperMap));
			double mSeconds = paperEnd.getTime() - start.getTime();
			seconds=(int) (mSeconds / 1000);
		}else{
			double mSeconds = paperEnd.getTime() - start.getTime();
			seconds = (int) (mSeconds / 1000);
			paperMap=(Map<String, Object>) ObjectUtil.unserialize(redis.hGet("PAPER_INFO", paperKey));
			String leaveQustNo=(String)paperMap.get("leaveQustNo");
			paramMap.put("leaveQustNo", leaveQustNo);
		}
		map = ontestService.findQuestion(paramMap);
		List l = (List) map.get("qustList");
		HashMap<String, Object> keyMap = (HashMap<String, Object>) l.get(0);
		for (Map.Entry<String, Object> m : keyMap.entrySet()) {
			qustId = m.getKey();
		}
		//从redis中获取时间点
		String flag = (String) map.get("flag");
		String count = (String) map.get("count");
		Integer qustPage=(Integer) map.get("qustPage");
		if (redis.hExists(paperAnswerKey, qustId)) {
			HashMap<String, Object> map2 = (HashMap<String, Object>) ObjectUtil.unserialize(redis.hGet(paperAnswerKey, qustId));
			thisAnswer = (String) map2.get("qustAnswer");
			if (thisAnswer != null) {
				rtn.add("thisAnswer", thisAnswer);
			}
		}
		Integer questionId=Integer.parseInt(qustId);
		//获取该题目的图片
		questionVo = ontestService.getImgs(questionId);
		if(redis.hExists("markList", paperKey)){
			ml=(List) ObjectUtil.unserialize(redis.hGet("markList", paperKey));
		}
		if(redis.hExists("qustFinishFlag", paperKey)){
			qfFlag=(List)ObjectUtil.unserialize(redis.hGet("qustFinishFlag", paperKey));
		}
		rtn.add("question", l);
		rtn.add("seconds", seconds);
		rtn.add("flag", flag);
		rtn.add("count", count);
		rtn.add("markList",ml);
		rtn.add("qfFlag", qfFlag);
		rtn.add("qustPage", qustPage);
		rtn.add("que", questionVo.getQueImgs());
		rtn.add("queStr", questionVo.getQueImgsStr());
		rtn.add("opt", questionVo.getOptImgs());
		rtn.add("optStr", questionVo.getOptImgsStr());
		resultMap = rtn.getReturnMap();
		return "json";
	}
	/**
	 ** 翻页（上下翻题）<br>
	 *  取出题目内容，答题的答案()
	 */
	public String findSubject2() throws Exception {
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("");
		HashMap<String, Object> map = new HashMap<String, Object>();
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		String qustId = null;
		String paperId = (String) paramMap.get("paperId");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String thisAnswer = null;
		String paperAnswerKey = "ANSWER" + "_" + userId + "_" + paperId;
		map = ontestService.findQuestion(paramMap);
		List l = (List) map.get("qustList");
		@SuppressWarnings("unchecked")
		HashMap<String, Object> keyMap = (HashMap<String, Object>) l.get(0);
		for (Map.Entry<String, Object> m : keyMap.entrySet()) {
			qustId = m.getKey();
		}
		String flag = (String) map.get("flag");
		String count = (String) map.get("count");
		if (redis.hExists(paperAnswerKey, qustId)) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map2 = (HashMap<String, Object>) ObjectUtil.unserialize(redis.hGet(paperAnswerKey, qustId));
			thisAnswer = (String) map2.get("qustAnswer");
			if (thisAnswer != null) {
				rtn.add("thisAnswer", thisAnswer);
			}
		}
		Integer questionId=Integer.parseInt(qustId);
		questionVo = ontestService.getImgs(questionId);
		rtn.add("que", questionVo.getQueImgs());
		rtn.add("queStr", questionVo.getQueImgsStr());
		rtn.add("opt", questionVo.getOptImgs());
		rtn.add("optStr", questionVo.getOptImgsStr());
		rtn.add("question", l);
		rtn.add("flag", flag);
		rtn.add("count", count);//题目总数
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 考生答题信息缓存到redis中
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String qustAnswer() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取参数
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		String qustId = (String) paramMap.get("qustId");
		String paperId = (String) paramMap.get("paperId");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String answerTime = DateTimeUtil.nowToDatabase();
		String pno = ontestService.findPno(paperId);
		String paperNo = "P_" + pno;
		List<Integer> l=new ArrayList();
		paramMap.put("answerTime", answerTime);
		paramMap.put("userId", userId);
		if(!paramMap.containsKey("qustAnswer")){//没有答题，手动添加答案
			paramMap.put("qustAnswer", "");
		}
		// 生成考生的这份试卷的答题卡的key
		String paperAnswerKey = "ANSWER" + "_" + userId + "_" + paperId;
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("");
		// 交卷时的处理（最后一题完成后将进行交卷处理）
		if (paramMap.containsKey("examEnd")) {
			redis.hSet(paperAnswerKey, qustId, ObjectUtil.serialize(paramMap));
			// 查看是否存在该考生的该试卷的答题信息
			int count = ontestService.findAnswerSheet(paramMap);
			if (count != 0) {
				ontestService.deleteAnswerSheet(paramMap);
			}
			// 遍历从redis中取出的答案信息表
			Map<String, String> map = redis.hGetAll(paperAnswerKey);
			
			/// 将没有作答的题目记录存入数据库///////////////////////////////////////////////////////////////////////////////	
			l = (List) ObjectUtil.unserialize(redis.hGet("PAPER_QUST", paperNo));
			List<Integer> l1=new ArrayList<Integer>();
			for(Map.Entry<String, String> entry : map.entrySet()){
				
				l1.add(Integer.parseInt(entry.getKey()));
			}
			List nodata=getIntersection(l, l1);
			for(int i=0;i<nodata.size();i++){
				Map<String,Object> noAnswer=new HashMap<String,Object>();
				noAnswer.put("paperId", paperId);
				noAnswer.put("userId", userId);
				noAnswer.put("qustAnswer", "");
				noAnswer.put("answerTime", answerTime);
				noAnswer.put("qustId", nodata.get(i));
				map.put(nodata.get(i).toString(),ObjectUtil.serialize(noAnswer));
			}
			//////////////////////////////////////////////////////////////////////
			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				list.add((Map<String, Object>) ObjectUtil.unserialize(entry.getValue()));
			}
			
			try {
				ontestService.addQustAnswer(list);
				redis.remove(paperAnswerKey);
			} catch (Exception e) {
				log.info(StringUtil.getStackTrace(e));
			}
			
			paperMap = (Map<String, Object>) ObjectUtil.unserialize(redis.hGet("PAPER_INFO", paperKey));
			paperMap.put("endTime", DateTimeUtil.nowToDatabase());
			paperMap.put("paperId", paperId);
			paperMap.put("userId", userId);

			Date startTime = sdf.parse(DateTimeUtil.databaseToSystem((String) paperMap.get("startTime")));
			Date endTime = sdf.parse(DateTimeUtil.databaseToSystem((String) paperMap.get("endTime")));
			double cost = endTime.getTime() - startTime.getTime();
			// 毫秒转为秒
			double milliSeconds = cost;
			int totalSeconds = (int) (milliSeconds / 1000);
			// 得到总小时数
			hours = totalSeconds / 3600;
			int remains_hours = totalSeconds % 3600;

			// 得到分种数
			minutes = remains_hours / 60;

			// 得到总秒数
			seconds = remains_hours % 60;
			String costTime = hours + ":" + minutes + ":" + seconds;
			paperMap.put("costTime", costTime);
			int num = ontestService.checkPaperInfo(paperMap);
			if (num != 0) {
				ontestService.deletePaperInfo(paperMap);
			}
			ontestService.addPaperInfo(paperMap);
			//更新历史表中的信息
			paramMap.put("startTime", (String) paperMap.get("startTime"));
			paramMap.put("endTime", DateTimeUtil.nowToDatabase());
			paramMap.put("longTime", DateTimeUtil.getMin((String) paperMap.get("startTime"),DateTimeUtil.nowToDatabase()));
			ontestService.editPlanPaperHis(paramMap);
			redis.hDel("PAPER_INFO", paperKey);
			rtn.add("flag", "success");
		} else {
			// 点击上一题或下一题的处理时，此题的答案信息缓存到redis中(将考生的答题信息存放到rdis中，map名为试卷号，key是题号)
			redis.hSet(paperAnswerKey, qustId, ObjectUtil.serialize(paramMap));
			paperMap = (Map<String, Object>) ObjectUtil.unserialize(redis.hGet(
					"PAPER_INFO", paperKey));
			if (paperMap == null) {
				return null;
			} else {
				String leaveQustNo = (String) paramMap.get("currentPage");
				paperMap.put("leaveQustNo", leaveQustNo);
				redis.hSet("PAPER_INFO", paperKey,
						ObjectUtil.serialize(paperMap));
				if (redis.hExists(paperAnswerKey, qustId)) {
					rtn.add("flag", "success");
				} else {
					rtn.add("flag", "fail");
				}
			}
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 页面离开指定次数后提交试卷
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String paperInfo() throws Exception {
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		String paperId = (String) paramMap.get("paperId");
		
		Integer leavetime = 0;
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("");
		// 将试卷（整体）信息缓存到redis中(map名称是"paperInfo",key是试卷id)
		if (redis.hExists("PAPER_INFO", paperKey)) {
			paperMap = (Map<String, Object>) ObjectUtil.unserialize(redis.hGet(
					"PAPER_INFO", paperKey));
			leavetime = (Integer) paperMap.get("leavelimit") + 1;
			// Integer.parseInt(String.valueOf(paperMap.get("leavetime"))) +1;
			// Integer.parseInt(redis.hGet("paperInfo", paperKey))+1;
			paperMap.put("leavelimit", leavetime);
		} else {
			paperMap.put("leavelimit", 1);
		}
		redis.hSet("PAPER_INFO", paperKey, ObjectUtil.serialize(paperMap));
		rtn.add("leavelimit", leavetime);
		resultMap = rtn.getReturnMap();
		return "json";
	}


	/**
	 * 考试期间判断是否再次答卷（考试期间刷新或关闭页面，若试卷未提交，则可以再次作答，若试卷已提交，则无法继续作答）
	 */
	public String allowExam() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		boolean l = false;
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		paramMap.put("userId", userId);
		// 判断库表中是否存在该试卷信息
		int num = ontestService.checkPaperInfo(paramMap);
		if (num != 0) {
			// 考试开始时间
			String startTime = ontestService.allowExam(paramMap);
			Date start = sdf.parse(DateTimeUtil.databaseToSystem(startTime));
			// 试卷考试总时长
			int totalTime = Integer
					.parseInt((String) paramMap.get("totalTime"));
			// 考试结束时间
			Date paperEndTime = DateTimeUtil.addSecond(start, totalTime * 60);
			Date nowDate = new Date();
			// 如果当前时间处于考试期间，并且已经提交了试卷，则禁止再次答卷
			if (nowDate.after(start) && nowDate.before(paperEndTime)) {
				l = true;
			}

		}

		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("true");
		rtn.add("flag", l);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	/**
	 * 考试离开时的题号
	 * @return
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	public String recordLeaveTime() throws Exception{
//		BtTableUtil util = new BtTableUtil();
//		Map<String, Object> paramMap = util.getParamers(httpRequest);
//		String paperId = (String) paramMap.get("paperId");
//		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
//		String userId = userNow.getId();
//		String paperKey = "Paper" + "_" + userId + "_" + paperId;
//		paperMap=(Map<String, Object>) ObjectUtil.unserialize(redis.hGet("PAPER_INFO", paperKey));
//		if(paperMap==null){
//			return null;
//		}else{
//		String leaveQustNo=(String)paramMap.get("currentPage");
//		paperMap.put("leaveQustNo", leaveQustNo);
//		redis.hSet("PAPER_INFO", paperKey, ObjectUtil.serialize(paperMap));
//		AjaxReturnInfo rtn = null;
//		rtn = AjaxReturnInfo.success("true");
//		resultMap = rtn.getReturnMap();
//		return "json";
//		}
//	}
	
	
	/**
	 * 查出两个list中不重复的数据
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List getIntersection(List<Integer> list1,
			List<Integer> list2) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer s : list1) {//遍历list1
			if (!list2.contains(s)) {//如果存在这个数
				result.add(s);
			}
		}
		return result;
	}
	/**
	 * 缓存题目是否标记的信息
	 */
	@SuppressWarnings("unchecked")
	public String qustMark() throws Exception{
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		Map<String,Object> map=new HashMap<String,Object>();
		List l=new ArrayList();
		String paperId = (String) paramMap.get("paperId");
		String currentPage=(String) paramMap.get("currentPage");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		String markflag=(String)paramMap.get("markflag");
		List l2=(List) ObjectUtil.unserialize(redis.hGet("qustFinishFlag", paperKey));
		if(l2.contains(currentPage)){
			l2.remove(currentPage);
			redis.hSet("qustFinishFlag", paperKey, ObjectUtil.serialize(l2));
		}
		if(redis.hExists("markList", paperKey)){
			l=(List) ObjectUtil.unserialize(redis.hGet("markList", paperKey));
			if(markflag.equals("true")&&!l.contains(currentPage)){
				l.add(currentPage);
			}else if(markflag.equals("false")){
				l.remove(currentPage);
			}
			redis.hSet("markList", paperKey, ObjectUtil.serialize(l));
		}else{
			l.add(currentPage);
			redis.hSet("markList", paperKey, ObjectUtil.serialize(l));
		}
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("true");
		rtn.add("markList", l);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	/**
	 * 将已完成的题号传到redis中记录
	 */
	@SuppressWarnings("unchecked")
	public String qustFinishFlag(){
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		Map<String,Object> map=new HashMap<String,Object>();
		List l=new ArrayList();
		String paperId = (String) paramMap.get("paperId");
		String current=(String) paramMap.get("current");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		if(redis.hExists("qustFinishFlag", paperKey)){
			l=(List) ObjectUtil.unserialize(redis.hGet("qustFinishFlag", paperKey));
			if(!l.contains(current)){
			l.add(current);
			redis.hSet("qustFinishFlag", paperKey, ObjectUtil.serialize(l));
			}
		}else{
			l.add(current);
			redis.hSet("qustFinishFlag", paperKey, ObjectUtil.serialize(l));
		}
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("true");
		rtn.add("qustFinishFlag", l);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	/**
	 *试卷提交前判断有多少题未完成以及标记的题目数是多少
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getUnfinishOrMark() throws Exception{
		BtTableUtil util = new BtTableUtil();
		Map<String, Object> paramMap = util.getParamers(httpRequest);
		AjaxReturnInfo rtn = null;
		String paperId = (String) paramMap.get("paperId");
		UserVo userNow = (UserVo) session.get(SystemConstant.USER);
		String userId = userNow.getId();
		//试卷标志key
		String paperKey = "Paper" + "_" + userId + "_" + paperId;
		List markList=new ArrayList();
		List qfFlagList=new ArrayList();
		int markNbr=0;
		int unFinishNbr=0;
		List<Integer> l=new ArrayList();
		String pno = ontestService.findPno(paperId);
		String paperNo = "P_" + pno;
		l = (List) ObjectUtil.unserialize(redis.hGet("PAPER_QUST", paperNo));
		if(redis.hExists("markList", paperKey)){
			markList=(List) ObjectUtil.unserialize(redis.hGet("markList", paperKey));
			markNbr=markList.size();
		}
		if(redis.hExists("qustFinishFlag", paperKey)){
			qfFlagList=(List) ObjectUtil.unserialize(redis.hGet("qustFinishFlag", paperKey));
			unFinishNbr= l.size()-qfFlagList.size()-markList.size();
		}else{
			unFinishNbr=l.size()-markNbr;
		}
		rtn = AjaxReturnInfo.success("true");
		rtn.add("markNbr", markNbr);
		rtn.add("unFinishNbr", unFinishNbr);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 *  服务器下载chrome浏览器
	 */
	public void downloadChrome() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"System"+File.separator+"Template"+File.separator;
		String filePath= baseDir+"Chrome_47.0_Setup.exe";//临时存储位置
		DownloadFileUtil util=new DownloadFileUtil();
		util.downLoad("Chrome_47.0_setup.exe", filePath, httpRequest, httpResponse);
	}
	   
	 
}
