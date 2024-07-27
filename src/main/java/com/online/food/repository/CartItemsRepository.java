package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.CartItem;

public interface CartItemsRepository extends JpaRepository<CartItem, Long> {

}
