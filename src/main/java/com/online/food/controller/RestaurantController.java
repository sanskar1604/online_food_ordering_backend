package com.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.food.dto.RestaurantDto;
import com.online.food.model.Restaurant;
import com.online.food.model.User;
import com.online.food.request.CreateRestaurantRequest;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	private ResponseEntity<List<Restaurant>> SearchRestaurant(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping("")
	private ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restaurantService.getAllRestaurant();
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Restaurant> getAllRestaurantById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant= restaurantService.findRestaurantById(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/add-favorites")
	private ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		RestaurantDto restaurant= restaurantService.addToFavorites(id, user);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
}
