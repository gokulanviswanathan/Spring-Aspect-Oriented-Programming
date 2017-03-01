package com.spring.aop.logic;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.exception.AOPException;
import com.spring.aop.pojo.Customer;

/**
 * This serves root class to display the details of the customer
 * 
 * @author Infosys
 *
 */
public class AOPLogic 
{
	public final static Logger logger = Logger.getLogger(AOPLogic.class);

	public static void main(String[] args) 
	{
		String customerId;
		String welcomeMessage;
		
		try
		{
			ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
			Customer customer = (Customer)context.getBean("customer");
			customer.displayCustomerName();
			customerId = customer.getCustomerId();
			logger.info("(MAIN) Customer ID: " + customerId);
			welcomeMessage = customer.displayWelcomeMessage("Hello!! ");
			logger.info("(MAIN) Welcome message: " + welcomeMessage);
			customer.displayStreet();
			
		}

		catch (AOPException e)
		{
			logger.info("(MAIN) Exception occurred in main method. Code: " + e.getCode() + " Message: " + e.getErrorMessage());
		}
	}
}
