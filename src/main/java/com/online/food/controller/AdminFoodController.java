package com.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.food.model.Food;
import com.online.food.model.Restaurant;
import com.online.food.model.User;
import com.online.food.request.CreateFoodRequest;
import com.online.food.response.MessageResponse;
import com.online.food.service.FoodService;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@RestController
@RequestMapping("/admin/food")
@CrossOrigin("*")
public class AdminFoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);
		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		foodService.deleteFood(id);
		MessageResponse  messageResponse = new MessageResponse();
		messageResponse.setMessage("food delete successfully");
		
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		Food food = foodService.foodAvailabilityStatus(id);
		
		return new ResponseEntity<>(food, HttpStatus.OK);
	}
	
}
