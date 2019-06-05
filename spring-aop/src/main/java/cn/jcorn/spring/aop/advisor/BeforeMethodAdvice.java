package cn.jcorn.spring.aop.advisor;

import java.lang.reflect.Method;


public interface BeforeMethodAdvice extends Advice {
    /**
     * 前置通知
     */
    void before(Method method, Object[] args, Object target);
}

