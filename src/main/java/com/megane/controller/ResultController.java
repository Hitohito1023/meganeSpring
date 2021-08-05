package com.megane.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megane.entity.Result;
import com.megane.entity.User;
import com.megane.service.ResultService;
import com.megane.service.UserService;

@RestController
@RequestMapping("result")
public class ResultController {

	@Autowired
	ResultService resultService;

	@Autowired
	UserService userService;


	//resultの全件取得（user関係なし）
	@CrossOrigin
	@GetMapping(value="/get")
	public List<Result> getResult() throws JsonProcessingException {
		List<Result> results = resultService.findAllResult();

		return results;
	}

	//resultの全件取得（特定userのみ）
	@CrossOrigin
	@GetMapping(value="/get/user/{id}")
	public List<Result> getResultByUser(@PathVariable Integer id) throws JsonProcessingException {
		List<Result> results = resultService.findByUserId(id);

		return results;
	}



	//resultの一件登録
	@CrossOrigin
	@PostMapping(value="/add")
	public void addResult(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		//JSON変換用
		ObjectMapper mapper = new ObjectMapper();

		Result result = new Result();
		JsonNode node = mapper.readTree(json);

		//JSONから各タイプのスコアを取得＆セット
		int driveScore = node.get("driveType").asInt();
		int analyzeScore = node.get("analyzeType").asInt();
		int createScore = node.get("createType").asInt();
		int volunteerScore = node.get("volunteerType").asInt();
		result.setDriveType(driveScore);
		result.setAnalyzeType(analyzeScore);
		result.setCreateType(createScore);
		result.setVolunteerType(volunteerScore);

		//配列にJSONで送られた各タイプのスコアを格納
		int[] scoreArray = {driveScore, analyzeScore, createScore, volunteerScore};

		//各タイプ名をKey、得点をValueとして格納
		HashMap<String, Integer> scoreMap = new HashMap<String, Integer>();
		scoreMap.put("drive", scoreArray[0]);
		scoreMap.put("analyze", scoreArray[1]);
		scoreMap.put("create", scoreArray[2]);
		scoreMap.put("volunteer", scoreArray[3]);

		//スコアの最大値を求める
		int maxScore = scoreArray[0];
		for(int i = 1; i < scoreArray.length; i++) {
			if(maxScore < scoreArray[i]) {
				maxScore = scoreArray[i];
			}
		}

		//得点が最大値のタイプ名と得点を別のMapに格納（最大値が複数ある場合もあるためMapを使用）
		HashMap<String, Integer> maxScoreMap = new HashMap<String, Integer>();
		for(HashMap.Entry<String, Integer> entry : scoreMap.entrySet()) {
			if(entry.getValue() == 10) {
				String maxKey = entry.getKey();
				int maxValue = entry.getValue();
				maxScoreMap.put(maxKey, maxValue);
			}
		}

		//最大値のタイプのFlgを立てる（最大値が複数あるとFlgが複数立つ）
		if(maxScoreMap.containsKey("drive")) {
			result.setDriveFlag(true);
		}
		if(maxScoreMap.containsKey("analyze")) {
			result.setAnalyzeFlag(true);
		}
		if(maxScoreMap.containsKey("create")) {
			result.setCreateFlg(true);
		}
		if(maxScoreMap.containsKey("volunteer")) {
			result.setVolunteerFlg(true);
		}

		//UserとjoinしているのでJSONのuserIdを頼りにUserを検索＆該当Userをセット
		int userId = node.get("userId").asInt();
		User user = userService.findUser(userId);
		result.setUser(user);

		resultService.saveResult(result);

	}

}
