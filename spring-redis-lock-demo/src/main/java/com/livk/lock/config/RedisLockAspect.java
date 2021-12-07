package com.livk.lock.config;

import com.livk.lock.annotation.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * RedisLockAspect
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

    private final RedisLockRegistry redisLockRegistry;

    @Around("@annotation(redisLock)")
    public Object redisLock(ProceedingJoinPoint joinPoint, RedisLock redisLock) {
        Object output = null;
        try {
            var signature = (MethodSignature) joinPoint.getSignature();
            var method = signature.getMethod();
            var args = joinPoint.getArgs();
            var lockKey = parseSpel(redisLock.lockKey(), method, args, null);
            var lock = redisLockRegistry.obtain(lockKey);
            try {
                var ifLock = lock.tryLock(12L, TimeUnit.SECONDS);
                log.info("线程[{}]是否获取到了锁:{}", Thread.currentThread().getName(), ifLock);

                if (ifLock) {
                    output = joinPoint.proceed();
                } else {
                    args[1] = "已有任务正在执行，请勿重复请求";
                    output = joinPoint.proceed(args);
                    log.info("线程[{}]未获取到锁，目前锁详情信息为：{}", Thread.currentThread().getName(), lock);
                }
            } catch (Exception e) {
                log.error("执行核心奖励扫描时出错:{}", e.getMessage());
            } finally {
                log.info("尝试解锁[{}]", lockKey);
                try {
                    lock.unlock();
                    log.info("[{}]解锁成功", lockKey);
                } catch (Exception e) {
                    log.error("解锁dealAction出错:{}", e.getMessage());
                }
            }
        } catch (Throwable e) {
            log.error("aop redis distributed lock error:{}", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return output;
    }

    public static <T> Object parseSpel(String key, Method method, Object[] args, Class<T> targetClass) {
        //创建解析器
        var parser = new SpelExpressionParser();
        var parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        var parameterNames = parameterNameDiscoverer.getParameterNames(method);
        Assert.notNull(parameterNames, "params is null!");
        // 构造上下文
        var context = new StandardEvaluationContext();
        if (args.length == parameterNames.length) {
            for (var i = 0; i < args.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        var expression = parser.parseExpression(key);
        return Objects.nonNull(targetClass) ? expression.getValue(context, targetClass) : expression.getValue(context);
    }
}
