package cn.jcorn.spring.aop.interceptor;

import cn.jcorn.spring.aop.invocation.MethodInvocation;

/**
 * 所有拦截器都要实现的接口，拦截器的目的是拦截所有方法调用（method invocation）
 */
public interface AopMethodInterceptor {
    Object invoke(MethodInvocation methodInvocation) throws Throwable;
}
