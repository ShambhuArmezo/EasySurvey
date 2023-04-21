package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="srv_comment_ques_master")
public class CommentMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long qid;
	@Column(length = 5000)
	private String question;
	private Long surveyId;
	private Long DimansionId;
	private Integer mqstatus;
	@Column(columnDefinition = "varchar(10) default 'A'")
	private String status;
	
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public Long getDimansionId() {
		return DimansionId;
	}

	public void setDimansionId(Long dimansionId) {
		DimansionId = dimansionId;
	}
	public Integer getMqstatus() {
		return mqstatus;
	}
	public void setMqstatus(Integer mqstatus) {
		this.mqstatus = mqstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CommentMaster [qid=" + qid + ", question=" + question + ", surveyId=" + surveyId + ", DimansionId="
				+ DimansionId + ", mqstatus=" + mqstatus + ", status=" + status + "]";
	}
	
	

}
