package hau.kute.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by HauKute on 9/18/2018.
 */
@Aspect
@Component
public class ExampleAspect {

    @Before("execution(* hau.kute.controller.AppController.listUsers(..))")
    public void logExecutionTime(JoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature() + " execution in " + start + ".");
    }
}
