package com.megane.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Entity
@Data
@Table(name = "types")
public class Type {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String desire;

	@Column
	private String keyword;

	@Column
	private String impression;

	@Column(name = "happy_word")
	private String happyWord;

	@Column
	private String detail;

	@Column
	private String stress;

	@Column
	private String strength;

	@Column
	private String weekness;

}
