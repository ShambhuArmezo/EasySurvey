package com.armezo.easysurvey.sc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.service.UserService;
import com.armezo.easysurvey.sc.model.AddLandingMaster;
import com.armezo.easysurvey.sc.model.EmailTempleteMaster;
import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.service.AddLandingService;
import com.armezo.easysurvey.sc.service.EmailTempleteService;
import com.armezo.easysurvey.sc.service.SurveyClientMappingService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;

@Controller
public class EmailTempleteController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailTempleteService emailTempleteService;
	
	@Autowired
	private SurveyClientMappingService surveyClientMappingService;
	
	@Autowired
	private SurveyMasterService surveyMasterService;
	
	@GetMapping("/getEmailTemplate")
	public String showEmailTemplate(Model mod)
	{
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		return Constant.emailTemp;
	}
	
	@PostMapping("/addEmailPro")
	 public String addEmail(@ModelAttribute("Modal")EmailTempleteMaster entity,Model mod)
	 {
		EmailTempleteMaster data=new EmailTempleteMaster();
		Optional<EmailTempleteMaster> ids=emailTempleteService.getByClientIdAndSurveyId(entity.getClientId(),entity.getSurveyId());
		 try {
			 if(ids.isPresent())
			 {
				 data=ids.get();
			 }
			 data.setClientId(entity.getClientId());
			 data.setSurveyId(entity.getSurveyId());
			 data.setSubjctLine(entity.getSubjctLine());
			 data.setTemplate(entity.getTemplate());
			 
			 emailTempleteService.addEmailTemplate(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<UserMaster> ob=userService.getAllCompany();
		 mod.addAttribute("allCompany",ob);
		 
		 return Constant.emailTemp;
	 }
	
	@RequestMapping(value = "/viewEmailPro", method = RequestMethod.POST)
	public String viewEmail(@RequestParam("clientId")Long clientId,@RequestParam("surveyId")Long surveyId,Model mod) 
	{
		//AddLandingMaster data=new AddLandingMaster();
		Optional<EmailTempleteMaster> ids=emailTempleteService.getByClientIdAndSurveyId(clientId,surveyId);
		String subjet=ids.get().getSubjctLine();
		String template=ids.get().getTemplate();
		
		List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		for(SurveyClientMapping sur: survey) {
			List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
			mod.addAttribute("surveyList",getSur);
		}
		
		mod.addAttribute("subjectLine",subjet);
		mod.addAttribute("msgBody",template);
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		 mod.addAttribute("client",clientId);
		 mod.addAttribute("survey",surveyId);
		
		 return Constant.emailTemp;
	 }
	
	@RequestMapping(value = "/viewSurveys", method = RequestMethod.POST)
	public String viewSurveys(@RequestParam("clientId")Long clientId,Model mod) 
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
		
		 return Constant.emailTemp;
	 }

}
