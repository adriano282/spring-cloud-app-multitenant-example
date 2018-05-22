package br.com.customersuggest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggerAspect.class);

    @AfterThrowing
            (pointcut = "execution(* *(..)) && " +
            "( "+
            "    within(br.com.customersuggest..controller..*) ||" +
            "    within(br.com.customersuggest..service..*) ||" +
            "    within(br.com.customersuggest..repo..*)" +
            ")", throwing = "error")
    public void logError(JoinPoint joinPoint, Exception error) {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        logger.error(
                String.format(
                        "An error ocurred during %s.%s method invocation: %s",
                        className, methodName, error.getStackTrace()));
    }
}
