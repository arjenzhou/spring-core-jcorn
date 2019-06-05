package cn.jcorn.spring.ioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * bean 的构造方法参数
 */
@Data
@ToString
public class ConstructorArg {
    private int index;
    private String ref;
    private String name;
    private Object value;
}
