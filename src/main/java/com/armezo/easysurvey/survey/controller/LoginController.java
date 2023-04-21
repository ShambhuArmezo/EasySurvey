package com.armezo.easysurvey.survey.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.admin.model.EmployeeMaster;
import com.armezo.easysurvey.admin.service.EmployeeService;
import com.armezo.easysurvey.sc.model.AddLandingMaster;
import com.armezo.easysurvey.sc.model.CommentMaster;
import com.armezo.easysurvey.sc.model.MultiplechoiceMaster;
import com.armezo.easysurvey.sc.model.SinglechoiceMaster;
import com.armezo.easysurvey.sc.model.SurveyDetails;
import com.armezo.easysurvey.sc.model.SurveyEndDateMaster;
import com.armezo.easysurvey.sc.service.AddLandingService;
import com.armezo.easysurvey.sc.service.CommentService;
import com.armezo.easysurvey.sc.service.MultiplechoiceService;
import com.armezo.easysurvey.sc.service.SinglechoiceService;
import com.armezo.easysurvey.sc.service.SurveyDetailsService;
import com.armezo.easysurvey.sc.service.SurveyEndDateService;
import com.armezo.easysurvey.survey.model.CommentAnswer;
import com.armezo.easysurvey.survey.model.EmpSurveyStatus;
import com.armezo.easysurvey.survey.model.MultichoiceAnswer;
import com.armezo.easysurvey.survey.model.SinglechoiceAnswer;
import com.armezo.easysurvey.survey.service.CommentAnswerService;
import com.armezo.easysurvey.survey.service.EmpSurveyStatusService;
import com.armezo.easysurvey.survey.service.MultichoiceAnswerService;
import com.armezo.easysurvey.survey.service.SinglechoiceAnswerService;

import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;    

