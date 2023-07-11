package com.nogayhusrev.accounting.aspect;


import com.nogayhusrev.accounting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final UserService userService;

    public LoggingAspect(UserService userService) {
        this.userService = userService;
    }

    private String getUsername() {

        return userService.getCurrentUser().getUsername();
    }


    /*  =========>>>  CONTROLLER LOGGING ASPECT  <<< =========  */


    @Pointcut("execution(* com.accounting..*(..))")
    public void controllerPointcut(){}

    @Before("controllerPointcut()")
    public void beforeControllerPointcut(JoinPoint joinPoint){
        log.info("|| *** ===>>>LOG INFO<<<=== *** || Before || Method: {}, Arguments: {}, User: {}, Target: {}",
                joinPoint.getSignature(), joinPoint.getArgs(),getUsername(), joinPoint.getTarget());
    }

    @AfterReturning(pointcut = "controllerPointcut()", returning = "results")
    public void afterReturningControllerPointcut(JoinPoint joinPoint, Object results) {
        log.info("|| *** ===>>>LOG INFO<<<=== *** || After Returning || Method: {}, User: {}, Results: {}"
                , joinPoint.getSignature().toShortString()
                , getUsername()
                , results.toString());
    }

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "exception")
    public void afterThrowingControllerPointcut(JoinPoint joinPoint, Exception exception) {
        log.error("|| *** ===>>>LOG ERROR<<<=== *** || After Throwing || Method: {}, User: {}, Results: {}"
                , joinPoint.getSignature().toShortString()
                , getUsername()
                , exception.getMessage());
    }




}