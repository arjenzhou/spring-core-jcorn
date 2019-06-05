package cn.jcorn.spring.aop.adapter;

import cn.jcorn.spring.aop.advisor.Advisor;
import cn.jcorn.spring.aop.advisor.AfterRunningAdvice;
import cn.jcorn.spring.aop.interceptor.AfterRunningAdviceInterceptor;
import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;

public class AfterRunningAdviceAdapter implements AdviceAdapter {

    private static final AfterRunningAdviceAdapter INSTANTS = new AfterRunningAdviceAdapter();

    private AfterRunningAdviceAdapter() {

    }

    public static final AfterRunningAdviceAdapter getInstance() {
        return INSTANTS;
    }


    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterRunningAdvice advice = (AfterRunningAdvice) advisor.getAdvice();
        return new AfterRunningAdviceInterceptor(advice);
    }
}
