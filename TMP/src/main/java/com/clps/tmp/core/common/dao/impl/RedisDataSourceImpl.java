package com.clps.tmp.core.common.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.clps.tmp.core.common.dao.RedisDataSource;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
	@Autowired
    private ShardedJedisPool shardedJedisPool;
	protected Logger log=Logger.getLogger(RedisDataSourceImpl.class);
	
	@Override
	public ShardedJedis getRedisClient() {
		try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClient Error ", e);
        }
        return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
	}

}
