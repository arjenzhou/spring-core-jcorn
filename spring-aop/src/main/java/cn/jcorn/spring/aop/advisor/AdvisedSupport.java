package cn.jcorn.spring.aop.advisor;

import cn.jcorn.spring.aop.interceptor.AopMethodInterceptor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 具体的切面，除了继承的切点和通知外，还包括了目标对象
 */
@Data
public class AdvisedSupport extends Advisor {
    /**
     * 拦截的目标对象
     */
    private TargetSource targetSource;
    /**
     * 拦截器列表
     */
    private List<AopMethodInterceptor> list = new LinkedList<>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor) {
        list.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> interceptors) {
        list.addAll(interceptors);
    }

}
