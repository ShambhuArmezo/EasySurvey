<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
     <title> :: EASYSURVEY :: </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
function fncAddEmail()
{
	if(document.addEmail.clientId.value=="")
	{
		alert("Please select Client name");
		return false;
	}
	if(document.addEmail.surveyId.value=="")
	{
		alert("Please add survey name");
		return false;
	}
	if(document.addEmail.subjctLine.value=="")
	{
		alert("Please add subjct line");
		return false;
	}
	if(document.addEmail.template.value=="")
	{
		alert("Please add template");
		return false;
	}
	document.addEmail.method="post";
	document.addEmail.action="/addEmailPro";
	document.addEmail.submit();	 	
}

function fncViewEmail()
{
	var clientId=document.addEmail.clientId.value;
	var surveyId=document.addEmail.surveyId.value;
	
	if(document.addEmail.clientId.value=="")
	{
		alert("Please select Client name");
		return false;
	}
	
	if(document.addEmail.surveyId.value=="")
	{
		alert("Please add survey name");
		return false;
	}
	if(document.addEmail.subjctLine.value=="")
	{
		alert("Please add subjct line");
		return false;
	}
	
	document.addEmail.method="post";
	document.addEmail.action="/viewEmailPro?clientId="+clientId+"&surveyId="+surveyId;
	document.addEmail.submit();
}

function fncSearch1()
{
	var clientId=document.addEmail.clientId.value;
	document.addEmail.method="post";
	document.addEmail.action="/viewSurveys?clientId="+clientId;
	document.addEmail.submit();
}

