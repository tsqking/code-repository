package com.clps.tmp.demo.JunitTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clps.tmp.core.common.util.ObjectUtil;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.demo.vo.DemoVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class RedisTest {
	
	public void StrTest(){
		RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
		//set
		redis.set("testStr", "testStr-value");
		//get
		System.out.println("get after set:"+redis.get("testStr"));//-1
		//remove
		redis.remove("testStr");
		//get
		System.out.println("get after remove:"+redis.get("testStr"));
		
	}
	
	@Test
	public void ListTest(){
		RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
		//push
		redis.lPush("testList", "t2", String.valueOf(2.3), ObjectUtil.serialize(new DemoVo()),"t4","t5");
		System.out.println("list size:"+redis.lsize("testList"));
		//pop
		System.out.println("pop:"+redis.lPop("testList"));
		System.out.println("list size:"+redis.lsize("testList"));
		//index
		//list index 0=null index 1=DemoVo [id=0, no=null, name=null, sex=null, age=null, nativePlace=null, enable=null] index 10=null
		System.out.println("list index 0="+ObjectUtil.unserialize(redis.lIndex("testList", 0))+ " index 1="+ObjectUtil.unserialize(redis.lIndex("testList", 1))+
					" index 10="+ObjectUtil.unserialize(redis.lIndex("testList", 10)));
		//lrange
		System.out.println("list range...testList,0,1");
		List<String> listRange=redis.lRange("testList", 0, 1);
		System.out.println("list range size:"+listRange.size());
		System.out.println("list size:"+redis.lsize("testList"));
		//lremove
		System.out.println("list remove 't2'... ");
		System.out.println("total remove count:"+redis.lremove("testList", 0, "t2"));
		System.out.println("list size:"+redis.lsize("testList"));
		//ltrim
		String listTrim=redis.ltrim("testList", 0, 2);
		System.out.println("list trim 0,2 :"+listTrim);//list trim 0,2 :OK
		System.out.println("list size:"+redis.lsize("testList"));
		//remove
		redis.remove("testList");
		//get
		System.out.println("get size after remove:"+redis.lsize("testList"));
		
	}
	
	
	public void hashTest(){
		RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
		redis.hSet("testMap", "m1", ObjectUtil.serialize(new DemoVo()));
		redis.hSet("testMap","m2", "www.sss.com");
		System.out.println("hget:"+redis.hGet("testMap", "m1"));
		Map<String,String> subMap=new HashMap<String,String>();
		subMap.put("sm1", "sm[1]");
		subMap.put("sm2", "sm[2]");
		redis.hmSet("testMap", subMap);
		System.out.println("hgetAll:"+redis.hGetAll("testMap").toString());
		redis.hDel("testMap", "m2");
		System.out.println("after hdel 'm2' hgetAll:"+redis.hGetAll("testMap").toString());
		//List<String> keysInMap=new ArrayList<String>();
		//keysInMap.add("sm1");keysInMap.add("sm2");
		List<String> list=redis.hMultiGet("testMap", "sm1", "sm2");
		System.out.println("hMultiGet result size:"+list.size());
		System.out.println("Hexists:(m1)"+redis.hExists("testMap", "m1")+" (m2)"+redis.hExists("testMap", "m2"));
		redis.remove("testMap");
	}
	
	public void incTest(){
		RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
		redis.set("incVal", String.valueOf(1));
		System.out.println(redis.get("incVal"));
		redis.increment("incVal", 1);
		System.out.println(redis.get("incVal"));
		redis.remove("incVal");
	}
	
}
