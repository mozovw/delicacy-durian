package com.delicacy.durian.redisson.lock;


import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RedissonDistributedLocker implements DistributedLocker {
	
	private RedissonClient redissonClient;

	@Override
	public void lock(String entityId) {
		RLock lock = redissonClient.getLock(entityId);
		lock.lock();
	}

	@Override
	public void unlock(String entityId) {
		if (redissonClient.isShutdown())return;
		RLock lock = redissonClient.getLock(entityId);
		if (lock.isLocked())
			if(lock.isHeldByCurrentThread())
		lock.unlock();
	}

	@Override
	public void lock(String entityId, int leaseTime) {
		lock(entityId,TimeUnit.SECONDS,leaseTime);

	}
	
	@Override
	public void lock(String entityId, TimeUnit unit ,int leaseTime) {
		RLock lock = redissonClient.getLock(entityId);
		lock.lock(leaseTime, unit);
	}

	@Override
	public boolean tryLock(String entityId, int leaseTime) {
		return tryLock(entityId,leaseTime);
	}

	@Override
	public boolean tryLock(String entityId, int waitTime, int leaseTime) {
		return tryLock(entityId,TimeUnit.SECONDS,waitTime,leaseTime);
	}

	@Override
	public boolean tryLock(String entityId, TimeUnit unit, int waitTime, int leaseTime) {
		RLock lock = redissonClient.getLock(entityId);
		try {
			return lock.tryLock(waitTime,leaseTime,unit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}
}
