package com.jwt.implementation.jwt.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.implementation.jwt.demo.entity.ShopUser;
import com.jwt.implementation.jwt.demo.repo.ShopRepo;

@Service
public class UserDetailIServiceImpl implements UserDetailsService{

	@Autowired
	private ShopRepo shopRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ShopUser shopUser = shopRepo.getByUsername(username);
		if(shopUser == null) {
			throw new UsernameNotFoundException("user name not found");
		}
		
		return User.withUsername(shopUser.getUsername())
                .password(shopUser.getPassword())
                .authorities(new SimpleGrantedAuthority(shopUser.getRole()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
	}

}
