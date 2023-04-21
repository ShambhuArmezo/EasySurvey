package com.armezo.easysurvey.sc.model;

	import java.util.Date;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "survey_master")
	public class SurveyMaster {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "survey_id")
	    private Long surveyId;

	    @Column(name = "survey_name")
	    private String surveyName;

	    @Column(name = "date_of_creation")
	    private Date dateOfCreation;

	    @Column(name = "status")
	    private String status; // Default value A

	    @Column(name = "publish")
	    private Integer publish; // Default 0

	    /*
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "company_id", nullable = false)  
	    private Company company;
	     
	    @Column(name = "company_id")
	    private Long companyId;
	    */
		public Long getSurveyId() {
			return surveyId;
		}

		public void setSurveyId(Long surveyId) {
			this.surveyId = surveyId;
		}

		public String getSurveyName() {
			return surveyName;
		}

		public void setSurveyName(String surveyName) {
			this.surveyName = surveyName;
		}

		public Date getDateOfCreation() {
			return dateOfCreation;
		}

		public void setDateOfCreation(Date dateOfCreation) {
			this.dateOfCreation = dateOfCreation;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getPublish() {
			return publish;
		}

		public void setPublish(Integer publish) {
			this.publish = publish;
		}

		@Override
		public String toString() {
			return "SurveyMaster [surveyId=" + surveyId + ", surveyName=" + surveyName + ", dateOfCreation="
					+ dateOfCreation + ", status=" + status + ", publish=" + publish + "]";
		}

		
		

	   
	}


