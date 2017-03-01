package com.spring.aop.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spring.aop.exception.AOPException;

@Component("movie")
public class Movie 
{
	@Value("Premam")
	private String movieName;
	@Value("AriesPlex")
	private String movieVenue;
	public String getMovieName() 
	{
		return movieName;
	}
	
	public void setMovieName(String movieName) 
	{
		this.movieName = movieName;
	}
	
	public String getMovieVenue() 
	{
		return movieVenue;
	}
	
	public void setMovieVenue(String movieVenue) 
	{
		this.movieVenue = movieVenue;
	}
	
	public String displayMovieName()
	{
		return movieName;
	}
	
	public String displayVenue()
	{
		if (movieVenue.equalsIgnoreCase("AriesPlex"))
		{
			throw new AOPException("invalid.venue", "Invalid movie venue", 400);
		}
		
		return movieVenue;
	}
}
