package com.armezo.easysurvey.admin.repository;

import java.util.Optional;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.easysurvey.admin.model.EmployeeMaster;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeMaster, Long> {
	
	@Query("From EmployeeMaster where accesskey=:accesskey and status='A' ")
	Optional<EmployeeMaster> getEmployeeData(String accesskey);
	
	@Query("SELECT e FROM EmployeeMaster e WHERE (e.clientId=:clientId OR :clientId IS NULL) AND (e.surveyId=:surveyId OR :surveyId IS NULL) AND "
			+ "(e.employeeId=:employeeId OR :employeeId IS NULL OR :employeeId='') AND (e.name=:name OR :name IS NULL OR :name='') AND (e.accesskey=:accesskey OR :accesskey IS NULL OR :accesskey='') ")
	List<EmployeeMaster> findEmployeesBySearchFilter(Long clientId, Long surveyId, String employeeId, String name,
			String accesskey);
	@Query("SELECT e FROM EmployeeMaster e WHERE (e.clientId=:clientId OR :clientId IS NULL) AND (e.surveyId=:surveyId OR :surveyId IS NULL) AND "
			+ "(e.employeeId=:employeeId OR :employeeId IS NULL OR :employeeId='') AND (e.name=:name OR :name IS NULL OR :name='') AND (e.accesskey=:accesskey OR :accesskey IS NULL OR :accesskey='') AND "
			+ "(e.registrationDate >= :fromDate AND e.registrationDate <= :toDate)")
	List<EmployeeMaster> findEmployeesBySearchFilterWithDates(Long clientId, Long surveyId, String employeeId, String name,
			String accesskey, Date fromDate, Date toDate);

}
