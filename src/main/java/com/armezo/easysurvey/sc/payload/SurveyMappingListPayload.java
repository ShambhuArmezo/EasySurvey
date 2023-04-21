package com.armezo.easysurvey.sc.payload;

import java.util.ArrayList;
import java.util.List;

public class SurveyMappingListPayload {
	
	private List<SurveyClientMappingPayload> payloads = new ArrayList<SurveyClientMappingPayload>();
	
	public SurveyMappingListPayload() {
	}
	
	public SurveyMappingListPayload(List<SurveyClientMappingPayload> payloads) {
		super();
		this.payloads = payloads;
	}
	public void setPayloads(List<SurveyClientMappingPayload> payloads) {
		this.payloads = payloads;
	}
	public List<SurveyClientMappingPayload> getPayloads() {
		return payloads;
	}

}
