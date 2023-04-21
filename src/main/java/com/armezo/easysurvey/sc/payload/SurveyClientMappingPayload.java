package com.armezo.easysurvey.sc.payload;

public class SurveyClientMappingPayload {
	
	private String surveyName;
	private String clientName;
	private Long clientId;
	private Long surveyId;
	private String questionType;
	private Integer questionTypeId;
	private Integer tid;
	private Long questionId;
	private String question;
	private Long dimensionId;
	private String dimension;
	private Integer sectionNo;
	private Integer orderNo;
	private Integer publish;
	
	
	
	public SurveyClientMappingPayload() {
	}
	
	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public Integer getQuestionTypeId() {
		return questionTypeId;
	}
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
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
		return "SurveyClientMappingPayload [surveyName=" + surveyName + ", clientName=" + clientName + ", clientId="
				+ clientId + ", surveyId=" + surveyId + ", questionType=" + questionType + ", questionTypeId="
				+ questionTypeId + ", tid=" + tid + ", questionId=" + questionId + ", question=" + question
				+ ", dimensionId=" + dimensionId + ", dimension=" + dimension + ", sectionNo=" + sectionNo
				+ ", orderNo=" + orderNo + ", publish=" + publish + "]";
	}
	
	
	
	
	
	

}
