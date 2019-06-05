package cn.jcorn.spring.aop.invocation;

import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;
import cn.jcorn.spring.aop.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectiveMethodMethodInvocation implements ProxyMethodInvocation {

    public ReflectiveMethodMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }

    protected final Object proxy;

    protected final Object target;

    protected final Method method;

    protected Object[] arguments = new Object[0];

    protected final List<AopMethodInterceptor> interceptorList;

    private int currentInterceptorIndex = -1;

    @Override
    public Object getProxy() {
        return proxy;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * 执行拦截器的方法，在执行完所有拦截器之后，执行被拦截的目标方法
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {

        //执行完所有的拦截器后，执行目标方法
        if (currentInterceptorIndex == this.interceptorList.size() - 1) {
            return invokeOriginal();
        }

        //迭代的执行拦截器。回顾上面的讲解，我们实现的拦击都会执行 im.proceed() 实际上就在调用这个方法。
        AopMethodInterceptor interceptor = interceptorList.get(++currentInterceptorIndex);
        return interceptor.invoke(this);

    }

    /**
     * 调用目标原来的方法
     * @return
     * @throws Throwable
     */
    protected Object invokeOriginal() throws Throwable {
        return ReflectionUtils.invokeMethodUseReflection(target, method, arguments);
    }
}