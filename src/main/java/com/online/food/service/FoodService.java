package com.online.food.service;

import java.util.List;

import com.online.food.model.Category;
import com.online.food.model.Food;
import com.online.food.model.Restaurant;
import com.online.food.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
	
	public void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory);
	
//	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food foodAvailabilityStatus(Long foodId) throws Exception;
	
	

}
