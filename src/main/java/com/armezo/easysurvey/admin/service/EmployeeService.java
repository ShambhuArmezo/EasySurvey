package com.armezo.easysurvey.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.easysurvey.admin.model.EmployeeMaster;
import com.armezo.easysurvey.admin.repository.EmployeeRepository;
import com.armezo.easysurvey.exception.EmployeeNotFoundException;
import com.armezo.easysurvey.sc.model.AddLandingMaster;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeMaster> findAll() {
        return employeeRepository.findAll();
    }

   
    public EmployeeMaster save(EmployeeMaster employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

	public void saveAllEmployees(List<EmployeeMaster> employees) {
		employeeRepository.saveAll(employees);
	}
	public Optional<EmployeeMaster> getEmployeeData(String accesskey) {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeeData(accesskey);
	}
	public List<EmployeeMaster> findEmployeesBySearchFilter(Long clientId, Long surveyId, String employeeId,
			String name, String accesskey, Date fromDate, Date toDate) {
		if(fromDate==null && toDate==null) {
			return employeeRepository.findEmployeesBySearchFilter(clientId, surveyId, employeeId, name, accesskey);
		}else {
		return employeeRepository.findEmployeesBySearchFilterWithDates(clientId,surveyId,employeeId,name,accesskey,fromDate,toDate);
		}
	}
	
	public Optional<EmployeeMaster> findById(Long id) {
        return employeeRepository.findById(id);
    }

}
