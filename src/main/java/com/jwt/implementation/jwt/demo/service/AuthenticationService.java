package com.jwt.implementation.jwt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.implementation.jwt.demo.entity.ShopUser;
import com.jwt.implementation.jwt.demo.model.LoginUserDto;
import com.jwt.implementation.jwt.demo.repo.ShopRepo;

@Service
public class AuthenticationService {
	
	 private final ShopRepo userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final AuthenticationManager authenticationManager;
	
	 public AuthenticationService(
		        ShopRepo userRepository,
		        AuthenticationManager authenticationManager,
		        PasswordEncoder passwordEncoder
		    ) {
		        this.authenticationManager = authenticationManager;
		        this.userRepository = userRepository;
		        this.passwordEncoder = passwordEncoder;
		    }
	
	public ShopUser authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getUserName(),
                input.getPassword()
            )
        );

        return userRepository.getByUsername(input.getUserName());
    }
}
