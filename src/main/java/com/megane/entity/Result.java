package com.megane.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Entity
@Data
@Table(name = "results")
public class Result {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "user_id")
	@OneToOne
	private User user;

	@Column(name = "drive_type")
	private int driveType;

	@Column(name = "analyze_type")
	private int analyzeType;

	@Column(name = "create_type")
	private int createType;

	@Column(name = "volunteer_type")
	private int volunteerType;

	@Column(name = "drive_flg")
	private boolean driveFlag;

	@Column(name = "analyze_flg")
	private boolean analyzeFlag;

	@Column(name = "create_flg")
	private boolean createFlg;

	@Column(name = "volunteer_flg")
	private boolean volunteerFlg;

	@Column(name = "created_date")
	private Date createdDate;

	@PrePersist
	public void onPrePresist() {
		setCreatedDate(new Date());
	}
}
