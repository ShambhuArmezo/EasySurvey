<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
.user-name {
	padding: 5px 0;
	color: #e4292f;
}
</style>
<html>
<head>
<title>:: EASYSURVEY ::</title>
<meta http-equiv="Expires" content="0">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700"
	rel="stylesheet">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0">

<script type="text/javascript" src="../js/util.js"></script>
<link rel="stylesheet" href="../assets/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../assets/css/style1.css">
<link rel="stylesheet" href="../assets/css/style.css"> 
<link rel="stylesheet" href="../assets/css/style-responsive.css"> 
<link rel="stylesheet" href="../survey/assets/css/style-responsive.css">
<link rel="stylesheet" type="text/css" href="../js/sweetalert.css">
<script type="text/javascript" src="../js/sweetalert.min.js"></script>



</head>
<body>
	<form name="ques" method="post" enctype="multipart/form-data">
<header>
		
		</header>
		<br><br>
		<div class="container">
		<h2>Survey</h2>
		<c:if test="${singleSize gt 0}">
		<c:forEach var="sin" items="${single}" varStatus="status">			
		<div class="question-row">
			
			<p class="question_head" id="tdsinglechoice@@${sin.qid}">
				<span class="question_no"><font style="color: #A6302E;">Q-${status.index + 1}</font></span>
				&nbsp;&nbsp;${sin.question}</p>
			<custom>
				<div class="col-lg-12 col-sm-12">
					<c:forEach items="${sin.singlechoiceOptionMaster}" var="options">
						<label  class="radio-inline col-lg-2 col-sm-6 col-xs-12">
							
							<input type="radio" ${options.optionId == Ansmap.get(sin.qid) ? 'checked' : ''} style="float: none; margin: 0 auto;" name="singlechoice@@${sin.qid}" value="${options.optionId}">  ${options.qoption}
						</label>
					</c:forEach>
				</div>
			</custom>
			
		</div>
		</c:forEach>
		</c:if>
		
		<c:if test="${commSize gt 0}">	
		<div class="question-row">
		<c:forEach var="comm" items="${comment}" varStatus="status1">
			<p id="td" class="question_head">
				<span class="question_no"><font style="color: #A6302E;">Q- ${status1.index + 1}</font></span>
				&nbsp;&nbsp;${comm.question}
			</p>
		</c:forEach>
			<custom>
				<div class="col-lg-12 col-sm-12" style="top: -8px;">
					<textarea rows="2" style="vertical-align: top; width: 50%;"maxlength="2000" name="" id=""></textarea>
				</div>
			</custom>
		</div>
		</c:if>
		
		<c:if test="${mulSize gt 0}">	
		<div class="question-row">
		<c:forEach var="multi" items="${multi}" varStatus="status2">
			<p id="td" class="question_head">
				<span class="question_no"><font style="color: #A6302E;">Q- ${status2.index + 1}</font></span>
				&nbsp;&nbsp;${multi.question}
				</p>
		</c:forEach>
			<custom>
				<div class="col-lg-12 col-sm-12">
				<c:forEach items="${multi.multiplechoiceOptionMaster}" var="options">
					<label class="radio-inline col-lg-2 col-sm-6 col-xs-12"> 
						<input type="checkbox" name=""value="">
					</label>
				</c:forEach>
				</div>
			</custom>
		</div>
		</c:if>
		<input type="hidden" value="${mqdetail}" name="mqdetail" id="mqdetail">
		<input type="hidden" value="${qdetail}" name="qdetail" id="qdetail">
		

		<div class="action-button">
			<table align="center">
				<tr>
					<td colspan="" align="center">
						<c:if test="${currsec ne 1}">
							<input type="button" name="prevBtn" class="btn" value="  Previous  " onClick="fncValidate('P');">&nbsp;&nbsp;
						</c:if>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td valign="top">
						<c:if test="${currsec eq totalsec}">
							<input type="button" name="submitBtn" class="btn" value="  Submit " onClick="fncValidate('ST');"> 
						</c:if>	
						<c:if test="${currsec ne totalsec}">
							<input type="button" name="nextBtn" class="btn" value="  Next   " onClick="fncValidate('N');"> 					</td>
						</c:if>	
				</tr>
			</table>
          </div>
			<div>&nbsp;</div>
			
</div>
	</form>
	
	<script type="text/javascript">
	
	//This get value of radio group
	function getRadioValue(radioGroup)
	{
		if(radioGroup.length>1)
		{
			for (var i=0; i<radioGroup.length; i++)
			{
				if(radioGroup[i].checked)
				{
					return (radioGroup[i].value);
				}
			}
		}else
		{
			if(radioGroup.checked)
			{
				return radioGroup.value;
			}
		}
		return "";
	}
	
	function fncValidate(flag) {
		if (flag != 'P' && flag != 'R') {
			var chkflag = false;
			var commentflag = false;
			var mqdetail = document.ques.mqdetail.value;
			//alert("mqdetail.."+mqdetail);
			var qArr = mqdetail.split(",");
			//alert("qArr.."+qArr);
			if (mqdetail.length > 0) {
				for (var i = 0; i < qArr.length; i++) {
					var tdObj = document.getElementById("td" + qArr[i]);
					//alert(qArr[i]);
					var elem = document.getElementById(qArr[i]);
					//alert(elem);
					if (elem != null && (elem.nodeName == "textarea" || elem.nodeName == "TEXTAREA")) {
						tdObj.style.background = "";
						var trimm=elem.value.trim();
						if (trimm == "") {
							tdObj.style.background = "#F76541";
							chkflag = true;
						}

					}else {
						var radObj = document.getElementsByName(qArr[i]);
						var val = getRadioValue(radObj);
						tdObj.style.backgroundColor = "";
						if (val == "") {
							var notanswered = true;

							var qdet = qArr[i].split("@@");
							var qtype = qdet[0];
							if (qtype == "multichoice") {
								var qid = qdet[1];
							}

							if (notanswered) {
								tdObj.style.background = "#F76541";
								chkflag = true;
							}
						}
					} 
				}
			}
			
			if (chkflag) {			
				alert("All questions are mandatory. Questions not attempted are highlighted in red.");
				return false;
			}
			
		}
		if (flag == "ST") {
			
			confirm("Are you sure you want to submit?");
		}
		document.ques.method="post";
	  	document.ques.action="/qursPro?flag="+flag;
	  	document.ques.submit();			
	}
	</script>
	
</body>
</html>
