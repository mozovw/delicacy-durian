package com.delicacy.durian.redisson.util;


import com.delicacy.durian.redisson.lock.DistributedLocker;

import java.util.concurrent.TimeUnit;


/**
 * redis分布式锁帮助类
 * @author yangzhilong
 *
 */
public class RedissLockUtil {
	private static DistributedLocker redissLock;
	
	public static void setLocker(DistributedLocker locker) {
		redissLock = locker;
	}
	
	public static void lock(String lockKey) {
		redissLock.lock(lockKey);
	}

	public static void unlock(String lockKey) {
		redissLock.unlock(lockKey);
	}

	/**
	 * 带超时的锁
	 * @param lockKey
	 * @param leaseTime 超时时间   单位：秒
	 */
	public static void lock(String lockKey, int leaseTime) {
		lock(lockKey,TimeUnit.SECONDS, leaseTime);
	}
	
	/**
	 * 带超时的锁
	 * @param lockKey
	 * @param unit 时间单位
	 * @param timeout 超时时间
	 */
	public static void lock(String lockKey, TimeUnit unit ,int timeout) {
		redissLock.lock(lockKey, unit, timeout);
	}

	/**
	 * 尝试获取锁
	 * @param lockKey
	 * @param unit
	 * @param waitTime
	 * @param leaseTime
	 * @return
	 */
	public static boolean tryLock(String lockKey, TimeUnit unit ,int waitTime,int leaseTime) {
		return redissLock.tryLock(lockKey, unit, waitTime,leaseTime);
	}

	/**
	 * 尝试获取锁
	 * @param lockKey
	 * @param waitTime
	 * @param leaseTime
	 * @return
	 */
	public static boolean tryLock(String lockKey,int waitTime,int leaseTime) {
		return tryLock(lockKey, TimeUnit.SECONDS , waitTime, leaseTime);
	}

	/**
	 * 尝试获取锁
	 * @param lockKey
	 * @param leaseTime
	 * @return
	 */
	public static boolean tryLock(String lockKey,int leaseTime) {
		return tryLock(lockKey , leaseTime, leaseTime);
	}
}
