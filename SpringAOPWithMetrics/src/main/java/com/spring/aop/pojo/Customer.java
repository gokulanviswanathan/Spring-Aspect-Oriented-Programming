package com.spring.aop.pojo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spring.aop.exception.AOPException;
import com.spring.aop.listener.AOPListener;

@Component("customer")
public class Customer 
{
	public final static Logger logger = Logger.getLogger(Customer.class);
	
	@Value("ABCD")
	private String customerName;
	@Value("12345A")
	private String customerId;
	@Autowired
	private Address address;
	
	public String getCustomerName() 
	{
		return customerName;
	}
	
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	
	public String getCustomerId() 
	{
		return customerId;
	}
	
	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	
	public Address getAddress() 
	{
		return address;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	
	public void displayCustomerName()
	{
		logger.info("Customer Name: " + customerName);
	}
	
	public void displayStreet()
	{
		if ("XYZ Street".equalsIgnoreCase(address.getStreet()))
		{
			throw new AOPException("invalid.street", "Invalid Street name", 400);
		}
	}
	
	public String displayWelcomeMessage(String welcomeMessage)
	{
		String updatedWelcomeMessage = welcomeMessage + customerName;
		logger.info("(CUSTOMER) Welcome message: " + updatedWelcomeMessage);
		return updatedWelcomeMessage;
	}
}
