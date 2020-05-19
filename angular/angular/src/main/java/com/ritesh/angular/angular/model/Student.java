package com.ritesh.angular.angular.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="std")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STU_SEQ")
	@SequenceGenerator(sequenceName = "student_seq", allocationSize = 1, name = "STU_SEQ")
	private Integer stdId;
	@NonNull
	private String stdName;
	@NonNull
	private Double stdFee;
	@NonNull
	private String stdCourse;

	public Student() {
		super();
	}

	public Student(Integer stdId, String stdName, Double stdFee, String stdCourse) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.stdFee = stdFee;
		this.stdCourse = stdCourse;
	}

	public Integer getStdId() {
		return stdId;
	}

	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public Double getStdFee() {
		return stdFee;
	}

	public void setStdFee(Double stdFee) {
		this.stdFee = stdFee;
	}

	public String getStdCourse() {
		return stdCourse;
	}

	public void setStdCourse(String stdCourse) {
		this.stdCourse = stdCourse;
	}

}
