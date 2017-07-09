package com.clps.mms.sys.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clps.mms.log.department.service.IDepartmentLogService;
import com.clps.mms.log.equipment.service.IEquipmentLogService;
import com.clps.mms.log.model.LogBean;
import com.clps.mms.sys.action.BaseAction;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * package:package com.clps.mms.log.action; since:JDK1.8 author:andy.wang
 * date:2017年2月9日 下午4:45:08
 */
@Controller
public class LogAction extends BaseAction implements ModelDriven<LogBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogBean equipmentLogInfo = null;
	@Resource(name = "equipmentLogService")
	private IEquipmentLogService service = null;
	@Resource(name="DepartmentLogService")
	private IDepartmentLogService serviceDepart;
	private Map<String, Object> resultMap;
	private Map<String, Object> mapType;
	public LogAction(Map<String, Object> resultMap) {
		super();
		this.resultMap = resultMap;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * getEquUserInfoLst:分页查询设备记录,获得设备记录列表.
	 * @return
	 * @throws Exception
	 */
	public String getEquInfoLst() throws Exception {
		System.out.println("进入服务");
		DataTableHelper<LogBean> dataTableHelper = new DataTableHelper<>();
		PageVo<LogBean> pageVo = dataTableHelper.getTableData(this.httpRequest);
		System.out.println(pageVo.toString());
		pageVo = service.findEquInfoLst(pageVo);
		resultMap = dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		mapType=new HashMap<String,Object>();
		List<LogBean> list = pageVo.getList();
		Iterator<LogBean> iterator = list.iterator();
		while(iterator.hasNext()){
			LogBean next = iterator.next();
			String type = next.getType();
			mapType.put(type, type);
		}
		session.put("mapType", mapType);
		return "json";
	}
	
	
	/**
	 * getEquUserInfoLst:分页查询部门记录,获得设备记录列表.
	 * @return
	 * @throws Exception
	 */
/*	public String getDepartInfoLst() throws Exception {
		System.out.println("进入服务");
		DataTableHelper<LogBean> dataTableHelper = new DataTableHelper<>();
		PageVo<LogBean> pageVo = dataTableHelper.getTableData(this.httpRequest);
		System.out.println(pageVo.toString());
		pageVo = serviceDepart.findDepartInfoLst(pageVo);
		resultMap = dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		mapType=new HashMap<String,Object>();
		List<LogBean> list = pageVo.getList();
		Iterator<LogBean> iterator = list.iterator();
		while(iterator.hasNext()){
			LogBean next = iterator.next();
			String type = next.getType();
			mapType.put(type, type);
		}
		session.put("mapType", mapType);
		return "json";
	}*/
	@Override
	public LogBean getModel() {
		if (equipmentLogInfo == null) {
			equipmentLogInfo = new LogBean();
		}
		return equipmentLogInfo;
	}
}
