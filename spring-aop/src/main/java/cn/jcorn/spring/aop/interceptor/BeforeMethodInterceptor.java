package cn.jcorn.spring.aop.interceptor;

import cn.jcorn.spring.aop.invocation.MethodInvocation;
import cn.jcorn.spring.aop.advisor.BeforeMethodAdvice;

/**
 * 前置拦截器,带有前置通知
 */
public class BeforeMethodInterceptor implements AopMethodInterceptor {
    private BeforeMethodAdvice advice;

    public BeforeMethodInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    /**
     * 在调用方法之前加入前置通知
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation);
        return methodInvocation.proceed();
    }
}
