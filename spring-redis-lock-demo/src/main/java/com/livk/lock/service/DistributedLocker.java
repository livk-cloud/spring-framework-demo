package com.livk.lock.service;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * DistributedLocker
 * </p>
 *
 * @author livk
 * @date 2021/12/7
 */
public interface DistributedLocker {

    RLock lock(String lockKey);


    RLock lock(String lockKey, int timeout);


    RLock lock(String lockKey, TimeUnit unit, int timeout);


    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);


    void unlock(String lockKey);


    void unlock(RLock lock);
}
