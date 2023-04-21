package com.armezo.easysurvey.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="srv_multichoice_answer")
public class MultichoiceAnswer {
	
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
	
	@Column(name="qid")
	private Long qid;
	
	@Column(name="answer")
	private String answer;

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

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "MultichoiceAnswer [id=" + id + ", empcode=" + empcode + ", surveyId=" + surveyId + ", clientId="
				+ clientId + ", accesskey=" + accesskey + ", qid=" + qid + ", answer=" + answer + "]";
	}
	
	

}
