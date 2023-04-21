package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="surveyenddate_master")
public class SurveyEndDateMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String surveyDate;
	private Long surveyId;
	private Long clientId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
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
	@Override
	public String toString() {
		return "SurveyEndDateMaster [id=" + id + ", surveyDate=" + surveyDate + ", surveyId=" + surveyId + ", clientId="
				+ clientId + "]";
	}


}
