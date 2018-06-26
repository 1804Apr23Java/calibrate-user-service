package com.revature.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	private Logger logger = Logger.getRootLogger();
	
	@AfterReturning(pointcut = "within(com.revature.service.*)", 
			returning = "result")
	public void logServiceAfterReturn(JoinPoint jp, Object result) {
		logger.info(jp.getSignature() + ", result: " + result);
	}
	
	@AfterReturning(pointcut = "within(com.revature.controller.*)", 
			returning = "result")
	public void logControllerAfterReturn(JoinPoint jp, Object result) {
		logger.info(jp.getSignature() + ", result: " + result);
	}
}
