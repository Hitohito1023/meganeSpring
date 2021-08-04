package com.megane.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "account")
	private String account;

	@Column(name= "admin_flg")
	private Boolean adminFlg;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "team")
	private Integer team;

	@Column(name = "suspended")
	private Boolean suspended;

	@Column(name = "created_date")
	private Date created_date;
}