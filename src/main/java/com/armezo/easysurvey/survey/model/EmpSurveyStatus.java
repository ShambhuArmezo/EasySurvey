package com.armezo.easysurvey.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="emp_survey_status_detail")
public class EmpSurveyStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="empcode")
	private String empcode;
	
	@Column(name="survey_id")
	private Long surveyId;
	
	@Column(name="client_id")
	private Long clientId;
	
	@Column(name="accesskey")
	private String accesskey;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="startdate")
	private Date startdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="enddate")
	private Date enddate;
	
	@Column(name="rhost")
	private String rhost;
	
	@Column(name="raddress")
	private String raddress;
	
	@Column(name="surveystatus")
	private Integer surveystatus;
	
	@Column(name="currsec")
	private Integer currsec;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
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

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getRhost() {
		return rhost;
	}

	public void setRhost(String rhost) {
		this.rhost = rhost;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public Integer getSurveystatus() {
		return surveystatus;
	}

	public void setSurveystatus(Integer surveystatus) {
		this.surveystatus = surveystatus;
	}

	public Integer getCurrsec() {
		return currsec;
	}

	public void setCurrsec(Integer currsec) {
		this.currsec = currsec;
	}

	@Override
	public String toString() {
		return "EmpSurveyStatus [id=" + id + ", empcode=" + empcode + ", surveyId=" + surveyId + ", clientId="
				+ clientId + ", accesskey=" + accesskey + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", rhost=" + rhost + ", raddress=" + raddress + ", surveystatus=" + surveystatus + ", currsec="
				+ currsec + "]";
	}

	
}
