package com.armezo.easysurvey.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dimension_master")
public class DimensionMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dimensionid;
	
	@Column(name="dimension")
	private String dimension;
	
	@Column(name="status",columnDefinition = "varchar(10) default 'A'")
	private String status;
		
	private Long surveyId;

	public Long getDimensionid() {
		return dimensionid;
	}

	public void setDimensionid(Long dimensionid) {
		this.dimensionid = dimensionid;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "DimensionMaster [dimensionid=" + dimensionid + ", dimension=" + dimension + ", status=" + status
				+ ", surveyId=" + surveyId + "]";
	}

	

}
