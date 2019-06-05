package cn.jcorn.spring.aop.adapter;

import cn.jcorn.spring.aop.advisor.Advisor;
import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;

/**
 * 适配器，将切面适配为拦截器
 */
public interface AdviceAdapter {
    AopMethodInterceptor getInterceptor(Advisor advisor);
}
