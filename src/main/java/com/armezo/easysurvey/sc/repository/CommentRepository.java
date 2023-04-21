package com.armezo.easysurvey.sc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.armezo.easysurvey.sc.model.CommentMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;

public interface CommentRepository extends CrudRepository<CommentMaster, Long> {
	
	@Query("SELECT s FROM CommentMaster s WHERE s.surveyId=:surveyId")
	List<CommentMaster> getCommQuestionBySurveyId(Long surveyId);
	
	@Query("SELECT s FROM CommentMaster s WHERE s.qid=:qid and s.surveyId=:surveyId and s.DimansionId=:DimansionId ")
	public Optional<CommentMaster> getQuestionById(@Param("qid") Long qid,@Param("surveyId") Long surveyId,@Param("DimansionId") Long DimansionId);

	@Query("SELECT s FROM CommentMaster s WHERE s.qid=:qid")
	Optional<CommentMaster> getCurrQuesId(Long qid);
}
