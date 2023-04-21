package com.armezo.easysurvey.sc.controller;

import java.util.ArrayList;
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
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.service.DimensionService;
import com.armezo.easysurvey.sc.service.SurveyDetailsService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;
import com.armezo.easysurvey.sc.model.SurveyView;
@Controller
public class DimensionController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DimensionService dimensionService;
	@Autowired
	private SurveyMasterService surveyMasterService;
	
	@GetMapping("/addDimesion")
	public String showDimesionInfo(Model mod)
	{
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		mod.addAttribute("surveys", surveyList);
		
		return Constant.addDimesion;
	}
	
	@PostMapping("/addDimenPro")
	public String addDimension(@ModelAttribute("dimensionMaster") DimensionMaster entity, Model mod) {
		DimensionMaster dMaster;
		//Check for existing dimension
		if(entity.getDimensionid()!=null) {
			Optional<DimensionMaster> optional = dimensionService.getDimensionById(entity.getDimensionid());
			if(optional.isPresent()) {
				dMaster=optional.get();
			}else {
				dMaster =new DimensionMaster();
			}}else {
			dMaster =new DimensionMaster();
		}
		try {
			dMaster.setStatus("A");
			dMaster.setDimension(entity.getDimension());
			dMaster.setSurveyId(entity.getSurveyId());
			dimensionService.dimensionAdd(dMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DimensionMaster> dimensionMasters = dimensionService.getAllDimension(entity.getSurveyId());
		List<SurveyView> views = new ArrayList<SurveyView>();
		for(DimensionMaster master : dimensionMasters) {
			SurveyView view = new SurveyView();
			//Get Client By Id
			/*Optional<UserMaster> user = userService.getUserById(master.getCompanyid());
			if(user.isPresent()) {
				view.setClientId(user.get().getId());
				view.setClientName(user.get().getCompanyName());
			}*/
			//Get Survey
			Optional<SurveyMaster> survey = surveyMasterService.getSurveyMasterById(master.getSurveyId());
			if(survey.isPresent()) {
				view.setSurveyId(survey.get().getSurveyId());
				view.setSurveyName(survey.get().getSurveyName());
			}
			view.setDimensionId(master.getDimensionid());
			view.setDimensionName(master.getDimension());
			views.add(view);
		}
		List<UserMaster> clients = userService.getAllCompany();
		List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
		mod.addAttribute("clients", clients);
		mod.addAttribute("surveys", surveyMasters);
		mod.addAttribute("dimensionMaster", new DimensionMaster());
		mod.addAttribute("views", views);
		return Constant.addDimesion;
	}
	
	// Show All Dimension
	@PostMapping("/showAllDimension")
		public String showAllDimension(@RequestParam("surveyId") Long surveyId,Model model) {
			List<DimensionMaster> dimensionMasters = dimensionService.getAllDimension(surveyId);
			List<SurveyView> views = new ArrayList<SurveyView>();
			for(DimensionMaster master : dimensionMasters) {
				SurveyView view = new SurveyView();
				//Get Client By Id
				/*Optional<UserMaster> user = userService.getUserById(master.getCompanyid());
				if(user.isPresent()) {
					view.setClientId(user.get().getId());
					view.setClientName(user.get().getCompanyName());
				}*/
				//Get Survey
				Optional<SurveyMaster> survey = surveyMasterService.getSurveyMasterById(surveyId);
				if(survey.isPresent()) {
					view.setSurveyId(survey.get().getSurveyId());
					view.setSurveyName(survey.get().getSurveyName());
				}
				view.setDimensionId(master.getDimensionid());
				view.setDimensionName(master.getDimension());
				views.add(view);
			}
			List<UserMaster> clients = userService.getAllCompany();
			List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
			model.addAttribute("clients", clients);
			model.addAttribute("surveys", surveyMasters);
			model.addAttribute("dimensionMaster", new DimensionMaster());
			model.addAttribute("survey", surveyId);
			model.addAttribute("views", views);
			return Constant.addDimesion;
		}
	@PostMapping("/editDimension")
		public String editDimension(@RequestParam("dimensionids") Long dimensionids,Model model) {
			//Get Dimension By Dimension Id
			DimensionMaster dimensionMaster=new DimensionMaster();
			Optional<DimensionMaster> optional = dimensionService.getDimensionById(dimensionids);
			if(optional.isPresent()) {
				dimensionMaster = optional.get();
			}
			List<UserMaster> clients = userService.getAllCompany();
			List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
			model.addAttribute("clients", clients);
			model.addAttribute("surveys", surveyMasters);
			model.addAttribute("survey", optional.get().getSurveyId());
			model.addAttribute("dimensionMaster", dimensionMaster);
			return Constant.addDimesion;
		}
		
	//Delete Dimension by Id
		@GetMapping("/deleteDimension")
		public String deleteDimensionById(@RequestParam("dimensionId") Long dimensionId, Model model) {
			//Find Dimension First
			Optional<DimensionMaster> optional = dimensionService.getDimensionById(dimensionId);
			if(optional.isPresent()) {
				//Delete 
				dimensionService.deleteDimensionById(optional.get().getDimensionid());
			}
			//Get All Dimensions
			List<DimensionMaster> dimensionMasters = dimensionService.getAllDimension(optional.get().getSurveyId());
			List<SurveyView> views = new ArrayList<SurveyView>();
			for(DimensionMaster master : dimensionMasters) {
				SurveyView view = new SurveyView();
				
				//Get Survey
				Optional<SurveyMaster> survey = surveyMasterService.getSurveyMasterById(master.getSurveyId());
				if(survey.isPresent()) {
					view.setSurveyId(survey.get().getSurveyId());
					view.setSurveyName(survey.get().getSurveyName());
				}
				view.setDimensionId(master.getDimensionid());
				view.setDimensionName(master.getDimension());
				views.add(view);
			}
			List<UserMaster> clients = userService.getAllCompany();
			List<SurveyMaster> surveyMasters = surveyMasterService.getAllSurveyMaster();
			model.addAttribute("clients", clients);
			model.addAttribute("surveys", surveyMasters);
			model.addAttribute("dimensionMaster", new DimensionMaster());
			model.addAttribute("views", views);
			model.addAttribute("survey", optional.get().getSurveyId());
			return Constant.addDimesion;
		}
}
