<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Employees Data File</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script type="text/javascript" src="./js/jquery-3.6.4.js"></script>
    <script type="text/javascript" src="./js/jquery.validate.js"></script>
    <script type="text/javascript" src="./js/additional-methods.js"></script>
    <script type="text/javascript" src="./js/validateForm.js"></script>
    
    <style>
   		 .error {
  				color: red;
				}
    </style>
    
    <script type="text/javascript">
    function fncSearch1()
    {
    	var clientId=document.upEmp.clientId.value;
    	document.upEmp.method="post";
    	document.upEmp.action="/viewSurvey1?clientId="+clientId;
    	document.upEmp.submit();
    }
    </script>
</head>
<body>
    <div class="container">
        <h1>Upload XLS File</h1>
        <form action="uploadEmployee" id="upEmp" name="upEmp" method="post" enctype="multipart/form-data">
        <div class="row mb-3">
    	 <div class="col-3">
          <label for="clientId">Client Name:</label>
          </div>
    	 <div class="col-6">
          <select name="clientId" id="clientId" class="form-control" onchange="fncSearch1()">
					<option  value="">-- Select --</option>
					 <c:forEach var="client" items="${clients}" >
					<option value="${client.id}" ${client.id == clientId ? 'selected' : ''}>${client.clientName}</option>
					</c:forEach>
					</select>
        </div></div>
        <div class="row mb-3">
    	 <div class="col-3">
          <label for="clientId">Survey Name:</label>
          </div>
    	 <div class="col-6">
          <select name="surveyid" id="surveyid" class="form-control">
					<option  value="">-- Select --</option>
					<c:forEach var="sur" items="${surveyList}" varStatus="status">
					<option value="${sur.surveyId}">${sur.surveyName}</option>
					</c:forEach>
					</select>
        </div></div>
            <div class="row mb-3">
    	 		<div class="col-3">
                <label for="file">Select XLS File:</label>
                </div>
    	 		<div class="col-6">
                <input type="file" id="file" name="file" class="form-control-file" accept=".xls,application/vnd.ms-excel">
            </div></div>
            <div class="row mb-3">
        <div class="col-9  text-center">
        <button type="submit" class="btn btn-primary btn-lg">Upload Employees</button>
        </div></div>
        </form>
        <table class="table">
        <thead>
          <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Email ID</th>
            <th>Zone</th>
            <th>Region</th>
            <th>Division/Department</th>
            <th>Date of Joining</th>
            <th>Reporting Manager Name</th>
            <th>Reporting Manager ID</th>
            <th>Employee Category</th>
          </tr>
        </thead>
        
      </table>
        
    </div>
    
   
</body>
</html>
