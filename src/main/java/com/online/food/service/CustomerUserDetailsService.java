package com.online.food.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.online.food.model.USER_ROLE;
import com.online.food.model.User;
import com.online.food.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if(user != null) {
			throw new UsernameNotFoundException("username name not found with email "+username);
		}
		USER_ROLE role = user.getRole();
		if(role == null) {
			role = USER_ROLE.ROLE_CUSTOMER;
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		return new  org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
