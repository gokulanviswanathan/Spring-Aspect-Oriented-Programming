package com.spring.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * This class serves as Aspect container with all the required advice methods
 * @author Infosys
 *
 */
@Component
@Aspect
public class AOPAspect 
{
	public final static Logger logger = Logger.getLogger(AOPAspect.class);
	
	@Before("execution(* com.spring.aop.pojo.Customer.displayCustomerName())")
	public void before()
	{
		logger.info("(ASPECT) This is Before displaying customer name");
	}
	
	@After("execution(* com.spring.aop.pojo.Customer.displayCustomerName(..))")
	public void after()
	{
		logger.info("(ASPECT) This is After displaying customer name");
	}
	
	@AfterThrowing("execution(* com.spring.aop.pojo.Customer.displayStreet(..))")
	public void afterThrowing()
	{
		logger.info("(ASPECT) This is After Throwing exception");
	}
	
	@AfterReturning(pointcut="execution(* com.spring.aop.pojo.Customer.getCustomerId(..))", returning="customerId")
	public void afterReturning(String customerId)
	{
		logger.info("(ASPECT) This is After Returning CustomerID. Customer ID: " + customerId);
	}
	
	@Around("execution(* com.spring.aop.pojo.Customer.displayWelcomeMessage(..))")
	public String around(ProceedingJoinPoint proceedingJointPoint)
	{
		logger.info("(ASPECT) This is Before Around");
		try 
		{
			Object object = proceedingJointPoint.proceed();
			logger.info("(ASPECT) Welcome message inside aspect: " + object.toString());
		} 
		
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		Object methodArgs[] = proceedingJointPoint.getArgs();
		String welcomeMessageFromUser = methodArgs[0].toString();
		
		if ("Hello!! ".equalsIgnoreCase(welcomeMessageFromUser))
		{
			welcomeMessageFromUser = welcomeMessageFromUser + "How are you??";
		}
		
		logger.info("(ASPECT) This is the end of Around");
		
		return welcomeMessageFromUser;
	}
	
	
}
