package com.online.food.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.model.Category;
import com.online.food.model.Food;
import com.online.food.model.Restaurant;
import com.online.food.repository.FoodRepository;
import com.online.food.request.CreateFoodRequest;
import com.online.food.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategoy(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredientsItem(req.getIngredients());
		food.setSeasonal(req.isSeasonal());
		food.setVegetarian(req.isVegetarian());
		
		Food saveFood = foodRepository.save(food);
		restaurant.getFoods().add(saveFood);
		
		return saveFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
		
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		if(isVegetarian) {
			foods = filterByVegetarian(foods, isVegetarian);
		}
		if(isNonveg){
			foods = filterByNonveg(foods, isNonveg);
		}
		if(isSeasonal) {
			foods = filterBySeasonal(foods, isSeasonal);
		}
		if(foodCategory != null && !foodCategory.equals("")) {
			foods = filterByFoodCategory(foods, foodCategory);
		}
		return foods;
	}

	private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food -> {
			if(food.getFoodCategoy()!=null) {
				return food.getFoodCategoy().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());	
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
		return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());
	}

//	@Override
//	public List<Food> searchFood(String keyword) {
//		return foodRepository.searchFood(keyword);
//	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood = foodRepository.findById(foodId);
		
		if(optionalFood.isEmpty()) {
			throw new Exception("food not exist...");
		}
		return optionalFood.get();
	}

	@Override
	public Food foodAvailabilityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
