package com.jwt.implementation.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.implementation.jwt.demo.entity.ShopUser;
import com.jwt.implementation.jwt.demo.model.LoginResponse;
import com.jwt.implementation.jwt.demo.model.LoginUserDto;
import com.jwt.implementation.jwt.demo.service.AuthenticationService;
import com.jwt.implementation.jwt.demo.service.JWTService;
import com.jwt.implementation.jwt.demo.service.UserDetailIServiceImpl;

@RestController
public class ShopController {
	
	@Autowired
	private UserDetailIServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JWTService jwtService;
	
	@GetMapping("/home")
	public String welcome() {
		return "Hi welcome to the shop";
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
		//UserDetails authenticatedUser = userDetailsServiceImpl.loadUserByUsername(loginUserDto.getUserName());
		ShopUser authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
