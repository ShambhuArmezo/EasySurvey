package com.armezo.easysurvey.sc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.SurveyEndDateMaster;
import com.armezo.easysurvey.sc.repository.SurveyEndDateRepository;

@Service
public class SurveyEndDateService {
	
	@Autowired
	private SurveyEndDateRepository surveyEndDateRepository;
	
	public Optional<SurveyEndDateMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId) {
		// TODO Auto-generated method stub
		return surveyEndDateRepository.getByClientIdAndSurveyId(clientId, surveyId);
	}
	
	public SurveyEndDateMaster addSurveyEndDate(SurveyEndDateMaster entity) throws Exception
    {
		surveyEndDateRepository.save(entity);
        return entity;
    }
	public Optional<SurveyEndDateMaster> getSurveyDate(Long surveyId,Long clientId) {
		// TODO Auto-generated method stub
		return surveyEndDateRepository.getSurveyDate(surveyId,clientId);
	}

}
