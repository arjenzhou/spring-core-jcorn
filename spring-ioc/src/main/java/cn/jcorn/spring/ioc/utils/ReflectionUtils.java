package cn.jcorn.spring.ioc.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
    /**
     * 对象依赖注入
     * @param field
     * @param object
     * @param value
     * @throws IllegalAccessException
     */
    public static void injectField(Field field, Object object, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            field.set(object, value);
        }
    }
}