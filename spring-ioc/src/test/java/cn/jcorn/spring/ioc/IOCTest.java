package cn.jcorn.spring.ioc;

import cn.jcorn.spring.ioc.core.JsonApplicationContext;
import cn.jcorn.spring.ioc.entity.Robot;
import org.junit.Test;


public class IOCTest {
    @Test
    public void testIOC() throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot) applicationContext.getBean("robot");
        aiRobot.show();
    }
}