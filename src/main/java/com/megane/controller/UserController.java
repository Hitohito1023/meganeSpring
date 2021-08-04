package com.megane.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.M.utils.CipherUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megane.entity.User;
import com.megane.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@CrossOrigin
	@GetMapping("/get")
	public List<User> getAllUsers() throws IOException {
		return userRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/get/{userId}")
	public Optional<User> getByUserId(@PathVariable("userId") Integer userId) throws IOException {
		return userRepository.findById(userId);
	}

	//最初に重複の確認を行うために、getAllをしてaccountの重複がないかどうかフロントで確認する。
	@CrossOrigin
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<List<User>> addUser(@RequestBody String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);
		String account = node.get("account").textValue();
		Boolean adminFlg = node.get("adminFlg").asBoolean();
		String name = node.get("name").textValue();
		String password = node.get("password").textValue();
		Integer team = node.get("team").asInt();
		Boolean suspended = node.get("suspended").asBoolean();
		Date createdDate = java.sql.Date.valueOf(node.get("createdDate").textValue());
		//entityのUserへset
		User user = new User();
		user.setAccount(account);
		user.setAdminFlg(adminFlg);
		user.setName(name);
		user.setPassword(CipherUtil.encrypt(password));
		user.setTeam(team);
		user.setSuspended(suspended);
		user.setCreatedDate(createdDate);
		userRepository.save(user);

		List<User> users = userRepository.findAll();
		return ResponseEntity.ok().header("ContentType", MediaType.APPLICATION_JSON_VALUE.toString()).body(users);
	}

	//管理者のみ可能
	@CrossOrigin
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<User>> deleteUser(@PathVariable("id") Integer id) {
	    userRepository.deleteById(id);
	    List<User> users = userRepository.findAll();
		return ResponseEntity.ok().header("ContentType", MediaType.APPLICATION_JSON_VALUE.toString()).body(users);
	}

	//idの指定について、管理者・利用者の情報更新時はcookieを、その他のユーザーの更新時はgetしたidをstoreで参照する
	//ログインユーザー自身の情報更新時はcookieの値もフロント側で変更する
	@CrossOrigin
	@ResponseBody
	@PostMapping("/edit")
	public ResponseEntity<List<User>> editUser(@RequestBody User user) throws IOException {
		User tergetUser = userRepository.getById(user.getId());
		if(StringUtils.hasLength(user.getPassword())) {
			user.setPassword(tergetUser.getPassword());
		} else {
			// パスワード暗号化
			user.setPassword(CipherUtil.encrypt(user.getPassword()));
		}
		userRepository.save(user);
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok().header("ContentType", MediaType.APPLICATION_JSON_VALUE.toString()).body(users);
	}
}

