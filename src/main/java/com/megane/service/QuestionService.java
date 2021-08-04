package com.megane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megane.entity.Question;
import com.megane.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;

	//全件取得
	public List<Question> findAll(){
		return questionRepository.findAll();
	}

}
