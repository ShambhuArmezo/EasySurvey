package com.armezo.easysurvey.sc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.CommentMaster;
import com.armezo.easysurvey.sc.model.MultiplechoiceMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.repository.MultiplechoiceRepository;

@Service
public class MultiplechoiceService {
	
	@Autowired
	private MultiplechoiceRepository multiplechoiceRepository;
	
	public List<MultiplechoiceMaster> saveAllQuestion(List<MultiplechoiceMaster> listQues) {
		List<MultiplechoiceMaster> mm = (List<MultiplechoiceMaster>) multiplechoiceRepository.saveAll(listQues);
		 List<MultiplechoiceMaster> list=new  ArrayList<MultiplechoiceMaster>();
		 for(MultiplechoiceMaster q:multiplechoiceRepository.findAll()){
			 //q.getSinglechoiceOptionMaster().get(0).getQoption();
			 list.add(q);
		 }
		return mm;
	}
	
	public List<MultiplechoiceMaster> getMultiQuestionBySurveyId(Long surveyId) {
		return multiplechoiceRepository.getMultiQuestionBySurveyId(surveyId);
	}
	
	public Optional<MultiplechoiceMaster> getQuestionById(Long questionId,Long surveyId,Long dimensionId)
	{
		return multiplechoiceRepository.getQuestionById(questionId,surveyId,dimensionId);
	}
	
	public void deleteQuestionById(Long qid) {
		multiplechoiceRepository.deleteById(qid);
	}
	
	public Optional<MultiplechoiceMaster> getCurrQuesId(Long qid) {
		return multiplechoiceRepository.getCurrQuesId(qid);
	}

}
