package cn.jcorn.spring.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class BeanUtils {
    /**
     * 通过cglib实例化对象
     * @param clazz
     * @param constructor
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T instanceByCglib(Class<T> clazz, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(NoOp.INSTANCE);
        if (constructor == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(constructor.getParameterTypes(), args);
        }
    }
}