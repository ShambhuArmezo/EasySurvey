package com.armezo.easysurvey.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.admin.model.UserMaster;


@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long> {

}
