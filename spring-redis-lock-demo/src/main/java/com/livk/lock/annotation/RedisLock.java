package com.livk.lock.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * RedisLock
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    /**
     * 可使用SpEL传方法参数
     */
    String value() default "";

    /**
     * redis锁的key值
     */
    String lockKey() default "";
}
