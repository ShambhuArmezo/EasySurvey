package com.armezo.easysurvey.sc.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.armezo.easysurvey.sc.model.DimensionMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyDetails;
import com.armezo.easysurvey.sc.model.SurveyEndDateMaster;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.payload.SurveyClientMappingPayload;
import com.armezo.easysurvey.sc.payload.SurveyMappingListPayload;
import com.armezo.easysurvey.sc.service.DimensionService;
import com.armezo.easysurvey.sc.service.QuestionTypService;
import com.armezo.easysurvey.sc.service.SinglechoiceService;
import com.armezo.easysurvey.sc.service.SurveyClientMappingService;
import com.armezo.easysurvey.sc.service.SurveyDetailsService;
import com.armezo.easysurvey.sc.service.SurveyEndDateService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;

@Controller
public class SurveyMasterController {

	@Autowired
	private SurveyMasterService surveyMasterService;
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionTypService questionTypService;
	@Autowired
	private SinglechoiceService singlechoiceService;
	@Autowired
	private DimensionService dimensionService;
	@Autowired
	private SurveyDetailsService surveyDetailsService;
	@Autowired
	private SurveyClientMappingService surveyClientMappingService;
	@Autowired
	private SurveyEndDateService surveyEndDateService;

	// Show Survey Page
	@GetMapping("/addSurvey")
	public String showPageToAddSurvey(Model model) {
		model.addAttribute("surveyMaster", new SurveyMaster());
		return Constant.addSurvey;
	}

	@PostMapping("/createSurvey")
	//public String createSurveyName(@RequestParam String surveyName, @RequestParam(required = false, defaultValue = "0") Long surveyId, Model model) {
	public String createSurveyName(@ModelAttribute("surveyMaster") SurveyMaster survey,@RequestParam(name="surveyId",required = false)Long surveyId, Model model) {
		SurveyMaster surveyMaster;
		//Check Existing Survey
		if(survey.getSurveyId()!=null) {
		Optional<SurveyMaster> optional = surveyMasterService.getSurveyMasterById(survey.getSurveyId());
		if(optional.isPresent()) {
			surveyMaster=optional.get();
		}else {
			surveyMaster = new SurveyMaster();
		}}else {
			surveyMaster = new SurveyMaster();
		}
		surveyMaster.setSurveyName(survey.getSurveyName());
		surveyMaster.setDateOfCreation(new Date());
		surveyMaster.setStatus("A");
		surveyMaster.setPublish(0);
		surveyMasterService.createSurveyMaster(surveyMaster);
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		model.addAttribute("surveyMaster", new SurveyMaster());
		model.addAttribute("surveys", surveyList);
		return Constant.addSurvey;
	}
	
	@PostMapping("/showAllSurvey")
	public String showAllSurvey(Model model) {
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		model.addAttribute("surveyMaster", new SurveyMaster());
		model.addAttribute("surveys", surveyList);
		return Constant.addSurvey;
	}
	@PostMapping("/getOneSurvey")
	public String getSurveyName(@RequestParam("surveyIds")Long surveyId, Model model) {
		Optional<SurveyMaster> surveyMaster = surveyMasterService.getSurveyMasterById(surveyId);
		model.addAttribute("surveyMaster", surveyMaster.get());
		return Constant.addSurvey;
	}

	@GetMapping("/showSurveyMapping")
	public String surveyMappingPage(Model model) {
		List<UserMaster> userMasters = userService.getAllCompany();
		List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
		model.addAttribute("surveys", surveyMasters);
		model.addAttribute("users", userMasters);
		return Constant.surveyMapping;
	}
	
