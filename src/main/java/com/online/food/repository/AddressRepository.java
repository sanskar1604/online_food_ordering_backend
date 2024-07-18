package com.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.food.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
