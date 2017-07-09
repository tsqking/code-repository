package com.clps.tmp.question.tag.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.question.tag.service.TagService;
import com.clps.tmp.question.tag.vo.TagVo;

@Service("tagService")
public class TagServiceImpl extends MBBaseService implements TagService {

	@SuppressWarnings("unchecked")
	@Override
	public PageVo<TagVo> queryTagPage(PageVo<TagVo> pageVo) throws Exception {
		//取出数据
		int page = pageVo.getPage();
		int limitPage = pageVo.getLimitPage();
		
		//RowBounds rowBounds = new RowBounds((page-1)*limitPage, limitPage);
		//SqlSession sqlSession = null;
		HashMap<String, String> where1 = pageVo.getWhere1();
		HashMap<String, String> where2 = pageVo.getWhere2();
		LinkedHashMap<String, String> sort = pageVo.getSort();
		//条件
		HashMap<String,Object> map = new HashMap<String,Object>();
		//精确查询
		if(!(where1==null||where1.size()<=0)){
			for (Map.Entry<String, String> entry : where1.entrySet()) {
				map.put("enable", entry.getValue());
			}	
		}
		//模糊查询
		if(!(where2==null||where2.size()<=0)){
			for (Map.Entry<String, String> entry : where2.entrySet()) {
				map.put("name", entry.getValue());
			}
		}
		//排序
		if(!(sort==null||sort.size()<=0)){
			for (Map.Entry<String, String> entry : sort.entrySet()) {
				map.put("order", entry.getKey());
				map.put("way", entry.getValue());
			}
		}else{
			map.put("order", "`id`");
			map.put("way", "desc");
		}
		map.put("start",(page-1)*limitPage);
		map.put("page", limitPage);
		//获取记录数(分页)
		List<TagVo> list = (List<TagVo>) this.mbDao.selectList("tag.queryTag_Map", map);
		long count = (Long) this.mbDao.selectOne("tag.queryTagCount");
		for(TagVo tag : list){
			tag.setCreate_time(DateTimeUtil.databaseToSystem(tag.getCreate_time()));
		}
		//汇总数据
		pageVo.setList(list);
		pageVo.setAllcount(String.valueOf(count));
		return pageVo;
	}

	@Override
	public int addTagData(TagVo tagVo) throws Exception {
		tagVo.setCreate_time(DateTimeUtil.nowToDatabase());
		this.mbDao.insert("tag.insertNewTag", tagVo);
		return tagVo.getId();
	}

	@Override
	public int changeTagStateDisable(TagVo tagVo) throws Exception {
        int count = this.mbDao.update("tag.updateTagStatusDisable", tagVo);
        return count;
	}
	
	@Override
	public int changeTagStateEnable(TagVo tagVo) throws Exception {
		int count = this.mbDao.update("tag.updateTagStatusEnable", tagVo);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<TagVo> findTagList(Map<String, Object> map) throws Exception {
		getLangParam(map);//获取国际化语言参数
		long total = (Long) mbDao.selectOne("tag.queryTagCount", map);
        List<TagVo> tagVoList = (List<TagVo>) mbDao.selectList("tag.queryTag_Map", map);
        for(TagVo tagVo : tagVoList){
        	tagVo.setCreate_time(DateTimeUtil.databaseToSystem(tagVo.getCreate_time()));
        }
        BtTableVo<TagVo> bootStrapPageVo = new BtTableVo<TagVo>();
        bootStrapPageVo.setTotal((int)total);
        bootStrapPageVo.setRows(tagVoList);
        return bootStrapPageVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getAllTag() throws Exception {
		List<SelectVo> tagList = (List<SelectVo>) this.mbDao.selectList("tag.queryAllTag");
		return tagList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagVo> getTagByName(String name) throws Exception {
		List<TagVo> list=(List<TagVo>) this.mbDao.selectList("tag.getTagByName", name);
		if(list.size()==0)
			return null;
		return list;
	}

}
