package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Cart findByCustomerId(Long userId);

}
