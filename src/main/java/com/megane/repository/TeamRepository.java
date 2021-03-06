package com.megane.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megane.entity.Team;



@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>  {
	 Optional<Team> findById(Integer id);

}
