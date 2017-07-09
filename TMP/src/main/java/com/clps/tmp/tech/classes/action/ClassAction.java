package com.clps.tmp.tech.classes.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.velocity.VelocityContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DocUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.MailSenderUtil;
import com.clps.tmp.common.util.ReadDocImgUtil;
import com.clps.tmp.common.util.ReadMultiDocUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.util.WordUtil;
import com.clps.tmp.common.util.XmlToDocx;
import com.clps.tmp.common.util.XmlToExcel;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.OptionService;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.classes.service.ClassService;
import com.clps.tmp.tech.classes.service.ExamNumService;
import com.clps.tmp.tech.classes.vo.ClassVo;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant; 
/**
 * 班级管理Action
 * 
 * @author Seven
 *
 *         2015年11月3日
 */
@SuppressWarnings({ "serial" })
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("class")
@Results({ @Result(name = "toClassManagePage", location = "class/classManage.jsp"),
		@Result(name = "addClassPage", location = "class/addClass.jsp"),
		@Result(name = "toEditClassPage", location = "class/editClass.jsp"),
		@Result(name = "toClassStudentPage", location = "class/ClassStudent.jsp"),
		@Result(name = "toAddPersonInClass", location = "class/addPersonInClass.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class ClassAction extends BaseAction {
	private ClassVo classVo;// 页面数据封装
	private UserVo userVo;
	private HashMap<String, Object> resultMap;// json返回数据map

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public ClassVo getClassVo() {
		return classVo;
	}

	public void setClassVo(ClassVo classVo) {
		this.classVo = classVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Resource
	private ClassService classService;

	@Resource(name = "examNumService")
	private ExamNumService examNumService;

	/**
	 * 跳转到班级管理界面
	 * 
	 * @return
	 */
	public String toClassManagePage() throws Exception {
		return "toClassManagePage";
	}

	/**
	 * 跳转到添加班级界面
	 * 
	 * @return
	 */
	public String addClassPage() throws Exception {
		return "addClassPage";
	}

	/**
	 * 添加班级数据
	 */
	public String addClassData() throws Exception {
		// 接收表单数据
		classVo.setNo(new GenerateNextNo().getNextClassNo());
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		classVo.setCreate_person(user.getName());
		classVo.setCreate_time(DateTimeUtil.nowToDatabase());
		classVo.setUpdate_person(user.getName());
		classVo.setUpdate_time(DateTimeUtil.nowToDatabase());

		// 调用service方法，操作数据库
		int resData = classService.addClassData(classVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0000");
		} else {
			rtn = AjaxReturnInfo.success("1111");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 查询班级
	 */
	public String getClassPage() throws Exception {
		DataTableUtil<ClassVo> dataTableUtil = new DataTableUtil<ClassVo>();
		PageVo<ClassVo> pageVo = dataTableUtil.getTableData(this.httpRequest);
		if (classVo == null)
			classVo = new ClassVo();// 若为modeldriven方式 可省略
		// 1.搜索条件
		// 1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if (classVo.getEnable() != null && !classVo.getEnable().equals("")) {
			where1.put("f.`value`", classVo.getEnable());
		}
		if (classVo.getDirection() != null && !classVo.getDirection().equals("")) {
			where1.put("f1.`value`", classVo.getDirection());
		}
		if (classVo.getLocation() != null && !classVo.getLocation().equals("")) {
			where1.put("f2.`value`", classVo.getLocation());
		}
		pageVo.setWhere1(where1);
		// 1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if (classVo.getName() != null && !classVo.getName().equals("")) {
			where2.put("a.`name`", classVo.getName());
		}
		pageVo.setWhere2(where2);
		// 3.获取数据
		pageVo = classService.queryClassPage(pageVo);
		// 4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}

	/**
	 * 删除技能
	 */
	public String deleteClass() throws Exception {
		// 获取记录序列号id
		String id = String.valueOf(classVo.getId());
		AjaxReturnInfo rtn = null;
		int effect = classService.deleteClass(id);
		if (effect < 0)// -1-使用中，不能删除
			rtn = AjaxReturnInfo.failed(String.valueOf(effect));
		else
			rtn = AjaxReturnInfo.success("");
		resultMap = rtn.getReturnMap();
		return "json";

	}

	/**
	 * 查找班级
	 */
	public String toEditClassPage() throws Exception {
		// 获取记录序列号id
		String id = String.valueOf(classVo.getId());
		// 封装表单数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		classVo = classService.selectClass(paramMap);
		return "toEditClassPage";
	}

	/**
	 * 更新技能
	 */
	public String editClass() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		classVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		classVo.setUpdate_person(user.getName());

		AjaxReturnInfo rtn = null;
		int count = classService.editClass(classVo);
		if (count < 0)
			rtn = AjaxReturnInfo.success("1");
		else
			rtn = AjaxReturnInfo.success("0");
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 跳转到批量添加学生界面
	 * 
	 * @return
	 */
	public String toClassStudentPage() throws Exception {
		return "toClassStudentPage";
	}

	/**
	 * 跳转到班级学生界面
	 * 
	 * @return
	 */
	public String getUserPage() throws Exception {
		DataTableUtil<UserVo> dataTableUtil = new DataTableUtil<UserVo>();
		PageVo<UserVo> pageVo = dataTableUtil.getTableData(this.httpRequest);
		if (userVo == null)
			userVo = new UserVo();// 若为modeldriven方式 可省略
		if (classVo == null)
			classVo = new ClassVo();// 若为modeldriven方式 可省略
		// 1.搜索条件
		// 1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if (userVo.getDescription() != null && !userVo.getDescription().equals("")) {
			where1.put("a.`belong`", userVo.getDescription());
		}
		pageVo.setWhere1(where1);
		// 1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if (userVo.getName() != null && !userVo.getName().equals("")) {
			where2.put("a.`name`", userVo.getName());
		}
		if (userVo.getNo() != null && !userVo.getNo().equals("")) {
			where2.put("a.`no`", userVo.getNo());
		}
		if (userVo.getExam_num() != null && !userVo.getExam_num().equals("")) {
			where2.put("a.`exam_num`", userVo.getExam_num());
		}
		pageVo.setWhere2(where2);
		// 3.获取数据
		pageVo = classService.queryUserPage(pageVo, classVo.getId());
		// 4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}

	/**
	 * 设置Monitor 2016年1月12日 Seven
	 */
	public String setMonitorInClass() throws Exception {
		log.info("设置Monitor： 类型-" + classVo.getDescription() + " 值-" + classVo.getDescription_en_US() + " 班级ID-"
				+ classVo.getId());
		AjaxReturnInfo rtn = null;
		String monitorNo = null;
		if ("".equals(classVo.getDescription_en_US()) || classVo.getDescription_en_US() == null) {
			monitorNo = "0";
		} else {
			monitorNo = classVo.getDescription_en_US();
		}
		int resData = 0;
		if ("student".equals(classVo.getDescription())) {// 设置班长
			resData = classService.setMonitorInClass(classVo.getId(), "student", monitorNo,
					((UserVo) this.session.get(SystemConstant.USER)).getName(), DateTimeUtil.nowToDatabase());
		} else if ("teacher".equals(classVo.getDescription())) {// 设置班主任
			resData = classService.setMonitorInClass(classVo.getId(), "teacher", monitorNo,
					((UserVo) this.session.get(SystemConstant.USER)).getName(), DateTimeUtil.nowToDatabase());
		}
		if (resData > 0) {
			rtn = AjaxReturnInfo.success("0");// 设置成功
		} else if (resData == 0) {
			rtn = AjaxReturnInfo.success("1");// 设置失败
		} else if (resData == -1) {
			rtn = AjaxReturnInfo.success("-1");// 查无此人
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 将人员加入班级 2016年1月12日 Seven
	 */
	public String addPersonInClass() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("class_id", classVo.getId());
		paramMap.put("student_id", userVo.getId());

		int resData = classService.addPersonsInClass(paramMap);
		int resData1 = classService.updatePersonsInClassSize(paramMap);

		AjaxReturnInfo rtn = null;
		if ((resData + resData1) == 2) {
			rtn = AjaxReturnInfo.success("0");// 添加成功
		} else {
			rtn = AjaxReturnInfo.success("1");// 添加失败!稍后重新添加
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 将人员移出班级 2016年1月12日 Seven
	 */
	public String deletePersonInClass() throws Exception {
		String class_id = String.valueOf(classVo.getId());
		String student_id = String.valueOf(userVo.getId());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("class_id", class_id);
		paramMap.put("student_id", student_id);
		AjaxReturnInfo rtn = null;
		int count = classService.deletePersonInClass(paramMap);
		int resData1 = classService.updatePersonsInClassSize(paramMap);
		if ((count + resData1) == 2)
			rtn = AjaxReturnInfo.success("0");
		else
			rtn = AjaxReturnInfo.success("1");
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 跳转到批量添加人员到班级界面
	 * 
	 * @return
	 */
	public String toAddPersonInClass() throws Exception {
		return "toAddPersonInClass";
	}

	/**
	 * 上传文件的属性
	 * 
	 * @return
	 */
	private FileUploadInfoVo file;

	public FileUploadInfoVo getFile() {
		return file;
	}

	public void setFile(FileUploadInfoVo file) {
		this.file = file;
	}

	private OptionService optionService;

	public OptionService getOptionService() {
		return optionService;
	}

	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}

	/**
	 * 批量添加用户(班级管理) 2015年12月2日 Seven
	 */
	public String batchAddUser() throws Exception {// 判断是否空传
		AjaxReturnInfo rtn = null;
		boolean upSuccess = true;
		UploadFileUtil uploadUtil = new UploadFileUtil();
		String lang = (String) session.get(SystemConstant.LANG);
		if (file == null) {// 判断是否空传
			rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请至少选择一个文件上传!" : "Please choose one file!");//// 请至少选择一个文件上传!
			upSuccess = false;
		} else {
			log.info(file.getFileFileName() + " " + file.getFileContentType());
			if (!file.getFileFileName().endsWith(".xls") && !file.getFileFileName().endsWith(".xlsx")) {// 判断是否传的excel
				rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请确认上传文件!" : "Please confirm the type of file!");//// 请确认上传文件!
				upSuccess = false;
			} else {
				HashMap<String, Object> fileInfoResultMap = new HashMap<String, Object>();
				// 获取上传文件信息
				file = uploadUtil.getUploadFileInfo(file, this.httpRequest);
				fileInfoResultMap = uploadUtil.getReturnMap(file, this.httpRequest);
				// 判断上传文件是否符合规则
				if ("false".equals((String) fileInfoResultMap.get("success"))) {
					rtn = AjaxReturnInfo.failed((String) fileInfoResultMap.get("message"));
					upSuccess = false;
				} else {// 上传到服务器指定路径下
					FileUtils.copyFile(file.getFile(), file.getFileTarget());
				}
			}
		}
		// 上传成功，业务处理
		if (upSuccess) {
			ExcelUtil excel = new ExcelUtil(2);
			String[] columnName = new String[] { "cardtype", "cardno", "name", "en_name", "gender", "mobile", "phone",
					"exam_num", "email", "age", "birthday", "education_background", "degree", "university", "college",
					"major", "cet4", "cet6", "gpa", "contact_address", "contact_postcode", "home_address",
					"home_postcode" };
			// 读取excel进入list中
			List<Map<String, Object>> list = excel.readExcel(file.getFilePath(), "UserList", columnName);

			boolean illegal = false;
			int i = 0;
			HashSet<String> mobileSet = new HashSet<String>();// 手机号不能重复检测
			// 遍历excel记录，进行基本验证
			for (Map<String, Object> map : list) {
				mobileSet.add((String) map.get("mobile"));
				// 人员其他信息填写
				map.put("exist", false);// 初始化存在标志位
				map.put("username", (String) map.get("mobile"));// 暂先手机号
				map.put("en_name", "test");
				map.put("cardtype", "1");
				map.put("role", "3");// 设定角色
				map.put("enable", "T");// 设定生效标识
				map.put("create_time", DateTimeUtil.nowToDatabase());// 创建时间
				map.put("create_person", ((UserVo) session.get(SystemConstant.USER)).getName());// 创建人
				map.put("update_time", DateTimeUtil.nowToDatabase());// 更新时间
				map.put("update_person", ((UserVo) session.get(SystemConstant.USER)).getName());// 更新人
				i++;
				if ("".equals(map.get("cardtype")) || "".equals(map.get("cardno")) || "".equals(map.get("name"))
						|| "".equals(map.get("en_name")) || "".equals(map.get("gender")) || "".equals(map.get("mobile"))
						|| "".equals(map.get("email"))) {
					rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? ("第" + (i + 2) + "行有背景色的列必须填写内容~")
							: ("Please input the info in column which have the background color title on the " + (i + 2)
									+ " row~"));////
					illegal = true;
					break;
				}
			}
			if (i > mobileSet.size()) {
				rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "手机号码不能重复~" : "Mobile duplicate~");//// 手机号码不能重复~
				illegal = true;
			}
			if (!illegal) {
				// 枚举数据调整
				// 获取枚举数据
				List<Map<String, Object>> cardTypeListMap = optionService.getOptionMapByGPVal("CARD_TYPE");
				List<Map<String, Object>> genderListMap = optionService.getOptionMapByGPVal("SEX");
				List<Map<String, Object>> eduListMap = optionService.getOptionMapByGPVal("EDU_BAK");
				List<Map<String, Object>> degreeListMap = optionService.getOptionMapByGPVal("DEGREE");
				// 遍历人员调整枚举数据
				for (Map<String, Object> map : list) {
					for (Map<String, Object> cardTypeMap : cardTypeListMap) {
						if (cardTypeMap.get("text").equals(map.get("cardtype"))) {
							map.put("cardtype", cardTypeMap.get("id"));
							break;
						}
					}
					for (Map<String, Object> genderMap : genderListMap) {
						if (genderMap.get("text").equals(map.get("gender"))) {
							map.put("gender", genderMap.get("id"));
							break;
						}
					}
					for (Map<String, Object> eduMap : eduListMap) {
						if (eduMap.get("text").equals(map.get("education_background"))) {
							map.put("education_background", eduMap.get("id"));
							break;
						}
					}
					for (Map<String, Object> degreeMap : degreeListMap) {
						if (degreeMap.get("text").equals(map.get("degree"))) {
							map.put("degree", degreeMap.get("id"));
							break;
						}
					}
				}
				// 调用业务批量添加操作
				HashMap<String, Object> batchAddRtn = classService.batchAddUser(list);
				rtn = AjaxReturnInfo.success("");
				String rtnMsg = null;
				if (!"".equals((String) batchAddRtn.get("existMobile"))) {
					if ("zh_CN".equals(lang)) {
						rtnMsg = "账号建立成功！以下手机用户已存在系统账号:<br>" + batchAddRtn.get("existMobile")
								+ (("".equals((String) batchAddRtn.get("userNameList"))) ? ""
										: ("<br>请谨记以下新添的用户名称，方便为新用户添加角色(基本角色已添加)：<br>"
												+ (String) batchAddRtn.get("userNameList")));
					} else {
						rtnMsg = "Account set up success! Bellow mobile exist system account:<br>"
								+ batchAddRtn.get("existMobile")
								+ (("".equals((String) batchAddRtn.get("userNameList"))) ? ""
										: ("<br>Please record the account name bellow, then use this to link the role(Already have base role):<br>"
												+ (String) batchAddRtn.get("userNameList")));
					}
					log.info(rtnMsg);
					rtn.add("message", rtnMsg);
				} else {
					if ("zh_CN".equals(lang)) {
						rtnMsg = "账号建立成功！<br>" + "<br>请谨记以下新添的用户名称，方便为新用户添加角色(基本角色已添加)：<br>"
								+ (String) batchAddRtn.get("userNameList");

					} else {
						rtnMsg = "Account set up success!<br>"
								+ "<br>Please record the account name bellow, then use this to link the role(Already have base role):<br>"
								+ (String) batchAddRtn.get("userNameList");
						;
					}
					log.info(rtnMsg);
					rtn.add("message", rtnMsg);
				}
				// 为人员添加进班级
				String userNameList = (String) batchAddRtn.get("userNameList");
				String usernames[] = userNameList.split(";");
				classService.batchAddUserInClass(usernames, classVo.getId());
				// 更新班级人数
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("class_id", classVo.getId());
				classService.updatePersonsInClassSize(paramMap);
			}
			// 删除临时附件
			uploadUtil.delFile(file.getFilePath());
		}
		// json返回
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 
	 * 批量添加接收人信息
	 * 
	 * @author Victor
	 * @return String
	 * @throws Exception
	 */
	public String batchAddApplyInfo() throws Exception {
		AjaxReturnInfo rtn = null;
		boolean upSuccess = true;
		String toFilePath=new UploadFileUtil().getBaseDir()+File.separator+File.separator;
		UploadFileUtil uploadUtil = new UploadFileUtil();
		String lang = (String) session.get(SystemConstant.LANG);
		if (file == null) {// 判断是否空传
			rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请至少选择一个文件上传!" : "Please choose one file!");//// 请至少选择一个文件上传!
			upSuccess = false;
		} else {
			log.info(file.getFileFileName() + " " + file.getFileContentType());
			if (!file.getFileFileName().endsWith(".xls") && !file.getFileFileName().endsWith(".xlsx")) {// 判断是否传的excel
				rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请确认上传文件!" : "Please confirm the type of file!");//// 请确认上传文件!
				upSuccess = false;
			} else {
				HashMap<String, Object> fileInfoResultMap = new HashMap<String, Object>();
				// 获取上传文件信息
				file = uploadUtil.getUploadFileInfo(file, this.httpRequest);
				fileInfoResultMap = uploadUtil.getReturnMap(file, this.httpRequest);
				// 判断上传文件是否符合规则
				if ("false".equals((String) fileInfoResultMap.get("success"))) {
					rtn = AjaxReturnInfo.failed((String) fileInfoResultMap.get("message"));
					upSuccess = false;
				} else {// 上传到服务器指定路径下
					FileUtils.copyFile(file.getFile(), file.getFileTarget());
				}
			}
		}
		String rtnMsg = null;
		if (upSuccess) {
			ExcelUtil excel = new ExcelUtil(1);
			String[] columnName = new String[] { "name", "tel", "email" };
			System.out.println(file.getFileFileName());
			// 读取excel进入list中
			List<Map<String, Object>> list = excel.readExcel(file.getFilePath(), null, columnName);
			System.out.println(list.size() + "-----------");
			Map<String, Object> nullMap = new HashMap<String, Object>();
			nullMap.put("name", "");
			nullMap.put("tel", "");
			nullMap.put("email", "");
			List<Map<String, Object>> nullArr = new ArrayList<Map<String, Object>>();
			nullArr.add(nullMap);
			// 去掉空值
			list.removeAll(nullArr);
			System.out.println(list.size());
			// 收件人地址列表
			String emails[] = new String[list.size()];
			// 收件人姓名列表
			String names[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				emails[i] = (String) list.get(i).get("email");
				System.out.println(emails[i]);
				names[i] = (String) list.get(i).get("name");
			}
			// 邮件标题
			String subject = "金融IT人才测评报名表";
			// 邮件的附件
			String attachFile[] = { toFilePath+"金融IT人才测评报名表.docx" };
			// 邮件的正文
			String htmlText = "Dear Customer" + "\\n" + "欢迎报名参加金融IT人才测评考试，请接收报名模板";

			MailSenderUtil.sendEmail(emails, null, null, subject, htmlText, attachFile);

			rtn = AjaxReturnInfo.success("");
			if ("zh_CN".equals(lang)) {
				rtnMsg = "上传成功";
			} else {
				rtnMsg = "upload success";
			}
			rtn.add("message", rtnMsg);
		}
		// json返回
		resultMap = rtn.getReturnMap();
		return "json";
	}

	public String selectMonitorInClass() throws Exception {
		// 获取记录序列号id
		String id = String.valueOf(classVo.getId());
		// 封装表单数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		classVo = classService.selectClass(paramMap);
		AjaxReturnInfo rtn = new AjaxReturnInfo();
		rtn.add("student_monitor", classVo.getStudent_monitor());
		rtn.add("teacher_monitor", classVo.getTeacher_monitor());
		rtn.add("size", classVo.getSize());
		resultMap = rtn.getReturnMap();

		return "json";
	}

	/**
	 * 根据ID获取班级信息 2016年2月3日 Seven
	 */
	public String getClassInfo() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", classVo.getId());
		classVo = classService.selectClass(paramMap);
		AjaxReturnInfo rtn = new AjaxReturnInfo();
		rtn.add("classVo", classVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	private File[] files;
	private String[] filesFileName;

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	/**
	 * batchTransfer:(批量上传报名表，生成Exel). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String batchWordToExel() throws Exception {
		// 加载保存路径的properties文件
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream("savePath.properties"));
		DownloadFileUtil downLoad=new DownloadFileUtil();
		File file = null;// 临时文件
		File docFile = null;// 保存至指定路径的word文档
		AjaxReturnInfo rtn = null;
		boolean upSuccess = true;
		String lang = (String) session.get(SystemConstant.LANG);
		// 保存路径：项目根目录下tmp文件夹中
		String tmppath = ServletActionContext.getServletContext().getRealPath("/tmp");
		// 上传成功后，将文件保存至指定位置
		String docPath = p.getProperty("docPath");
		String imgPath = p.getProperty("imgPath");
		String exelPath = p.getProperty("exelPath");
		// 创建存放临时文件的文件夹
		file = new File(tmppath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 创建存放doc的文件夹
		docFile = new File(docPath);
		if (!docFile.exists()) {
			docFile.mkdirs();
		}
		// 创建存放图片的文件夹
		File imgDir = new File(imgPath);
		if (!imgDir.exists()) {
			imgDir.mkdirs();
		}
		// 创建存放表格的文件夹
		File exelDir = new File(exelPath);
		if (!exelDir.exists()) {
			exelDir.mkdirs();
		}

		if (files == null) {// 判断是否空传
			rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请至少选择一个文件上传!" : "Please choose one file!");//// 请至少选择一个文件上传!
			upSuccess = false;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (!filesFileName[i].endsWith(".doc") && !filesFileName[i].endsWith(".docx")) {// 判断是否传的excel
					rtn = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请确认上传文件!" : "Please confirm the type of file!");//// 请确认上传文件!
					// 清除缓存文件
					FileUtils.forceDelete(file);
					upSuccess = false;
				} else {
					// 文件上传至临时文件夹
					File savefile = new File(file + File.separator + filesFileName[i]);
					FileUtils.copyFile(files[i], savefile);

					// 将上传文件复制到指定位置
					File saveDocFile = new File(docFile + File.separator + savefile.getName());
					FileUtils.copyFile(savefile, saveDocFile);

					upSuccess = true;
				}
			}
		}

		if (upSuccess) {
			// 如果上传成功，则 开始解析word报名表
			List<String> listPath = new ArrayList<>();// 存放所有doc的绝对路径

			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				listPath.add(f.getAbsolutePath());
			}
			String[] wordName = new String[] { "性  别","手  机","考生来源","工作地编码","考试地编码","参加测评科目","等级","身份证号码", "姓  名", "考试日期","考试时间","考试地点","考场","座位号","电子邮箱","出生日期(xxxx-xx-xx)",   "学  校",
					"院  系", "专  业", "联系地址", "邮政编码", "是否参加考前培训",  };
			String[] alignName = new String[] { "sex","mobile","from","post1","post2","subject","level","cardno","name", "examDate","examTime","examPlace","examRoom","examSeat", "email","birth",    "school",
					"college", "major", "contact_address", "contact_post", "train",   };

			// 获得解析后的数据
			List<Map<String, Object>> listMap = ReadMultiDocUtil.read07TableByXWPF(listPath, wordName, alignName);
			// 解析后的数据放入新list
			List<Map<String, Object>> dealMap = new ArrayList<>();

			// 处理解析后的数据
			String readName = null;
			List<String> imgSavePath = new ArrayList<>();
			for (Map<String, Object> readMap : listMap) {

				String orgSubject = (String) readMap.get("subject");
				String preSub = orgSubject.split("（")[0];
				String sufSub = orgSubject.split("：")[1].split("）")[0];
				if (sufSub.trim().equals("选择一项。")) {
					sufSub = new String("");
				}
//				readMap.put("subject", preSub + " " + sufSub);
				readMap.put("subject", sufSub.trim());

				String orgTrain = (String) readMap.get("train");
				String preTrain = orgTrain.split("（")[0];
				String sufTrain = orgTrain.split("[:]")[1].split("）")[0];
				if (sufTrain.trim().equals("单击此处输入文字。")) {
					sufTrain = new String("");
				}
				readMap.put("train", preTrain + " " + sufTrain);

				dealMap.add(readMap);
				readName = (String) readMap.get("name");
				imgSavePath.add(imgPath + File.separator + readName);

				log.info("处理后的word表格信息为：");
				for (Entry<String, Object> entry : readMap.entrySet()) {
					log.info("	" + entry.getKey() + ":" + entry.getValue());
				}
			}
			// 清空原先 返回数据
			listMap.clear();
			// 开始解析word中的图片
			ReadDocImgUtil.readImg(listPath, imgSavePath,readName);
			// 删除缓存文件
			FileUtils.forceDelete(file);

			// 把得到的数据存入Exel
			// 开始生成表格
			ExcelUtil excelUtil = new ExcelUtil();
			HSSFWorkbook hssfWookbook = excelUtil.getHSSFWookbook(dealMap, alignName, wordName);
			// 将生成的表格存到指定文件
			OutputStream out = null;
			File exelFile = new File(exelPath + File.separator + DateTimeUtil.getNow() + ".xls");
			//
			
			try{
				out = new FileOutputStream(exelFile);
				hssfWookbook.write(out);
				log.info("表格生成成功，请前往" + exelPath + "目录下查看");
				out.flush();
			} catch (Exception e) {
				log.info("生成文件出错...");
				e.printStackTrace();
			}finally{
				out.close();
			}
			//
//			out = new FileOutputStream(exelFile);
//			hssfWookbook.write(out);
//			log.info("表格生成成功，请前往" + exelPath + "目录下查看");
//			out.flush();
//			out.close();
			
			
//			if (exelFile.exists()) {
//				log.info("文件下载中...");
//				downLoad.downLoad(DateTimeUtil.getNow()+"报名表", exelPath + "\\"+exelFile.getName(), this.httpRequest, this.httpResponse);
//			}else{
//				log.info("下载失败：文件不存在");
//			}
			
			rtn = AjaxReturnInfo.success("");
			String rtnMsg = null;
			if ("zh_CN".equals(lang)) {
				rtnMsg = "上传并解析成功";
			} else {
				rtnMsg = "upload and read success";
			}
			rtn.add("message", rtnMsg);
			rtn.add("excelName",exelFile.getName());
			rtn.add("excelPath", exelPath);
		}
		resultMap = rtn.getReturnMap();
		
		
		return "json";
	}

	/**
	 * 下载 word解析成的excel
	 * @throws Exception
	 */
	public void downExcel() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String excelName=request.getParameter("excelName");
		String excelPath=request.getParameter("excelPath");
		//下载
		DownloadFileUtil downLoad=new DownloadFileUtil();
		log.info("文件下载中...");
		downLoad.downLoad(DateTimeUtil.getNow()+"报名表.xls", excelPath + "/"+excelName, this.httpRequest, this.httpResponse);
//		if (file.exists()) {
//			log.info("文件下载中...");
//			downLoad.downLoad(planPaperVo.getClass_name()+"-AnswerData.xls", filePath, 
//					"application/vnd.ms-excel", this.httpRequest, this.httpResponse);
//		}else{
//			log.info("下载失败：文件不存在");
//		}
	}
	
	/**
	 * 批量生成准考证号(班级管理) 2017年03月29日 Terry
	 */
	public String batchAddUserExamNum() throws Exception {
		 String classPath=ClassAction.class.getClassLoader().getResource("/").getPath();
			// xml的文件名  
	        String xmlTemplate = "test.xml";//classPath+File.separator+"velocity"+File.separator+"test.xml";  
	        // docx的路径和文件名  
	        String docxTemplate = "/home/fudan/TMP_File/Templete/test_template.docx";//"/home/fudan/TMP_File/Templete/test_template.docx";//"e:\\test_template.docx";  
	        // 填充完数据的临时xml  
	        String xmlTemp ="/home/fudan/TMP_File/Templete/temp.xml"; //"/home/fudan/TMP_File/Templete/temp.xml";//"e:\\temp.xml";  
	        // 目标文件名  
	        String toFilePath="/home/fudan/TMP_File/Templete/";    //"/home/fudan/TMP_File/Templete/";//"e:\\test.docx"; 
	        
       
        // 1.需要动态传入的数据  
        Map<String, Object> p = new HashMap<String, Object>();  
		AjaxReturnInfo ajaxReturnInfo = null;
		boolean uploadSuccess = true;// 判断是否上传成功标志位
		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		String lang = (String) session.get(SystemConstant.LANG);
		ajaxReturnInfo=AjaxReturnInfo.success("");
		String rtnMsg=null;
		if (file == null) {// 判断是否空传
			ajaxReturnInfo = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "请至少选择一个文件上传!" : "Please choose one file!");
			uploadSuccess = false;// 上传失败,返回信息
		} else {
			log.info(file.getFileFileName() + " " + file.getFileContentType());// 打印文件名称和文件类型
			if (!file.getFileFileName().endsWith(".xls") && !file.getFileFileName().endsWith(".xlsx")) {// 判断文件格式是否为excel
				ajaxReturnInfo = AjaxReturnInfo
						.failed("zh_CN".equals(lang) ? "请确认上传文件!" : "Please confirm the type of file!");//// 请确认上传文件!
				uploadSuccess = false;// 上传失败,返回信息
			} else {
				// 获得上传文件的信息,上传到服务器
				HashMap<String, Object> fileInfoResMap = new HashMap<String, Object>();
				file = uploadFileUtil.getUploadFileInfo(file, this.httpRequest);
				fileInfoResMap = uploadFileUtil.getReturnMap(file, this.httpRequest);
				// 判断上传文件是否符合规则
				if ("false".equals((String) fileInfoResMap.get("success"))) {
					ajaxReturnInfo = AjaxReturnInfo.failed((String) fileInfoResMap.get("message"));
					uploadSuccess = false;
				} else {
					FileUtils.copyFile(file.getFile(), file.getFileTarget());// 目标文件与拷贝目标文件
				}
			}
		}
		// 上传成功处理业务
		// String rtnMsg=null;
		if (uploadSuccess) {
			ExcelUtil excelUtil = new ExcelUtil(1);// 从第二行开始读取数据
			String[] columnName = new String[] { "gender", "mobile", "affiliation", "addcode1", "addcode2", "test_type",
					"level", "cardno", "name", "test_date", "test_time", "test_place","test_room", "test_seat", "email" };
			// 读取excel进入list中
			List<Map<String, Object>> list = excelUtil.readExcel(file.getFilePath(), null, columnName);
			log.info(list + "表格信息");
			list = this.dealExcelList(list);// 把性别等字段转换成我们需要的格式
			log.info(list + "处理后表格信息");
			boolean illegal = false;// 是否违反规则标志位
			int i = 0;
			HashSet<String> mobileSet = new HashSet<String>();// 手机号不能重复检测
			HashSet<String> cardNoSet = new HashSet<String>();// 身份证号不能重复检测
			// 遍历Excel中的数据
			for (Map<String, Object> map : list) {
				mobileSet.add((String) map.get("mobile"));// 获得所有的手机号
				cardNoSet.add((String) map.get("cardno"));
				// 人员其他信息填写
				map.put("update_time", DateTimeUtil.nowToDatabase());// 更新时间
				map.put("update_person", ((UserVo) session.get(SystemConstant.USER)).getName());// 更新人
				i++;
				// 检测生成准考证号对的字段是否为null
				if ("".equals(map.get("gender")) || "".equals(map.get("mobile")) || "".equals(map.get("affiliation"))
						|| "".equals(map.get("addcode1")) || "".equals(map.get("addcode2"))
						|| "".equals(map.get("test_type")) || "".equals(map.get("level"))
						|| "".equals(map.get("cardno")) || "".equals(map.get("name")) || "".equals(map.get("test_date"))
						|| "".equals(map.get("test_time")) || "".equals(map.get("test_place"))
						|| "".equals(map.get("test_seat"))|| "".equals(map.get("email"))) {
					ajaxReturnInfo = AjaxReturnInfo.failed("zh_CN".equals(lang) ? ("第" + (i + 1) + "行的列必须填写内容~")
							: ("Please input the info in column which have the background color title on the " + (i + 2)
									+ " row~"));
					illegal = true;
					break;
				}

				// 检测数据库中是否存在该号码,不存在返回错误信息
//				if (!classService.existMobile((String) map.get("mobile"))) {
//					ajaxReturnInfo = AjaxReturnInfo.failed("zh_CN".equals(lang) ? ("第" + (i + 2) + "行的用户信息(手机号)不存在")
//							: ("Please check the info in column which not exist on the " + (i + 2) + " row~"));
//					illegal = true;
//				}
			}

			log.info(list + "2处理过后表格信息");

			// 检测手机号码是否有重复的
			if (i > mobileSet.size()) {
				ajaxReturnInfo = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "手机号码不能重复~" : "Mobile duplicate~");//// 手机号码不能重复~
				illegal = true;
			}

			// 检测手机号码是否有重复的
			if (i > cardNoSet.size()) {
				ajaxReturnInfo = AjaxReturnInfo.failed("zh_CN".equals(lang) ? "身份证号码不能重复~" : "cardno duplicate~");//// 手机号码不能重复~
				illegal = true;
			}

			// 除准考证号以外的数据处理成功后，生成准考证号，进行插入
			if (!illegal) {								
				for (Map<String, Object> map : list) {
				
					
					Integer max = 0;
					String maxstr=null;
					StringBuffer examNum = new StringBuffer();// 准考证号
					boolean beyondFlag = true;//判断最大值是否超过9999
					// 封装返回数据
//					VelocityContext velocityContext = new VelocityContext();
					 
					// 按批次将属于参加同一科目的男女考生进行编号
					if (map.get("gender").equals("1") && map.get("test_type").equals("Java")) {
						// 查出准考证号后5位的最大值
						maxstr=examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

					// 按批次将属于参加同一科目的男女考生进行编号
					if ((map.get("gender").equals("1") && map.get("test_type").equals("Mainframe"))) {
						// 查出准考证号后5位的最大值
						map.put("test_type"	, "MF");
						maxstr = examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

					
					// 按批次将属于参加同一科目的男女考生进行编号
					if (map.get("gender").equals("1") && map.get("test_type").equals("Testing")) {
						// 查出准考证号后5位的最大值
						maxstr = examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

				if (map.get("gender").equals("2") && map.get("test_type").equals("Java")) {
							// 按批次将属于参加同一科目的男女考生进行编号
					// 查出准考证号后5位的最大值
						maxstr = examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

					// 按批次将属于参加同一科目的男女考生进行编号
					if (map.get("gender").equals("2") && map.get("test_type").equals("Mainframe")) {
						map.put("test_type"	, "MF");
						// 查出准考证号后5位的最大值
						maxstr = examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

					// 按批次将属于参加同一科目的男女考生进行编号
					if (map.get("gender").equals("2") && map.get("test_type").equals("Testing")) {
						// 查出准考证号后5位的最大值
						maxstr = examNumService.queryUserExamNumMax(map);
						if (maxstr == null||maxstr.equals("")) {
							max = 1;
						}
						else if (maxstr.length()==4) {
							if (maxstr.equals("9999")) {
								max=1;
								beyondFlag=false;
							}else{
								max=Integer.parseInt(maxstr);
								max++;
							}
						}
						else if(maxstr.length()==5){
							max=Integer.parseInt(maxstr.substring(16,20));
							max++;
							beyondFlag=false;
						}
					}

					// 处理数据
					if ((map.get("test_type")).equals("Java")) {
						map.put("test_type", "01");
					}
					if ((map.get("test_type")).equals("MF")) {
						map.put("test_type", "02");
					}
					if ((map.get("test_type")).equals("Testing")) {
						map.put("test_type", "03");
					}

					// 准考证号前16位
					if (beyondFlag == true) {//0001~9999拼接
						examNum.append(map.get("gender")).append(map.get("affiliation")).append(map.get("addcode1"))
								.append(map.get("addcode2")).append(map.get("test_type")).append(map.get("level"))
								.append(dealExcelData1(max));
					} else {//0001A~9999A拼接
						examNum.append(map.get("gender")).append(map.get("affiliation")).append(map.get("addcode1"))
								.append(map.get("addcode2")).append(map.get("test_type")).append(map.get("level"))
								.append(dealExcelData2(max));
					}

					map.put("exam_num", examNum.toString());
					
					UserVo user = (UserVo) this.session.get(SystemConstant.USER);
					map.put("update_time", DateTimeUtil.nowToDatabase());
					map.put("update_person",user.getName());
					map.put("username", (String) map.get("mobile"));// 暂先手机号
					map.put("en_name", "");
					map.put("cardtype", "1");
					map.put("role", "3");// 设定角色
					map.put("enable", "T");// 设定生效标识
					map.put("create_time", DateTimeUtil.nowToDatabase());// 创建时间
					map.put("create_person", ((UserVo) session.get(SystemConstant.USER)).getName());// 创建人
					map.put("update_time", DateTimeUtil.nowToDatabase());// 更新时间
					map.put("update_person", ((UserVo) session.get(SystemConstant.USER)).getName());// 更新人
					map.put("no", new GenerateNextNo().getNextPersonNo("3"));//动态生成学号
					map.put("password", SecurityHelper.DESEncrypt(genertePassWord((String)map.get("cardno"))));//动态生成密码
					// 插入准考证号
					
					int flag =examNumService.addUserAndExamNum(map);//examNumService.updUserExamList(map);
					
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("class_id", classVo.getId());
					paramMap.put("username", map.get("username").toString());
					int user_id=examNumService.usernameToId(paramMap);
					paramMap.put("student_id", user_id);
					// 为人员添加进班级
					examNumService.addUserInClass(paramMap);
					
					// 更新班级人数
					
					examNumService.updatePersonsInClassSize(paramMap);
					
					if (flag <= 0) {
						ajaxReturnInfo = AjaxReturnInfo
								.failed("zh_CN".equals(lang) ? ("生成准考证失败") : ("Add examNum Failed"));
						break;
					}

					// 生成准考证号后生成附件
					if (map.get("test_type").equals("01")) {
						p.put("type", "JAVA");
					}
					if (map.get("test_type").equals("02")) {
						p.put("type", "Mainframe");
					}
					if (map.get("test_type").equals("03")) {
						p.put("type", "Testing");
					}
					p.put("exam_num", map.get("exam_num"));
					p.put("name", map.get("name"));
					p.put("cardno", map.get("cardno"));
					p.put("date", map.get("test_date"));
//					p.put("test_date1", map.get("test_date").toString().substring(0, 3));
//					p.put("test_date2", map.get("test_date").toString().substring(3, 4));
//					p.put("test_date3", map.get("test_date").toString().substring(4, 10));
//					p.put("test_time1", map.get("test_time").toString().substring(0, 1));
//					p.put("test_time2", map.get("test_time").toString().substring(1, 4));
//					p.put("test_time3", map.get("test_time").toString().substring(4, 6));
//					p.put("test_time4", map.get("test_time").toString().substring(6, 11));
//					p.put("test_place1", map.get("test_place").toString().substring(0, 1));
//					p.put("test_place2", map.get("test_place").toString().substring(1, 2));
					p.put("time", map.get("test_time"));
					p.put("place", map.get("test_place"));
					p.put("room", map.get("test_room"));
					p.put("header", "${header}"); 
					p.put("seat", map.get("test_seat"));
					String[] locarr;
					   try {
                        
                        Writer w = new FileWriter(new File(xmlTemp));  
   					 XmlToExcel.process(xmlTemplate, p, w);  
   					  
   			            // 3.把填充完成的xml写入到docx中  
   			            XmlToDocx xtd = new XmlToDocx();  
   			            xtd.outDocx(new File(xmlTemp), docxTemplate,toFilePath+"test"+String.valueOf(i)+".docx"); //"e:\\"+p.get("name")+"准考证.docx"
   			            w.close();
   			            locarr=new String[]{"/home/fudan/TMP_File/Tech/apply/doc/"+p.get("name")+".docx"};
   			            //locarr=new String[]{"F:\\"+p.get("name")+"准考证.docx"};
//   					String[] locarr = new String[] { System.getProperty("user.dir").substring(0, 2) + DocUtil
//   							.createDoc("test.vm", p, p.get("name") + "准考证") };
   					///////////////
   						//给准考证添加照片
   			            String img="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name");
   			         String	imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".jpeg";
   			            File file=new File(img+".jpg");
   			         if(file.exists()){
   			        		imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".jpg";
   			         }else{
   			        	 file=new File(img+".JPG");
   			        	if(file.exists()){
			        			imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".JPG";
			        	}else{
   			        	 file=new File(img+".jpeg");
   			        	if(file.exists()){
   			        			imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".jpeg";
   			        	}else{
	   			        	 file=new File(img+".JPEG");
	    			        	if(file.exists()){
	 			        			imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".JPEG";
	 			        	}else{
	 	   			        	 file=new File(img+".png");
	 	   			        	if(file.exists()){
	 				        			imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".png";
	 				        	}else{
		   			        		file=new File(img+".PNG");
		   			        		if(file.exists()){
		   			        			imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".PNG";
		   	   			            	//imgsrc="d:\\test.jpeg";
		   			        		}else{
		   			        			 imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+"test.jpeg";
		   			        		}
	 				        	}
	 			        	}
   			        	}
			        }
   			      }
   			           // String imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".jpeg";//"/home/fudan/TMP_File/Tech/"+"apply/img/"+p.get("name")+".jpeg";
//   			         String imgsrc="E:\\home\\fudan\\TMP_File\\Tech\\apply\\img\\"+p.get("name")+".jpeg";
//   			            File file = new File(imgsrc);
//   			            if(!file.exists()){
//   			            	//imgsrc="/home/fudan/TMP_File/Tech/"+"apply/img/"+"test.jpeg";
//   			            	imgsrc="d:\\test.jpeg";
//   			            }
   						Map<String, Object> param = new HashMap<String, Object>();  
   				        Map<String,Object> header = new HashMap<String, Object>();  
   				        header.put("width", 130);  
   				        header.put("height", 190);  
   				        header.put("type", "jpeg");  
   				        header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream(imgsrc), true));  
   				        param.put("${header}",header);  
   				        XWPFDocument doc = WordUtil.generateWord(param,toFilePath+"test"+String.valueOf(i)+".docx");  //"F:\\"+p.get("name")+"准考证.docx"
   				        FileOutputStream fopts = new FileOutputStream("/home/fudan/TMP_File/Tech/apply/doc/"+p.get("name")+".docx");  
   				        //FileOutputStream fopts = new FileOutputStream("F:\\"+p.get("name")+"准考证.docx");  
   				        doc.write(fopts);  
   				        fopts.close();  
   				      String[] accarr = new String[] { map.get("email").toString() };
   					log.info(Arrays.toString(accarr));
   					MailSenderUtil.sendTemplateEmail(accarr, null, null, "【金融IT人才测评（FITT）】"+p.get("name")+"准考证","template_admission_email", p, locarr);
   					Thread.sleep(1200);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
					// uploadFileUtil.delFile(System.getProperty("user.dir").substring(0,
					// 2)+DocUtil.createDoc("template_examcard.vm",
					// velocityContext,velocityContext.get("name")+"准考证"));

				}
				if ("zh_CN".equals(lang)) {
					rtnMsg = "发送成功";
				} else {
					rtnMsg = "send mail success";
				}
				ajaxReturnInfo.add("message", rtnMsg);
			}else{
				if ("zh_CN".equals(lang)) {
					rtnMsg = "发送失败";
				} else {
					rtnMsg = "send mail fail";
				}
				ajaxReturnInfo.add("message", rtnMsg);
			}

			
			
			
		

			// 删除临时附件
			uploadFileUtil.delFile(file.getFilePath());
		}
		// json返回
		resultMap = ajaxReturnInfo.getReturnMap();
		return "json";
	}

	public static void main(String[] args) {
		//System.out.println(Integer.parseInt("11111111111111110001A".substring(16, 20)));
		System.out.println(Integer.parseInt("0001A"));
	}

	public List<Map<String, Object>> dealExcelList(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			// 处理数据
			if ((map.get("gender")).equals("男")) {
				map.put("gender", "1");
			}
			if ((map.get("gender")).equals("女")) {
				map.put("gender", "2");
			}
			if ((map.get("affiliation")).equals("CSTS")) {
				map.put("affiliation", "1001");
			}
			if ((map.get("affiliation")).equals("SC")) {
				map.put("affiliation", "1002");
			}
			if ((map.get("affiliation")).equals("HSBC")) {
				map.put("affiliation", "1003");
			}
			if ((map.get("affiliation")).equals("摩根")) {
				map.put("affiliation", "1004");
			}
			if ((map.get("affiliation")).equals("ANZ")) {
				map.put("affiliation", "1005");
			}
			if ((map.get("affiliation")).equals("野村")) {
				map.put("affiliation", "1006");
			}
			if ((map.get("affiliation")).equals("CLPS")) {
				map.put("affiliation", "2001");
			}
			if ((map.get("affiliation")).equals("中软")) {
				map.put("affiliation", "2002");
			}
			if ((map.get("affiliation")).equals("文思海辉")) {
				map.put("affiliation", "2003");
			}
			if ((map.get("affiliation")).equals("infosys")) {
				map.put("affiliation", "2004");
			}
			if ((map.get("affiliation")).equals("凯捷")) {
				map.put("affiliation", "2005");
			}
			if ((map.get("affiliation")).equals("杉达")) {
				map.put("affiliation", "4001");
			}
			if ((map.get("affiliation")).equals("天府")) {
				map.put("affiliation", "4002");
			}
		}
		return list;
	}

	public String dealExcelData1(Integer count) {
		String countStr = count.toString();
		switch (countStr.length()) {
		case 1:
			countStr = "000" + countStr;
			return countStr;
		case 2:
			countStr = "00" + countStr;
			return countStr;
		case 3:
			countStr = "0" + countStr;
			return countStr;
		case 4:
			return countStr;
		default:
			break;
		}
		return null;
	}
	
	public String dealExcelData2(Integer count) {
		String countStr = count.toString();
		switch (countStr.length()) {
		case 1:
			countStr = "000" + countStr+"A";
			return countStr;
		case 2:
			countStr = "00" + countStr+"A";
			return countStr;
		case 3:
			countStr = "0" + countStr+"A";
			return countStr;
		case 4:
			return countStr+"A";
		default:
			break;
		}
		return null;
	}

	/**
	 * 根据身份证号生成密码--身份证后6位
	 */
	private String genertePassWord(String cardNo)throws Exception{
		return cardNo.substring(cardNo.length()-6);
	}
}