package com.armezo.easysurvey.admin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_master")
public class EmployeeMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String employeeId;
    private String name;
    private String accesskey; //(6 digits)
    private String email;
    private String status;  // Default 'A'
    private String zone;
    private Long clientId;
    private Long surveyId;
    private String region;
    private String department;
    private Date dateOfJoining;
    private Date registrationDate;
    private String reportingManagerName;
    private Long reportingManagerId;
    
    
    
    public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	private String employeeCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public Long getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(Long reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public String getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(String employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", zone="
				+ zone + ", region=" + region + ", department=" + department + ", dateOfJoining=" + dateOfJoining
				+ ", reportingManagerName=" + reportingManagerName + ", reportingManagerId=" + reportingManagerId
				+ ", employeeCategory=" + employeeCategory + "]";
	}

	
    

}
