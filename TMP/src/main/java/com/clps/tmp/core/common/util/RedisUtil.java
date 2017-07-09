package com.clps.tmp.core.common.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;

import com.clps.tmp.core.common.dao.RedisDataSource;
/**
 * Redis工具类<br><br>
 * <b>调用此类的方法，请通过以下方法</b><br>
 * RedisUtil redis=(RedisUtil) SpringContextUtil.getBean("redisUtil");
 * @author Seven 2016年5月31日
 */
public class RedisUtil {
	protected Logger log=Logger.getLogger(RedisUtil.class);
	@Autowired
    private RedisDataSource redisDataSource;
	
	public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }
	
	/**
	 * 判断缓存中是否有对应key元素
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		boolean bool=false;
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		try{
			bool=shardedJedis.exists(key);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally{
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return bool;
	}
	
	/**
	 * 删除对应key元素
	 * @param key
	 */
	public void remove(final String key) {
		if(exists(key)){
			boolean broken = false;
			ShardedJedis shardedJedis = redisDataSource.getRedisClient();
			try{
				shardedJedis.del(key);
			}catch(Exception e){
				log.error(e.toString());
				broken = true;
			}finally {
	            redisDataSource.returnResource(shardedJedis,broken);
	        }
		}
	}
	
	/**
	 * 排序
	 * @param key
	 */
	public List<String> sort(final String key) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		List<String> list=null;
		try{
			list=shardedJedis.sort(key);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return list;
	}
	
	/**
	 * 排序
	 * @param key
	 * @param sortingParameters
	 */
	public List<String> sort(final String key, final SortingParams sortingParameters) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		List<String> list=null;
		try{
			list=shardedJedis.sort(key, sortingParameters);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return list;
	}
	
	/**
	 * 设置过期时长<br>
	 * @param key
	 * @param seconds
	 * @return 
	 */
	public long expire(final String key, final int seconds) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long l=-1;
		try{
			l=shardedJedis.expire(key, seconds);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return l;
	}
	
	/**
	 * 设置过期时长<br>
	 * @param key
	 * @param unixTime
	 * @return 
	 */
	public long expire(final String key, final long unixTime) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long l=-1;
		try{
			l=shardedJedis.expireAt(key, unixTime);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return l;
	}
	
	
	//---------------------------------------------------------------String--------------------------------------------------------
	/**
	 * （String）读取缓存对应元素
	 * @param key
	 * @return 返回对应值；不存在返回null；出错返回null
	 */
	public String get(final String key) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.get(key);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （String）将给定 key 的值设为 value ，并返回 key 的旧值<br>
	 *  当 key 存在但不是字符串类型时，返回一个错误
	 * @param key
	 * @param newVal
	 * @return 旧值;出错返回null
	 */
	public String getSet(final String key, final String newVal) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.getSet(key, newVal);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （String）写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(final String key, final String value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.set(key, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （String）写入缓存并设置过期时间
	 * @param key
	 * @param value
	 * @param seconds 秒
	 * @return
	 */
	public String set(final String key, final String value, final int seconds) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.setex(key, seconds, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （String）追加写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public long append(final String key, final String value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.append(key, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （String）将 key 中储存的数字值做加减,如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行运算操作
	 * @param key
	 * @param value
	 * @return 加上 increment 之后， key 的值; 错误返回-1
	 */
	public double increment(final String key, final double value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		double obj=-1;
		try{
			obj=shardedJedis.incrByFloat(key, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	
	//---------------------------------------------------------------List----------------------------------------------------------
	/**
	 * （List）返回列表的长度<br>
	 * @param listName
	 * @return 如果列表不存在，返回 0；如果listName不是列表类型，返回 -1
	 */
	public long lsize(final String listName) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.llen(listName);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）返回列表中，下标为 index(从0开始) 的元素 
	 * @param listName
	 * @param index
	 * @return 如果 key 不是列表类型，返回null；index 参数的值不在列表的区间范围内，返回null
	 */
	public String lIndex(final String listName, final long index) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.lindex(listName, index);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）移除并返回列表头元素
	 * @param listName
	 * @return 当list不存在时，返回null
	 */
	public String lPop(final String listName) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.lpop(listName);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）移除并返回列表尾元素
	 * @param listName
	 * @return 当list不存在时，返回null
	 */
	public String RPop(final String listName) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.rpop(listName);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）返回列表中指定区间内的元素<br>
	 *  如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表<br>
	 *  如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end<br>
	 *  下标(index)参数 start 和 stop 都以 0 表示列表的第一个元素,以 -1 表示列表的最后一个元素,-2 表示列表的倒数第二个元素，以此类推
	 * @param listName
	 * @param start 
	 * @param end
	 * @return
	 */
	public List<String> lRange(final String listName,final long start,final long end) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		List<String> obj=null;
		try{
			obj=shardedJedis.lrange(listName, start, end);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除<br>
	 *  下标(index)参数 start 和 stop 都以 0 表示列表的第一个元素,以 -1 表示列表的最后一个元素,-2 表示列表的倒数第二个元素，以此类推<br>
	 *  start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start > stop ,返回一个空列表<br>
	 *  如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end <br>
	 * @param listName
	 * @param start
	 * @param end
	 * @return 
	 */
	public String ltrim(final String listName, final long start, final long end) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.ltrim(listName, start, end);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）将值插入到列表表头<br>
	 *  如果列表不存在，一个空列表会被创建并执行 LPUSH 操作<br>
	 * @param listName
	 * @param values
	 * @return 返回列表的长度; 当listName存在但不是列表类型时，返回-1
	 */
	public long lPush(final String listName, final String... values) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.lpush(listName, values);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）将值插入到列表表尾<br>
	 *  如果列表不存在，一个空列表会被创建并执行 RPUSH 操作<br>
	 * @param listName
	 * @param values
	 * @return 返回列表的长度; 当listName存在但不是列表类型时，返回-1
	 */
	public long RPush(final String listName, final String... values) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.rpush(listName, values);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （List）根据参数 count 的值，移除列表中与参数 value 相等的元素<br>
	 *  ★ count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count<br>
	 *  ★ count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值<br>
	 *  ★ count = 0 : 移除表中所有与 value 相等的值
	 * @param listName
	 * @param count
	 * @param value
	 * @return 被移除元素的数量；若为空表，返回0
	 */
	public long lremove(final String listName, final long count, final String value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.lrem(listName, count, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	
	//---------------------------------------------------------------Hash----------------------------------------------------------
	/**
	 * （Hash）查看哈希表 mapName 中，给定域 keyInMap 是否存在<br>
	 * @param mapName
	 * @param keyInMap
	 * @return 
	 */
	public boolean hExists(final String mapName, final String keyInMap) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		boolean bool=false;
		try{
			bool=shardedJedis.hexists(mapName, keyInMap);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return bool;
	}
	
	/**
	 * （Hash）读取缓存对应元素<br>
	 * @param mapName
	 * @param keyInMap
	 * @return 
	 */
	public String hGet(final String mapName, final String keyInMap) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.hget(mapName, keyInMap);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）读取缓存对应元素<br>
	 * @param mapName
	 * @param keyInMap
	 * @return 
	 */
	public List<String> hMultiGet(final String mapName, final String... keyInMap) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		List<String> obj=null;
		try{
			obj=shardedJedis.hmget(mapName, keyInMap);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）读取缓存对应所有元素
	 * @param mapName
	 */
	public Map<String,String> hGetAll(final String mapName) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		Map<String,String> obj=null;
		try{
			obj=shardedJedis.hgetAll(mapName);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）写入缓存<br>
	 * @param mapName
	 * @param keyInMap
	 * @param value
	 * @return
	 */
	public long hSet(final String mapName, final String keyInMap, final String value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.hset(mapName, keyInMap, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）为哈希表 mapName 中的域 keyInMap 的值加上增量 value <br>
	 * @param mapName
	 * @param keyInMap
	 * @param value
	 * @return 执行命令后，keyInMap的值；错误返回-1
	 */
	public double hIncrByFloat(final String mapName, final String keyInMap, final double value) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		double obj=-1;
		try{
			obj=shardedJedis.hincrByFloat(mapName, keyInMap, value);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）写入缓存<br>
	 * @param mapName
	 * @param subMap
	 * @return
	 */
	public String hmSet(final String mapName, final Map<String,String> subMap) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String obj=null;
		try{
			obj=shardedJedis.hmset(mapName, subMap);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
	
	/**
	 * （Hash）删除哈希表中的一个或多个指定域，不存在的域将被忽略<br>
	 * @param mapName
	 * @param keysInMap
	 * @return
	 */
	public long hDel(final String mapName, final String ... keysInMap) {
		boolean broken = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long obj=-1;
		try{
			obj=shardedJedis.hdel(mapName, keysInMap);
		}catch(Exception e){
			log.error(e.toString());
			broken = true;
		}finally {
            redisDataSource.returnResource(shardedJedis,broken);
        }
		return obj;
	}
}
