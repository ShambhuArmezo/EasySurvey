package com.armezo.easysurvey.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	 public UserMaster addUserData(UserMaster entity) throws Exception
	    {
		 	userRepository.save(entity);
	        return entity;
	    }
	 
	 public List<UserMaster> getAllCompany()
	    {
	        List<UserMaster> companyList = userRepository.findAll();
	         
	        if(companyList.size() > 0) {
	            return companyList;
	        } else {
	            return new ArrayList<UserMaster>();
	        }
	    }
	 
	 public Optional<UserMaster> getUserById(Long clientId) {
			return userRepository.findById(clientId);
		}
	 

}
