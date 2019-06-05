package cn.jcorn.spring.aop.invocation;

/**
 * 代理方法的调用,增加了获取代理的方法
 */
public interface ProxyMethodInvocation extends MethodInvocation{
    Object getProxy();
}
