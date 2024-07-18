package com.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.food.model.Restaurant;
import com.online.food.model.User;
import com.online.food.request.CreateRestaurantRequest;
import com.online.food.response.MessageResponse;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	private ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.createRestaurant(req, user);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurant(id, req);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		restaurantService.deleteRestaurant(id);
		MessageResponse res = new MessageResponse();
		res.setMessage("Restaurant deleted successfully");
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	private ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	private ResponseEntity<Restaurant> getRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
}
