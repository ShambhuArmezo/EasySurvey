package com.armezo.easysurvey.sc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.sc.model.SurveyEndDateMaster;

public interface SurveyEndDateRepository extends JpaRepository<SurveyEndDateMaster, Long> {
	
	@Query("From SurveyEndDateMaster where clientId=:clientId and surveyId=:surveyId")
	Optional<SurveyEndDateMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId);
	
	@Query("From SurveyEndDateMaster where clientId=:clientId and surveyId=:surveyId")
	Optional<SurveyEndDateMaster> getSurveyDate(Long surveyId, Long clientId);

}
