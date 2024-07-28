package com.jwt.implementation.jwt.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.implementation.jwt.demo.entity.ShopUser;

@Repository
public interface ShopRepo extends JpaRepository<ShopUser, Integer>{
	
	ShopUser getByUsername(String username);
}
