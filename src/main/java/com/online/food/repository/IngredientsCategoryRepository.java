package com.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.IngredientsCategory;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategory, Long> {

	List<IngredientsCategory> findByRestaurantId(Long id);
}
