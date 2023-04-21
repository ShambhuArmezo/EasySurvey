package com.armezo.easysurvey.sc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.armezo.easysurvey.sc.model.MultiplechoiceMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;

public interface MultiplechoiceRepository extends CrudRepository<MultiplechoiceMaster, Long> {
	
	@Query("SELECT s FROM MultiplechoiceMaster s WHERE s.surveyId=:surveyId")
	List<MultiplechoiceMaster> getMultiQuestionBySurveyId(Long surveyId);
	
	@Query("SELECT s FROM MultiplechoiceMaster s WHERE s.qid=:qid and s.surveyId=:surveyId and s.DimansionId=:DimansionId ")
	public Optional<MultiplechoiceMaster> getQuestionById(@Param("qid") Long qid,@Param("surveyId") Long surveyId,@Param("DimansionId") Long DimansionId);

	@Query("SELECT s FROM MultiplechoiceMaster s WHERE s.qid=:qid")
	Optional<MultiplechoiceMaster> getCurrQuesId(Long qid);
}
