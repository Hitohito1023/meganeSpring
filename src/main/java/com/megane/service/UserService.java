package com.megane.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megane.entity.User;
import com.megane.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User findUser(Integer id) {
		return userRepository.findById(id).get();
	}

}
