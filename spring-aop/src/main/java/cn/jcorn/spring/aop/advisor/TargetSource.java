package cn.jcorn.spring.aop.advisor;

import lombok.Data;

/**
 * 目标对象
 */
@Data
public class TargetSource {

    private Class<?> targetClass;

    private Object targetObject;

}
