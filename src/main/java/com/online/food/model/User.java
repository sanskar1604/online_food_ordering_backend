package com.online.food.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.food.dto.RestaurantDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String fullName;
	private String email;
	private String password;
	private USER_ROLE role;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	private List<RestaurantDto> favorites = new ArrayList<>();
	
//	CascadeType.ALL -- when we delete user, all the address related to that user also deleted.
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Address> addresses = new ArrayList<>();
	
	
	
}
