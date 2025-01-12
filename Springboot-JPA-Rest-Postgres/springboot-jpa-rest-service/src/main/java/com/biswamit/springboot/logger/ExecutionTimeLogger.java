/*
 * Copyright (C) 2021 McAfee, LLC  All Rights Reserved.
 */
package com.biswamit.springboot.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLogger {
    private static final Logger LOG = LoggerFactory.getLogger(ExecutionTimeLogger.class);

    @Around("@annotation(com.biswamit.springboot.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;
        LOG.debug("EXECUTION TIME -- " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
