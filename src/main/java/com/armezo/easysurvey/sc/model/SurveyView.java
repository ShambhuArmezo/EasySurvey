package com.armezo.easysurvey.sc.model;

public class SurveyView {
	
	private Long surveyId;
	private Long clientId;
	private Long dimensionId;
	private String surveyName;
	private String clientName;
	private String dimensionName;
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
	public Long getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getDimensionName() {
		return dimensionName;
	}
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	@Override
	public String toString() {
		return "SurveyView [surveyId=" + surveyId + ", clientId=" + clientId + ", dimensionId=" + dimensionId
				+ ", surveyName=" + surveyName + ", clientName=" + clientName + ", dimensionName=" + dimensionName
				+ "]";
	}
	
	

}
