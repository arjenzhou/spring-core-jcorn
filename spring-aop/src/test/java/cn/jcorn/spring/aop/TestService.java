package cn.jcorn.spring.aop;

public class TestService {
    public void testMethod() throws InterruptedException {
        System.out.println("this is a test method");
        Thread.sleep(1000);
    }
}