	@PostMapping("/surveyMappingWithClient")
	public String saveSurveyMappingWithClient(@RequestParam Long surveyId, @RequestParam Long clientId, Model model) {
		// Now Show Related Data
		// Taking A payload to show data on jsp page
		List<SurveyClientMappingPayload> payloads = new ArrayList<SurveyClientMappingPayload>();
		List<SinglechoiceMaster> questions = new ArrayList<SinglechoiceMaster>();
		//Get Question data from table by surveyId
		questions = singlechoiceService.getAllQuestionBySurveyId(surveyId);
		/**********************************/
		//Set Data To Payload
		for(SinglechoiceMaster question : questions) {
			SurveyClientMappingPayload payload = new SurveyClientMappingPayload();
			//SurveyMaster survey = surveyMasterService.getSurveyMasterById(surveyId);
			Optional<SurveyMaster> survey = surveyMasterService.getSurveyMasterById(surveyId);
			//Client Data
			Optional<UserMaster> users = userService.getUserById(clientId);
			if(users.isPresent()) {
				payload.setClientId(users.get().getId());
				payload.setClientName(users.get().getClientName());
			}
			if(survey.isPresent()) {
				payload.setSurveyId(survey.get().getSurveyId());
				payload.setSurveyName(survey.get().getSurveyName());
				}
			payload.setQuestionId(question.getQid());
			payload.setQuestion(question.getQuestion());
			payload.setQuestionType("SINGLECHOICE");
			//Get Dimension by id
			Optional<DimensionMaster> dimension = dimensionService.getDimensionById(question.getDimansionId());
			if(dimension.isPresent()) {
				payload.setDimensionId(dimension.get().getDimensionid());
				payload.setDimension(dimension.get().getDimension());
			}
			//Check Existing Survey Details
			Optional<SurveyDetails> optional = surveyDetailsService.getSurveyDetailsBySurveyIdClientIdAndQuestionId(surveyId, clientId, question.getQid());
			if(optional.isPresent()) {
				payload.setSectionNo(optional.get().getSectionNo());
				payload.setOrderNo(optional.get().getOrderNo());
			}
			payloads.add(payload);
		}
		
		//Form Binding
		SurveyMappingListPayload surveyList = new SurveyMappingListPayload(payloads); 
		// Data For DropDown
		List<UserMaster> userMasters = userService.getAllCompany();
		List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
		model.addAttribute("surveys", surveyMasters);
		model.addAttribute("users", userMasters);
	//	model.addAttribute("payloads", payloads);
		model.addAttribute("surveyList", surveyList);
		return Constant.surveyMapping;
	}
	
	@PostMapping("/saveSurveyMapping")
	public String saveSurveyMappingWithClient(@ModelAttribute("surveyList") SurveyMappingListPayload surveyList, Model model) {
		List<SurveyClientMappingPayload> surveyPayloads = surveyList.getPayloads();
		List<SurveyDetails> surveyDetailsList = new ArrayList<SurveyDetails>();
		SurveyClientMapping mapping = new SurveyClientMapping();
		Integer tid=0;
		for(SurveyClientMappingPayload payload : surveyPayloads) {
			SurveyDetails surveyDetails = new SurveyDetails();
			//Check Existing Survey Details 
			Optional<SurveyDetails> optional = surveyDetailsService.getSurveyDetailsBySurveyIdClientIdAndQuestionId(payload.getSurveyId(), payload.getClientId(), payload.getQuestionId());
			if(optional.isPresent()) {
				surveyDetails = optional.get();
			}
			if(payload.getQuestionType().equals("SINGLECHOICE")) {
				tid=4;
			}
			if(payload.getQuestionType().equals("COMMENT")) {
				tid=3;
			}
			if(payload.getQuestionType().equals("MULTIPLECHOICE")) {
				tid=1;
			}
			//Setting Data For Mapping
			mapping.setClientId(payload.getClientId());
			mapping.setSurveyId(payload.getSurveyId());
			mapping.setStatus("A");
			//Setting Data To SurveyDetails
			surveyDetails.setClientId(payload.getClientId());
			surveyDetails.setSurveyId(payload.getSurveyId());
			surveyDetails.setQuestionId(payload.getQuestionId());
			surveyDetails.setDimensionId(payload.getDimensionId());
			surveyDetails.setTid(tid);
			surveyDetails.setSectionNo(payload.getSectionNo());
			surveyDetails.setOrderNo(payload.getOrderNo());
			surveyDetailsList.add(surveyDetails);
		}
		//Save In DB
		surveyDetailsService.saveAllSurveyDetails(surveyDetailsList);
		surveyClientMappingService.saveSurveyClientMapping(mapping);
		model.addAttribute("surveyList", surveyList);
		return Constant.surveyMapping;
	}
	// Show Survey Publish Details
	@GetMapping("/showSurveyPublish")
	public String showSurveyPublishDetailsPage(Model model) {
		List<UserMaster> users = userService.getAllCompany();
		model.addAttribute("users", users);
		return Constant.surveyPublish;
	}
	@PostMapping("/showSurveyPublishDetails")
	public String showSurveyPublishDetails(@RequestParam Long clientId, Model model) {
		//Get SurveyClientMapping by Client Id
		String clientName ="";
		List<SurveyClientMapping> mappings = surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		List<Long> surveyIdList = new ArrayList<Long>();
		for(SurveyClientMapping mapping : mappings) {
			surveyIdList.add(mapping.getSurveyId());
		}
		//Get Client By Client Id
		Optional<UserMaster> userOptional = userService.getUserById(clientId);
		if(userOptional.isPresent()) {
			clientName=userOptional.get().getClientName();
		}
		//Get All Survey By Survey Id List
		List<SurveyMaster> surveys = surveyMasterService.getAllSurveyBySurveyIdList(surveyIdList);
		//Add in Model
		List<UserMaster> users = userService.getAllCompany();
		model.addAttribute("users", users);
		model.addAttribute("surveys", surveys);
		model.addAttribute("client", clientName);
		return Constant.surveyPublish;
	}
	//PublishUnpublish a Survey
	@PostMapping("/publishUnpublishSurvey")
	public String publishUnpublishSurvey(@RequestParam String surveyId, @RequestParam Integer publish, Model model) {
		String msg = "";
		//Get Survey By Survey Id
		//SurveyMaster survey = surveyMasterService.getSurveyMasterById(Long.valueOf(surveyId));
		Optional<SurveyMaster> survey = surveyMasterService.getSurveyMasterById(Long.valueOf(surveyId));
		//survey.setPublish(publish);
		//surveyMasterService.createSurveyMaster(survey);
		survey.get().setPublish(publish);
		surveyMasterService.createSurveyMaster(survey.get());
		if(publish.equals("1")) {
			msg="Survey Published";
		}else if (publish.equals("0")) {
			msg="Survey Un-Published";
		}
		List<UserMaster> users = userService.getAllCompany();
		model.addAttribute("users", users);
		model.addAttribute("msg", msg);
		return Constant.surveyPublish;
	}
	
