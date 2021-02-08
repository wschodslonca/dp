package com.project.dp.Filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FilterAspect {

    private final Filter filter = new Filter();

    @Around("@annotation(ACLSecu)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        String query = (String)args[0];
        Long user_id = (Long)args[1];
        query = filter.filter(query,user_id);
        args[0] = query;
        return joinPoint.proceed(args);
    }
}
