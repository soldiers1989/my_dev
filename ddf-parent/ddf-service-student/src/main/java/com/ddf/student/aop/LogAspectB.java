package com.ddf.student.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspectB implements Ordered{
    @Pointcut("execution(* com.ddf.student.service.simple.StudentService.test())")
    public void testMethodPoint(){}

    @Around("testMethodPoint()")
    public Object Interceptor(ProceedingJoinPoint pjp){
        try {
            System.out.println("b_begin");
            pjp.proceed();
            System.out.println("b_end");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    @Override
    public int getOrder(){
        return 1;
    }
}
