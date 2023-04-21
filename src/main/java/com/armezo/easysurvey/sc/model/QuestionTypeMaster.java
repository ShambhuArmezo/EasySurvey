package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Question_Type_Master")
public class QuestionTypeMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tid;
	
	@Column(name="question_type")
	private String questionType;
	
	@Column(name="question_table")
	private String questionTable;
	
	@Column(name="ans_table")
	private String ansTable;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionTable() {
		return questionTable;
	}

	public void setQuestionTable(String questionTable) {
		this.questionTable = questionTable;
	}

	public String getAnsTable() {
		return ansTable;
	}

	public void setAnsTable(String ansTable) {
		this.ansTable = ansTable;
	}

	@Override
	public String toString() {
		return "QuestionTypeMaster [tid=" + tid + ", questionType=" + questionType + ", questionTable=" + questionTable
				+ ", ansTable=" + ansTable + "]";
	}
	
	

}
