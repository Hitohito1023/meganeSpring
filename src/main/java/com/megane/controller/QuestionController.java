package com.megane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megane.entity.Question;
import com.megane.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
	private final QuestionRepository questionRepository;

	@GetMapping
	public List<Question> getAll() {
	return questionRepository.findAll();
	}

}
