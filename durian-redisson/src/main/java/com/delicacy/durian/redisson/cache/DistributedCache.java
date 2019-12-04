package com.delicacy.durian.redisson.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DistributedCache {

	boolean expire(final String key, final int expire);

	long decrByValue(final String key, final long value);

	long decr(final String key);

	long incrByValue(final String key, final long value);

	long incr(final String key);

	String get(final String key);

	Integer getInt(final String key);

	Float getFloat(final String key);

	void set(final String key, final Object value);

	void set(final String key, final Object value, final int expire);

	boolean expireAt(final String key, final Date expire);

	void valueMultiSet(final Map<String, Object> map);

	List<String> batchGet(final Collection<String> keys);

	List<String> batchGet(final String[] keys);

	List<String> batchGetLike(final String pattern);

	Map<String, String> batchGetKV(final Collection<String> keys);

	Map<String, String> batchGetKV(final String[] keys);

	Map<String, String> batchGetKVLike(final String pattern);

	void batchDelLike(final String pattern);

	void batchDel(final Collection<String> keys);

	void del(final String key);

	boolean setObject2Bytes(final String key, final Object value);

	boolean setObject2Json(final String key, final Object value);

	<T> T getBytesObject(final String key);

	<T> T getJsonObject(final String key, final Class<T> clazz);

	void hashPut(String key, String hashKey, Object value);

	void hashPutAll(String key, Map<String, Object> hash);

	Object hashGet(String key, Object hashKey);

	Map<String, Object> hashGetAll(String key);

	List<?> hashMultiGet(String key, Collection<Object> hashKeys);

	void sendMessage(String topic, String message);

	boolean hasKey(String key);
}