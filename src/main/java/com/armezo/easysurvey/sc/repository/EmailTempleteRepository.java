package com.armezo.easysurvey.sc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.sc.model.EmailTempleteMaster;

public interface EmailTempleteRepository extends JpaRepository<EmailTempleteMaster, Long> {
	
	@Query("From EmailTempleteMaster where clientId=:clientId and surveyId=:surveyId")
	Optional<EmailTempleteMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId);

}
