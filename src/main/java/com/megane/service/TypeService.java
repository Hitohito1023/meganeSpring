package com.megane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megane.entity.Type;
import com.megane.repository.TypeRepository;

@Service
public class TypeService {

	@Autowired
	TypeRepository typeRepository;

	//typeの全件取得
	public List<Type> findAllType() {
		return typeRepository.findAll();
	}
}