</script>
  
  </head>
  <body>
    <div class="container">
      <h2>Update Mail Content</h2>
     <form name="addEmail" method="POST" enctype="multipart/form-data">
        <div class="form-group">
          <label for="client-name">Client Name:</label>
          <select class="form-control" name="clientId" id="clientId" onchange="fncSearch1()">
            <option  value="">-- Select --</option>
            <c:forEach var="com" items="${allCompany}" varStatus="status">
				<option value="${com.id}" ${com.id == client ? 'selected' : ''}>${com.clientName}</option>
			</c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="survey-name">Survey Name:</label>
          <select class="form-control" name="surveyId" id="surveyId">
            <option  value="">-- Select --</option>
            <c:forEach var="sur" items="${surveyList}" varStatus="status">
				<option value="${sur.surveyId}" ${sur.surveyId == survey ? 'selected' : ''}>${sur.surveyName}</option>
			</c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="email-subject">Email Subject Line:</label>
          <c:choose>
						<c:when test="${subjectLine =='' || subjectLine ==null}">
							<input  type="text" class="form-control" value="Easy Survey Link" required="required" name="subjctLine" id="subjctLine"/>					
						</c:when>
						<c:otherwise>
                    		<input  type="text" class="form-control" value="${subjectLine}" required="required" name="subjctLine" id="subjctLine"/>
                    	</c:otherwise>
					</c:choose>
        </div>
        <div class="form-group">
          <label for="email-template">Email Template:</label>
          <c:choose>
				<c:when test="${msgBody =='' || msgBody ==null}">
            		<textarea  rows="20" cols="" required="required" name="template"  id="template" style="width:100%;">
					<div style="box-sizing: border-box; color: rgb(38, 50, 56); font-size: 13px; text-align: -webkit-left; margin: 0px; font-family: sans-serif; padding: 0px !important;"><div style="box-sizing: border-box; max-width: 700px; margin: 0px auto;"><table role="presentation" style="border-spacing: 0px; border-collapse: collapse; margin: auto; border-color: rgb(44, 154, 235); border-style: groove; border-width: 12px;" width="100%" cellspacing="0" cellpadding="0" border="0" align="center"><tbody style="box-sizing: border-box;"><tr style="box-sizing: border-box;"><td style="box-sizing: border-box; padding: 0px;" valign="top"><table role="presentation" style="border-spacing: 0px; border-collapse: collapse; background-color: rgb(11, 63, 114); padding: 10px;" width="100%" cellspacing="0" cellpadding="0" border="0"><tbody style="box-sizing: border-box;"><tr style="box-sizing: border-box;"><td style="box-sizing: border-box; padding: 0px; width: 491px;"><a href="https://demo.armezo.com:8443/EmailUtility-v1/update-mail?id=34" style="box-sizing: border-box; background-color: transparent; color: rgb(51, 122, 183); text-decoration-line: none;"><img src="https://dev.gammezo.com/dev-gba/tr/img/logo.png" style="box-sizing: border-box; border: 0px; vertical-align: middle; padding:3px" width="100px"></a></td></tr></tbody></table></td></tr><tr style="box-sizing: border-box;"><td style="box-sizing: border-box; padding: 0px;"><table role="presentation" style="border-spacing: 0px; border-collapse: collapse; background: rgb(246, 246, 246); position: relative;" width="100%" cellspacing="0" cellpadding="0" border="0"><tbody style="box-sizing: border-box;"><tr style="box-sizing: border-box;"><td style="box-sizing: border-box; padding: 0px;"><div style="box-sizing: border-box; padding: 10px 20px;"><h2 style="color:rgb(80,0,80);font-family:Roboto,RobotoDraft,Helvetica,Arial,sans-serif;background-color:rgb(246,246,246)"><font size="5"><i style="box-sizing:border-box"><span style="box-sizing:border-box;color:rgb(41,143,207)">Greetings,</span></i></font></h2><span style="box-sizing:border-box"><span style="box-sizing:border-box;color:rgb(41,143,207)">We hope you are doing great! You have been invited to take&nbsp;the&nbsp;<span>Gammezo</span>&nbsp;<span>assessment</span>.</span></span><br><br><span style="box-sizing:border-box"><span style="box-sizing:border-box;color:rgb(41,143,207)">Please note the following before starting the&nbsp;<span>assessment</span>.</span></span><br><br>a. Please take this Assessment from a computer/laptop via a web browser; i.e. Google Chrome or Mozilla Firefox.<br>b. You need to use a headphone or earphones as the assessment has audio
 					in the background which you need to hear; please adjust the volume 
					accordingly.<br>c. Try completing this assessment in one-go (it is 
					approx. 30 minutes) however if you get disconnected due to any reason; 
					you can easily resume by using the same link &amp; access key.&nbsp; If the 
					screen takes time to load, kindly exit the window and log in again. Your
 					progress will always be saved.<br>&nbsp;d. Take the assessment in a comfortable space and avoid any distractions.<br>&nbsp;e. For any Technical Support, you can reach Mr. Vikas at 9310306220 / <span style="box-sizing:border-box;font-size:10.5pt"><a href="mailto:vikas@armezo.com" style="box-sizing:border-box;background-color:transparent;text-decoration-line:none" target="_blank">vikas@armezo.com</a></span></div><div style="box-sizing: border-box; padding: 10px 20px;"><span style="box-sizing:border-box;font-size:10.5pt"></span><b>GAMMEZO LINK:</b><span style="box-sizing:border-box;color:black"><a href="https://dev.gammezo.com/UserManagement/test/Testing/login" target="_blank" data-saferedirecturl="https://www.google.com/url?q=https://dev.gammezo.com/UserManagement/test/Testing/login&amp;source=gmail&amp;ust=1600145962284000&amp;usg=AFQjCNFIEMEWJ9Cn6ojwoZEfqpPrY3np5A"><font size="4">${link}</font></a></span><b><br></b></div><div style="box-sizing: border-box; padding: 10px 20px;"><b>Access key:<font color="#CC0000"> </font></b><font color="#CC0000"><b>${accesskey}</b></font></div><div style="box-sizing: border-box; padding: 10px 20px;"><b>Wishing You All The Best.</b><br><br>Regards,<br><br>Armezo Team</div></td></tr></tbody></table></td></tr></tbody></table></div></div><br>
					</textarea>
            	</c:when>
				<c:otherwise>
            		<textarea  rows="20" cols="" required="required"  name="template"  id="template" style="width:100%;">${msgBody}</textarea>
            	</c:otherwise>
			</c:choose>
        </div>
        <button type="button" class="btn btn-primary" onclick="fncAddEmail()">Save</button>
        <input type="button"  class="btn btn-primary" value="View" onclick="fncViewEmail()"></td>
      </form>
    </div>
  </body>
</html>
