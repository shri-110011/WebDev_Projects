package com.shri.ecommercebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(30);
		jedisPoolConfig.setMaxIdle(20);
		jedisPoolConfig.setMinIdle(10);
		
		return new JedisPool(jedisPoolConfig, "localhost", 6379);
	}
	
	@Bean
	public Jedis jedis(JedisPool jedisPool) {
		return jedisPool.getResource();
	}

}
