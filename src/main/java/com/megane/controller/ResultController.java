package com.megane.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megane.service.ResultService;

@RestController
@RequestMapping("result")
public class ResultController {

	@Autowired
	ResultService resultService;



}
