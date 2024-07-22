package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.IngredientsItem;

public interface IngredientsItemsRepository extends JpaRepository<IngredientsItem, Long> {

	
}
