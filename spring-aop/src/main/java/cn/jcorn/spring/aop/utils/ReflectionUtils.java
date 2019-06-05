package cn.jcorn.spring.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    /**
     * 通过反射调用目标的方法 target.method(arguments)
     * @param target
     * @param method
     * @param arguments
     * @return
     */
    public static Object invokeMethodUseReflection(Object target, Method method, Object[] arguments) {
        method.setAccessible(true);
        try {
            return method.invoke(target, arguments);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
