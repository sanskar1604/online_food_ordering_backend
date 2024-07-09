package com.online.food.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private Long price;
	
	@ManyToOne
	private Category foodCategoy;
	
//	@ElementCollection -- Created separate table for food images
	@Column(length=1000)
	@ElementCollection  
	private List<String> images;
	
	private boolean available;	
	
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean isVegetarian;
	private boolean isSeasonal;
	
	@ManyToMany
	private List<IngredientsItem> ingredientsItem = new ArrayList<>();
	
	private Date creationDate;

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Food(Long id, String name, String description, Long price, Category foodCategoy, List<String> images,
			boolean available, Restaurant restaurant, boolean isVegetarian, boolean isSeasonal,
			List<IngredientsItem> ingredientsItem, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.foodCategoy = foodCategoy;
		this.images = images;
		this.available = available;
		this.restaurant = restaurant;
		this.isVegetarian = isVegetarian;
		this.isSeasonal = isSeasonal;
		this.ingredientsItem = ingredientsItem;
		this.creationDate = creationDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getFoodCategoy() {
		return foodCategoy;
	}

	public void setFoodCategoy(Category foodCategoy) {
		this.foodCategoy = foodCategoy;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isSeasonal() {
		return isSeasonal;
	}

	public void setSeasonal(boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}

	public List<IngredientsItem> getIngredientsItem() {
		return ingredientsItem;
	}

	public void setIngredientsItem(List<IngredientsItem> ingredientsItem) {
		this.ingredientsItem = ingredientsItem;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
