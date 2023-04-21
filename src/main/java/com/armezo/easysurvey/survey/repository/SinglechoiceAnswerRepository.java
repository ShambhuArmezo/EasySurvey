package com.armezo.easysurvey.survey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.survey.model.EmpSurveyStatus;
import com.armezo.easysurvey.survey.model.SinglechoiceAnswer;

public interface SinglechoiceAnswerRepository extends JpaRepository<SinglechoiceAnswer, Long> {
	
	@Query("From SinglechoiceAnswer where accesskey=:accesskey and empcode=:empcode and surveyId=:surveyId and clientId=:clientId and qid=:qid")
	Optional<SinglechoiceAnswer> findById(String accesskey,String empcode,Long surveyId, Long clientId, Long qid);

}
