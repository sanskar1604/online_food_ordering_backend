package com.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.food.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	
	List<Food> findByRestaurantId(Long restaurantId);
	
//	@Query("SELECT f FROM Food f where f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
//	List<Food> searchFood(@Param("keyword") String kewyword);
}
