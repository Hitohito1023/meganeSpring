package com.megane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megane.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByAccountAndPassword(String account, String password);
}
