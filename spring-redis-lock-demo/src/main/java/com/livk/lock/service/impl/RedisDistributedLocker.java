package com.livk.lock.service.impl;

import com.livk.lock.service.DistributedLocker;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * RedisDistributedLocker
 * </p>
 *
 * @author livk
 * @date 2021/12/7
 */
@Component
@RequiredArgsConstructor
public class RedisDistributedLocker implements DistributedLocker {

    private final RedissonClient redissonClient;

    @Override
    public RLock lock(String lockKey) {
        return this.lock(lockKey, null, -1);
    }

    @Override
    public RLock lock(String lockKey, int timeout) {
        return this.lock(lockKey, TimeUnit.SECONDS, timeout);
    }

    @Override
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        var lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        var lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        this.unlock(redissonClient.getLock(lockKey));
    }

    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }
}
