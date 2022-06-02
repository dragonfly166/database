package com.database.aspect;

import com.database.annotation.DataSource;
import com.database.util.DataSourceUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author sunlongfei
 */
@Component
@Aspect
@Slf4j
public class DataSourceAspect {

    /**
     * 切面点
     */
    @Pointcut("@annotation(com.database.annotation.DataSource)")
    private void dataSource() {}

    /**
     * 前置通知
     */
    @Before("dataSource()")
    public void before(JoinPoint joinPoint) {
        System.out.println();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            DataSource annotation = signature.getMethod().getAnnotation(DataSource.class);
            if (annotation == null) {
                annotation = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);
                if (annotation == null) {
                    throw new Exception("缺少相关注解");
                }
            }
            DataSourceUtil.setDataSource(annotation.value());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}
