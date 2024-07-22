package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.IngredientsCategory;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategory, Long> {

}
