package com.online.food.service;

import java.util.List;

import com.online.food.model.IngredientsCategory;
import com.online.food.model.IngredientsItem;

public interface IngredientsService {

	public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception;
	
	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;
	
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
	
	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
	
}
