package com.armezo.easysurvey.sc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.sc.model.QuestionTypeMaster;
import com.armezo.easysurvey.sc.repository.QuestionTypRepository;

@Service
public class QuestionTypService {
	
	@Autowired
	private QuestionTypRepository questionTypRepository;
	
	public List<QuestionTypeMaster> getQuestionTyp()
    {
        List<QuestionTypeMaster> questionTyp = questionTypRepository.findAll();
         
        if(questionTyp.size() > 0) {
            return questionTyp;
        } else {
            return new ArrayList<QuestionTypeMaster>();
        }
    }

}
