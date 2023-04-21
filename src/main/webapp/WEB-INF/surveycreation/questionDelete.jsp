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
	function addSurveyFn() 
	{
		var surveyId = document.delQues.surveyId.value
		document.delQues.method="post";
		document.delQues.action="/viewQues?surveyId="+surveyId;
		document.delQues.submit();

	}
	
	function fncDelQues(questionId,surveyId,dimensionId,tin)
	{
		//alert(questionId+".."+surveyId+".."+dimensionId);
		document.delQues.method="post";
		document.delQues.action="/delQues?questionId="+questionId+"&surveyId="+surveyId+"&dimensionId="+dimensionId+"&tin="+tin;
		document.delQues.submit();
	}
	
</script>
  
  </head>
  <body>
    <div class="container">
      <h2>Question Delete</h2><br>
     <form name="delQues" method="post" enctype="multipart/form-data">
     
     <div class="form-group">
          <label for="surveyName">Survey Name:</label>
          <select name="surveyId" id="surveyId"  onchange="addSurveyFn()">
					<option  value="">-- Select --</option>
					 <c:forEach var="sur" items="${surveys}" varStatus="status">
					<option value="${sur.surveyId}" ${sur.surveyId == survey ? 'selected' : ''}>${sur.surveyName}</option>
					</c:forEach>
					</select>
        </div>
     
      <table class="table">
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Question Type</th>
            <th>Survey Name</th>
            <th>Dimension Name</th>
            <th>Question</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${questionMaster }" var="ts" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${ts.questionType }</td>
            <td>${ts.surveyName }</td>
            <td>${ts.dimension }</td>
            <td>${ts.question }</td>
            <c:choose>
    			<c:when test="${ts.publish=='0'}">
					<td><button class="btn btn-danger" onclick="fncDelQues(${ts.questionId},${ts.surveyId},${ts.dimensionId},${ts.tin})">Delete</button></td>    			</c:when>    
    			<c:otherwise>
        			<td><button class="btn btn-danger" disabled="disabled">Delete</button></td>. 
    			</c:otherwise>
			</c:choose>
            
          </tr>
          </c:forEach>
        </tbody>
      </table>
       </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
