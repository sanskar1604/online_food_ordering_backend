package com.online.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.model.IngredientsCategory;
import com.online.food.model.IngredientsItem;
import com.online.food.model.Restaurant;
import com.online.food.repository.IngredientsCategoryRepository;
import com.online.food.repository.IngredientsItemsRepository;
import com.online.food.service.IngredientsService;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@Service
public class IngredientsServiceImpl implements IngredientsService {
	
	@Autowired
	private IngredientsCategoryRepository ingredientsCategoryRepository;
	
	@Autowired
	private IngredientsItemsRepository ingredientsItemsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	

	@Override
	public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientsCategory category = new IngredientsCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		
		return ingredientsCategoryRepository.save(category);
	}

	@Override
	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
		
		Optional<IngredientsCategory> opt = ingredientsCategoryRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("Ingredient category not found.");
		}
		
		return opt.get();
	}

	@Override
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		
		restaurantService.findRestaurantById(id);
		
		return ingredientsCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientsCategory category = findIngredientsCategoryById(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientsItem ingredientItem = ingredientsItemsRepository.save(item);
		category.getIngredientItem().add(ingredientItem);
		
		return ingredientItem;
	}

	@Override
	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
		
		return ingredientsItemsRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		
		Optional<IngredientsItem> optionalIngredientsItem = ingredientsItemsRepository.findById(id);
		if(optionalIngredientsItem.isEmpty()) {
			throw new Exception("Ingredient not found");
		}
		IngredientsItem ingredientsItem = optionalIngredientsItem.get();
		
		ingredientsItem.setInStock(!ingredientsItem.isInStock());
		
		return ingredientsItemsRepository.save(ingredientsItem);
	}

}
