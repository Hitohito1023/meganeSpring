package com.megane.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megane.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {

	public List<Result> findByUserId(int id);
}
