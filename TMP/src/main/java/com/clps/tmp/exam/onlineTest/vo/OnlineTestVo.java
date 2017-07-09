package com.clps.tmp.exam.onlineTest.vo;

import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;

public class OnlineTestVo {
	//id自增主键
		private int id;
		//题型：1-单选，2-多选，3-判断，4-填空，5-简答，6-编程
		private String type;
		//主客标识位：s-Subject主观题，o-Object客观题
		private String so_flag;
		//难易度：1-易，2-中，3-难
		private String difficulty;
		//题目属性：00-练习题，01-课堂练习，02-课后练习，03-模拟练习，04-综合练习，05-实战练习
		private String property;
		//用途标识位：1-教学，2-校招，3-通用
		private String use_flag;
		//题干
		private String content;
		//答案：主观参考，客观判题；多选题与多空填空题，答案之间用
		private String answer;
		//选型内容一
		private String opt1;
		//选型内容二
		private String opt2;
		//选型内容三
		private String opt3;
		//选型内容四
		private String opt4;
		//选型内容五
		private String opt5;
		//选型内容六
		private String opt6;
		//题目解析
		private String analysis;
		//生效标识：T-生效，F-不生效
		private String enable;
		//创建时间（YYYY-MM-DD HH-mm-SS）
		private String create_time;
		//创建人
		private String create_person;
		//更新时间（YYYY-MM-DD HH-mm-SS）
		private String update_time;
		//更新人
		private String update_person;
		//试卷Id
		private PaperVo pv;
		//计划测试Vo
		private PlanPaperVo ppv;
		//题目表Vo
		private QuestionVo qv;
		
		private String userName;
		private String paperName;
		private String totalItem;
		private String totalTime;
		private String paperId;
		private String paperNo;
		private String instruction;
		private String leaveLimitTotal;
		private String startTime;
		private String endTime;
		private String pstartTime;
		private String planPaperId;
		private String planId;
		private String nowTime;
		
		
		
		
		
		
		
		
		public String getNowTime() {
			return nowTime;
		}
		public void setNowTime(String nowTime) {
			this.nowTime = nowTime;
		}
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public QuestionVo getQv() {
			return qv;
		}
		public void setQv(QuestionVo qv) {
			this.qv = qv;
		}
		public String getPlanPaperId() {
			return planPaperId;
		}
		public void setPlanPaperId(String planPaperId) {
			this.planPaperId = planPaperId;
		}
		public String getPstartTime() {
			return pstartTime;
		}
		public void setPstartTime(String pstartTime) {
			this.pstartTime = pstartTime;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public PlanPaperVo getPpv() {
			return ppv;
		}
		public void setPpv(PlanPaperVo ppv) {
			this.ppv = ppv;
		}
		public String getLeaveLimitTotal() {
			return leaveLimitTotal;
		}
		public void setLeaveLimitTotal(String leaveLimitTotal) {
			this.leaveLimitTotal = leaveLimitTotal;
		}
		public String getInstruction() {
			return instruction;
		}
		public void setInstruction(String instruction) {
			this.instruction = instruction;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPaperName() {
			return paperName;
		}
		public void setPaperName(String paperName) {
			this.paperName = paperName;
		}
		public String getTotalItem() {
			return totalItem;
		}
		public void setTotalItem(String totalItem) {
			this.totalItem = totalItem;
		}
		public String getTotalTime() {
			return totalTime;
		}
		public void setTotalTime(String totalTime) {
			this.totalTime = totalTime;
		}
		public String getPaperId() {
			return paperId;
		}
		public void setPaperId(String paperId) {
			this.paperId = paperId;
		}
		public String getPaperNo() {
			return paperNo;
		}
		public void setPaperNo(String paperNo) {
			this.paperNo = paperNo;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getSo_flag() {
			return so_flag;
		}
		public void setSo_flag(String so_flag) {
			this.so_flag = so_flag;
		}
		public String getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getUse_flag() {
			return use_flag;
		}
		public void setUse_flag(String use_flag) {
			this.use_flag = use_flag;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public String getOpt1() {
			return opt1;
		}
		public void setOpt1(String opt1) {
			this.opt1 = opt1;
		}
		public String getOpt2() {
			return opt2;
		}
		public void setOpt2(String opt2) {
			this.opt2 = opt2;
		}
		public String getOpt3() {
			return opt3;
		}
		public void setOpt3(String opt3) {
			this.opt3 = opt3;
		}
		public String getOpt4() {
			return opt4;
		}
		public void setOpt4(String opt4) {
			this.opt4 = opt4;
		}
		public String getOpt5() {
			return opt5;
		}
		public void setOpt5(String opt5) {
			this.opt5 = opt5;
		}
		public String getOpt6() {
			return opt6;
		}
		public void setOpt6(String opt6) {
			this.opt6 = opt6;
		}
		public String getAnalysis() {
			return analysis;
		}
		public void setAnalysis(String analysis) {
			this.analysis = analysis;
		}
		public String getEnable() {
			return enable;
		}
		public void setEnable(String enable) {
			this.enable = enable;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getCreate_person() {
			return create_person;
		}
		public void setCreate_person(String create_person) {
			this.create_person = create_person;
		}
		public String getUpdate_time() {
			return update_time;
		}
		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}
		public String getUpdate_person() {
			return update_person;
		}
		public void setUpdate_person(String update_person) {
			this.update_person = update_person;
		}
		public PaperVo getPv() {
			return pv;
		}
		public void setPv(PaperVo pv) {
			this.pv = pv;
		}
		@Override
		public String toString() {
			return "OnlineTestVo [id=" + id + ", type=" + type + ", so_flag="
					+ so_flag + ", difficulty=" + difficulty + ", property="
					+ property + ", use_flag=" + use_flag + ", content="
					+ content + ", answer=" + answer + ", opt1=" + opt1
					+ ", opt2=" + opt2 + ", opt3=" + opt3 + ", opt4=" + opt4
					+ ", opt5=" + opt5 + ", opt6=" + opt6 + ", analysis="
					+ analysis + ", enable=" + enable + ", create_time="
					+ create_time + ", create_person=" + create_person
					+ ", update_time=" + update_time + ", update_person="
					+ update_person + ", pv=" + pv + ", ppv=" + ppv + ", qv="
					+ qv + ", userName=" + userName + ", paperName="
					+ paperName + ", totalItem=" + totalItem + ", totalTime="
					+ totalTime + ", paperId=" + paperId + ", paperNo="
					+ paperNo + ", instruction=" + instruction
					+ ", leaveLimitTotal=" + leaveLimitTotal + ", startTime="
					+ startTime + ", endTime=" + endTime + ", pstartTime="
					+ pstartTime + ", planPaperId=" + planPaperId + "]";
		}
	
	

	
}
