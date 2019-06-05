package cn.jcorn.spring.aop.bean;

import cn.jcorn.spring.ioc.bean.BeanDefinition;
import lombok.Data;

import java.util.List;

@Data
public class AopBeanDefinition extends BeanDefinition {
    private String target;
    private List<String> interceptorNames;
}
