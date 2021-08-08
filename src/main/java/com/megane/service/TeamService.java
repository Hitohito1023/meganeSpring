package com.megane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megane.entity.Team;
import com.megane.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	//全件取得
	public List<Team> findAll(){
		return teamRepository.findAll();
	}

	public Team findTeamById(Integer id) {
		return teamRepository.findById(id).get();
	}
}
