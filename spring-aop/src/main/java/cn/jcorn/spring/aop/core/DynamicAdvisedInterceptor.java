package cn.jcorn.spring.aop.core;

import cn.jcorn.spring.aop.advisor.TargetSource;
import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;
import cn.jcorn.spring.aop.invocation.CglibMethodInvocation;
import cn.jcorn.spring.aop.invocation.MethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class DynamicAdvisedInterceptor implements MethodInterceptor {
    protected final List<AopMethodInterceptor> interceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> list, TargetSource targetSource) {
        this.interceptorList = list;
        this.targetSource = targetSource;
    }


    /**
     * Cglib 通过 Enhance 生产代理类的一个子类，每次调用原来的方法都会被转换为调用代理对象的 intercept 方法
     * 在该方法中即可实现对代理方法的增强
     * @param obj 被代理的对象
     * @param method 被拦截的方法
     * @param args 被拦截方法的参数
     * @param proxy  CGLIB 提供的被拦截方法
     * @return CGLIB 的调用过程
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MethodInvocation invocation = new CglibMethodInvocation(obj, targetSource.getTargetObject(), method, args, interceptorList, proxy);
        return invocation.proceed();
    }
}
