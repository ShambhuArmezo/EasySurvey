<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: EASYSURVEY ::</title>
<link href="../css/global.css" rel="stylesheet" type="text/css">
<link href="../css/base.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
 function surveyByClientId(){
	 var clientId = document.querySelector("[name='company']").value;
	 var form = document.createElement("form");
		form.method = "post";
		form.action = "./showSurveyPublishDetails?clientId=" + clientId;
		document.body.appendChild(form);
		form.submit();
 }
 
 function surveyPublishStatus(surveyId, publish){
	 	//	alert(surveyId+publish);
	 	 var form1 = document.createElement("form");
		form1.method = "post";
		form1.action = "./publishUnpublishSurvey?surveyId=" + surveyId+"&publish="+publish;
		document.body.appendChild(form1);
		form1.submit();
		  	//window.location.href="./publishUnpublishSurvey?surveyId=" + surveyId+"&publish="+publish;
 }
</script>
</head>
<body>
	<%@ include file="../headerfooter/header.jsp"%>
	<%@ include file="../include/surveyMenu.jsp"%>

	<form name="addSurveyQue" id="addSurveyQue">
		<table width="100%" height="535px">
			<tr valign="top">
				<td>
					<table align="center">
						<td class="txt1">Select Client Name 
						<select name="company"
							id="company" onchange="surveyByClientId()">
								<option value="">Select</option>
								<c:forEach items="${users}" var="user">
										<option value="${user.id}">${user.companyName}</option>
									</c:forEach>
						</select>
						</td>
					</table>

					<table align="center" class="tblreport" cellspacing="0"
						cellpadding="8">
						<%-- <caption align="left" class="header">
							:: Survey Status ::
							<%=res.equals("1") ? "  Survey Published Successfully" : ""%></caption> --%>
						<br>
						<br>
						<tr class="headerrow">
							<td align="center">Srno</td>
							<td align="center">Client Name</td>
							<td align="center">Survey Name</td>
							<td align="center">Publish</td>
							<td align="center">Un-Publish</td>
							<td align="center">Preview</td>
						</tr>
						<c:forEach items="${surveys}" var="survey" 	varStatus="status">
						<tr>
							<td class="txt1">${status.count}</td>
							<td class="txt1">${client}</td>
							<td class="txt1">${survey.surveyName}</td>
							<td class="txt1">
								
								<c:if test="${survey.publish eq 0}">
								<input type="button" class="btn" value="Publish"
								onclick="surveyPublishStatus('${survey.surveyId}','1')" /> 
										</c:if>
							</td>
							<td class="txt1">
							<c:if test="${survey.publish eq 1}">
								 <input type="button" class="btn" value="Un-Publish"
								onclick="surveyPublishStatus('${survey.surveyId}','0')" /> 
								</c:if>
							</td>
							<td class="txt1"><input type="button" class="btn"
								value="Preview" onclick="fncPreview('${survey.surveyId}')" /></td>
						</tr>
						</c:forEach>
					</table>

				</td>
			</tr>
		</table>
	</form>
	<%@ include file="../headerfooter/footer.jsp"%>
</body>
</html>
