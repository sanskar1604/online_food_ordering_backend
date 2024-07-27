package com.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
	public List<Order> findByCutomerId(Long userId);
	
	public List<Order> findByRestaurantId(Long restaurantId);
}

