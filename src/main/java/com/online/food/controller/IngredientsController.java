package com.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.food.model.IngredientsCategory;
import com.online.food.model.IngredientsItem;
import com.online.food.request.IngredientsCategoryRequest;
import com.online.food.request.IngredientsRequest;
import com.online.food.service.IngredientsService;
import com.online.food.service.UserService;

@RestController
@RequestMapping("/admin/ingredients")
@CrossOrigin("*")
public class IngredientsController {

	@Autowired
	private IngredientsService ingredientsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/category")
	public ResponseEntity<IngredientsCategory> createIngredientCategory(
			@RequestBody IngredientsCategoryRequest req) throws Exception{
		
		IngredientsCategory item = ingredientsService.createIngredientsCategory(req.getName(), req.getRestaurantId());
		
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	@PostMapping("")
	public ResponseEntity<IngredientsItem> createIngredientItem(
			@RequestBody IngredientsRequest req) throws Exception{
		
		IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
		
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientsItem> updateIngredientStock(
			@PathVariable Long id) throws Exception{
		
		IngredientsItem item = ingredientsService.updateStock(id);
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
			@PathVariable Long id) throws Exception{
		
		List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(id);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(
			@PathVariable Long id) throws Exception{
		
		List<IngredientsCategory> items = ingredientsService.findIngredientsCategoryByRestaurantId(id);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
}
