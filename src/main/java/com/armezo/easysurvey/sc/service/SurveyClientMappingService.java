package com.armezo.easysurvey.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.repository.SurveyClientMappingRepository;

@Service
public class SurveyClientMappingService {

	@Autowired
	private SurveyClientMappingRepository mappingRepository;
	
	public void saveSurveyClientMapping(SurveyClientMapping mapping){
		
		mappingRepository.save(mapping);
	}

	public void saveAllSurveyClientMapping(List<SurveyClientMapping> mappings) {
		mappingRepository.saveAll(mappings);
		
	}

	public List<SurveyClientMapping> getAllSurveyClientMapping() {
		return mappingRepository.findAll();
	}

	public List<SurveyClientMapping> getSurveyClientMappingByClientId(Long clientId) {
		return mappingRepository.findSurveyClientMappingByClientId(clientId);
	}
	
}
