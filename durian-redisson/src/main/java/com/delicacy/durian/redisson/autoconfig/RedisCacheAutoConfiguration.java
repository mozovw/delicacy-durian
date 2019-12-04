package com.delicacy.durian.redisson.autoconfig;


import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.delicacy.durian.redisson.cache.DistributedCache;
import com.delicacy.durian.redisson.cache.SpringDataRedisCache;
import com.delicacy.durian.redisson.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

@Configuration
@ConditionalOnClass({ JedisConnection.class, RedisOperations.class, Jedis.class })
public class RedisCacheAutoConfiguration {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Bean
	public DistributedCache redisCache() {
		// 设置默认序列化方式为json序列化
		redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer()); // 创建只输出初始值被改变的属性到Json字符串的Mapper,
																					// 最节约的存储方式
		SpringDataRedisCache redisCache = new SpringDataRedisCache();
		redisCache.setRedisTemplate(redisTemplate); // 填充实例 放入spring上下文
		RedisCacheUtil.setRedisCache(redisCache); // 填充工具类 代理
		return redisCache;
	}
}