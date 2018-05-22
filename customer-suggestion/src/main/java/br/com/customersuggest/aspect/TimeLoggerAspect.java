package br.com.customersuggest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimeLoggerAspect.class);

    @Around("execution(* *(..)) && " +
            "( "+
            "    within(br.com.customersuggest.cotroller..*) ||" +
            "    within(br.com.customersuggest.service..*) ||" +
            "    within(br.com.customersuggest.repo..*)" +
            ")")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch sw = new StopWatch();

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        logger.info("The method {}.{} invoked.", className, methodName);

        sw.start();

        try {
            return proceedingJoinPoint.proceed();
        }
        finally {
            sw.stop();
            logger.info("The method {}.{} finished. Execution time {} ms.",
                    className, methodName, sw.getTotalTimeMillis());
        }
    }
}
