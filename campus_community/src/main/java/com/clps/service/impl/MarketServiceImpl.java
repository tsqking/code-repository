/**
 * Project Name:campus_community
 * File Name:MarketServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年4月1日上午11:46:17
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clps.common.bean.Market;
import com.clps.common.exception.MaketServiceException;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.dao.MarketDao;
import com.clps.service.IMarketService;

/**
 * ClassName:MarketServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月1日 上午11:46:17 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class MarketServiceImpl implements IMarketService {
	@Autowired
	private MarketDao marketDao;
	private boolean flag;

	@Override
	public PageVo<Market> listAllGoods(PageVo<Market> pv) throws MaketServiceException {

		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Market> markets = marketDao.listAllGoods(map);
		PageVo<Market> pageVo = new PageVo<>();
		if (markets != null) {
			pageVo.setList(markets);
			pageVo.setCount(Integer.toString(marketDao.countGoods(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean addGoods(Market market) throws MaketServiceException {
		if (market != null) {
			market.setM_date(DateFormat.getNow());
			return marketDao.addGoods(market);
		}
		return flag;
	}

	@Override
	public PageVo<Market> listAllGoodsMng(PageVo<Market> pv) throws MaketServiceException {

		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Market> markets = marketDao.listAllGoodsMng(map);
		PageVo<Market> pageVo = new PageVo<>();
		if (markets != null) {
			pageVo.setList(markets);
			pageVo.setCount(Integer.toString(marketDao.countGoodsMng(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean applyGoods(List<Map<String, Object>> goodsList) throws MaketServiceException {
		if (goodsList != null) {
			return marketDao.applyGoods(goodsList);
		}
		return false;
	}

	@Override
	public PageVo<Map<String, Object>> listGoodsApply(PageVo<Map<String, Object>> pv) throws MaketServiceException {
		
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Map<String, Object>> markets = marketDao.listGoodsApply(map);
		PageVo<Map<String, Object>> pageVo = new PageVo<>();
		if (markets != null) {
			pageVo.setList(markets);
			pageVo.setCount(Integer.toString(marketDao.countGoodsApply(map)));
			return pageVo;
		}
		return null;
	}

}
