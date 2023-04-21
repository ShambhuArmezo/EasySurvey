package com.armezo.easysurvey.admin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.Utility.DataFormatting;
import com.armezo.easysurvey.Utility.GenAccesskey;
import com.armezo.easysurvey.admin.model.EmployeeMaster;
import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.service.EmployeeService;
import com.armezo.easysurvey.admin.service.UserService;
import com.armezo.easysurvey.exception.EmployeeNotFoundException;
import com.armezo.easysurvey.sc.model.SurveyClientMapping;
import com.armezo.easysurvey.sc.model.SurveyMaster;
import com.armezo.easysurvey.sc.service.SurveyClientMappingService;
import com.armezo.easysurvey.sc.service.SurveyMasterService;
import com.armezo.easysurvey.sc.payload.FilterPayload;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	@Autowired
	private SurveyClientMappingService surveyClientMappingService;
	@Autowired
	private SurveyMasterService surveyMasterService;
	
	//Show Employee Upload Page
	@GetMapping("/uploadEmp")
	public String showPageToUploadEmployee(Model model) {
		List<UserMaster> clients = userService.getAllCompany();
		List<EmployeeMaster> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("clients", clients);
		return Constant.uploadEmp;
	}
	//Get Survey By Client Id
	@PostMapping("/viewSurvey1")
	public String viewSurvey1(@RequestParam("clientId")Long clientId,Model mod) 
	{
		long surveyId=0;
		List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
		for(SurveyClientMapping sur: survey) {
			List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
			mod.addAttribute("surveyList",getSur);
		}
		
		List<UserMaster> clients=userService.getAllCompany();
		mod.addAttribute("clients",clients);
		 mod.addAttribute("clientId",clientId);
		
		 return Constant.uploadEmp;
	 }
	
	  @PostMapping("/uploadEmployee")
	    public String uploadXLS(@RequestParam("clientId") Long clientId, @RequestParam("surveyid") Long surveyid,
	    @RequestParam("file") MultipartFile file, Model model) throws IOException {
	        if (!file.getOriginalFilename().endsWith(".xls")) {
	            throw new IOException("Only XLS files are allowed.");
	        }

	        List<EmployeeMaster> employees = new ArrayList<>();

	        // Get the workbook object for the uploaded XLS file
	        Workbook workbook = new HSSFWorkbook(file.getInputStream());

	        // Get the first sheet in the workbook
	        Sheet worksheet = workbook.getSheetAt(0);

	        // Iterate through each row in the worksheet
	        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
	        	EmployeeMaster employee = new EmployeeMaster();

	            // Get the current row
	            Row row = worksheet.getRow(i);
	            //Generate Accesskey for this employee
	            employee.setAccesskey(GenAccesskey.generateAccesskey(6));
	            employee.setStatus("A");
	            employee.setRegistrationDate(new Date());
	            employee.setClientId(clientId);
	            employee.setSurveyId(surveyid);
	            // Set the employee properties from the row data
	            employee.setEmployeeId(row.getCell(0).getStringCellValue());
	            employee.setName(row.getCell(1).getStringCellValue());
	            employee.setEmail(row.getCell(2).getStringCellValue());
	            employee.setZone(row.getCell(3).getStringCellValue());
	            employee.setRegion(row.getCell(4).getStringCellValue());
	            employee.setDepartment(row.getCell(5).getStringCellValue());

	            // Convert the date string to a Date object
	            String dateString = row.getCell(6).getStringCellValue();
	            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            Date dateOfJoining=new Date();
				try {
					dateOfJoining = dateFormat.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
	            employee.setDateOfJoining(dateOfJoining);

	            employee.setReportingManagerName(row.getCell(7).getStringCellValue());
	            employee.setReportingManagerId(Long.valueOf(row.getCell(8).getStringCellValue()));
	            employee.setEmployeeCategory(row.getCell(9).getStringCellValue());

	            employees.add(employee);
	        }

	        // Save the employees to the database
	        employeeService.saveAllEmployees(employees);
	        List<EmployeeMaster> employees2 = employeeService.findAll();
			  model.addAttribute("employees", employees2);
			  return Constant.uploadEmp;
	    }
	  
	  
	//View All Employee
	  @GetMapping("/viewEmp")
	  public String getAllEmployees(Model model) {
		  List<EmployeeMaster> employees = employeeService.findAll();
		  List<UserMaster> clients = userService.getAllCompany();
		  model.addAttribute("employees", employees);
		  model.addAttribute("clients", clients);
		  model.addAttribute("surveyStatus", "Active");
		  model.addAttribute("surveyEndDate", DataFormatting.dateFormatToString(new Date()));
		  return Constant.viewEmp;
	  }
	  
	//Get Survey By Client Id
		@PostMapping("/viewSurvey2")
		public String viewSurvey2(@RequestParam("clientId")Long clientId,Model mod) 
		{
			long surveyId=0;
			List<SurveyClientMapping> survey=surveyClientMappingService.getSurveyClientMappingByClientId(clientId);
			for(SurveyClientMapping sur: survey) {
				List<SurveyMaster> getSur = surveyMasterService.getSurveyName(sur.getSurveyId());
				mod.addAttribute("surveyList",getSur);
			}
			
			List<UserMaster> clients=userService.getAllCompany();
			mod.addAttribute("clients",clients);
			 mod.addAttribute("clientId",clientId);
			
			 return Constant.viewEmp;
		 }
		
		//Show Employee By Search Filter
		  @PostMapping("/earchEmployee")
		  public String searchEmployee(@RequestParam("clientName") Long clientId, @RequestParam("surveyName") Long surveyId,
				  @RequestParam("employeeId") String employeeId, @RequestParam("empName") String name,
				  @RequestParam("accesskey") String accesskey, @RequestParam(name = "status", required = false) Integer status,
				  @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo,  Model model) {
			  
			  //Date Fromatting
			  Date fromDate=null;
				Date toDate=null;
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					if (dateFrom != null && dateFrom != "") {
						fromDate = sdf.parse(dateFrom);
					}
					if (dateTo != null && dateTo != "") {
						toDate = DataFormatting.addTimeInDate(sdf.parse(dateTo));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//System.out.println("Filter C : "+clientId+"<>"+surveyId+"<>"+employeeId+"<>"+name+"<>"+accesskey+"<>"+status+"<>"+fromDate+"<>"+toDate);
				//Get Employee Data By Search Criteria
				List<EmployeeMaster> employees = employeeService.findEmployeesBySearchFilter(clientId,surveyId,employeeId,name,accesskey,fromDate,toDate);
			  System.out.println("Size : "+employees.size());
			  List<UserMaster> clients = userService.getAllCompany();
			  //Adding Filter Data for Auto select
			  FilterPayload payload = new FilterPayload();
			  payload.setAccesskey(accesskey);
			  payload.setClientId(clientId);
			  payload.setEmployeeId(employeeId);
			  payload.setName(name);
			  payload.setFromDate(dateFrom);
			  payload.setToDate(dateTo);
			  payload.setStatus(status);
			  payload.setSurveyId(surveyId);
			  model.addAttribute("payload", payload);
			  model.addAttribute("employees", employees);
			  model.addAttribute("clients", clients);
			  model.addAttribute("surveyStatus", "Active");
			  model.addAttribute("surveyEndDate", DataFormatting.dateFormatToString(new Date()));
			  return Constant.viewEmp;
		  }

		// Show Employee By Employee Id
		  @GetMapping("/editEmployee")
		  public String viewEmployeeToUpdate(@RequestParam("empId") Long id, Model model) {
			  Optional<EmployeeMaster> optional = employeeService.findById(id);
			  EmployeeMaster employee = new EmployeeMaster();
			  if(optional.isPresent()) {
				  employee=optional.get();
			  }else {
				throw new EmployeeNotFoundException("Employee not found with Id : "+id);
			  }
			  model.addAttribute("employee", employee);
			  return "surveycreation/editEmployee";
		  }
		  
		  @PostMapping("/updateEmployee")
		  public String updateEmployee(@ModelAttribute("employee") EmployeeMaster employeeMaster, Model model) {
			  Optional<EmployeeMaster> optional = employeeService.findById(employeeMaster.getId());
			  if(optional.isPresent()) {
				  EmployeeMaster master = optional.get();
				  master.setEmployeeId(employeeMaster.getEmployeeId());
				  master.setName(employeeMaster.getName());
				  master.setEmail(employeeMaster.getEmail());
				  master.setZone(employeeMaster.getZone());
				  master.setRegion(employeeMaster.getRegion());
				  master.setDepartment(employeeMaster.getDepartment());
				  master.setReportingManagerId(employeeMaster.getReportingManagerId());
				  master.setReportingManagerName(employeeMaster.getReportingManagerName());
				  employeeService.save(master);
			  }else {
				  throw new EmployeeNotFoundException("Employee not found with Id : "+employeeMaster.getId());
			  }
			  	List<UserMaster> clients = userService.getAllCompany();
				List<EmployeeMaster> employees = employeeService.findAll();
				model.addAttribute("employees", employees);
				model.addAttribute("clients", clients);
			  return "surveycreation/employee";
		  }
		  
		  //Delete Employee By Delete Id
		  @GetMapping("/deleteEmployee")
		  public String deleteEmployeeById(@RequestParam("empId") Long id, Model model) {
			  employeeService.deleteById(id);
			  List<UserMaster> clients = userService.getAllCompany();
			  List<EmployeeMaster> employees = employeeService.findAll();
			  model.addAttribute("employees", employees);
			  model.addAttribute("clients", clients);
			  return "surveycreation/employee";
		  }
}
