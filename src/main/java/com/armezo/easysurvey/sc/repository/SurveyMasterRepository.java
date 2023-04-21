package com.armezo.easysurvey.sc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.sc.model.SurveyMaster;

@Repository
public interface SurveyMasterRepository extends JpaRepository<SurveyMaster, Long> {

	@Query("SELECT s FROM SurveyMaster s WHERE s.surveyId IN :surveyIdList OR :surveyIdList IS NULL")
	List<SurveyMaster> findAllSurveyBySurveyIdList(List<Long> surveyIdList);
	
	@Query("SELECT s FROM SurveyMaster s WHERE s.surveyId=:surveyId")
	List<SurveyMaster> getSurveyName(Long surveyId);
	
	@Query("SELECT s FROM SurveyMaster s WHERE s.surveyId=:surveyId")
	public Optional<SurveyMaster> getSurvey(@Param("surveyId") Long surveyId);

}
