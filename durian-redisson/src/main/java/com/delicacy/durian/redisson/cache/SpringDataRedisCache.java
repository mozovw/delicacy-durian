package com.delicacy.durian.redisson.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SpringDataRedisCache implements DistributedCache {
	private StringRedisTemplate redisTemplate;

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public StringRedisTemplate getRedisTemplate() {
		return this.redisTemplate;
	}

	@Override
	public long decrByValue(String key, long value) {
		return redisTemplate.opsForValue().increment(key, -value);
	}

	@Override
	public long decr(String key) {
		return redisTemplate.opsForValue().increment(key, -1);
	}

	@Override
	public long incrByValue(String key, long value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	@Override
	public long incr(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}

	@Override
	public void valueMultiSet(final Map<String, Object> map) {
		Map<String, String> varsMap = objectMapToStringMap(map);
		redisTemplate.opsForValue().multiSet(varsMap);
	}

	private Map<String, String> objectMapToStringMap(final Map<String, Object> map) {
		Set<Map.Entry<String, Object>> entrySet = map.entrySet();
		Map<String, String> varsMap = new HashMap<>();
		if (entrySet != null) {
			Iterator<Map.Entry<String, Object>> iter = entrySet.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = iter.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value != null) {
					varsMap.put(key, value.toString());
				}
			}
		}
		return varsMap;
	}

	@Override
	public List<String> batchGet(final Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public List<String> batchGet(final String[] keys) {
		List<String> listKeys = new ArrayList<>();
		Collections.addAll(listKeys, keys);
		return redisTemplate.opsForValue().multiGet(listKeys);
	}

	@Override
	public List<String> batchGetLike(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Map<String, String> batchGetKV(Collection<String> keys) {
		Map<String, String> retMap = new HashMap<>();
		List<String> listKeys = new ArrayList<>(keys);
		if (keys != null) {
			List<String> retList = batchGet(keys);
			int retSize = retList.size();
			for (int i = 0; i < retSize; i++) {
				String key = listKeys.get(i);
				String value = retList.get(i);
				retMap.put(key, value);
			}
		}
		return retMap;
	}

	@Override
	public Map<String, String> batchGetKV(final String[] keys) {
		List<String> listKeys = new ArrayList<>();
		Collections.addAll(listKeys, keys);
		return batchGetKV(listKeys);
	}

	@Override
	public Map<String, String> batchGetKVLike(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		return batchGetKV(keys);
	}

	@Override
	public void batchDelLike(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		batchDel(keys);
	}

	@Override
	public void batchDel(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public void del(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public boolean setObject2Bytes(final String key, final Object value) {
		final byte[] bytesObject = SerializationUtils.serialize((Serializable) value);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				conn.set(redisTemplate.getStringSerializer().serialize(key), bytesObject);
				return true;
			}
		}, false, true);
		return result;
	}

	@Override
	public boolean setObject2Json(final String key, final Object value) {
		String jsonObject = JSON.toJSONString(value);
		// final String jsonObject = JsonMapper.nonDefaultMapper().toJson(value);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				redisTemplate.opsForValue().set(key, jsonObject);
				return true;
			}
		}, false, true);
		return result;
	}

	@Override
	public <T> T getJsonObject(final String key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection conn) throws DataAccessException {
				T value = null;
				String valueString = redisTemplate.opsForValue().get(key);
				// value = JsonMapper.nonDefaultMapper().fromJson(valueString, clazz);
				value = JSON.parseObject(valueString, clazz);
				return value;
			}
		});
	}

	@Override
	public <T> T getBytesObject(final String key) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T doInRedis(RedisConnection conn) throws DataAccessException {
				T value = null;

				byte[] keyBytes = redisTemplate.getStringSerializer().serialize(key);
				byte[] valueBytes = conn.get(keyBytes);

				if (valueBytes != null)
					value = (T) SerializationUtils.deserialize(valueBytes);

				return value;
			}
		});
	}

	@Override
	public void hashPut(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	@Override
	public void hashPutAll(String key, Map<String, Object> hash) {
		redisTemplate.opsForHash().putAll(key, hash);
	}

	@Override
	public Object hashGet(final String key, final Object hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public Map<String, Object> hashGetAll(final String key) {
		return redisTemplate.execute(new RedisCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInRedis(RedisConnection conn) throws DataAccessException {
				byte[] bytesKey = redisTemplate.getStringSerializer().serialize(key);
				Map<byte[], byte[]> bytesMap = conn.hGetAll(bytesKey);
				Map<String, Object> retMap = new HashMap<>();
				if (bytesMap != null) {
					for (Iterator<byte[]> it = bytesMap.keySet().iterator(); it.hasNext();) {
						byte[] btKey = it.next();
						byte[] byValue = bytesMap.get(btKey);
						retMap.put(redisTemplate.getStringSerializer().deserialize(btKey),
								redisTemplate.getStringSerializer().deserialize(byValue));
					}
				}
				return retMap;
			}
		});
	}

	@Override
	public List<?> hashMultiGet(String key, Collection<Object> hashKeys) {
		return redisTemplate.opsForHash().multiGet(key, hashKeys);
	}

	@Override
	public void set(final String key, final Object value) {
		redisTemplate.opsForValue().set(key, value == null ? null : value.toString());
	}

	@Override
	public void set(final String key, final Object value, final int expire) {
		redisTemplate.opsForValue().set(key, String.valueOf(value), expire, TimeUnit.SECONDS);
	}

	@Override
	public boolean expireAt(final String key, final Date expire) {
		return redisTemplate.expireAt(key, expire);
	}

	@Override
	public boolean expire(final String key, final int expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public Integer getInt(String key) {
		String value = redisTemplate.opsForValue().get(key);
		return Integer.valueOf(value);
	}

	@Override
	public Float getFloat(String key) {
		String value = redisTemplate.opsForValue().get(key);
		return Float.valueOf(value);
	}

	@Override
	public void sendMessage(String topic, String message) {
		redisTemplate.convertAndSend(topic, message);
	}

	@Override
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}
}