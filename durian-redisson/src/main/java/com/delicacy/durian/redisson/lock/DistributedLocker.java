package com.delicacy.durian.redisson.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLocker {

	void lock(String entityId);

	void unlock(String entityId);

	void lock(String entityId, int leaseTime);
	
	void lock(String entityId, TimeUnit unit, int leaseTime);

	boolean tryLock(String entityId, int leaseTime);

	boolean tryLock(String entityId, int waitTime, int leaseTime);

	boolean tryLock(String entityId, TimeUnit unit, int waitTime, int leaseTime);
}
