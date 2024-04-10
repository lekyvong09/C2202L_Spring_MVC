package com.ray.crm.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger myLogger = LogManager.getLogger(LoggingAspect.class);
	
	/// execution(public void com.ray.crm.dao.CustomerDAOImpl.deleteCustomer())
	@Pointcut("execution(* com.ray.crm.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.ray.crm.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.ray.crm.service.*.*(..))")
	private void forServicePackage() {}
	
	
	@Pointcut("forDaoPackage() || forControllerPackage() || forServicePackage()")
	private void forLoggingAspect(){}


	@Before("forLoggingAspect()")
	public void beforeLogging(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		Object[] args = theJoinPoint.getArgs();
		
		myLogger.info("===> in @Before advice: calling method: " + theMethod);
		
		for (Object a : args) {
			myLogger.info("===> argument: " + a);
		}
	}
	
	
	@AfterReturning(pointcut = "forLoggingAspect()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @AfterReturning advice: calling method: " + theMethod);
		
		myLogger.info("===> Result: " + theResult);
	}
}
