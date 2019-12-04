package com.delicacy.durian.redisson.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.delicacy.durian.redisson.cache.SpringDataRedisCache;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;

import java.util.*;

/**
 * 缓存 工具类 代理类
 * 
 * Title: RedisCacheUtils<br>
 * Description: RedisCacheUtils<br>
 * CreateDate:2017年9月6日 下午4:46:54
 * 
 * @author woody
 */
public class RedisCacheUtil {
	private static SpringDataRedisCache cache;

	public static void setRedisCache(SpringDataRedisCache redisCache) {
		cache = redisCache;
	}

	public static long decrByValue(String key, long value) {
		return cache.decrByValue(key, value);
	}

	public static long decr(String key) {
		return cache.decr(key);
	}

	public static long incrByValue(String key, long value) {
		return cache.incrByValue(key, value);
	}

	public static long incr(String key) {
		return cache.incr(key);
	}

	public static void valueMultiSet(final Map<String, Object> map) {
		cache.valueMultiSet(map);
	}

	public static List<String> batchGet(final Collection<String> keys) {
		return cache.batchGet(keys);
	}

	public static List<String> batchGet(final String[] keys) {
		return cache.batchGet(keys);
	}

	public static List<String> batchGetLike(final String pattern) {
		return cache.batchGetLike(pattern);
	}

	public static Map<String, String> batchGetKV(Collection<String> keys) {
		return cache.batchGetKV(keys);
	}

	public static Map<String, String> batchGetKV(final String[] keys) {
		return cache.batchGetKV(keys);
	}

	public static Map<String, String> batchGetKVLike(String pattern) {
		return cache.batchGetKVLike(pattern);
	}

	public static void batchDelLike(String pattern) {
		cache.batchDelLike(pattern);
	}

	public static void batchDel(Collection<String> keys) {
		cache.batchDel(keys);
	}

	public static void del(String key) {
		cache.del(key);
	}

	public static boolean setObject2Bytes(final String key, final Object value) {
		return cache.setObject2Bytes(key, value);
	}

	public static boolean setObject2Json(final String key, final Object value) {
		return cache.setObject2Json(key, value);
	}

	public static <T> T getJsonObject(final String key, final Class<T> clazz) {
		return cache.getJsonObject(key, clazz);
	}

	public static <T> T getBytesObject(final String key) {
		return cache.getBytesObject(key);
	}

	public static void hashPut(String key, String hashKey, Object value) {
		cache.hashPut(key, hashKey, value);
	}

	public static void hashPutAll(String key, Map<String, Object> hash) {
		cache.hashPutAll(key, hash);
	}

	public static Object hashGet(final String key, final Object hashKey) {
		return cache.hashGet(key, hashKey);
	}

	public static Map<String, Object> hashGetAll(final String key) {
		return cache.hashGetAll(key);
	}

	public static List<?> hashMultiGet(String key, Collection<Object> hashKeys) {
		return cache.hashMultiGet(key, hashKeys);
	}

	public static void set(final String key, final Object value) {
		cache.set(key, value);
	}

	public static void set(final String key, final Object value, final int expire) {
		cache.set(key, value, expire);
	}

	public static boolean expireAt(final String key, final Date expire) {
		return cache.expireAt(key, expire);
	}

	public static boolean expire(final String key, final int expire) {
		return cache.expire(key, expire);
	}

	public static String get(String key) {
		return cache.get(key);
	}

	public static Integer getInt(String key) {
		return cache.getInt(key);
	}

	public static Float getFloat(String key) {
		return cache.getFloat(key);
	}

	public static void sendMessage(String topic, String message) {
		cache.sendMessage(topic, message);
	}

	public static boolean hasKey(String key) {
		return cache.hasKey(key);
	}

	public static <T> List<T> getList(String key, Class<T> clazz) {
		return JSONObject.parseArray(cache.get(key), clazz);
	}

	public static <K, V> ListOperations<String, String> opsForList() {
		return cache.getRedisTemplate().opsForList();
	}

	public static Map getMap(String key) {
		return JSONObject.parseObject(cache.get(key), new TypeReference<Map<String, Object>>() {
		});
	}

	public static <K, V> SetOperations<String, String> opsForSet() {
		return cache.getRedisTemplate().opsForSet();
	}

	public static Set<String> difference(String key, String... compareKey) {
		List<String> list = new ArrayList<>(compareKey.length);
		Collections.addAll(list, compareKey);
		return opsForSet().difference(key, list);
	}

	public static Long differenceAndStore(String key, String storeKey, String... compareKey) {
		List<String> list = new ArrayList<>(compareKey.length);
		Collections.addAll(list, compareKey);
		return opsForSet().differenceAndStore(key, list, storeKey);
	}
}