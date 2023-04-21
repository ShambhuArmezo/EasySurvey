package com.armezo.easysurvey.sc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.sc.model.AddLandingMaster;

public interface AddLandingRepository extends JpaRepository<AddLandingMaster, Long> {

	@Query("From AddLandingMaster where clientId=:clientId and surveyId=:surveyId")
	Optional<AddLandingMaster> getByClientIdAndSurveyId(Long clientId, Long surveyId);

	
}
