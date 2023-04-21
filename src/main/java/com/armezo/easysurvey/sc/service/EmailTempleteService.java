package com.armezo.easysurvey.sc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.EmailTempleteMaster;
import com.armezo.easysurvey.sc.repository.EmailTempleteRepository;

@Service
public class EmailTempleteService {
	
	@Autowired
	private EmailTempleteRepository emailTempleteRepository;
	
	public EmailTempleteMaster addEmailTemplate(EmailTempleteMaster entity) throws Exception
    {
		emailTempleteRepository.save(entity);
        return entity;
    }

	public Optional<EmailTempleteMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId) {
		// TODO Auto-generated method stub
		return emailTempleteRepository.getByClientIdAndSurveyId(clientId, surveyId);
	}

	

}
