package com.clps.tmp.common.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.clps.tmp.common.vo.TreeViewVo;

/**
 * TreeViewVoUtil工具类
  * @ClassName: TreeViewVoUtil
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年12月8日 上午9:34:35
 */
public class TreeViewVoUtil {

	private String TREEVIEW = "tree";
	
	public TreeViewVoUtil() {
		super();
	}
		
	public HashMap<String, Object> getResultMap(AjaxReturnInfo rtn,ArrayList<TreeViewVo> al){
		rtn.add(TREEVIEW, al);
		return rtn.getReturnMap();	
	}
	
}
