package com.armezo.easysurvey.sc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.CommentMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<CommentMaster> saveAllQuestion(List<CommentMaster> listQues) {
		List<CommentMaster> mm = (List<CommentMaster>) commentRepository.saveAll(listQues);
		 List<CommentMaster> list=new  ArrayList<CommentMaster>();
		 for(CommentMaster q:commentRepository.findAll()){
			 //q.getSinglechoiceOptionMaster().get(0).getQoption();
			 list.add(q);
		 }
		return mm;
	}
	
	public List<CommentMaster> getCommQuestionBySurveyId(Long surveyId) {
		return commentRepository.getCommQuestionBySurveyId(surveyId);
	}
	
	public Optional<CommentMaster> getQuestionById(Long questionId,Long surveyId,Long dimensionId)
	{
		return commentRepository.getQuestionById(questionId,surveyId,dimensionId);
	}
	
	public void deleteQuestionById(Long qid) {
		commentRepository.deleteById(qid);
	}
	
	public Optional<CommentMaster> getCurrQuesId(Long qid) {
		return commentRepository.getCurrQuesId(qid);
	}

}
