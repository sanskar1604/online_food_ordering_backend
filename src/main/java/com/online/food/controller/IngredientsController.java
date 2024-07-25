package com.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.food.model.IngredientsCategory;
import com.online.food.request.IngredientsCategoryRequest;
import com.online.food.service.IngredientsService;

@RestController
@RequestMapping("/admin/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsService ingredientsService;
	
	public ResponseEntity<IngredientsCategory> createIngredientCategory(
			@RequestBody IngredientsCategoryRequest req){
		return null;
	}
}
