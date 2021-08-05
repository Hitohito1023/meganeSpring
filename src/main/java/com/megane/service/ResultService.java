package com.megane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megane.entity.Result;
import com.megane.repository.ResultRepository;

@Service
public class ResultService {

	@Autowired
	ResultRepository resultRepository;

	//resultの全件取得
	public List<Result> findAllResult() {
		return resultRepository.findAll();
	}

	public List<Result> findByUserId(int id) {
		return resultRepository.findByUserId(id);
	}


	//resultの一件保存
	public void saveResult(Result result) {
		resultRepository.save(result);
	}
}
