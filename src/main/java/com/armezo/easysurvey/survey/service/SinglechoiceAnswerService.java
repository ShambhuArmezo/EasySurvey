package com.armezo.easysurvey.survey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.survey.model.EmpSurveyStatus;
import com.armezo.easysurvey.survey.model.SinglechoiceAnswer;
import com.armezo.easysurvey.survey.repository.SinglechoiceAnswerRepository;

@Service
public class SinglechoiceAnswerService {
	
	@Autowired
	private SinglechoiceAnswerRepository singlechoiceAnswerRepository;
	
	public SinglechoiceAnswer saveSingleQues(SinglechoiceAnswer single)
	{
		return singlechoiceAnswerRepository.save(single);
	}
	
	public Optional<SinglechoiceAnswer> findById(String accesskey,String empCode,Long surveyId, Long clientId, Long qid) {
		// TODO Auto-generated method stub
		return singlechoiceAnswerRepository.findById(accesskey,empCode,surveyId, clientId,qid);
	}

}
