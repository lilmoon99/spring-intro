package com.lilmoon.timer.aspect;

import com.lilmoon.timer.config.TimerProperties;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;


//    @Pointcut("within(@com.lilmoon.timer.aspect.Measurable *)")
//    public void typesAnnotatedWith() {
//    }

    @Pointcut("@annotation(com.lilmoon.timer.aspect.LogMethodExecTime)")
    public void methodsAnnotatedWith() {
    }

    @Around("methodsAnnotatedWith()")
    public Object timerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Level level = extractLevel(joinPoint);

        log.atLevel(level).log("Class name: {}, Method name: {}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long finish = System.currentTimeMillis();
        final long delta = finish - start;
        log.atLevel(level).log("Execution time: {} ms", delta);

        return proceed;
    }

    private Level extractLevel(ProceedingJoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Measurable annotation = signature.getMethod().getAnnotation(Measurable.class);
//        if (annotation != null) {
//            return annotation.level();
//        }
//
//        return joinPoint.getTarget().getClass().getAnnotation(Measurable.class).level();
        return properties.getLogLevel();
    }
}
