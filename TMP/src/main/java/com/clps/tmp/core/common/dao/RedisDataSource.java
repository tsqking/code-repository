package com.clps.tmp.core.common.dao;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	/**
	 * 取得redis的客户端
	 * 2016年5月31日 Seven
	 */
	public abstract ShardedJedis getRedisClient();
	/**
	 * 将资源返还给pool
	 * 2016年5月31日 Seven
	 */
    public void returnResource(ShardedJedis shardedJedis);
    /**
     * 出现异常后，将资源返还给pool 
     * 2016年5月31日 Seven
     */
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
