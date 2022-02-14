//package com.livk.lock.util;
//
//import com.livk.common.SpringContextHolder;
//import com.livk.lock.service.DistributedLocker;
//import org.redisson.api.RLock;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * <p>
// * RedisLockUtils
// * </p>
// *
// * @author livk
// * @date 2021 /12/7
// */
//public class RedisLockUtils {
//
//    private static final DistributedLocker DISTRIBUTED_LOCKER = SpringContextHolder.getBean(DistributedLocker.class);
//
//    /**
//     * 加锁
//     *
//     * @param lockKey the lock key
//     * @return r lock
//     */
//    public static RLock lock(String lockKey) {
//
//        return DISTRIBUTED_LOCKER.lock(lockKey);
//
//    }
//
//
//    /**
//     * 释放锁
//     *
//     * @param lockKey the lock key
//     */
//    public static void unlock(String lockKey) {
//
//        DISTRIBUTED_LOCKER.unlock(lockKey);
//
//    }
//
//
//    /**
//     * 释放锁
//     *
//     * @param lock the lock
//     */
//    public static void unlock(RLock lock) {
//
//        DISTRIBUTED_LOCKER.unlock(lock);
//
//    }
//
//
//    /**
//     * 带超时的锁
//     *
//     * @param lockKey the lock key
//     * @param timeout 超时时间   单位：秒
//     * @return the r lock
//     */
//    public static RLock lock(String lockKey, int timeout) {
//
//        return DISTRIBUTED_LOCKER.lock(lockKey, timeout);
//
//    }
//
//
//    /**
//     * 带超时的锁
//     *
//     * @param lockKey the lock key
//     * @param timeout 超时时间
//     * @param unit    时间单位
//     * @return the r lock
//     */
//    public static RLock lock(String lockKey, int timeout, TimeUnit unit) {
//
//        return DISTRIBUTED_LOCKER.lock(lockKey, unit, timeout);
//
//    }
//
//
//    /**
//     * 尝试获取锁
//     *
//     * @param lockKey   the lock key
//     * @param waitTime  最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return boolean
//     */
//    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
//
//        return DISTRIBUTED_LOCKER.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
//
//    }
//
//
//    /**
//     * 尝试获取锁
//     *
//     * @param lockKey   the lock key
//     * @param unit      时间单位
//     * @param waitTime  最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return boolean
//     */
//    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
//
//        return DISTRIBUTED_LOCKER.tryLock(lockKey, unit, waitTime, leaseTime);
//
//    }
//
//
////    /**
////     * 获取计数器
////     *
////     * @param name
////     * @return
////     */
////
////    public static RCountDownLatch getCountDownLatch(String name) {
////
////        return DISTRIBUTED_LOCKER.getCountDownLatch(name);
////
////    }
////
////
////    /**
////     * 获取信号量
////     *
////     * @param name
////     * @return
////     */
////
////    public static RSemaphore getSemaphore(String name) {
////
////        return DISTRIBUTED_LOCKER.getSemaphore(name);
////
////    }
//
//}
