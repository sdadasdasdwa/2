package com.liujinhang.demo.logic.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvice {

    @Pointcut("execution (* com.liujinhang.demo.logic.*.sayGreeting(String))")
    public void sayGreeting() {}

    @Before("sayGreeting()")
    public void beforeAdvice() {
        System.out.println("beforeAdvice...");
    }

    @After("sayGreeting()")
    public void afterAdvice() {
        System.out.println("afterAdvice...");
    }
}
