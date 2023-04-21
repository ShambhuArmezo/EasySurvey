package com.armezo.easysurvey.sc.payload;


public class FilterPayload {
	
	private String fromDate;
	private String toDate;
	private String accesskey;
	private String name;
	private String employeeId;
	private Long clientId;
	private Long surveyId;
	private Integer status;
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FilterPayload [fromDate=" + fromDate + ", toDate=" + toDate + ", accesskey=" + accesskey + ", name="
				+ name + ", employeeId=" + employeeId + ", clientId=" + clientId + ", surveyId=" + surveyId
				+ ", status=" + status + "]";
	}
	
	
	

}
