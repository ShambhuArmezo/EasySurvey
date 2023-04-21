package com.armezo.easysurvey.survey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.survey.model.EmpSurveyStatus;

public interface EmpSurveyStatusRepository extends JpaRepository<EmpSurveyStatus, Long> {
	
	@Query("From EmpSurveyStatus where accesskey=:accesskey and empcode=:empcode and surveyId=:surveyId and clientId=:clientId")
	Optional<EmpSurveyStatus> findByAccesskey(String accesskey,String empcode,Long surveyId, Long clientId);

}
