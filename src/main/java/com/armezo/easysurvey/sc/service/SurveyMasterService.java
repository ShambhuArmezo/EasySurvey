package com.armezo.easysurvey.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.exception.SurveyMasterNotFoundException;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.repository.SurveyMasterRepository;

@Service
public class SurveyMasterService {
	
	@Autowired
	private SurveyMasterRepository surveyMasterRepository;
	
	public SurveyMaster createSurveyMaster(SurveyMaster surveyMaster) {
        return surveyMasterRepository.save(surveyMaster);
    }

    public SurveyMaster updateSurveyMaster(Long surveyId, SurveyMaster updatedSurveyMaster) {
        SurveyMaster existingSurveyMaster = surveyMasterRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyMasterNotFoundException("Survey master not found with ID: " + surveyId));

        existingSurveyMaster.setSurveyName(updatedSurveyMaster.getSurveyName());
        existingSurveyMaster.setDateOfCreation(updatedSurveyMaster.getDateOfCreation());
        existingSurveyMaster.setStatus(updatedSurveyMaster.getStatus());
        existingSurveyMaster.setPublish(updatedSurveyMaster.getPublish());

        return surveyMasterRepository.save(existingSurveyMaster);
    }

    public void deleteSurveyMasterById(Long surveyId) {
        SurveyMaster SurveyMasterToDelete = surveyMasterRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyMasterNotFoundException("Survey master not found with ID: " + surveyId));
        surveyMasterRepository.delete(SurveyMasterToDelete);
    }
    
    public List<SurveyMaster> getAllSurveyMaster(){
    	return surveyMasterRepository.findAll();
    }

	public List<SurveyMaster> getAllSurveyBySurveyIdList(List<Long> surveyIdList) {
		return surveyMasterRepository.findAllSurveyBySurveyIdList(surveyIdList);
	}
	
	public List<SurveyMaster> getSurveyName(Long surveyId) {
		return surveyMasterRepository.getSurveyName(surveyId);
	}
	
	public Optional<SurveyMaster> getSurvey(Long surveyId)
	{
		return surveyMasterRepository.getSurvey(surveyId);
	}
	
	public Optional<SurveyMaster> getSurveyClientMappingByClientId(Long surveyId) {
        return surveyMasterRepository.findById(surveyId);
    }
	
	public Optional<SurveyMaster> getSurveyMasterById(Long surveyId) {
        return surveyMasterRepository.findById(surveyId);
    }

}
