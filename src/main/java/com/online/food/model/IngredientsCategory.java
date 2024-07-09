package com.online.food.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class IngredientsCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL)
	private List<IngredientsItem> ingredientItem = new ArrayList<>();

	public IngredientsCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientsCategory(Long id, String name, Restaurant restaurant, List<IngredientsItem> ingredientItem) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant = restaurant;
		this.ingredientItem = ingredientItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<IngredientsItem> getIngredientItem() {
		return ingredientItem;
	}

	public void setIngredientItem(List<IngredientsItem> ingredientItem) {
		this.ingredientItem = ingredientItem;
	}
	
	
	
}
