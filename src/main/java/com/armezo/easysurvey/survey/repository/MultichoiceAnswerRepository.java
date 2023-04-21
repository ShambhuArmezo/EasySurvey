package com.armezo.easysurvey.survey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armezo.easysurvey.survey.model.MultichoiceAnswer;

public interface MultichoiceAnswerRepository extends JpaRepository<MultichoiceAnswer, Long> {
	
	@Query("From MultichoiceAnswer where accesskey=:accesskey and empcode=:empcode and surveyId=:surveyId and clientId=:clientId and qid=:qid")
	Optional<MultichoiceAnswer> findById(String accesskey,String empcode,Long surveyId, Long clientId, Long qid);

}
