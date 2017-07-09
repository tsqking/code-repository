/**
 * Project Name:campus_community
 * File Name:IWeiBoService.java
 * Package Name:com.clps.service
 * Date:2017年3月24日上午12:18:47
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service;

/**
 * ClassName:IWeiBoService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月24日 上午12:18:47 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import java.util.List;
import java.util.Map;
import com.clps.common.bean.WeiBo;
import com.clps.common.exception.ActivityServiceException;
import com.clps.common.exception.WeiBoServiceException;
import com.clps.common.util.PageVo;

public interface IWeiBoService {
	/**
	 * listAllWeiBo:(前台查出所有微博). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws WeiBoServiceException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllWeiBo() throws WeiBoServiceException;

	/**
	 * listWeiboMng:(管理员分页查询微博). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws ActivityServiceException
	 * @since JDK 1.8
	 */
	PageVo<WeiBo> listWeiboMng(PageVo<WeiBo> pv) throws ActivityServiceException;

	/**
	 * checkWeibo:(管理员审核微博). <br/>
	 * 
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @throws WeiBoServiceException
	 * @since JDK 1.8
	 */
	boolean checkWeibo(WeiBo weiBo) throws WeiBoServiceException;

	/**
	 * deleteWeibo:(删除微博). <br/>
	 * @author Charles
	 * @param w_id
	 * @return
	 * @throws WeiBoServiceException
	 * @since JDK 1.8
	 */
	boolean deleteWeibo(String w_id) throws WeiBoServiceException;
	
	/**
	 * publishWeiBo:(这里用一句话描述这个方法的作用). <br/>
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @throws WeiBoServiceException
	 * @since JDK 1.8
	 */
	boolean publishWeiBo(WeiBo weiBo)throws WeiBoServiceException;
	
	/**
	 * listAllFriendWeiBo:(查出朋友圈所有微博). <br/>
	 * @author Charles
	 * @return
	 * @throws WeiBoServiceException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllFriendWeiBo() throws WeiBoServiceException;

}
