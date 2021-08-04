package com.megane.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Question {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="text")
	private String text;

	@Column(name="drive_select")
	private String driveSelect;

	@Column(name="volunteer_select")
	private String volunteerSelect;

	@Column(name="create_select")
	private String createSelect;

	@Column(name="analyze_select")
	private String analyzeSelect;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDriveSelect() {
		return driveSelect;
	}

	public void setDriveSelect(String driveSelect) {
		this.driveSelect = driveSelect;
	}

	public String getVolunteerSelect() {
		return volunteerSelect;
	}

	public void setVolunteerSelect(String volunteerSelect) {
		this.volunteerSelect = volunteerSelect;
	}

	public String getCreateSelect() {
		return createSelect;
	}

	public void setCreateSelect(String createSelect) {
		this.createSelect = createSelect;
	}

	public String getAnalyzeSelect() {
		return analyzeSelect;
	}

	public void setAnalyzeSelect(String analyzeSelect) {
		this.analyzeSelect = analyzeSelect;
	}

}
