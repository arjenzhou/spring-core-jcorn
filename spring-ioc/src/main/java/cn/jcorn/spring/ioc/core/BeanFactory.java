package cn.jcorn.spring.ioc.core;

public interface BeanFactory {
    /**
     * 通过 name 来获取 bean
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;
}