	@GetMapping("/surveyEndDate")
	public String surveyEndDate(Model mod) {
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		return Constant.surveyEndDate;
	}
	
	@PostMapping("/viewSurveyss")
	public String viewSurveyss(@RequestParam("clientId")Long clientId,Model mod) 
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
		
		 return Constant.surveyEndDate;
	 }
	
	@PostMapping("/survEndDate")
	 public String survEndDate(@ModelAttribute("Modal")SurveyEndDateMaster entity,Model mod)
	 {
		SurveyEndDateMaster data=new SurveyEndDateMaster();
		Optional<SurveyEndDateMaster> ids=surveyEndDateService.getByClientIdAndSurveyId(entity.getClientId(),entity.getSurveyId());
		 try {
			 if(ids.isPresent())
			 {
				 data=ids.get();
			 }
			 data.setClientId(entity.getClientId());
			 data.setSurveyId(entity.getSurveyId());
			 data.setSurveyDate(entity.getSurveyDate());
			 
			 surveyEndDateService.addSurveyEndDate(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(entity.getClientId());
			for(SurveyClientMapping sur: survey) {
				List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
				mod.addAttribute("surveyList",getSur);
			}
		 
		 List<UserMaster> ob=userService.getAllCompany();
		 mod.addAttribute("allCompany",ob);
		 mod.addAttribute("client",entity.getClientId());
		 mod.addAttribute("survey",entity.getSurveyId());
		 
		 return Constant.surveyEndDate;
	 }
	
	@PostMapping("/viewEndDate")
	public String viewEndDate(@RequestParam("clientId")Long clientId,@RequestParam("surveyId")Long surveyId,Model mod) 
	{
		Optional<SurveyEndDateMaster> ids=surveyEndDateService.getByClientIdAndSurveyId(clientId,surveyId);
		String endDate=ids.get().getSurveyDate();
		mod.addAttribute("endDate",endDate);
		
		List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		for(SurveyClientMapping sur: survey) {
			List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
			mod.addAttribute("surveyList",getSur);
		}
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		mod.addAttribute("client",clientId);
		mod.addAttribute("survey",surveyId);
		
		 return Constant.surveyEndDate;
	 }
}
