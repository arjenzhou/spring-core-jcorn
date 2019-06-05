package cn.jcorn.spring.aop.invocation;

import java.lang.reflect.Method;

/**
 * 所有方法调用的接口,描述一个方法调用。包括获取方法对象，获取参数，调用。
 */
public interface MethodInvocation {
    Method getMethod();

    Object[] getArguments();

    Object proceed() throws Throwable;
}
