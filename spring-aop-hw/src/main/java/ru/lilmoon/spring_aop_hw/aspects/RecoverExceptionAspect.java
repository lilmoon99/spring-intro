package ru.lilmoon.spring_aop_hw.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RecoverExceptionAspect {

    @Pointcut("@annotation(ru.lilmoon.spring_aop_hw.aspects.RecoverException)")
    public void methodsAnnotatedWith() {
    }

    @Around("methodsAnnotatedWith()")
    public Object recoverException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            log.info("Throwable: {}, Exception from annotation:{}", throwable.getClass().getName(), extractExceptionFromAnnotation(joinPoint).getName());
            if (extractExceptionFromAnnotation(joinPoint).isAssignableFrom(throwable.getClass())) {
                log.info("Not recovered exception handled: {}",throwable.getClass().getName());
                throw throwable;
            } else {
                log.info("Returned null!");
                return null;
            }
        }
        log.info("Exception not detected!");
        return new Object();
    }

    private Class<? extends RuntimeException> extractExceptionFromAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        if (annotation != null) {
            return annotation.noRecoverFor();
        }
        return joinPoint.getTarget().getClass().getAnnotation(RecoverException.class).noRecoverFor();
    }
}
