package cn.jcorn.spring.aop;

import cn.jcorn.spring.aop.core.AopApplicationContext;

public class MainTest {
    public static void main(String[] args) throws Exception {

        AopApplicationContext aopApplicationContext = new AopApplicationContext("application.json");
        aopApplicationContext.init();
        /**
         * 获取该方法的代理
         */
        TestService testService = (TestService) aopApplicationContext.getBean("testServiceProxy");
        /**
         * 调用代理方法
         */
        testService.testMethod();
    }
}
