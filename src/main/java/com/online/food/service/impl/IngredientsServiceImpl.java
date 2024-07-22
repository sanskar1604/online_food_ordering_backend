package com.online.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.model.IngredientsCategory;
import com.online.food.model.IngredientsItem;
import com.online.food.repository.IngredientsCategoryRepository;
import com.online.food.repository.IngredientsItemsRepository;
import com.online.food.service.IngredientsService;
import com.online.food.service.UserService;

@Service
public class IngredientsServiceImpl implements IngredientsService {
	
	@Autowired
	private IngredientsCategoryRepository ingredientsCategoryRepository;
	
	@Autowired
	private IngredientsItemsRepository ingredientsItemsRepository;
	
	@Autowired
	private UserService userService;
	

	@Override
	public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
