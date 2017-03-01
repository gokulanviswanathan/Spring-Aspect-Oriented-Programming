package com.spring.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.spring.aop.listener.AOPListener;

@Component
@Aspect
public class AOPWebAspect 
{
	public final static Logger logger = Logger.getLogger(AOPWebAspect.class);
	
	@Before("execution(* com.spring.aop.pojo.Movie.displayMovieName())")
	public void beforeDisplay()
	{
		logger.info("This is Before displaying movie name");
	}
	
	@After("execution(* com.spring.aop.pojo.Movie.displayMovieName(..))")
	public void afterDisplay()
	{
		logger.info("This is After displaying movie name");
	}
	
	@AfterThrowing("execution(* com.spring.aop.pojo.Movie.displayVenue(..))")
	public void afterThrowingDisplay()
	{
		logger.info("This is After Throwing exception");
		AOPListener.failureMeter.mark();
	}
}
