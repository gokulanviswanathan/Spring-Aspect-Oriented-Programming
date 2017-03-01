package com.spring.aop.pojo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Address 
{
	public final static Logger logger = Logger.getLogger(Address.class);
	
	@Value("XYZ Street")
	private String street;
	@Value("ABC City")
	private String city;
	@Value("098765PQ")
	private String pincode;
	
	public String getStreet() 
	{
		return street;
	}
	
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public String getPincode() 
	{
		return pincode;
	}
	
	public void setPincode(String pincode) 
	{
		this.pincode = pincode;
	}
}
