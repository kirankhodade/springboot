package com.kiran.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiran.model.Rating;
import com.kiran.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataController {

		@RequestMapping("/{movieId}")
		public Rating getRating(@PathVariable String movieId) {
			return new Rating(movieId, 4);
		}
		
		@RequestMapping("users/{userId}")
		public UserRating getUserRating(@PathVariable String userId) {
			List<Rating> ratingList = Arrays.asList(
										new Rating("400", 4),
										new Rating("300", 2));
			
			UserRating userRating = new UserRating();
			userRating.setUserRatingList(ratingList);
			
			return userRating;
		}
}
