/**
 * Project Name:campus_community
 * File Name:IMarketService.java
 * Package Name:com.clps.service
 * Date:2017年4月1日上午11:45:22
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service;

import java.util.List;
import java.util.Map;

import com.clps.common.bean.Market;
import com.clps.common.exception.ActivityServiceException;
import com.clps.common.exception.MaketServiceException;
import com.clps.common.util.PageVo;

/**
 * ClassName:IMarketService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月1日 上午11:45:22 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface IMarketService {

	/**
	 * listAllGoods:(分页查询所有商品). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MaketServiceException
	 * @since JDK 1.8
	 */
	PageVo<Market> listAllGoods(PageVo<Market> pv) throws MaketServiceException;

	/**
	 * addGoods:(添加商品). <br/>
	 * 
	 * @author Charles
	 * @param market
	 * @return
	 * @throws MaketServiceException
	 * @since JDK 1.8
	 */
	boolean addGoods(Market market) throws MaketServiceException;

	/**
	 * listAllGoodsMng:(后台分页查询所有商品). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MaketServiceException
	 * @since JDK 1.8
	 */
	PageVo<Market> listAllGoodsMng(PageVo<Market> pv) throws MaketServiceException;

	/**
	 * applyGoods:(批量申请商品). <br/>
	 * 
	 * @author Charles
	 * @param goodsList
	 * @return
	 * @throws MaketServiceException
	 * @since JDK 1.8
	 */
	boolean applyGoods(List<Map<String, Object>> goodsList) throws MaketServiceException;
	
	/**
	 * listGoodsApply:(分页查询商品申请表信息). <br/>
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MaketServiceException
	 * @since JDK 1.8
	 */
	PageVo<Map<String, Object>> listGoodsApply(PageVo<Map<String, Object>> pv) throws MaketServiceException;
}
