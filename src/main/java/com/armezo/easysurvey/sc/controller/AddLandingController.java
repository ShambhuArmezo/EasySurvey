package com.armezo.easysurvey.sc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.service.UserService;
import com.armezo.easysurvey.sc.model.AddLandingMaster;
import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.service.AddLandingService;
import com.armezo.easysurvey.sc.service.SurveyClientMappingService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;

@Controller
public class AddLandingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddLandingService addLandingService;
	
	@Autowired
	private SurveyClientMappingService surveyClientMappingService;
	
	@Autowired
	private SurveyMasterService surveyMasterService;
	
	@GetMapping("/addInstruction")
	public String showLandingInfo(Model mod)
	{
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		return Constant.addInstruction;
	}
	
	@PostMapping("/addInsPro")
	 public String addInstruction(@ModelAttribute("Modal")AddLandingMaster entity,Model mod)
	 {
		AddLandingMaster data=new AddLandingMaster();
		Optional<AddLandingMaster> ids=addLandingService.getByClientIdAndSurveyId(entity.getClientId(),entity.getSurveyId());
		 try {
			 if(ids.isPresent())
			 {
				 data=ids.get();
			 }
			 data.setClientId(entity.getClientId());
			 data.setSurveyId(entity.getSurveyId());
			 data.setInstruction(entity.getInstruction());
			 
			 addLandingService.addIns(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<UserMaster> ob=userService.getAllCompany();
		 mod.addAttribute("allCompany",ob);
		// mod.addAttribute("client",entity.getClient_id());
		// mod.addAttribute("survey",entity.getSurvey_id());
		 
		 return Constant.addInstruction;
	 }
	
	@PostMapping("/viewInsPro")
	public String viewIns(@RequestParam("clientId")Long clientId,@RequestParam("surveyId")Long surveyId,Model mod) 
	{
		//AddLandingMaster data=new AddLandingMaster();
		Optional<AddLandingMaster> ids=addLandingService.getByClientIdAndSurveyId(clientId,surveyId);
		String inst=ids.get().getInstruction();
		mod.addAttribute("inst",inst);
		
		List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		for(SurveyClientMapping sur: survey) {
			List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
			mod.addAttribute("surveyList",getSur);
		}
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		 mod.addAttribute("client",clientId);
		 mod.addAttribute("survey",surveyId);
		
		 return Constant.addInstruction;
	 }
	
	@PostMapping("/viewSurvey")
	public String viewSurvey(@RequestParam("clientId")Long clientId,Model mod) 
	{
		long surveyId=0;
		List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		for(SurveyClientMapping sur: survey) {
			List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
			mod.addAttribute("surveyList",getSur);
		}
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		 mod.addAttribute("client",clientId);
		
		 return Constant.addInstruction;
	 }

}
