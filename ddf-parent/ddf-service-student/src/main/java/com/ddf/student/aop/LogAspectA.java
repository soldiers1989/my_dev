package com.ddf.student.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspectA implements Ordered{
    @Pointcut("execution(* com.ddf.student.service.simple.StudentService.test())")
    public void testMethodPoint(){}

    @Around("testMethodPoint()")
    public Object Interceptor(ProceedingJoinPoint pjp){
        try {
            System.out.println("a_begin");
            pjp.proceed();
            System.out.println("a_end");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
