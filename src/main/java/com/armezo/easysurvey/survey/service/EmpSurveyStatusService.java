package com.armezo.easysurvey.survey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.AddLandingMaster;
import com.armezo.easysurvey.survey.model.EmpSurveyStatus;
import com.armezo.easysurvey.survey.repository.EmpSurveyStatusRepository;

@Service
public class EmpSurveyStatusService {
	
	@Autowired
	private EmpSurveyStatusRepository empSurveyStatusRepository;
	
	public Optional<EmpSurveyStatus> findByAccesskey(String accesskey,String empCode,Long surveyId, Long clientId) {
		// TODO Auto-generated method stub
		return empSurveyStatusRepository.findByAccesskey(accesskey,empCode,surveyId, clientId);
	}
	
	public EmpSurveyStatus generateProfile(EmpSurveyStatus survey)
	{
		return empSurveyStatusRepository.save(survey);
	}

}
