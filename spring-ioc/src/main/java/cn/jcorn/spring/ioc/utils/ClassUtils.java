package cn.jcorn.spring.ioc.utils;

public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过类名加载 Class 对象
     * @param className
     * @return
     */
    public static Class loadClass(String className) {
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
