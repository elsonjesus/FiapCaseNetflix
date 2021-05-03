package com.fiap.appcase.netflix.service;

import org.springframework.stereotype.Service;

import com.fiap.appcase.netflix.entity.Movie;
import com.fiap.appcase.netflix.utils.RandomUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class SearchMovieService {

//	public Movie getFilme (int filmeId) {
//		return new Movie(filmeId);
//	}
	
	@HystrixCommand(fallbackMethod = "getFilmeFallBack")
	public Movie getFilmeInfo(int id) {
		Movie filme = null;
		
		if (RandomUtils.random50PercentError() == 1) {
			throw new RuntimeException();
		} else {
			filme = new Movie();
		}
		
		return filme;
	}
	
	@HystrixCommand(fallbackMethod = "getGeneroCircuitBreak", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200") })
	public Movie getFilme (int filmeId) {
		
		if (RandomUtils.random50PercentError() == 1) {
			RandomUtils.randomSleep();
		}
		
		return new Movie();
	}

	private int getGeneroCircuitBreak() {
		return -1;
	}
	
	private String getFilmeFallBack() {
		return "No information available about film";
	}
	
}
