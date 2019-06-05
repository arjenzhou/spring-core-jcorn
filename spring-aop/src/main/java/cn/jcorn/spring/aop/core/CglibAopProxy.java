package cn.jcorn.spring.aop.core;

import cn.jcorn.spring.aop.advisor.AdvisedSupport;
import cn.jcorn.spring.ioc.utils.ClassUtils;
import lombok.Data;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * 通过 CGlib 获得代理（Enhance）
 */
@Data
public class CglibAopProxy implements AopProxy {

    private AdvisedSupport advised;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisedSupport config) {
        this.advised = config;
    }

    /**
     * 通过 CGlib 获得代理（Enhance）
     * @see  Object getProxy(ClassLoader classLoader)
     * @return
     */
    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    /**
     *  通过 CGlib 获得代理（Enhance）
     * @param classLoader
     * @return
     */
    @Override
    public Object getProxy(ClassLoader classLoader) {

        /**
         * 被拦截对象的 Class
         */
        Class<?> rootClass = advised.getTargetSource().getTargetClass();

        if (classLoader == null) {
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        /**
         * 生成子类
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());
        //增加拦截器的核心方法
        Callback callback = getCallBack(advised);
        /**
         * 设置回调
         */
        enhancer.setCallback(callback);
        enhancer.setClassLoader(classLoader);
        if (constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        }

        return enhancer.create();
    }

    /**
     * 被设置为回调时，先调用 intercept，再调用被代理对象的方法
     * DynamicAdvisedInterceptor extends MethodInterceptor extends Callback
     * @param advised
     * @return
     */
    private Callback getCallBack(AdvisedSupport advised) {
        return new DynamicAdvisedInterceptor(advised.getList(), advised.getTargetSource());
    }
}
