package com.livk.aop.proxy;

import com.livk.aop.proxy.constant.ProxyConstant;
import com.livk.aop.proxy.mode.ProxyMode;
import com.livk.aop.proxy.mode.ScanMode;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * <p>
 * AbstractAutoProxy
 * </p>
 *
 * @author livk
 * @date 2022/4/2
 */
@Slf4j
public abstract class AbstractAutoProxy extends AbstractAutoProxyCreator {

    @Serial
    private static final long serialVersionUID = 6827218905375993727L;

    // Bean名称和Bean对象关联
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    // Spring容器中哪些接口或者类需要被代理
    private final Map<String, Boolean> proxyMap = new ConcurrentHashMap<>();

    // Spring容器中哪些类是类代理，哪些类是通过它的接口做代理
    private final Map<String, Boolean> proxyTargetClassMap = new ConcurrentHashMap<>();

    // 扫描目录，如果不指定，则扫描全局。两种方式运行结果没区别，只是指定扫描目录加快扫描速度，同时可以减少缓存量
    private final String[] scanPackages;

    // 通过注解确定代理
    private final ProxyMode proxyMode;

    // 扫描注解后的处理
    private final ScanMode scanMode;

    public AbstractAutoProxy(String[] scanPackages, ProxyMode proxyMode, ScanMode scanMode, boolean exposeProxy) {
        this.scanPackages = scanPackages;
        this.setExposeProxy(exposeProxy);
        this.proxyMode = proxyMode;
        this.scanMode = scanMode;
        String packages = String.join(ProxyConstant.SEPARATOR, scanPackages);
        log.info("------------- Matrix Aop Information ------------");
        log.info("Auto scan proxy class is {}", getClass().getCanonicalName());
        log.info("Scan packages is {}", ArrayUtils.isNotEmpty(scanPackages) ? packages : "not set");
        log.info("Proxy mode is {}", proxyMode);
        log.info("Scan mode is {}", scanMode);
        log.info("Expose proxy is {}", exposeProxy);
        log.info("-------------------------------------------------");
        Class<? extends MethodInterceptor>[] commonInterceptors = getCommonInterceptors();
        String[] commonInterceptorNames = getCommonInterceptorNames();
        String[] interceptorNames = ArrayUtils.addAll(commonInterceptorNames, convertInterceptorNames(commonInterceptors));
        setInterceptorNames(interceptorNames);
    }

    private String[] convertInterceptorNames(Class<? extends MethodInterceptor>[] commonInterceptorClasses) {
        if (ArrayUtils.isNotEmpty(commonInterceptorClasses)) {
            return Arrays.stream(commonInterceptorClasses)
                    .map(commonInterceptorClass -> AnnotationUtils.findAnnotation(commonInterceptorClass, Component.class))
                    .filter(Objects::nonNull)
                    .map(Component::value)
                    .toArray(String[]::new);
        }
        return new String[0];
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        if (scanPackagesEnabled()) {
            if (!scanPackagesContained(beanClass)) {
                return DO_NOT_PROXY;
            }
        }

        Object bean = beanMap.get(beanName);
        // 获取最终目标类
        Class<?> targetClass = bean != null ? AopProxyUtils.ultimateTargetClass(bean) : beanClass;

        if (!targetClass.isInterface()) {
            for (Class<?> targetClassInterface : targetClass.getInterfaces()) {

            }
        }
        return new Object[0];
    }

    protected Object[] scanAndProxyForTarget(Class<?> targetClass, String beanName, boolean proxy) {
        String targetClassName = targetClass.getCanonicalName();
        return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
    }

    protected Object[] getInterceptors(Class<?> targetClass){
        MethodInterceptor[] interceptors = getAdditionalInterceptors(targetClass);
        if (ArrayUtils.isNotEmpty(interceptors)){
            return interceptors;
        }
        Class<? extends MethodInterceptor>[] commonInterceptorClasses = getCommonInterceptors();
        String[] commonInterceptorNames = getCommonInterceptorNames();
        if (ArrayUtils.isNotEmpty(commonInterceptorClasses) || ArrayUtils.isNotEmpty(commonInterceptorNames)) {
            return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
        }

        return DO_NOT_PROXY;
    }

    // 是否是“只扫描指定目录”的方式
    protected boolean scanPackagesEnabled() {
        return ArrayUtils.isNotEmpty(scanPackages);
    }

    // 是否指定的beanClass包含在扫描目录中
    protected boolean scanPackagesContained(Class<?> beanClass) {
        String beanClassName = beanClass.getCanonicalName();
        if (StringUtils.hasText(beanClassName)) {
            if (beanClassName.contains(ProxyConstant.JDK_PROXY_NAME_KEY)
                || beanClassName.contains(ProxyConstant.CGLIB_PROXY_NAME_KEY)) {
                return true;
            }
            return Stream.of(scanPackages)
                    .filter(StringUtils::hasText)
                    .anyMatch(beanClassName::startsWith);
        }
        return false;
    }

    // 返回具有调用拦截的全局切面实现类，拦截类必须实现MethodInterceptor接口, 可以多个，通过@Component("xxx")方式寻址
    // 如果返回null， 全局切面代理关闭
    protected abstract Class<? extends MethodInterceptor>[] getCommonInterceptors();

    // 返回具有调用拦截的全局切面实现类的Bean名，拦截类必须实现MethodInterceptor接口, 可以多个
    // 如果返回null， 全局切面代理关闭
    protected abstract String[] getCommonInterceptorNames();

    // 返回额外的拦截类实例列表，拦截类必须实现MethodInterceptor接口，分别对不同的接口或者类赋予不同的拦截类，可以多个
    // 如果返回null， 额外切面代理关闭
    protected abstract MethodInterceptor[] getAdditionalInterceptors(Class<?> targetClass);
}
