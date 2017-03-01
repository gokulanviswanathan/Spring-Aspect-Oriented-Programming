package com.spring.aop.rest;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.aop.exception.AOPException;
import com.spring.aop.pojo.Customer;
import com.spring.aop.pojo.Movie;

@Controller
@RequestMapping("/movie")
public class AOPRestService 
{

	public final static Logger logger = Logger.getLogger(AOPRestService.class);

	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
		Movie movie = (Movie)context.getBean("movie");
		movie.displayMovieName();
		model.addAttribute("movie", name);

		return "list";
	}


	@RequestMapping(value="/show/{name}", method = RequestMethod.GET)
	public String getMovieNameAndVenue(@PathVariable String name, ModelMap model) 
	{
		try 
		{
			ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
			Customer customer = (Customer)context.getBean("customer");
			Movie movie = (Movie)context.getBean("movie");
			customer.displayCustomerName();
			movie.displayMovieName();
			movie.displayVenue();
			model.addAttribute("movie", name);
			
			if (name.equalsIgnoreCase("hello"))
			{
				customer.displayStreet();
			}

		}

		catch (AOPException e)
		{
			logger.info("(REST) Exception occurred in REST service. Code: " + e.getCode() + " Message: " + e.getErrorMessage());
			model.addAttribute("movie", e.getErrorMessage());
			return "list";
			
		}
		
		return "list";
	}

}
