<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title> :: EASYSURVEY :: </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript">
	function uploadQuesFn()
	{
		var surveyId=document.question.surveyId.value;
		if(surveyId=="")
	  	{
	  		alert("Please Select survey name");
	  		return false;
	  	}
		document.question.method="post";
	  	document.question.action="/uploadQuestionPro?surveyId="+surveyId;
	  	document.question.submit();	 
	}
</script>
</head>
<body>
  <div class="container mt-5">
    <h1>Upload Questions</h1>
    <form name="question" method="POST" enctype="multipart/form-data">
      <div class="form-group">
        <label for="survey-select">Select Survey Name:</label>
        <select class="form-control" name="surveyId" id="surveyId">
        	<option  value="">-- Select --</option>
				<c:forEach var="sur" items="${surveys}" varStatus="status">
					<option value="${sur.surveyId}" ${sur.surveyId == survey ? 'selected' : ''}>${sur.surveyName}</option>
				</c:forEach>
        </select>
      </div>
      <div class="form-group">
        <label for="file-input">Upload File:</label>
        <input type="file" class="form-control-file" id="uploadtofile" name="uploadtofile">
      </div>
      <button type="button" class="btn btn-primary" onclick="uploadQuesFn()">Upload</button>
    </form>
    <hr>
    <h2>Survey Questions:</h2>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Sr No.</th>
          <th scope="col">Question Type</th>
          <th scope="col">Mandatory</th>
          <th scope="col">Survey</th>
          <th scope="col">Dimension</th>
          <th scope="col">Question</th>
          <th scope="col">Number of options</th>
          <th scope="col">Option1</th>
          <th scope="col">Option2</th>
          <th scope="col">Option3</th>
          <th scope="col">Option4</th>
          <th scope="col">Option5</th>
          <th scope="col">Option6</th>
          <th scope="col">Option7</th>
          <th scope="col">Score1</th>
          <th scope="col">Score2</th>
          <th scope="col">Score3</th>
          <th scope="col">Score4</th>
          <th scope="col">Score5</th>
          <th scope="col">Score6</th>
          <th scope="col">Score7</th>
        </tr>
      </thead>
      <tbody id="survey-questions">
        <tr>
          <c:forEach items="${questionMaster }" var="ts" varStatus="status">
													<tr>
														<td>${status.index + 1}</td>
														<td>${withData.get(status.index).getAttribute("q_type") }</td>
														<td>${withData.get(status.index).getAttribute("mandatry") }</td>
														<td>${withData.get(status.index).getAttribute("survey") }</td>
														<td>${withData.get(status.index).getAttribute("dimantion") }</td>
														<td>${ts.question }</td>
														<td>${withData.get(status.index).getAttribute("noOfOptions") }</td>
														
														<c:if test="${withData.get(status.index).getAttribute('q_type') eq 'SINGLECHOICE'}">
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 0}">
																 <td>${ts.singlechoiceOptionMaster.get(0).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 1}">
																 <td>${ts.singlechoiceOptionMaster.get(1).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 2}">
																 <td>${ts.singlechoiceOptionMaster.get(2).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 3}">
																 <td>${ts.singlechoiceOptionMaster.get(3).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 4}">
																 <td>${ts.singlechoiceOptionMaster.get(4).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 5}">
																 <td>${ts.singlechoiceOptionMaster.get(5).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 6}">
																 <td>${ts.singlechoiceOptionMaster.get(6).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
													
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 0}">
																 <td>${ts.singlechoiceOptionMaster.get(0).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 1}">
																 <td>${ts.singlechoiceOptionMaster.get(1).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 2}">
																 <td>${ts.singlechoiceOptionMaster.get(2).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 3}">
																 <td>${ts.singlechoiceOptionMaster.get(3).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 4}">
																 <td>${ts.singlechoiceOptionMaster.get(4).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 5}">
																 <td>${ts.singlechoiceOptionMaster.get(5).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.singlechoiceOptionMaster) > 6}">
																 <td>${ts.singlechoiceOptionMaster.get(6).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														</c:if>
														
														<c:if test="${withData.get(status.index).getAttribute('q_type') eq 'MULTIPLECHOICE'}">
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 0}">
																 <td>${ts.multiplechoiceOptionMaster.get(0).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 1}">
																 <td>${ts.multiplechoiceOptionMaster.get(1).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 2}">
																 <td>${ts.multiplechoiceOptionMaster.get(2).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 3}">
																 <td>${ts.multiplechoiceOptionMaster.get(3).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 4}">
																 <td>${ts.multiplechoiceOptionMaster.get(4).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 5}">
																 <td>${ts.multiplechoiceOptionMaster.get(5).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 6}">
																 <td>${ts.multiplechoiceOptionMaster.get(6).qoption }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
													
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 0}">
																 <td>${ts.multiplechoiceOptionMaster.get(0).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 1}">
																 <td>${ts.multiplechoiceOptionMaster.get(1).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 2}">
																 <td>${ts.multiplechoiceOptionMaster.get(2).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 3}">
																 <td>${ts.multiplechoiceOptionMaster.get(3).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 4}">
																 <td>${ts.multiplechoiceOptionMaster.get(4).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 5}">
																 <td>${ts.multiplechoiceOptionMaster.get(5).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:length(ts.multiplechoiceOptionMaster) > 6}">
																 <td>${ts.multiplechoiceOptionMaster.get(6).score }</td>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
														</c:if>
														
													</tr>
												</c:forEach> 
        </tr>
      </tbody>
    </table>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  