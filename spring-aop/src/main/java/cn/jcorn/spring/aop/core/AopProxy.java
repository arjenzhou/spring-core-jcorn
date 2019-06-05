package cn.jcorn.spring.aop.core;

public interface AopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
