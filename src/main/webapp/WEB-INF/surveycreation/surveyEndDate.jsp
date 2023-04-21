<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title> :: EASYSURVEY :: </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
  	
  	<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.2.620/styles/kendo.bootstrap-v4.min.css">
    <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2018.2.620/js/kendo.all.min.js"></script>
  <script type="text/javascript">

function fncSearch1()
{
	var clientId=document.surveyDate.clientId.value;
	document.surveyDate.method="post";
	document.surveyDate.action="/viewSurveyss?clientId="+clientId;
	document.surveyDate.submit();
}

function fncEndSur()
{
	var clientId=document.surveyDate.clientId.value;
	var surveyId=document.surveyDate.surveyId.value;
	var surveyDate=document.surveyDate.surveyDate.value;
	//alert(surveyDate);
	if(clientId=="")
	{
		alert("Please select Client name");
		return false;
	}
	if(surveyId=="")
	{
		alert("Please add survey name");
		return false;
	}
	if(surveyDate=="day/month/year hours:minutes")
	{
		alert("Please add survey End Date");
		return false;
	}
	document.surveyDate.method="post";
	document.surveyDate.action="/survEndDate";
	document.surveyDate.submit();
}

function fncView()
{
	var clientId=document.surveyDate.clientId.value;
	var surveyId=document.surveyDate.surveyId.value;
	
	if(document.surveyDate.clientId.value=="")
	{
		alert("Please select Client name");
		return false;
	}
	
	if(document.surveyDate.surveyId.value=="")
	{
		alert("Please add survey name");
		return false;
	}
	
	document.surveyDate.method="post";
	document.surveyDate.action="/viewEndDate?clientId="+clientId+"&surveyId="+surveyId;
	document.surveyDate.submit();
	
	//fncSearch1();
}

</script>
  
  </head>
  <body>
    <div class="container">
      <h1>ADD Survey Date</h1>
      <form name="surveyDate" method="post" enctype="multipart/form-data">
        <div class="form-group">
          <label for="clientSelect">Client Name:</label>
            <select class="form-control" name="clientId" id="clientId" onchange="fncSearch1()" style="width:150px;">
					<option  value="">-- Select --</option>
					<c:forEach var="com" items="${allCompany}" varStatus="status">
					<option value="${com.id}" ${com.id == client ? 'selected' : ''}>${com.clientName}</option>
					</c:forEach>
					
            <!-- Add more options for additional clients -->
          </select>
        </div>
        <div class="form-group">
          <label for="surveySelect">Survey Name:</label>
          <select class="form-control" name="surveyId" id="surveyId" style="width:150px;">
					<option  value="">-- Select --</option>
					<c:forEach var="sur" items="${surveyList}" varStatus="status">
					<option value="${sur.surveyId}" ${sur.surveyId == survey ? 'selected' : ''}>${sur.surveyName}</option>
					</c:forEach>
					</select>
        </div>
        <div class="form-group">
          <label for="instructionTextarea">SURVEY DATE:</label><br>
          <input type="text" class="form-control" id="surveyDate" name="surveyDate" value="${endDate}" style="width:400px;">  
    <script type="text/javascript">    
    $('#surveyDate').kendoDateTimePicker({
    	  dateInput: true,
    	  format: 'dd/MM/yyyy HH:mm',
    	  timeFormat: 'HH:mm',
    	});    
    </script> 
        </div>
        <button type="button" class="btn btn-primary" onclick="fncEndSur()">Add</button>
        <input type="button"  class="btn btn-primary" value="View" onclick="fncView()"></td>
      </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
