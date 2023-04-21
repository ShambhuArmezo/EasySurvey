package com.armezo.easysurvey.sc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_survey_mapping")
public class SurveyClientMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long surveyId;
	private Long clientId;
	private String status; /// A
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SurveyClientMapping [id=" + id + ", surveyId=" + surveyId + ", clientId=" + clientId + ", status="
				+ status + "]";
	}
	
	
	
	
	//Alt+s
	
	

}
