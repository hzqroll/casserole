package com.roll.casserole.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2019-03-24 16:26.
 */
@Aspect
public class AspectTest {
    @Pointcut("execution(public void say())")
    public void ponitcutName() {
    }

    @Pointcut("args(com.roll.casserole.common.User)")
    public void argsTest() {
    }

    @Pointcut("execution(public void say(String,..)) && args(talk)")
    public void beforeArgsPointcut(String talk) {
    }

    @Around("ponitcutName()")
    public Object invokeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            System.out.println("aspect simple casserole start.");
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            System.out.println("aspect simple casserole end.");
        }
    }

    @Around("argsTest()")
    public Object invokeArgs(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            System.out.println("aspect simple casserole start user.");
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            System.out.println("aspect simple casserole end user.");
        }
    }

    @Before(value = "beforeArgsPointcut(talk)")
    public void invokeBeforeArgs(JoinPoint joinPoint, String talk) throws Throwable {
        System.out.println("aspect simple casserole start args: " + Arrays.toString(joinPoint.getArgs()) + "." + joinPoint.getSignature().getName());

        System.out.println("aspect simple casserole start args: " + talk + ".");
    }
}
