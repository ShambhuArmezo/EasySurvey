package com.armezo.easysurvey.survey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.survey.model.CommentAnswer;
import com.armezo.easysurvey.survey.model.SinglechoiceAnswer;
import com.armezo.easysurvey.survey.repository.CommentAnswerRepository;

@Service
public class CommentAnswerService {
	
	@Autowired
	private CommentAnswerRepository commentAnswerRepository;
	
	public CommentAnswer saveCommentQues(CommentAnswer comment)
	{
		return commentAnswerRepository.save(comment);
	}

	public Optional<CommentAnswer> findById(String accesskey,String empCode,Long surveyId, Long clientId, Long qid) {
		// TODO Auto-generated method stub
		return commentAnswerRepository.findById(accesskey,empCode,surveyId, clientId,qid);
	}
	
}
