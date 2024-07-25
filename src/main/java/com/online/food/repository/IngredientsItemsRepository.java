package com.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.IngredientsItem;

public interface IngredientsItemsRepository extends JpaRepository<IngredientsItem, Long> {

	List<IngredientsItem> findByRestaurantId(Long id);
}
