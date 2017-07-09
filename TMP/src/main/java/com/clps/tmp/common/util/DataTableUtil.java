package com.clps.tmp.common.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.clps.tmp.common.vo.PageVo;

/**
 * bootstrap数据表格工具类
 * 
 * @ClassName: DataTableUtil
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年10月12日 下午3:04:14
 */
public class DataTableUtil<T> {

	private final String SECHO = "sEcho";
	private final String ITOTALRECORDS = "iTotalRecords";
	private final String ITOTALDISPLAYRECORDS = "iTotalDisplayRecords";
	private final String AADATA = "aaData";
	private final String IDISPLAYSTART = "iDisplayStart";
	private final String IDISPLAYLENGTH = "iDisplayLength";
	private final String SSORTDIR = "sSortDir_0";
	private final String ISORTCOL = "iSortCol_0";
	private final String MDATAPROP_ = "mDataProp_";

	private String sEcho = null;
	private int iDisplayStart = 0;
	private int iDisplayLength = 0;
	private int pageindex;
	private String SortCol = null;
	private String SortDir = null;

	/**
	 * 取值
	  * getTableData(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: getTableData
	  * @Description: TODO
	  * @param @param request
	  * @param @return    设定文件
	  * @return PageVo<T>    返回类型
	  * @throws
	 */
	public PageVo<T> getTableData(HttpServletRequest request) {
		PageVo<T> pv = new PageVo<T>();
		request = ServletActionContext.getRequest();
		// 数据
		if (request.getParameter(SECHO) != null
				&& !request.getParameter(SECHO).equals("")) {
			sEcho = request.getParameter(SECHO);
		}
		if (request.getParameter(IDISPLAYSTART) != null
				&& !request.getParameter(IDISPLAYSTART).equals("")) {
			iDisplayStart = Integer.parseInt(request
					.getParameter(IDISPLAYSTART));
		}
		if (request.getParameter(IDISPLAYLENGTH) != null
				&& !request.getParameter(IDISPLAYLENGTH).equals("")) {
			iDisplayLength = Integer.parseInt(request
					.getParameter(IDISPLAYLENGTH));
		}
		pageindex = iDisplayStart / iDisplayLength + 1;
		// 排序条件
		if (request.getParameter(SSORTDIR) != null
				&& !request.getParameter(SSORTDIR).equals("")) {
			SortDir = request.getParameter(SSORTDIR);
		}
		if (request.getParameter(ISORTCOL) != null
				&& !request.getParameter(ISORTCOL).equals("")) {
			SortCol = request.getParameter(MDATAPROP_ + request.getParameter(ISORTCOL));
		}
		LinkedHashMap<String, String> sort = new LinkedHashMap<String, String>();
		sort.put(SortCol, SortDir);
		//
		pv.setSort(sort);
		pv.setPage(pageindex);
		pv.setLimitPage(iDisplayLength);
		return pv;
	}
	
	/**
	 * 返回值
	  * getReturnMap(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: getReturnMap
	  * @Description: TODO
	  * @param @param pv
	  * @param @return    设定文件
	  * @return HashMap<String,Object>    返回类型
	  * @throws
	 */
	public HashMap<String,Object> getReturnMap(PageVo<T> pv){
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put(SECHO,sEcho);
		resultMap.put(ITOTALRECORDS,pv.getAllcount());
		resultMap.put(ITOTALDISPLAYRECORDS,pv.getAllcount());
		resultMap.put(AADATA,pv.getList());
		return resultMap;
	}
	

}
