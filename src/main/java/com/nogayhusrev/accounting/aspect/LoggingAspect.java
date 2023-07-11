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


    @Pointcut("execution(* com.nogayhusrev.accounting..*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void beforeControllerPointcut(JoinPoint joinPoint) {
        log.info("|| *** ===>>>BEFORE<<<=== *** || Method: {}, Arguments: {}, Target: {}",
                joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
    }

    @AfterReturning(pointcut = "controllerPointcut()")
    public void afterReturningControllerPointcut(JoinPoint joinPoint) {

        log.info("|| *** ===>>>AFTER RETURNING<<<=== *** || Method: {}"
                , joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "exception")
    public void afterThrowingControllerPointcut(JoinPoint joinPoint, Exception exception) {

        log.error("|| *** ===>>>AFTER THROWING<<<=== *** || Method: {}, Results: {}"
                , joinPoint.getSignature().toShortString()
                , exception.getMessage());
    }


}