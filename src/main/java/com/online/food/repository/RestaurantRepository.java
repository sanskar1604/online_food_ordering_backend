package com.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.food.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	@Query("SELECT r FROM RESTAURANT r WHERE lower(r.name) like lower(concat('%',:query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%',:query, '%'))")
	List<Restaurant> findBySearchQuery(String query);
	Restaurant findByOwnerId(Long userId);
}
