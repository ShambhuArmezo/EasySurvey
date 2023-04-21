package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email_template")
public class EmailTempleteMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="subjct_line")
	private String subjctLine;
	
	@Column(name="template_type")
	private String templateType;
	
	private Long clientId;
	
	private Long surveyId;
	
	@Column(name="status",columnDefinition = "varchar(2) default 'A'")
	private String status;
	
	@Column(length = 5000)
	private String template;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSubjctLine() {
		return subjctLine;
	}

	public void setSubjctLine(String subjctLine) {
		this.subjctLine = subjctLine;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmailTempleteMaster [id=" + id + ", subjctLine=" + subjctLine + ", templateType=" + templateType
				+ ", clientId=" + clientId + ", surveyId=" + surveyId + ", status=" + status + ", template=" + template
				+ "]";
	}

	
}
