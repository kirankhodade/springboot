package com.kiran.controller;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiran.model.CatalogItem;
import com.kiran.model.Movie;
import com.kiran.model.Rating;
import com.kiran.model.UserRating;
import com.kiran.service.MovieInfoService;
import com.kiran.service.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfoService movieInfoService;
	
	@Autowired
	UserRatingService userRatingService;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		UserRating userRating = userRatingService.getUserRating(userId);
		
		return userRating.getUserRatingList().stream()
											 .map(rating -> movieInfoService.getCatalogItem(rating))
											 .collect(Collectors.toList());
	}
}
