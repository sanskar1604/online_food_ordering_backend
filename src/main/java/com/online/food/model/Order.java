package com.online.food.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private Long totalAmount;
	private String orderStatus;
	private Date createdDate;
	
	@ManyToOne
	private Address deliveryAddress;
	
	@OneToMany
	private List<Orderitem> items;
	
//	private Payment payment;
	
	private int totalItem;
	private int totalPrice;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, User customer, Restaurant restaurant, Long totalAmount, String orderStatus, Date createdDate,
			Address deliveryAddress, List<Orderitem> items, int totalItem, int totalPrice) {
		super();
		this.id = id;
		this.customer = customer;
		this.restaurant = restaurant;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.createdDate = createdDate;
		this.deliveryAddress = deliveryAddress;
		this.items = items;
		this.totalItem = totalItem;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<Orderitem> getItems() {
		return items;
	}

	public void setItems(List<Orderitem> items) {
		this.items = items;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
