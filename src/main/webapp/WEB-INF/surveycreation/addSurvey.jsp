<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title> :: EASYSURVEY :: </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
  <script language="javascript">
		function addSurveyFn(){
			var surveyName = document.querySelector("[name='surveyName']").value;
			var form = document.createElement("form");
			var surveyId=document.addSurvey.surveyId.value;
		    form.method="post";
		    form.action="./createSurvey?surveyName="+surveyName+"&surveyId="+surveyId;
		    document.body.appendChild(form);
		    form.submit();
			
			}
		function viewSurvey()
		{
			document.addSurvey.method="post";
		  	document.addSurvey.action="/showAllSurvey";
		  	document.addSurvey.submit();
		}
		
		function editSurvey(surveyId)
		{
			document.addSurvey.method="post";
		  	document.addSurvey.action="/getOneSurvey?surveyIds="+surveyId;
		  	document.addSurvey.submit();
		}
		</script> 
  
  </head>
  <body>
    <div class="container">
      <h2>ADD SURVEY</h2>
      <form name="addSurvey" method="post" enctype="multipart/form-data">
      <input type="text" name="surveyId" value="${surveyMaster.surveyId}" />
        <div class="form-group">
          <label for="surveyName">Survey Name:</label>
          <input type="text" class="form-control" id="surveyName" name="surveyName" value="${surveyMaster.surveyName}">
        </div>
        <button type="button" class="btn btn-primary" onclick="addSurveyFn()">Add</button>
        <input type="button" class="btn btn-primary" value="View" onclick="viewSurvey()" /></td>
      </form><br>
      
      <table class="table">
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Survey Name</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${surveys}" var="survey" varStatus="status">
          <tr>
            <td>${status.count}</td>
            <td>${survey.surveyName}</td>
            <td>
            <button type="button" class="btn btn-primary" onclick="editSurvey(${survey.surveyId})">Edit</button>
            </td>
          </tr>
         </c:forEach>
         
          <!-- Add more rows for additional surveys -->
        </tbody>
      </table>
      
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
