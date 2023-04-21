package com.armezo.easysurvey.sc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="srv_singlechoice_ques_master")
public class SinglechoiceMaster {
	
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
	
	@OneToMany(mappedBy="singlechoiceMaster")
	@Cascade({CascadeType.ALL})
	private List<SinglechoiceOptionMaster> singlechoiceOptionMaster=new ArrayList<SinglechoiceOptionMaster>();
	
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

	public List<SinglechoiceOptionMaster> getSinglechoiceOptionMaster() {
		return singlechoiceOptionMaster;
	}

	public void setSinglechoiceOptionMaster(List<SinglechoiceOptionMaster> singlechoiceOptionMaster) {
		this.singlechoiceOptionMaster = singlechoiceOptionMaster;
	}

	@Override
	public String toString() {
		return "SinglechoiceMaster [qid=" + qid + ", question=" + question + ", surveyId=" + surveyId + ", DimansionId="
				+ DimansionId + ", mqstatus=" + mqstatus + ", status=" + status + ", singlechoiceOptionMaster="
				+ singlechoiceOptionMaster + "]";
	}

	

}
