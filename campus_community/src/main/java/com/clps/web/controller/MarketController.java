/**
 * Project Name:campus_community
 * File Name:MarketController.java
 * Package Name:com.clps.web.controller
 * Date:2017年4月1日上午11:48:08
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clps.common.bean.Market;
import com.clps.common.bean.UserInfo;
import com.clps.common.exception.MaketServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.service.IMarketService;

/**
 * ClassName:MarketController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月1日 上午11:48:08 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class MarketController {
	@Autowired
	private IMarketService service;
	Map<String, Object> resultMap = new HashMap<>();
	private final String MESSAGE = "msg";

	/**
	 * listMarket:(分页查询所有可用商品). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listMaket.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listMarket(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Market> dth = new DataTableHelper<>();
		PageVo<Market> pv = dth.getTableData(map);
		try {
			pv = service.listAllGoods(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MaketServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * addGoods:(添加商品). <br/>
	 * 
	 * @author Charles
	 * @param market
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "addGoods.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> addGoods(@RequestBody Market market) {
		resultMap.clear();
		if (market != null) {
			try {
				if (service.addGoods(market)) {
					resultMap.put(MESSAGE, "发布成功");
					return resultMap;
				}
				resultMap.put(MESSAGE, "发布失败");
			} catch (MaketServiceException e) {
				resultMap.put(MESSAGE, "发布失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * listMarketMng:(后台分页查询所有可用商品). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listMaketMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listMarketMng(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Market> dth = new DataTableHelper<>();
		PageVo<Market> pv = dth.getTableData(map);
		try {
			pv = service.listAllGoods(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MaketServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = "applyGoods.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> applyGoods(@RequestBody List<Integer> idList, HttpSession session) {
		resultMap.clear();
		UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
		List<Map<String, Object>> newList = new ArrayList<>();
		if (idList != null) {
			for (Integer id : idList) {
				Map<String, Object> newMap = new HashMap<>();
				newMap.put("ma_market_id", id);
				newMap.put("ma_nickname", userinfo.getU_nickname());
				newMap.put("ma_apply_date", DateFormat.getNow());
				newMap.put("ma_phone", userinfo.getU_phone());
				newList.add(newMap);
			}
			try {
				if (service.applyGoods(newList)) {
					resultMap.put(MESSAGE, "申请成功");
					return resultMap;
				}
				resultMap.put(MESSAGE, "申请失败");
			} catch (MaketServiceException e) {
				resultMap.put(MESSAGE, "申请失败");
				e.printStackTrace();
			}
		}
		return resultMap;

	}

	/**
	 * listGoodsApply:(分页查询商品申请表). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listGoodsApply.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listGoodsApply(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Map<String, Object>> dth = new DataTableHelper<>();
		PageVo<Map<String, Object>> pv = dth.getTableData(map);
		try {
			pv = service.listGoodsApply(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MaketServiceException e) {
			e.printStackTrace();
		}
		return resultMap;

	}

}
