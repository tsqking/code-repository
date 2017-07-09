package com.clps.tmp.demo.service;

import java.util.Map;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.demo.vo.DemoVo;

/**
 * @author	seven
 *
 */
public interface DemoService {
	public DemoVo queryByName(String name) throws Exception;
	
	public BtTableVo<DemoVo> findDemoList(Map<String,Object> paramMap) throws Exception;
	
	
}
