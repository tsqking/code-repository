/**
 * Project Name:campus_community
 * File Name:MarketDao.java
 * Package Name:com.clps.dao
 * Date:2017年4月1日上午11:41:19
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

/**
 * ClassName:MarketDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月1日 上午11:41:19 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.clps.common.bean.Market;

public interface MarketDao {

	/**
	 * listAllGoods:(分页查询所有商品). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Market> listAllGoods(Map<String, Object> map) throws DataAccessException;

	/**
	 * countGoods:(分页查询所有商品数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countGoods(Map<String, Object> map) throws DataAccessException;

	/**
	 * addGoods:(添加商品). <br/>
	 * 
	 * @author Charles
	 * @param market
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean addGoods(Market market) throws DataAccessException;

	/**
	 * listAllGoodsMng:(后台管理员分页查询所有商品). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Market> listAllGoodsMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * countGoodsMng:(后台管理员分页查询所有商品数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countGoodsMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * applyGoods:(批量申请商品). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean applyGoods(List<Map<String, Object>> goodList) throws DataAccessException;
	
	/**
	 * listGoodsApply:(分页查询商品申请情况). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listGoodsApply(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * countGoodsApply:(分页查询商品情况申请数量). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countGoodsApply(Map<String, Object> map) throws DataAccessException;
}
