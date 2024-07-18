package com.online.food.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.food.dto.RestaurantDto;
import com.online.food.model.Address;
import com.online.food.model.Restaurant;
import com.online.food.model.User;
import com.online.food.repository.AddressRepository;
import com.online.food.repository.RestaurantRepository;
import com.online.food.repository.UserRepository;
import com.online.food.request.CreateRestaurantRequest;
import com.online.food.service.RestaurantService;
import com.online.food.service.UserService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	//Create new restaurant
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		
		Address address = addressRepository.save(req.getAddress());
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCusineType(req.getCusineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		return restaurantRepository.save(restaurant);
	}

	//Update existing restaurant using restaurant id
	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
		Restaurant restaurant = new Restaurant();
		
		if(restaurant.getCusineType()!=null) {
			restaurant.setCusineType(updateRestaurant.getCusineType());
		}
		if(restaurant.getDescription()!= null) {
			restaurant.setDescription(updateRestaurant.getDescription());
		}
		if(restaurant.getName()!=null) {
			restaurant.setName(updateRestaurant.getName());
		}
		return restaurantRepository.save(restaurant);
	}

	//Delete restaurant using restaurantId
	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = findRestaurantById(restaurantId);
		restaurantRepository.delete(restaurant);
	}

	//Method to get list of restaurant
	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("restaurant not found with id: "+id);
		}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
		if(restaurant == null) {
			throw new Exception("Restaurant not found with owner id: "+userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		RestaurantDto restaurantDto = new RestaurantDto();
		restaurantDto.setDescription(restaurant.getDescription());
		restaurantDto.setImages(restaurant.getImages());
		restaurantDto.setTitle(restaurant.getName());
		restaurantDto.setId(restaurantId);
		
		if(user.getFavorites().contains(restaurantDto)) {
			user.getFavorites().remove(restaurantDto);
		}else {
			user.getFavorites().add(restaurantDto);
		}
		userRepository.save(user);
		return restaurantDto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant = findRestaurantById(id);
		restaurant.setOpen(restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

	
}
