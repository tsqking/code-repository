package com.clps.tmp.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.demo.service.DemoService;
import com.clps.tmp.demo.vo.DemoVo;
/**
 * @author	seven
 */
@Service("demoService")
public class DemoServiceImpl extends MBBaseService implements DemoService {
	@Override
	public DemoVo queryByName(String name) throws Exception {
		return (DemoVo) mbDao.selectOne("demo.queryByName_Type", name);
	}

    @SuppressWarnings("unchecked")
	@Override
    public BtTableVo<DemoVo> findDemoList(Map<String, Object> paramMap) throws Exception {
        // TODO Auto-generated method stub
        long total = (Long) mbDao.selectOne("demo.allCount", paramMap);
        List<DemoVo> demoVoList = (List<DemoVo>) mbDao.selectList("demo.findDemoList", paramMap);
        BtTableVo<DemoVo> bootStrapPageVo = new BtTableVo<DemoVo>();
        bootStrapPageVo.setTotal((int)total);
        bootStrapPageVo.setRows(demoVoList);
        return bootStrapPageVo;
    }
}
