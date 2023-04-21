package com.armezo.easysurvey.survey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.survey.model.MultichoiceAnswer;
import com.armezo.easysurvey.survey.model.SinglechoiceAnswer;
import com.armezo.easysurvey.survey.repository.MultichoiceAnswerRepository;

@Service
public class MultichoiceAnswerService {
	
	@Autowired
	private MultichoiceAnswerRepository multichoiceAnswerRepository;
	
	public MultichoiceAnswer saveMultiQuesQues(MultichoiceAnswer multi)
	{
		return multichoiceAnswerRepository.save(multi);
	}
	
	public Optional<MultichoiceAnswer> findById(String accesskey,String empCode,Long surveyId, Long clientId, Long qid) {
		// TODO Auto-generated method stub
		return multichoiceAnswerRepository.findById(accesskey,empCode,surveyId, clientId,qid);
	}

}
