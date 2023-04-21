package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instruction_master")
public class AddLandingMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="instruction",length = 5000)
	private String instruction;
	
	private Long clientId;
	
	private Long surveyId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "AddLandingMaster [id=" + id + ", instruction=" + instruction + ", clientId=" + clientId + ", surveyId="
				+ surveyId + "]";
	}

	

}
