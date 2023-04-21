package com.armezo.easysurvey.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyDetails;
import com.armezo.easysurvey.sc.repository.SurveyDetailsRepository;

@Service
public class SurveyDetailsService {

	@Autowired
	private SurveyDetailsRepository detailsRepository;
	
	public SurveyDetails saveSurveyDetails(SurveyDetails details) {
		return detailsRepository.save(details);
	}
	
	public Optional<SurveyDetails> getSurveyDetailsById(Long id){
		return detailsRepository.findById(id);
	}
	
	public Optional<SurveyDetails> getSurveyDetailsBySurveyIdClientIdAndQuestionId(Long surveyId, Long clientId, Long questionId){
		return detailsRepository.findSurveyDetailsBySurveyIdClientIdAndQuestionId(surveyId,clientId,questionId);
	}

	public void saveAllSurveyDetails(List<SurveyDetails> surveyDetailsList) {
		detailsRepository.saveAll(surveyDetailsList);
	}
	public void deleteSurQuestionById(Long questionId,Long surveyId,Long dimensionId,Integer tid) {
		detailsRepository.deleteByQuestionIdAndSurveyIdAndDimensionIdAndTid(questionId,surveyId,dimensionId,tid);
	}
	
	public Integer totalSec(Long surveyId,Long clientId) {
		Integer totSec=detailsRepository.getTotalSec(surveyId,clientId);
		return totSec;
	}
	
	public List<SurveyDetails> getQuesId(Long surveyId,Long clientId) {
		return detailsRepository.getQuesId(surveyId,clientId);
	}
	
	public List<SurveyDetails> getCurrQuesId(Long surveyId,Long clientId,Integer currsec) {
		return detailsRepository.getCurrQuesId(surveyId,clientId,currsec);
	}
}
