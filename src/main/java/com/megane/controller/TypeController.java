package com.megane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.megane.entity.Type;
import com.megane.service.TypeService;

@RestController
@RequestMapping("type")
public class TypeController {

	@Autowired
	TypeService typeService;

	//typeの全件取得
	@CrossOrigin
	@GetMapping(value="/get")
	public List<Type> getType() throws JsonProcessingException {
		List<Type> types = typeService.findAllType();

		return types;
	}

}
