package cn.jcorn.spring.aop.interceptor;

import cn.jcorn.spring.aop.invocation.MethodInvocation;
import cn.jcorn.spring.aop.advisor.AfterRunningAdvice;

public class AfterRunningAdviceInterceptor implements AopMethodInterceptor {
    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    /**
     * 后置拦截器，拦截方法调用并加入后置通知
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object returnVal = methodInvocation.proceed();
        advice.after(returnVal, methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation);
        return returnVal;
    }
}
