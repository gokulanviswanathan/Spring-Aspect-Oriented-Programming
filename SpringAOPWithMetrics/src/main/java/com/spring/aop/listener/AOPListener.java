package com.spring.aop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class AOPListener implements ServletContextListener 
{

	JmxReporter jmxReporter = null;
	public static MetricRegistry aopMetricsRegistry = null;
	public static Meter requestMeter;
	public static Meter failureMeter;
	public static Timer responseTimer;

	public void contextInitialized(ServletContextEvent sce) 
	{
		buildAndReportMetrics();
	}

	public void contextDestroyed(ServletContextEvent sce) 
	{
		jmxReporter.close();
	}

	public void buildAndReportMetrics() {

		//Create new registry
		aopMetricsRegistry = new MetricRegistry();
		//Create meters and timers for recording the metrics
		requestMeter = aopMetricsRegistry.meter(MetricRegistry.name("com.monitoring", "requests"));
		failureMeter = aopMetricsRegistry.meter(MetricRegistry.name("com.monitoring", "failure"));
		responseTimer = aopMetricsRegistry.timer(MetricRegistry.name("com.monitoring", "timer"));
		//Report the metrics to JMX console via JMX reporter
		jmxReporter = JmxReporter.forRegistry(aopMetricsRegistry).inDomain("com.monitoring").build();
		jmxReporter.start();
	}
}