@Controller
public class LoginController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SurveyEndDateService surveyEndDateService;
	
	@Autowired
	private AddLandingService addLandingService;
	
	@Autowired
	private EmpSurveyStatusService empSurveyStatusService;
	
	@Autowired
	private SurveyDetailsService surveyDetailsService;
	
	@Autowired
	private SinglechoiceAnswerService singlechoiceAnswerService;
	
	@Autowired
	private CommentAnswerService commentAnswerService;
	
	@Autowired
	private MultichoiceAnswerService multichoiceAnswerService;
	
	@Autowired
	private SinglechoiceService singlechoiceService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MultiplechoiceService multiplechoiceService;
	
	@GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", "");
        return Constant.login;
    }
	
	@PostMapping("/loginPro")
    public String loginPro(@RequestParam String accesskey, @RequestParam int answer, HttpSession session, Model model) {
        int correctAnswer = (int) session.getAttribute("answer");
        String empCode="",empName="",surveyDate="",instruction;
        Long surveyId=0L,clientId=0L;
        long time_difference=0L;
        Integer surveystatus=0,currsec=0;
        if (correctAnswer != answer) {
        	model.addAttribute("error", "Invalid Calculation.");
            return Constant.login;
        }
        Optional<EmployeeMaster> ids=employeeService.getEmployeeData(accesskey);
        if(ids.isPresent())
		 {
        	empCode=ids.get().getEmployeeId();
        	empName=ids.get().getName();
        	surveyId=ids.get().getSurveyId();
        	clientId=ids.get().getClientId();
        	
        	Optional<SurveyEndDateMaster> dates=surveyEndDateService.getSurveyDate(surveyId,clientId);
        	 if(dates.isPresent())
    		 {
        		 surveyDate=dates.get().getSurveyDate();
        		 
        		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
        		 LocalDateTime now = LocalDateTime.now();
        		 String todays=dtf.format(now);

        		 SimpleDateFormat obj = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        		 try {   
        	            // Use parse method to get date object of both dates  
        	            Date date1 = obj.parse(surveyDate);   
        	            Date date2 = obj.parse(todays); 
        	            time_difference = date1.getTime() - date2.getTime();  
        		 }
        		 catch (ParseException excep) {   
        	            excep.printStackTrace();   
        	        } 
        		 if (time_difference <=0) {
        	         return Constant.surveyClosed;
                 }
    		 }else
    		 {
    			 model.addAttribute("error", "Please insert Survey Date.");
    	         return Constant.login;
    		 }
		 }
        else
        {
        	model.addAttribute("error", "Invalid Access Key");
            return Constant.login;
        }
        
        Integer totalsec = surveyDetailsService.totalSec(surveyId,clientId);
        Optional<EmpSurveyStatus> employeeSurveyDetail = empSurveyStatusService.findByAccesskey(accesskey,empCode,surveyId,clientId);
        if (employeeSurveyDetail.isPresent()) {
        	surveystatus=employeeSurveyDetail.get().getSurveystatus();
        	currsec=employeeSurveyDetail.get().getCurrsec();
        	
        	if (surveystatus >= 2) {
				model.addAttribute("error", "Survey Completed");
	            return Constant.login;
			}
		}
        
        Optional<AddLandingMaster> ins=addLandingService.getByClientIdAndSurveyId(clientId,surveyId);
        instruction=ins.get().getInstruction();
        
        session.setAttribute("accesskey", accesskey);
        session.setAttribute("empCode", empCode);
        session.setAttribute("empName", empName);
        session.setAttribute("surveyId", surveyId);
        session.setAttribute("clientId", clientId);
        session.setAttribute("totalsec", totalsec);
        session.setAttribute("surveystatus", surveystatus);
        session.setAttribute("currsec", currsec);
        
        model.addAttribute("ins", instruction);
        return Constant.instructions;
    }

	@PostMapping("/onlineSurvey")
	public String profileGenerate(HttpSession session, Model model) {
		String accesskey = (String) session.getAttribute("accesskey");
		String empCode = (String) session.getAttribute("empCode");
		String empName = (String) session.getAttribute("empName");
		Long surveyId = (Long) session.getAttribute("surveyId");
		Long clientId = (Long) session.getAttribute("clientId");
		Integer currsec = 0;
		Integer totalsec = (Integer) session.getAttribute("totalsec");
		
		Map<Long,String> map=new HashMap<Long,String>();
		
		List<SinglechoiceMaster> sing = new ArrayList<>();
		List<CommentMaster> comm = new ArrayList<>();
		List<MultiplechoiceMaster> mul = new ArrayList<>();
		int mqstatus=0;
		String elemName = "",mqdetail = "",qdetail = "";
		Optional<EmpSurveyStatus> isProfileGenerated = empSurveyStatusService.findByAccesskey(accesskey,empCode,surveyId,clientId);
		if (isProfileGenerated.isPresent()) {
			
		} else {
			EmpSurveyStatus empSurveyStatus = new EmpSurveyStatus();
			empSurveyStatus.setAccesskey(accesskey);
			empSurveyStatus.setEmpcode(empCode);
			empSurveyStatus.setSurveyId(surveyId);
			empSurveyStatus.setClientId(clientId);
			empSurveyStatus.setStartdate(new Date());
			empSurveyStatus.setSurveystatus(0);
			empSurveyStatus.setCurrsec(1);
			empSurveyStatusService.generateProfile(empSurveyStatus);
			
			List<SurveyDetails> queId=surveyDetailsService.getQuesId(surveyId,clientId);
			for(SurveyDetails qId: queId) {
				
				if(qId.getTid()==4)
				{
					SinglechoiceAnswer singeQues = new SinglechoiceAnswer();
					singeQues.setAccesskey(accesskey);
					singeQues.setEmpcode(empCode);
					singeQues.setSurveyId(surveyId);
					singeQues.setClientId(clientId);
					singeQues.setQid(qId.getQuestionId());
					singlechoiceAnswerService.saveSingleQues(singeQues);
				}
				if(qId.getTid()==3)
				{
					CommentAnswer commentQues = new CommentAnswer();
					commentQues.setAccesskey(accesskey);
					commentQues.setEmpcode(empCode);
					commentQues.setSurveyId(surveyId);
					commentQues.setClientId(clientId);
					commentQues.setQid(qId.getQuestionId());
					commentAnswerService.saveCommentQues(commentQues);
				}
				if(qId.getTid()==1)
				{
					MultichoiceAnswer multiQues = new MultichoiceAnswer();
					multiQues.setAccesskey(accesskey);
					multiQues.setEmpcode(empCode);
					multiQues.setSurveyId(surveyId);
					multiQues.setClientId(clientId);
					multiQues.setQid(qId.getQuestionId());
					multichoiceAnswerService.saveMultiQuesQues(multiQues);
				}
				
			}
		}
		
		Optional<EmpSurveyStatus> employeeSurveyDetail = empSurveyStatusService.findByAccesskey(accesskey,empCode,surveyId,clientId);
        if (employeeSurveyDetail.isPresent()) {
        	currsec=employeeSurveyDetail.get().getCurrsec();
		}
		
		//System.out.println("surveyId.."+surveyId+".."+clientId+".."+currsec);
		List<SurveyDetails> currQueId=surveyDetailsService.getCurrQuesId(surveyId,clientId,currsec);
		for(SurveyDetails currQId: currQueId) {
			//System.out.println("currQId.getTid().."+currQId.getTid()+".."+currQId.getQuestionId());
			if(currQId.getTid()==4)
			{
				Optional<SinglechoiceMaster> single = singlechoiceService.getCurrQuesId(currQId.getQuestionId());
				if (single.isPresent()) {
					mqstatus = single.get().getMqstatus();
					//System.out.println("mqstatus.."+mqstatus);
					elemName = "singlechoice@@"+single.get().getQid(); 
					qdetail += elemName+","; 
					if(mqstatus==1) 
					{  
						mqdetail += elemName+","; 
					}
					Optional<SinglechoiceAnswer> ans = singlechoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
					String answer=ans.get().getAnswer();
					map.put(currQId.getQuestionId(), answer);
					
					sing.add(single.get());
					//System.out.println("sing.."+sing.size());
				}
			}
			if(currQId.getTid()==3)
			{
				Optional<CommentMaster> comment=commentService.getCurrQuesId(currQId.getQuestionId());
				if (comment.isPresent()) {
					
					mqstatus = comment.get().getMqstatus();
					elemName = "comment@@"+comment.get().getQid(); 
					qdetail += elemName+","; 
					if(mqstatus==1) 
					{  
						mqdetail += elemName+","; 
					}
					Optional<CommentAnswer> ans = commentAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
					String answer=ans.get().getAnswer();
					map.put(currQId.getQuestionId(), answer);
					
					comm.add(comment.get());
				}
			}
			if(currQId.getTid()==1)
			{
				Optional<MultiplechoiceMaster> multiple=multiplechoiceService.getCurrQuesId(currQId.getQuestionId());
				if (multiple.isPresent()) {
					
					mqstatus = multiple.get().getMqstatus();
					elemName = "multichoice@@"+multiple.get().getQid(); 
					qdetail += elemName+","; 
					if(mqstatus==1) 
					{  
						mqdetail += elemName+","; 
					}
					Optional<MultichoiceAnswer> ans = multichoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
					String answer=ans.get().getAnswer();
					map.put(currQId.getQuestionId(), answer);
					
					mul.add(multiple.get());
				}
			}
			
		}
		if(!mqdetail.equals(""))
		{
			mqdetail = mqdetail.substring(0,mqdetail.length()-1);
		}
		if(!qdetail.equals(""))
		{
			qdetail = qdetail.substring(0,qdetail.length()-1);
		}
		model.addAttribute("mqdetail", mqdetail);
		model.addAttribute("qdetail", qdetail);
		
		model.addAttribute("singleSize", sing.size());
		model.addAttribute("commSize", comm.size());
		model.addAttribute("mulSize", mul.size());
		
		model.addAttribute("single", sing);
		model.addAttribute("comment", comm);
		model.addAttribute("multi", mul);
		
		model.addAttribute("currsec", currsec);
		model.addAttribute("totalsec", totalsec);
		
		model.addAttribute("Ansmap", map);
		
		return Constant.onlineSurvey;
	}
	
	@PostMapping("/qursPro")
	public String qursPro(@RequestParam("flag")String flag,HttpServletRequest request,HttpSession session, Model model) {
	
		String result = "";
		String accesskey = (String) session.getAttribute("accesskey");
		String empCode = (String) session.getAttribute("empCode");
		String empName = (String) session.getAttribute("empName");
		Long surveyId = (Long) session.getAttribute("surveyId");
		Long clientId = (Long) session.getAttribute("clientId");
		Integer currsec = 0;
		Integer totalsec = (Integer) session.getAttribute("totalsec");
		int newsecno = 0;
		Integer surveystatus=0;
		Map<Long,String> map=new HashMap<Long,String>();
		int mqstatus=0;
		String elemName = "",mqdetail = "",qdetail = "";
		
		List<SinglechoiceMaster> sing = new ArrayList<>();
		List<CommentMaster> comm = new ArrayList<>();
		List<MultiplechoiceMaster> mul = new ArrayList<>();
		
		String qdetails = request.getParameter("qdetail");
		String[] qdetailArr = qdetails.split(",");
		String[] elemDetail = null;
		String qtype = "";
		String answer = "";
		String[] quesInfo = null;
		 String qids = "";
		for (int i = 0; i < qdetailArr.length; ++i) 
		{
			elemDetail = qdetailArr[i].split("@@");
            qtype = elemDetail[0];
            answer = "";
            
            if (qtype.equals("singlechoice")) {
            	quesInfo = elemDetail[1].split("##");
                qids = quesInfo[0];
                Long qid = Long.parseLong(qids);
                answer = request.getParameter(qdetailArr[i]);
                
                Optional<SinglechoiceAnswer> singleUpdate = singlechoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,qid);
                if (singleUpdate.isPresent()) {
                	SinglechoiceAnswer single = singleUpdate.get();
                	single.setId(singleUpdate.get().getId());
                	single.setAnswer(answer);
                	singlechoiceAnswerService.saveSingleQues(single);
                }
            }
            else if (qtype.equals("comment")) {
            	quesInfo = elemDetail[1].split("##");
                qids = quesInfo[0];
                Long qid = Long.parseLong(qids);
                answer = request.getParameter(qdetailArr[i]);
                
                Optional<CommentAnswer> commentUpdate = commentAnswerService.findById(accesskey,empCode,surveyId,clientId,qid);
                if (commentUpdate.isPresent()) {
                	CommentAnswer comment = commentUpdate.get();
                	comment.setId(commentUpdate.get().getId());
                	comment.setAnswer(answer);
                	commentAnswerService.saveCommentQues(comment);
                }
            }
            
            else if (qtype.equals("multichoice")) {
            	quesInfo = elemDetail[1].split("##");
                qids = quesInfo[0];
                Long qid = Long.parseLong(qids);
                answer = request.getParameter(qdetailArr[i]);
                
                Optional<MultichoiceAnswer> multiUpdate = multichoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,qid);
                if (multiUpdate.isPresent()) {
                	MultichoiceAnswer multi = multiUpdate.get();
                	multi.setId(multiUpdate.get().getId());
                	multi.setAnswer(answer);
                	multichoiceAnswerService.saveMultiQuesQues(multi);
                }
            }
		}
		
		Optional<EmpSurveyStatus> employeeSurveyDetails = empSurveyStatusService.findByAccesskey(accesskey,empCode,surveyId,clientId);
        if (employeeSurveyDetails.isPresent()) {
        	currsec=employeeSurveyDetails.get().getCurrsec();
		}
		
		Optional<EmpSurveyStatus> employeeSurveyDetail = empSurveyStatusService.findByAccesskey(accesskey,empCode,surveyId,clientId);
        if (employeeSurveyDetail.isPresent()) {
        	Long ids=employeeSurveyDetail.get().getId();
        	if (flag.equalsIgnoreCase("N") || flag.equalsIgnoreCase("P") || flag.equalsIgnoreCase("R")) {
        		if (flag.equalsIgnoreCase("N")) {
                    newsecno = currsec + 1;
                }
                else if (flag.equalsIgnoreCase("R")) {
                    newsecno = 1;
                }
                else {
                    newsecno = currsec - 1;
                }
        		EmpSurveyStatus status = employeeSurveyDetail.get();
        		status.setId(employeeSurveyDetail.get().getId());
        		status.setSurveystatus(1);
        		status.setCurrsec(newsecno);
    			empSurveyStatusService.generateProfile(status);
    			
    			List<SurveyDetails> currQueId=surveyDetailsService.getCurrQuesId(surveyId,clientId,newsecno);
    			for(SurveyDetails currQId: currQueId) {
    				if(currQId.getTid()==4)
    				{
    					Optional<SinglechoiceMaster> single = singlechoiceService.getCurrQuesId(currQId.getQuestionId());
    					if (single.isPresent()) {
    						mqstatus = single.get().getMqstatus();
    						//System.out.println("mqstatus.."+mqstatus);
    						elemName = "singlechoice@@"+single.get().getQid(); 
    						qdetail += elemName+","; 
    						if(mqstatus==1) 
    						{  
    							mqdetail += elemName+","; 
    						}
    						Optional<SinglechoiceAnswer> ans = singlechoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
    						String answers=ans.get().getAnswer();
    						map.put(currQId.getQuestionId(), answers);
    						sing.add(single.get());
    						//System.out.println("sing.."+sing.size());
    					}
    				}
    				if(currQId.getTid()==3)
    				{
    					Optional<CommentMaster> comment=commentService.getCurrQuesId(currQId.getQuestionId());
    					if (comment.isPresent()) {
    						
    						mqstatus = comment.get().getMqstatus();
    						elemName = "comment@@"+comment.get().getQid(); 
    						qdetail += elemName+","; 
    						if(mqstatus==1) 
    						{  
    							mqdetail += elemName+","; 
    						}
    						Optional<CommentAnswer> ans = commentAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
    						String answers=ans.get().getAnswer();
    						map.put(currQId.getQuestionId(), answers);
    						
    						comm.add(comment.get());
    					}
    				}
    				if(currQId.getTid()==1)
    				{
    					Optional<MultiplechoiceMaster> multiple=multiplechoiceService.getCurrQuesId(currQId.getQuestionId());
    					if (multiple.isPresent()) {
    						
    						mqstatus = multiple.get().getMqstatus();
    						elemName = "multichoice@@"+multiple.get().getQid(); 
    						qdetail += elemName+","; 
    						if(mqstatus==1) 
    						{  
    							mqdetail += elemName+","; 
    						}
    						Optional<MultichoiceAnswer> ans = multichoiceAnswerService.findById(accesskey,empCode,surveyId,clientId,currQId.getQuestionId());
    						String answers=ans.get().getAnswer();
    						map.put(currQId.getQuestionId(), answers);
    						
    						mul.add(multiple.get());
    					}
    				}
    				
    			}
        	}
        	
        	
        	 else if (flag.equalsIgnoreCase("ST")) {
        		 newsecno = currsec; 
        		 
        		 EmpSurveyStatus status = employeeSurveyDetail.get();
        		 status.setId(employeeSurveyDetail.get().getId());
        		 status.setEnddate(new Date());
        		 status.setSurveystatus(2);
     			 empSurveyStatusService.generateProfile(status);
     			 
     			return Constant.thankyou;
        	 }
        	 else {
                 newsecno = currsec;
             }
		}
        
        if(!mqdetail.equals(""))
		{
			mqdetail = mqdetail.substring(0,mqdetail.length()-1);
		}
		if(!qdetail.equals(""))
		{
			qdetail = qdetail.substring(0,qdetail.length()-1);
		}
		model.addAttribute("mqdetail", mqdetail);
		model.addAttribute("qdetail", qdetail);
        
        model.addAttribute("singleSize", sing.size());
		model.addAttribute("commSize", comm.size());
		model.addAttribute("mulSize", mul.size());
		
		model.addAttribute("single", sing);
		model.addAttribute("comment", comm);
		model.addAttribute("multi", mul);
		
		model.addAttribute("Ansmap", map);
		
		model.addAttribute("currsec", newsecno);
		model.addAttribute("totalsec", totalsec);
		return Constant.onlineSurvey;
		
	}

}