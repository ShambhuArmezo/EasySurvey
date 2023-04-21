package com.armezo.easysurvey.sc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armezo.easysurvey.sc.model.QuestionTypeMaster;

public interface QuestionTypRepository extends JpaRepository<QuestionTypeMaster, Long> {

}
