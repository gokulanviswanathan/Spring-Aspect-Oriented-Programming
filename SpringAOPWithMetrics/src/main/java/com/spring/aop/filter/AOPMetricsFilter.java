package com.spring.aop.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.util.StopWatch;

import com.spring.aop.listener.AOPListener;

public class AOPMetricsFilter implements Filter {

	public void init(FilterConfig config) throws ServletException 
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{

		//Using stopwatch to calculate the response time
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();	
		//Log all the requests to request meter (Can be success or failure)
		AOPListener.requestMeter.mark();
		chain.doFilter(request, response);
		stopWatch.stop();
		AOPListener.responseTimer.update(stopWatch.getTotalTimeMillis(), TimeUnit.MILLISECONDS);
	} 

	public void destroy() 
	{
		
	}
}