package com.megane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megane.entity.Team;
import com.megane.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
	private final TeamRepository teamRepository;

	//全チーム取得
	@GetMapping("/get")
	public List<Team> findAll(){
		return teamRepository.findAll();
	}

	//チーム削除
	@DeleteMapping("/delete/{id}")
	public void  delete(@PathVariable("id") Integer id){
		teamRepository.deleteById(id);
	}

	//チーム追加
	@PostMapping("/add")
	public List<Team> save(@RequestBody Team request){
		Team team = new Team();
		team.setName(request.getName());
		teamRepository.save(team);
		return teamRepository.findAll();
	}

}
