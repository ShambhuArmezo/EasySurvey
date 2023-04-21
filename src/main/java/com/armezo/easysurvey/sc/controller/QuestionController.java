package com.armezo.easysurvey.sc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.service.UserService;
import com.armezo.easysurvey.sc.model.CommentMaster;
import com.armezo.easysurvey.sc.model.DimensionMaster;
import com.armezo.easysurvey.sc.model.MultiplechoiceMaster;
import com.armezo.easysurvey.sc.model.MultiplechoiceOptionMaster;
import com.armezo.easysurvey.sc.model.QuestionTypeMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceOptionMaster;
import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.payload.SurveyClientMappingPayload;
import com.armezo.easysurvey.sc.service.CommentService;
import com.armezo.easysurvey.sc.service.DimensionService;
import com.armezo.easysurvey.sc.service.MultiplechoiceService;
import com.armezo.easysurvey.sc.service.QuestionTypService;
import com.armezo.easysurvey.sc.service.SinglechoiceService;
import com.armezo.easysurvey.sc.service.SurveyDetailsService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionTypService questionTypService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SinglechoiceService singlechoiceService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MultiplechoiceService multiplechoiceService;
	
	@Autowired
	private SurveyMasterService surveyMasterService;
	
	@Autowired
	private DimensionService dimensionService;
	
	@Autowired
	private SurveyDetailsService surveyDetailsService;
	

	@GetMapping("/addSurveyQue")
	public String showQuestionInfo(Model mod)
	{
		List<QuestionTypeMaster> qt=questionTypService.getQuestionTyp();
		mod.addAttribute("questionType",qt);
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		mod.addAttribute("surveys", surveyList);
		
		mod.addAttribute("sqType","0");
		mod.addAttribute("company","0");
		mod.addAttribute("section","0");
		mod.addAttribute("mandatoryQue","0");
		mod.addAttribute("noofopt","0");
		mod.addAttribute("nuofAttr","0");
		
		return Constant.addQuestion;
	}
	
	@PostMapping("/viewTable")
	public String viewDimension(@RequestParam("sqType")String sqType,@RequestParam("company")Integer company,@RequestParam("section")Integer section,@RequestParam("mandatoryQue")Integer mandatoryQue,@RequestParam("noofopt")Integer noofopt,@RequestParam("nuofAttr")Integer nuofAttr,Model mod) 
	{
		mod.addAttribute("sqType",sqType);
		mod.addAttribute("company",company);
		mod.addAttribute("section",section);
		mod.addAttribute("mandatoryQue",mandatoryQue);
		mod.addAttribute("noofopt",noofopt);
		mod.addAttribute("nuofAttr",nuofAttr);
		
		List<QuestionTypeMaster> qt=questionTypService.getQuestionTyp();
		mod.addAttribute("questionType",qt);
		
		List<UserMaster> ob=userService.getAllCompany();
		mod.addAttribute("allCompany",ob);
		
		 return Constant.addQuestion;
	 }
	
	@RequestMapping(value = "uploadQuestionPro", method = RequestMethod.POST)
	public String uploadQues(@RequestParam("uploadtofile") MultipartFile file,@RequestParam("surveyId")Long surveyId, ModelMap map) {
		Long dimenstionId=0L;
		List<SinglechoiceMaster> listQues = new ArrayList<SinglechoiceMaster>();
		List<CommentMaster> commentQues = new ArrayList<CommentMaster>();
		List<MultiplechoiceMaster> multipleQues = new ArrayList<MultiplechoiceMaster>();
		
		List<ModelMap> data=new ArrayList<ModelMap>();
		List<SinglechoiceMaster> hh = new ArrayList<SinglechoiceMaster>();
		List<CommentMaster> hh1 = new ArrayList<CommentMaster>();
		List<MultiplechoiceMaster> hh2 = new ArrayList<MultiplechoiceMaster>();
		
		Optional<SurveyMaster> getSurveyName = surveyMasterService.getSurvey(surveyId);
			String surveyName=	getSurveyName.get().getSurveyName();
		
		try {
			XSSFWorkbook xms = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = xms.getSheetAt(0);
			
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				ModelMap mod=new ModelMap();
				SinglechoiceMaster que = new SinglechoiceMaster();
				CommentMaster commQue = new CommentMaster();
				MultiplechoiceMaster multiQue = new MultiplechoiceMaster();
				XSSFRow row = sheet.getRow(i);
				
				
				String q_type=row.getCell(0).getStringCellValue();
				String mandatry=row.getCell(1).getStringCellValue();
				
				String dimention=row.getCell(2).getStringCellValue();
				List<DimensionMaster> dimensionName=dimensionService.getDimension(dimention);
				for(DimensionMaster dime: dimensionName) {
					dimenstionId=dime.getDimensionid();
				}
				Integer noOfOptions=(int) row.getCell(4).getNumericCellValue();
				//SINGLECHOICE question
				if(q_type.equals("SINGLECHOICE"))
				{
					if(mandatry.equals("Yes"))
					{
						que.setMqstatus(1);
					}
					else
					{
						que.setMqstatus(0);
					}
					
					String ques=row.getCell(3).getStringCellValue().replaceAll("'", "&apos;");
					que.setQuestion(ques);
					que.setSurveyId(surveyId);
					que.setDimansionId(dimenstionId);
					que.setStatus("A");
					List<SinglechoiceOptionMaster> optionsList = new ArrayList<SinglechoiceOptionMaster>();
					
					String[] options = row.getCell(5).getStringCellValue().replaceAll("'", "&apos;").split("@");
					String[] score = row.getCell(6).getStringCellValue().split("@");
					if (options.length == noOfOptions) {
						for (int count = 0; count < noOfOptions; count++) {
							SinglechoiceOptionMaster opt = new SinglechoiceOptionMaster();
							opt.setQoption(options[count]);
							
							if (score.length > 0) {
								opt.setScore(Integer.parseInt(score[count]));
							}
							opt.setSinglechoiceMaster(que);
							opt.setStatus("A");
							optionsList.add(opt);
							

						}

					}
					que.setSinglechoiceOptionMaster(optionsList);
					listQues.add(que);
					hh=singlechoiceService.saveAllQuestion(listQues);
					
					map.addAttribute("questionMaster", hh);
				}
				if(q_type.equals("COMMENT"))
				{
					if(mandatry.equals("Yes"))
					{
						commQue.setMqstatus(1);
					}
					else
					{
						commQue.setMqstatus(0);
					}
					
					String ques=row.getCell(3).getStringCellValue().replaceAll("'", "&apos;");
					commQue.setQuestion(ques);
					commQue.setSurveyId(surveyId);
					commQue.setDimansionId(dimenstionId);
					commQue.setStatus("A");
					commentQues.add(commQue);
					hh1=commentService.saveAllQuestion(commentQues);
					
					map.addAttribute("questionMaster", hh1);
				}
				
				if(q_type.equals("MULTIPLECHOICE"))
				{
					if(mandatry.equals("Yes"))
					{
						multiQue.setMqstatus(1);
					}
					else
					{
						multiQue.setMqstatus(0);
					}
					
					String ques=row.getCell(3).getStringCellValue().replaceAll("'", "&apos;");
					multiQue.setQuestion(ques);
					multiQue.setSurveyId(surveyId);
					multiQue.setDimansionId(dimenstionId);
					multiQue.setStatus("A");
					List<MultiplechoiceOptionMaster> optionsList = new ArrayList<MultiplechoiceOptionMaster>();
					
					String[] options = row.getCell(5).getStringCellValue().replaceAll("'", "&apos;").split("@");
					String[] score = row.getCell(6).getStringCellValue().split("@");
					if (options.length == noOfOptions) {
						for (int count = 0; count < noOfOptions; count++) {
							MultiplechoiceOptionMaster opt = new MultiplechoiceOptionMaster();
							opt.setQoption(options[count]);
							
							if (score.length > 0) {
								opt.setScore(Integer.parseInt(score[count]));
							}
							opt.setMultiplechoiceMaster(multiQue);
							opt.setStatus("A");
							optionsList.add(opt);

						}

					}
					multiQue.setMultiplechoiceOptionMaster(optionsList);
					multipleQues.add(multiQue);
					hh2=multiplechoiceService.saveAllQuestion(multipleQues);
					
					map.addAttribute("questionMaster", hh2);
				}
				
				
				mod.addAttribute("q_type", q_type);
				mod.addAttribute("mandatry", mandatry);
				mod.addAttribute("survey", surveyName);
				mod.addAttribute("dimantion", dimention);
				mod.addAttribute("noOfOptions", noOfOptions);
				
				data.add(mod);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		map.addAttribute("withData",data);
		return Constant.addQuestion;
	}
	
	@GetMapping("/questionDelete")
	public String deleteQuestionInfo(Model mod)
	{
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		mod.addAttribute("surveys", surveyList);
		
		return Constant.questionDelete;
	}
	
	@PostMapping("/viewQues")
	public String viewQues(@RequestParam Long surveyId, Model mod) {
		String dimenstionName="";
		String surveyName="";
		Integer publish=0;
		
		List<SurveyClientMappingPayload> ques = new ArrayList<SurveyClientMappingPayload>();
		
		List<SinglechoiceMaster> singleQues = singlechoiceService.getAllQuestionBySurveyId(surveyId);
		for(SinglechoiceMaster question : singleQues) {
			SurveyClientMappingPayload payload = new SurveyClientMappingPayload();
			
			List<DimensionMaster> dimensionName=dimensionService.getDimensionName(question.getDimansionId());
			for(DimensionMaster dime: dimensionName) {
				dimenstionName=dime.getDimension();
			}
			List<SurveyMaster> survName=surveyMasterService.getSurveyName(question.getSurveyId());
			for(SurveyMaster surv: survName) {
				surveyName=surv.getSurveyName();
				publish=surv.getPublish();
			}
			payload.setQuestionId(question.getQid());
			payload.setQuestion(question.getQuestion());
			payload.setDimension(dimenstionName);
			payload.setDimensionId(question.getDimansionId());
			payload.setSurveyName(surveyName);
			payload.setSurveyId(surveyId);
			payload.setPublish(publish);
			payload.setQuestionType("SINGLECHOICE");
			payload.setTid(4);
			ques.add(payload);
			mod.addAttribute("questionMaster", ques);
			
		}
		
		List<CommentMaster> commentQues = commentService.getCommQuestionBySurveyId(surveyId);
		for(CommentMaster question : commentQues) {
			SurveyClientMappingPayload payload = new SurveyClientMappingPayload();
			
			List<DimensionMaster> dimensionName=dimensionService.getDimensionName(question.getDimansionId());
			for(DimensionMaster dime: dimensionName) {
				dimenstionName=dime.getDimension();
			}
			List<SurveyMaster> survName=surveyMasterService.getSurveyName(question.getSurveyId());
			for(SurveyMaster surv: survName) {
				surveyName=surv.getSurveyName();
			}
			payload.setQuestionId(question.getQid());
			payload.setQuestion(question.getQuestion());
			payload.setDimension(dimenstionName);
			payload.setDimensionId(question.getDimansionId());
			payload.setSurveyName(surveyName);
			payload.setSurveyId(surveyId);
			payload.setPublish(publish);
			payload.setQuestionType("COMMENT");
			payload.setTid(3);
			ques.add(payload);
			mod.addAttribute("questionMaster", ques);
			
		}
		
		List<MultiplechoiceMaster> multipleQues = multiplechoiceService.getMultiQuestionBySurveyId(surveyId);
		for(MultiplechoiceMaster question : multipleQues) {
			SurveyClientMappingPayload payload = new SurveyClientMappingPayload();
			
			List<DimensionMaster> dimensionName=dimensionService.getDimensionName(question.getDimansionId());
			for(DimensionMaster dime: dimensionName) {
				dimenstionName=dime.getDimension();
			}
			List<SurveyMaster> survName=surveyMasterService.getSurveyName(question.getSurveyId());
			for(SurveyMaster surv: survName) {
				surveyName=surv.getSurveyName();
			}
			payload.setQuestionId(question.getQid());
			payload.setQuestion(question.getQuestion());
			payload.setDimension(dimenstionName);
			payload.setDimensionId(question.getDimansionId());
			payload.setSurveyName(surveyName);
			payload.setSurveyId(surveyId);
			payload.setPublish(publish);
			payload.setQuestionType("MULTIPLECHOICE");
			payload.setTid(1);
			ques.add(payload);
			mod.addAttribute("questionMaster", ques);
			
		}
		
		List<SurveyMaster> surveyList = surveyMasterService.getAllSurveyMaster();
		mod.addAttribute("surveys", surveyList);
		mod.addAttribute("survey", surveyId);
		
		return Constant.questionDelete;
	}
	
	@PostMapping("/delQues")
	public String delQues(@RequestParam("questionId")Long questionId,@RequestParam("surveyId")Long surveyId,@RequestParam("dimensionId")Long dimensionId,@RequestParam("tin")Integer tid,Model mod) 
	{
		if(tid==4)
		{
			Optional<SinglechoiceMaster> optional = singlechoiceService.getQuestionById(questionId,surveyId,dimensionId);
			if(optional.isPresent()) {
				//Delete 
				singlechoiceService.deleteQuestionById(optional.get().getQid());
				//surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,tid);
				surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,4);
			}
		}
		if(tid==3)
		{
			Optional<CommentMaster> optional = commentService.getQuestionById(questionId,surveyId,dimensionId);
			if(optional.isPresent()) {
				//Delete 
				commentService.deleteQuestionById(optional.get().getQid());
				//surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,tid);
				surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,3);
			}
		}
		if(tid==1)
		{
			Optional<MultiplechoiceMaster> optional = multiplechoiceService.getQuestionById(questionId,surveyId,dimensionId);
			if(optional.isPresent()) {
				//Delete 
				multiplechoiceService.deleteQuestionById(optional.get().getQid());
				//surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,tid);
				surveyDetailsService.deleteSurQuestionById(questionId,surveyId,dimensionId,1);
			}
		}
		return Constant.questionDelete;
	}
}
