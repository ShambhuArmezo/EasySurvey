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
  
  <script type="text/javascript">
function fncAddInd()
{
	if(document.addIns.clientId.value=="")
	{
		alert("Please select Client name");
		return false;
	}
	
	if(document.addIns.surveyId.value=="")
	{
		alert("Please add survey name");
		return false;
	}
	
	document.addIns.method="post";
	document.addIns.action="/addInsPro";
	document.addIns.submit();	 	
}

function fncView()
{
	var clientId=document.addIns.clientId.value;
	var surveyId=document.addIns.surveyId.value;
	
	if(document.addIns.clientId.value=="")
	{
		alert("Please select Client name");
		return false;
	}
	
	if(document.addIns.surveyId.value=="")
	{
		alert("Please add survey name");
		return false;
	}
	
	document.addIns.method="post";
	document.addIns.action="/viewInsPro?clientId="+clientId+"&surveyId="+surveyId;
	document.addIns.submit();
	
	//fncSearch1();
}

function fncSearch1()
{
	var clientId=document.addIns.clientId.value;
	document.addIns.method="post";
	document.addIns.action="/viewSurvey?clientId="+clientId;
	document.addIns.submit();
}

</script>
  
  </head>
  <body>
    <div class="container">
      <h1>ADD LANDING PAGE CONTENT</h1>
      <form name="addIns" method="post" enctype="multipart/form-data">
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
          <label for="instructionTextarea">Instructions:</label>
          <textarea class="form-control" id="instruction" name="instruction" rows="3">${inst}</textarea>
        <script>
      tinymce.init({
        selector: '#instruction',
        plugins: [
          'a11ychecker','advlist','advcode','advtable','autolink','checklist','export',
          'lists','link','image','charmap','preview','anchor','searchreplace','visualblocks',
          'powerpaste','fullscreen','formatpainter','insertdatetime','media','table','help','wordcount'
        ],
        toolbar: 'undo redo | formatpainter casechange styleselect | bold italic backcolor | ' +
          'alignleft aligncenter alignright alignjustify | ' +
          'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help'
      });
    </script>
        </div>
        <button type="button" class="btn btn-primary" onclick="fncAddInd()">Add</button>
        <input type="button"  class="btn btn-primary" value="View" onclick="fncView()"></td>
      </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
