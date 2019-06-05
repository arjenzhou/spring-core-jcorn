package cn.jcorn.spring.ioc.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * application.json 中配置的运行时数据结构，用来描述 bean
 */
@ToString
@Data
public class BeanDefinition {
    private String name;
    private String className;
    private String interfaceName;
    private List<ConstructorArg> constructorArgs;
    private List<PropertyArg> propertyArgs;
}
