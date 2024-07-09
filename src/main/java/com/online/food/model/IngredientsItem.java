package com.online.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class IngredientsItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne
	private IngredientsCategory category;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean inStock=true;

	public IngredientsItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientsItem(Long id, String name, IngredientsCategory category, Restaurant restaurant, boolean inStock) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.restaurant = restaurant;
		this.inStock = inStock;
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

	public IngredientsCategory getCategory() {
		return category;
	}

	public void setCategory(IngredientsCategory category) {
		this.category = category;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	
}
