package com.armezo.easysurvey.sc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.AddLandingMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.repository.AddLandingRepository;

@Service
public class AddLandingService {
	
	@Autowired
	private AddLandingRepository addLandingRepository;
	
	public AddLandingMaster addIns(AddLandingMaster entity) throws Exception
    {
		addLandingRepository.save(entity);
        return entity;
    }

	public Optional<AddLandingMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId) {
		// TODO Auto-generated method stub
		return addLandingRepository.getByClientIdAndSurveyId(clientId, surveyId);
	}

	
}
