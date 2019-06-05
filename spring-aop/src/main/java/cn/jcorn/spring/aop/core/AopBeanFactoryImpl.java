package cn.jcorn.spring.aop.core;

import cn.jcorn.spring.aop.advisor.*;
import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;
import cn.jcorn.spring.aop.adapter.AfterRunningAdviceAdapter;
import cn.jcorn.spring.aop.adapter.BeforeMethodAdviceAdapter;
import cn.jcorn.spring.aop.bean.AopBeanDefinition;
import cn.jcorn.spring.ioc.core.BeanFactoryImpl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AopBeanFactoryImpl extends BeanFactoryImpl {

    private static final ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Object> aopBeanMap = new ConcurrentHashMap<>();

    /**
     * 获取该方法的代理bean
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean = aopBeanMap.get(name);

        if (aopBean != null) {
            return aopBean;
        }
        /**
         * 当代理不存在时，通过 aopBeanDefinition 创建代理对象
         */
        if (aopBeanDefinitionMap.containsKey(name)) {
            /**
             * 通过 name 获取已注册的 aopBeanDefinition
             */
            AopBeanDefinition aopBeanDefinition = aopBeanDefinitionMap.get(name);
            /**
             * 通过 aopBeanDefinition 获取切面(AdvisedSupport)
             */
            AdvisedSupport advisedSupport = getAdvisedSupport(aopBeanDefinition);
            /**
             * 获得该切面的代理对象（CGlib Enhance）
             */
            aopBean = new CglibAopProxy(advisedSupport).getProxy();
            /**
             * 将其放入map中缓存
             */
            aopBeanMap.put(name, aopBean);
            /**
             * 返回 代理对象
             */
            return aopBean;
        }

        return super.getBean(name);
    }

    /**
     * 将 aopBeanDefinition 放入 map 中
     *
     * @param name
     * @param aopBeanDefinition
     */
    protected void registerBean(String name, AopBeanDefinition aopBeanDefinition) {
        aopBeanDefinitionMap.put(name, aopBeanDefinition);
    }

    /**
     * 获取切面
     * @param aopBeanDefinition
     * @return
     * @throws Exception
     */
    private AdvisedSupport getAdvisedSupport(AopBeanDefinition aopBeanDefinition) throws Exception {

        AdvisedSupport advisedSupport = new AdvisedSupport();
        List<String> interceptorNames = aopBeanDefinition.getInterceptorNames();
        /**
         * 注册的拦截器不空
         */
        if (interceptorNames != null && !interceptorNames.isEmpty()) {
            for (String interceptorName : interceptorNames) {
                /**
                 * 获取拦截器通知
                 */
                Advice advice = (Advice) getBean(interceptorName);

                /**
                 * 定义切面
                 */
                Advisor advisor = new Advisor();
                advisor.setAdvice(advice);

                /**
                 * 适配器把通知适配成拦截器，用于拦截方法调用
                 * 并向切面（AdvisedSupport）中加入拦截器
                 */
                if (advice instanceof BeforeMethodAdvice) {
                    AopMethodInterceptor interceptor = BeforeMethodAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }

                if (advice instanceof AfterRunningAdvice) {
                    AopMethodInterceptor interceptor = AfterRunningAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }

            }
        }

        TargetSource targetSource = new TargetSource();
        Object object = getBean(aopBeanDefinition.getTarget());
        targetSource.setTargetClass(object.getClass());
        targetSource.setTargetObject(object);
        advisedSupport.setTargetSource(targetSource);
        return advisedSupport;

    }

}

