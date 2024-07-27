package com.online.food.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.model.Cart;
import com.online.food.model.CartItem;
import com.online.food.model.Food;
import com.online.food.model.User;
import com.online.food.repository.CartItemsRepository;
import com.online.food.repository.CartRepository;
import com.online.food.repository.FoodRepository;
import com.online.food.repository.UserRepository;
import com.online.food.request.AddCartItemsRequest;
import com.online.food.service.CartService;
import com.online.food.service.FoodService;
import com.online.food.service.UserService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(AddCartItemsRequest req, String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Food food = foodService.findFoodById(req.getFoodId());
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItem()) {
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setFood(food);
		cartItem.setCart(cart);
		cartItem.setQuantity(req.getQuantity());
		cartItem.setIngredients(req.getIngredients());
		cartItem.setTotalPrice(req.getQuantity()*food.getPrice());
		
		CartItem saveCartItem = cartItemsRepository.save(cartItem);
		
		cart.getItem().add(saveCartItem);
		
		return saveCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItemOptional = cartItemsRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		CartItem cartItem = cartItemOptional.get();
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(cartItem.getFood().getPrice()*quantity);
		return cartItemsRepository.save(cartItem);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> cartItemOptional = cartItemsRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		
		CartItem item = cartItemOptional.get();
		cart.getItem().remove(item);
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		
		for(CartItem cartItem :cart.getItem()) {
			total += cartItem.getFood().getPrice() * cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional <Cart> optionalCart = cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new Exception("cart not found with id: " + id);
		}
		
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserid(Long userId) throws Exception {
//		User user = userService.findUserByJwtToken(jwt);
		Cart cart =  cartRepository.findByCustomerId(userId);
		cart.setTotal(calculateCartTotals(cart));
		return cart;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
//		User user = userService.findUserByJwtToken(jwt);
		Cart cart = findCartByUserid(userId);
		cart.getItem().clear();
		return cartRepository.save(cart);
	}

}
