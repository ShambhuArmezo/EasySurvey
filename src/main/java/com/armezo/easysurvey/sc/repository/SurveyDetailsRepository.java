package com.armezo.easysurvey.sc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyDetails;

@Repository
public interface SurveyDetailsRepository extends JpaRepository<SurveyDetails, Long> {

	@Query("SELECT s FROM SurveyDetails s WHERE s.surveyId = :surveyId AND s.clientId = :clientId AND s.questionId = :questionId")
	Optional<SurveyDetails> findSurveyDetailsBySurveyIdClientIdAndQuestionId(Long surveyId, Long clientId,
			Long questionId);
	
	public void deleteByQuestionIdAndSurveyIdAndDimensionIdAndTid(Long questionId,Long surveyId,Long dimensionId,Integer tid);
	
	@Query("select count(distinct section_no) From SurveyDetails m where m.surveyId = :surveyId and m.clientId = :clientId")
	public Integer getTotalSec(@Param("surveyId") Long surveyId,@Param("clientId") Long clientId);
	
	@Query("SELECT s FROM SurveyDetails s WHERE s.surveyId = :surveyId and s.clientId = :clientId")
	List<SurveyDetails> getQuesId(@Param("surveyId") Long surveyId,@Param("clientId") Long clientId);
	
	@Query("SELECT s FROM SurveyDetails s WHERE s.surveyId = :surveyId and s.clientId = :clientId and s.sectionNo = :sectionNo")
	List<SurveyDetails> getCurrQuesId(@Param("surveyId") Long surveyId,@Param("clientId") Long clientId,@Param("sectionNo") Integer sectionNo);
}
