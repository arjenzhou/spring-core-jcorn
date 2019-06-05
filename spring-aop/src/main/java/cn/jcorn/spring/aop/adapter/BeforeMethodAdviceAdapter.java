package cn.jcorn.spring.aop.adapter;

import cn.jcorn.spring.aop.advisor.Advisor;
import cn.jcorn.spring.aop.advisor.BeforeMethodAdvice;
import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;
import cn.jcorn.spring.aop.interceptor.BeforeMethodInterceptor;

/**
 * 前置适配器，将切面适配为拦截器来拦截方法调用
 */
public class BeforeMethodAdviceAdapter implements AdviceAdapter {

    private BeforeMethodAdviceAdapter() {
    }

    private static final BeforeMethodAdviceAdapter INSTANCES = new BeforeMethodAdviceAdapter();

    /**
     * 获取前置拦截器适配器，其目的为获取前置拦截器
     * @return
     */
    public static BeforeMethodAdviceAdapter getInstance() {
        return INSTANCES;
    }

    /**
     * 获取前置拦截器实例
     * @param advisor 切面
     * @return 返回一个该切面的前置拦截器
     */
    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodInterceptor(advice);
    }
}
