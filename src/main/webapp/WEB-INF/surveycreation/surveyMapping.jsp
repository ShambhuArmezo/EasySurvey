<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: EASYSURVEY ::</title>
<link href="../css/global.css" rel="stylesheet" type="text/css">
<link href="../css/base.css" rel="stylesheet" type="text/css">


<script language="javascript">
	function addSurveyFn() {
		var surveyId = document.querySelector("[name='survey']").value;
		var clientIds = document.querySelector("[name='company']").value;
		/*	var clientArray = [], i;
			 for (i = 0; i < selectedOptions.options.length ; i += 1) {
		        if (selectedOptions.options[i].selected) {
		        	clientArray.push( selectedOptions.options[i].value); 
		        }
		    }
		    console.log(clientArray); */

		var form = document.createElement("form");
		form.method = "post";
		form.action = "./surveyMappingWithClient?surveyId=" + surveyId
				+ "&clientId=" + clientIds;
		document.body.appendChild(form);
		form.submit();

	}
</script>
</head>
<body>
	<%@include file="../headerfooter/header.jsp"%>
	<form name="surveyMapping">
		<table width="100%" height="540px">
			<tr align="center" valign="top">
				<td class="txt1"><br> <br> <br>

					<table class="tblborder1" cellpadding="5" cellspacing="5"
						align="center">
						<caption>
							<font color="red" size="3"><b></b></font>
						</caption>
						<caption class="header">Survey Mapping</caption>
						<tr>
							<td>Client Name</td>
							<td><select name="company" id="company"
								style="width: 150px;">
									<option value="">-- Select --</option>
									<c:forEach items="${users}" var="user">
										<option value="${user.id}">${user.clientName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Survey Name</td>
							<td><select name="survey" style="width: 150px;"
								onchange="addSurveyFn()">
									<option value="">-- Select --</option>
									<c:forEach items="${surveys}" var="survey">
										<option value="${survey.surveyId}">${survey.surveyName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<!-- <tr align="center">
							<td colspan="2"><input type="button" class="btn"
								value="Add" onclick="addSurveyFn()"> <input
								type="button" class="btn" value="View" onclick="fncViewSurvey()" /></td>
						</tr> -->
					</table></td>
			</tr>
		</table>
	</form>
	<form:form action="/saveSurveyMapping" method="post"	modelAttribute="surveyList">
		<table width="60%" border="0" align="center" cellspacing="0"
			cellpadding="4" bgcolor="#FFFFFF" class="tblreport">
			<tr class="headerrowbold">
				<td align="center">S.No.</td>
				<td align="center">Survey Name</td>
				<td align="center">Client Name</td>
				<td align="center">Question Type</td>
				<td align="center">Question</td>
				<td align="center">Dimension</td>
				<td align="center">Section No.</td>
				<td align="center">Order No.</td>
			</tr>
			<c:forEach items="${surveyList.payloads}" var="payload"
				varStatus="status">
				<tr>
					<td align="center">${status.count}</td>
					<%-- 
				<td value=""> payloads[${status.index}].surveyName</td>
					--%>
					<td>${payload.surveyName}</td>
					<td>${payload.clientName}</td>
					<td>${payload.questionType}</td>
					<td>${payload.question}</td>
					<td>${payload.dimension}</td>
					
					<td><input type="number"
						name="payloads[${status.index}].sectionNo" value="${payload.sectionNo}" min="1" max="99"></td>
					<td><input type="number"
						name="payloads[${status.index}].orderNo" value="${payload.orderNo}" min="1" maxlength="2"></td>
				</tr>
				<!-- Taking Hiden Field To Pass Required Data -->
				<input type="hidden" name="payloads[${status.index}].surveyId" value="${payload.surveyId}">
				<input type="hidden" name="payloads[${status.index}].clientId" value="${payload.clientId}">
				<input type="hidden" name="payloads[${status.index}].questionId" value="${payload.questionId}">
				<input type="hidden" name="payloads[${status.index}].dimensionId" value="${payload.dimensionId}">
				<input type="hidden" name="payloads[${status.index}].questionType" value="${payload.questionType}">
			</c:forEach>
		</table>
		<div class="button">
			<button id="btn">Mapping</button>
		</div>
	</form:form>
	<!-- </td>
	</tr>  contacts[${status.index}].firstname" 
</table>
</form> 
<%@ include file="../headerfooter/footer.jsp" %>
-->
</body>
</html>