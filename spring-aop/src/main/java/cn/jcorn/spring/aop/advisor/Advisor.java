package cn.jcorn.spring.aop.advisor;

import lombok.Data;

/**
 * 切面
 */
@Data
public class Advisor {
    private Advice advice;
    private PointCut pointCut;
}
