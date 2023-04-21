package com.armezo.easysurvey.sc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.repository.SinglechoiceRepository;


@Service
public class SinglechoiceService {
	
	@Autowired
	private SinglechoiceRepository singlechoiceRepository;
	
	public List<SinglechoiceMaster> saveAllQuestion(List<SinglechoiceMaster> listQues) {
		List<SinglechoiceMaster> mm = (List<SinglechoiceMaster>) singlechoiceRepository.saveAll(listQues);
		 List<SinglechoiceMaster> list=new  ArrayList<SinglechoiceMaster>();
		 for(SinglechoiceMaster q:singlechoiceRepository.findAll()){
			 //q.getSinglechoiceOptionMaster().get(0).getQoption();
			 list.add(q);
		 }
		return mm;
	}
	
	public List<SinglechoiceMaster> getAllQuestionBySurveyId(Long surveyId) {
		return singlechoiceRepository.getAllQuestionBySurveyId(surveyId);
	}
	
	public Optional<SinglechoiceMaster> getQuestionById(Long questionId,Long surveyId,Long dimensionId)
	{
		return singlechoiceRepository.getQuestionById(questionId,surveyId,dimensionId);
	}
	
	public void deleteQuestionById(Long qid) {
		singlechoiceRepository.deleteById(qid);
	}
	
	public Optional<SinglechoiceMaster> getCurrQuesId(Long qid) {
		return singlechoiceRepository.getCurrQuesId(qid);
	}

}
