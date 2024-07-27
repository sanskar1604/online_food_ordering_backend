package com.online.food.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.model.Address;
import com.online.food.model.Cart;
import com.online.food.model.CartItem;
import com.online.food.model.Order;
import com.online.food.model.Orderitem;
import com.online.food.model.Restaurant;
import com.online.food.model.User;
import com.online.food.repository.AddressRepository;
import com.online.food.repository.OrderItemRepository;
import com.online.food.repository.OrderRepository;
import com.online.food.repository.UserRepository;
import com.online.food.request.OrderRequest;
import com.online.food.service.CartService;
import com.online.food.service.OrderService;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress = order.getDeliveryAddress();
		Address saveAddress = addressRepository.save(shippAddress);
		
		if(!user.getAddresses().contains(saveAddress)) {
			user.getAddresses().add(saveAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		Order createOrder = new Order();
		createOrder.setCustomer(user);
		createOrder.setCreatedDate(new Date());
		createOrder.setOrderStatus("PENDING");
		createOrder.setDeliveryAddress(saveAddress);
		createOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserid(user.getId());
		List<Orderitem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem: cart.getItem()) {
			Orderitem orderItem = new Orderitem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			Orderitem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long totalPrice = cartService.calculateCartTotals(cart);
		
		createOrder.setItems(orderItems);
		createOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createOrder);
		restaurant.getOrders().add(savedOrder);
		
		return createOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		if(orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		
		return orderRepository.findByCutomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if(orderStatus != null) {
			orders = orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		return orders;
	}
	
	@Override
	public Order findOrderById(Long orderId) throws Exception{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("Order not found");
		}
		return optionalOrder.get();
	}

}
