package ru.lilmoon.spring_aop_hw.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@ru.lilmoon.spring_aop_hw.aspects.Measurable *)")
    public void typesAnnotatedWith() {
    }

    @Pointcut("@annotation(ru.lilmoon.spring_aop_hw.aspects.Measurable)")
    public void methodsAnnotatedWith() {
    }

    @Around("typesAnnotatedWith() || methodsAnnotatedWith()")
    public void timerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Level level = extractLevel(joinPoint);
        long start = System.currentTimeMillis();
        try {
            log.atLevel(level).log("Class name: {}, Method name: {}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
            joinPoint.proceed();
            long finish = System.currentTimeMillis();
            long delta = finish - start;
            log.atLevel(level).log("Execution time: {}",delta);
        } catch (Throwable e) {
            log.atLevel(level).log("Exception caught: {}", e.getMessage());
            throw e;
        }
    }

    private Level extractLevel(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Measurable annotation = signature.getMethod().getAnnotation(Measurable.class);
        if (annotation != null) {
            return annotation.level();
        }

        return joinPoint.getTarget().getClass().getAnnotation(Measurable.class).level();
    }

}
