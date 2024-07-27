package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long> {

}
