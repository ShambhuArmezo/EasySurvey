package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="srv_singlechoice_ques_option")
public class SinglechoiceOptionMaster {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long optionId;
	private String qoption;
	private Integer score;
	@Column(columnDefinition = "varchar(10) default 'A'")
	private String status;
	
	@ManyToOne() 
	@JoinColumn(name="qid")
	@Cascade({CascadeType.ALL})
	private SinglechoiceMaster singlechoiceMaster;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getQoption() {
		return qoption;
	}

	public void setQoption(String qoption) {
		this.qoption = qoption;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SinglechoiceMaster getSinglechoiceMaster() {
		return singlechoiceMaster;
	}

	public void setSinglechoiceMaster(SinglechoiceMaster singlechoiceMaster) {
		this.singlechoiceMaster = singlechoiceMaster;
	}

	@Override
	public String toString() {
		return "SinglechoiceOptionMaster [optionId=" + optionId + ", qoption=" + qoption + ", score=" + score
				+ ", status=" + status + ", singlechoiceMaster=" + singlechoiceMaster + "]";
	}
	
	

}
