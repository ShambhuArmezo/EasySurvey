package com.armezo.easysurvey.sc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="srv_multichoice_ques_master")
public class MultiplechoiceMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long qid;
	@Column(length = 5000)
	private String question;
	private Long surveyId;
	private Long DimansionId;
	private Integer mqstatus;
	@Column(columnDefinition = "varchar(10) default 'A'")
	private String status;
	
	@OneToMany(mappedBy="multiplechoiceMaster")
	@Cascade({CascadeType.ALL})
	private List<MultiplechoiceOptionMaster> multiplechoiceOptionMaster=new ArrayList<MultiplechoiceOptionMaster>();

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public Long getDimansionId() {
		return DimansionId;
	}

	public void setDimansionId(Long dimansionId) {
		DimansionId = dimansionId;
	}

	public Integer getMqstatus() {
		return mqstatus;
	}

	public void setMqstatus(Integer mqstatus) {
		this.mqstatus = mqstatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MultiplechoiceOptionMaster> getMultiplechoiceOptionMaster() {
		return multiplechoiceOptionMaster;
	}

	public void setMultiplechoiceOptionMaster(List<MultiplechoiceOptionMaster> multiplechoiceOptionMaster) {
		this.multiplechoiceOptionMaster = multiplechoiceOptionMaster;
	}

	@Override
	public String toString() {
		return "MultiplechoiceMaster [qid=" + qid + ", question=" + question + ", surveyId=" + surveyId
				+ ", DimansionId=" + DimansionId + ", mqstatus=" + mqstatus + ", status=" + status
				+ ", multiplechoiceOptionMaster=" + multiplechoiceOptionMaster + "]";
	}
	
	

}
