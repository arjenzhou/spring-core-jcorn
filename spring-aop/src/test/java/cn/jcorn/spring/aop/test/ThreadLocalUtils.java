package cn.jcorn.spring.aop.test;

public class ThreadLocalUtils {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static long get() {
        if (threadLocal == null) {
            throw new IllegalStateException();
        }
        return threadLocal.get();
    }

    public static void set(long startTime) {
        threadLocal.set(startTime);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
