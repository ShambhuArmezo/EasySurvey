package com.armezo.easysurvey.sc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "survey_details")
public class SurveyDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long clientId;
	private Long surveyId;
	private Long questionId;
	private Long dimensionId;
	private Integer tid;
	private Integer sectionNo;
	private Integer orderNo;
	
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return tid;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}
	
	public Integer getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "SurveyDetails [id=" + id + ", clientId=" + clientId + ", surveyId=" + surveyId + ", questionId="
				+ questionId + ", dimensionId=" + dimensionId + ", tid=" + tid + ", sectionNo=" + sectionNo
				+ ", orderNo=" + orderNo + "]";
	}
	
	
	
	

}
