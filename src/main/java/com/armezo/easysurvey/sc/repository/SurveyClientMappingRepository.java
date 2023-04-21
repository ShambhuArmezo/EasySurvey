package com.armezo.easysurvey.sc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.sc.model.SurveyClientMapping;

@Repository
public interface SurveyClientMappingRepository extends JpaRepository<SurveyClientMapping, Long> {

	@Query("SELECT s FROM SurveyClientMapping s WHERE s.clientId = :clientId")
	List<SurveyClientMapping> findSurveyClientMappingByClientId(Long clientId);

}
