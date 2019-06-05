package cn.jcorn.spring.aop.test;

import cn.jcorn.spring.aop.advisor.BeforeMethodAdvice;

import java.lang.reflect.Method;

public class StartTimeBeforeMethod implements BeforeMethodAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        long time = System.currentTimeMillis();
        System.out.println("开始计时");
        ThreadLocalUtils.set(time);
    }
}
