package com.armezo.easysurvey.sc.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.armezo.easysurvey.sc.model.SinglechoiceMaster;

public interface SinglechoiceRepository extends CrudRepository<SinglechoiceMaster, Long> {
	
	@Query("SELECT s FROM SinglechoiceMaster s WHERE s.surveyId=:surveyId")
	List<SinglechoiceMaster> getAllQuestionBySurveyId(Long surveyId);
	
	@Query("SELECT s FROM SinglechoiceMaster s WHERE s.qid=:qid and s.surveyId=:surveyId and s.DimansionId=:DimansionId ")
	public Optional<SinglechoiceMaster> getQuestionById(@Param("qid") Long qid,@Param("surveyId") Long surveyId,@Param("DimansionId") Long DimansionId);
	
	@Query("SELECT s FROM SinglechoiceMaster s WHERE s.qid=:qid")
	Optional<SinglechoiceMaster> getCurrQuesId(Long qid);

}
