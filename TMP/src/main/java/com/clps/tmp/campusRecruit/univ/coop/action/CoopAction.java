package com.clps.tmp.campusRecruit.univ.coop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.coop.service.CoopAttnService;
import com.clps.tmp.campusRecruit.univ.coop.service.CoopService;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopAttnVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopHisVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.JsonUtil;
import com.clps.tmp.common.util.TimeLineUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * 合作信息管理
 * 
 * @ClassName: CoopAction
 * @Description: TODO
 * @author Wellen
 * @date 2016年3月14日
 */
@SuppressWarnings({ "serial" })
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")
@Action("coop")
@Results({ @Result(name = "toCoopManagePage", location = "../campusRecruit/univ/coop/coopManage.jsp"),
		@Result(name = "toAddCoopPage", location = "../campusRecruit/univ/coop/addCoop.jsp"),
		@Result(name = "toEditCoopPage", location = "../campusRecruit/univ/coop/editCoop.jsp"),
		@Result(name = "toViewCoopPage", location = "../campusRecruit/univ/coop/viewCoop.jsp"),
		@Result(name = "toCoopEdit", location = "../campusRecruit/univ/coop/editCoopHis.jsp"),
		@Result(name = "toCoopAdd", location = "../campusRecruit/univ/coop/addCoopHis.jsp"),
		@Result(name = "toCoopEditAttn", location = "../campusRecruit/univ/coop/editCoopAttn.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class CoopAction extends BaseAction {

	// 页面属性 页面通过'xx.属性'方式
	private CoopVo coopVo;
	private CoopHisVo coopHisVo;
	private CoopAttnVo coopAttnVo;
	// json返回数据map
	private HashMap<String, Object> resultMap;
	// 一对多关系
	private List<CoopAttnVo> coopAttns;
	@Resource
	CoopAttnService coopAttnService;
	@Resource
	CoopService coopService;
	@Resource
	UniversityService universityService;
	@Resource
	CollegeService collegeService;

	public CoopHisVo getCoopHisVo() {
		return coopHisVo;
	}

	public void setCoopHisVo(CoopHisVo coopHisVo) {
		this.coopHisVo = coopHisVo;
	}

	public CoopVo getCoopVo() {
		return coopVo;
	}

	public void setCoopVo(CoopVo coopVo) {
		this.coopVo = coopVo;
	}

	public CoopAttnVo getCoopAttnVo() {
		return coopAttnVo;
	}

	public List<CoopAttnVo> getCoopAttns() {
		return coopAttns;
	}

	public void setCoopAttns(List<CoopAttnVo> coopAttns) {
		this.coopAttns = coopAttns;
	}

	public void setCoopAttnVo(CoopAttnVo coopAttnVo) {
		this.coopAttnVo = coopAttnVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * 跳转合作信息管理界面
	 */
	public String toCoopManagePage() throws Exception {
		return "toCoopManagePage";
	}

	/**
	 * 跳转合作信息管理界面
	 */
	public String toAddCoopPage() throws Exception {
		return "toAddCoopPage";
	}

	/**
	 * 跳转合作信息更新界面
	 * 
	 * @throws Exception
	 */
	public String toEditCoopPage() throws Exception {
		// 获取记录序列号id
		String id = String.valueOf(coopVo.getId()); // 封装表单数据
		// 查询基本信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		coopVo = coopService.selectEditCoop(paramMap);
		String info = DateTimeUtil.databaseToSystem(coopVo.getCoop_time()) + " - " + coopVo.getCompany_name() + " - "
				+ (coopVo.getUniv_name() == null ? "" : (coopVo.getUniv_name() + " "))
				+ (coopVo.getUniv_branch_name() == null ? "" : (coopVo.getUniv_branch_name() + " "))
				+ (coopVo.getCollege_name() == null ? "" : (coopVo.getCollege_name())) + " - " + coopVo.getStyle_name()
				+ " - " + (coopVo.getStatus_name() == null ? "" : (coopVo.getStatus_name()));
		coopVo.setInfo(info);
		// 查询联系人信息
		List<AttnVo> attnList = coopService.getCoopAttn(id);
		String contacts = JsonUtil.toJSONString(attnList);
		coopVo.setContacts(contacts);
		return "toEditCoopPage";
	}

	/**
	 * 跳转合作信息详情界面
	 * 
	 * @throws Exception
	 */
	public String viewCoopPage() throws Exception {
		// 获取记录序列号id
		String id = String.valueOf(coopVo.getId());
		// 封装表单数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		coopVo = coopService.selectViewCoop(paramMap);
		coopVo.setCoop_time(DateTimeUtil.databaseToSystem(coopVo.getCoop_time()));
		if (coopVo.getRecruit_email().equals("")) {
			coopVo.setRecruit_email(" ");
		}
		if (coopVo.getRecruit_time().equals("")) {
			coopVo.setRecruit_time(" ");
		}
		return "toViewCoopPage";
	}

	/**
	 * 添加合作信息
	 */
	public String addCoop() throws Exception {
		coopVo.setCoop_time(DateTimeUtil.systemToDatabase(coopVo.getCoop_time()));
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		coopVo.setCreate_time(DateTimeUtil.nowToDatabase());
		coopVo.setCreate_user(user.getName());
		coopVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		coopVo.setUpdate_user(user.getName());
		// 添加合作信息
		int resData = coopService.addCoopInfo(coopVo);
		// 添加联系人信息
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		Map<String, Object> param = new HashMap<String, Object>();
		int resAttnData = 0;
		String[] ids = null;
		if (dataMap.get("coop_id") != null) {
			ids = ((String) dataMap.get("coop_id")).split(",");
			param.put("attn_ids", ids);
			param.put("coop_id", coopVo.getId());
			resAttnData = coopAttnService.addCoopAttnInfo(param);
		}
		// 添加初始tag
		coopVo.setContactIds(ids);
		coopService.addCoopInfoHis(coopVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1 && resAttnData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 查询合作信息
	 */
	public String selectCoopInfo() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		if (dataMap.get("coopTime") != null) {
			String cooptime[] = ((String) dataMap.get("coopTime")).split("~");
			String end = ("'" + (cooptime[1]).trim() + "'").trim();
			String start = ("'" + (cooptime[0]).trim() + "'").trim();
			dataMap.put("end", end);
			dataMap.put("start", start);
		}
		BtTableVo<CoopVo> bootStrapPageVo = coopService.selectCoopInfo(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 更新合作信息
	 */
	public String editCoopInfo() throws Exception {
		// 接收数据
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		coopVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		coopVo.setUpdate_user(user.getUpdate_person());
		int deleteAttnRtn = 0;
		int resData = coopService.editCoopInfo(coopVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", coopVo.getId());
		deleteAttnRtn = coopAttnService.deleteCoopAttn(map);

		// 调用service方法，操作数据库
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		Map<String, Object> param = new HashMap<String, Object>();
		int resAttnData = 0;
		if (dataMap.get("attn_id") != null) {
			String[] ids = ((String) dataMap.get("attn_id")).split(",");
			param.put("attn_ids", ids);
			param.put("coop_id", coopVo.getId());
			resAttnData = coopAttnService.addCoopAttnInfo(param);
		}
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1 && deleteAttnRtn >= 0 && resAttnData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 删除合作信息
	 */
	public String deleteCoop() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		String[] ids = ((String) dataMap.get("id")).split(",");
		int deleteCoopRtn = coopService.deleteCoopInfo(ids);
		int deleteAttnRtn = coopAttnService.deleteCoopAttnInfo(ids);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (deleteCoopRtn >= 1 || deleteAttnRtn >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 查询合作信息 联系人信息
	 */
	public String findAttnList() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<AttnVo> bootStrapPageVo = coopAttnService.selectAttnInfo(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * 查询所有公司名称 下拉框初始化
	 */
	public String selectAllCompany() throws Exception {
		List<SelectVo> companyList;
		companyList = coopService.selectCompanyName();
		// 返回json数据 data.datas.options
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("");
		rtn.add("options", companyList);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (获取时间线数据)
	 * @return
	 * @throws Exception
	 */
	public String getCoopHisTimeLine() throws Exception {
		// 获取数据
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		String id = dataMap.get("coopId").toString();
		// 调用服务
		List<CoopHisVo> list = coopService.getCoopHis(id + "");
		// 工具类
		TimeLineUtil<CoopHisVo> tlu = new TimeLineUtil<CoopHisVo>();
		ArrayList<HashMap<String, Object>> data = tlu.getTimeLineObject(list, "time", "");
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("1");
		rtn.add("timeLine", data);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (跳转历史编辑界面)
	 * @return
	 * @throws Exception
	 */
	public String toCoopHisEdit() throws Exception {
		int hisId = coopVo.getId();
		coopHisVo = coopService.getCoopHisById(hisId + "");
		coopHisVo.setTime(DateTimeUtil.databaseToSystem(coopHisVo.getTime()));
		coopHisVo.setCreate_time(DateTimeUtil.databaseToSystem(coopHisVo.getCreate_time()));
		coopHisVo.setUpdate_time(DateTimeUtil.databaseToSystem(coopHisVo.getUpdate_time()));
		return "toCoopEdit";
	}

	/**
	 * @Description (跳转历史添加界面)
	 * @return
	 * @throws Exception
	 */
	public String toCoopHisAdd() throws Exception {
		return "toCoopAdd";
	}

	/**
	 * @Description (添加新的变更记录)
	 * @return
	 * @throws Exception
	 */
	public String addCoopHis() throws Exception {
		// 准备数据
		coopHisVo.setTime(DateTimeUtil.systemToDatabase(coopHisVo.getTime()));
		coopHisVo.setCreate_time(DateTimeUtil.nowToDatabase());
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		coopHisVo.setCreate_user(user.getName());
		coopHisVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		coopHisVo.setUpdate_user(user.getName());
		coopHisVo.setDel("N");
		// 联系人数据
		String[] str = coopHisVo.getContacts().replaceAll(" ", "").split(",");
		coopHisVo.setContacts(JsonUtil.toJSONString(str));
		// 调用service方法，操作数据库
		int resData = coopService.addCoopHis(coopHisVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO编辑历史信息)
	 * @return
	 * @throws Exception
	 */
	public String editCoopHis() throws Exception {
		// 准备数据
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		coopHisVo.setTime(DateTimeUtil.systemToDatabase(coopHisVo.getTime()));
		coopHisVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		coopHisVo.setUpdate_user(user.getName());
		// 联系人数据
		String[] str = coopHisVo.getContacts().replaceAll(" ", "").split(",");
		coopHisVo.setContacts(JsonUtil.toJSONString(str));
		// 调用service方法，操作数据库
		int resData = coopService.editCoopHis(coopHisVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO获取联系人信息选择列表)
	 * @return
	 * @throws Exception
	 */
	public String getContacts() throws Exception {
		int hisId = coopVo.getId();
		List<SelectVo> list = coopService.getContactsList(hisId);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO跳转合作联系人修改)
	 * @return
	 * @throws Exception
	 */
	public String toCoopEditAttn() throws Exception {
		int coopId = coopVo.getId();
		coopVo = coopService.getCoopInfo(coopId);
		return "toCoopEditAttn";
	}

	/**
	 * @Description (TODO编辑合作信息)
	 * @return
	 * @throws Exception
	 */
	public String editCoop() throws Exception {
		// 准备数据
		// UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		// coopVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		// coopVo.setUpdate_user(user.getName());
		// 联系人数据
		String[] str = coopVo.getContacts().replaceAll(" ", "").split(",");
		coopVo.setContactIds(str);
		// 调用服务
		int resData = coopService.updateCoopInfo(coopVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
			// 查询联系人信息
			List<AttnVo> attnList = coopService.getCoopAttn(coopVo.getId() + "");
			String contacts = JsonUtil.toJSONString(attnList);
			rtn.add("contacts", contacts);
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO删除历史标记)
	 * @return
	 * @throws Exception
	 */
	public String deleteCoopHis() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		String coopHisId = (String) dataMap.get("id");
		String coopId = (String) dataMap.get("coopId");
		int resData = coopService.deleteCoopHis(coopHisId, coopId, user.getName());
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
}
