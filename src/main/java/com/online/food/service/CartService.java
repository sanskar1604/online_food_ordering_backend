package com.online.food.service;

import com.online.food.model.Cart;
import com.online.food.model.CartItem;
import com.online.food.request.AddCartItemsRequest;

public interface CartService {

	public CartItem addItemToCart(AddCartItemsRequest req, String jwt) throws Exception;
	
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
	
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
	
	public Long calculateCartTotals(Cart cart) throws Exception;
	
	public Cart findCartById(Long id) throws Exception;
	
	public Cart findCartByUserid(Long userId) throws Exception;
	
	public Cart clearCart(Long userId) throws Exception;
}
