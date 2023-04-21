<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
   <title> :: EASYSURVEY :: </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
  <script type="text/javascript">
  	
  	function fncLogin()
  	{
  		var accesskey=document.loginUser.accesskey.value;
		var studResult=document.loginUser.answer.value.trim();
  		var calculationresult=document.loginUser.calculationresult.value;
		
  		if((accesskey==""))
		{
			alert("Please enter Aceeskey.");
			document.forms[0].accesskey.focus();
			return false;
		}

		if(studResult=="")
		{
			alert("Please enter result of calculation.");
			document.forms[0].answer.focus();
			return false;
		}
		if(calculationresult != studResult)
		{
			alert("Result and Answer not match.");
			document.forms[0].answer.focus();
			return false;
		}
		
		document.loginUser.method="post";
	  	document.loginUser.action="/loginPro";
	  	document.loginUser.submit();
  	}
  	
  </script>
  
  </head>
  <body>
    <div class="container">
      <h2>Login Page</h2>
     <form name="loginUser" method="post" enctype="multipart/form-data">
        <div>
            <p><font color="red">${error}</font></p>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="accesskey" name="accesskey" placeholder="ACCESS KEY" style="font-family: Arial, Helvetica, sans-serif; font-size:14px;">
        </div>
        <div>
            <label for="mathProblem">RESULT FOR:</label>
            <% 
                int num1 = (int)(Math.random() * 10);
                int num2 = (int)(Math.random() * 10);
                int operator = (int)(Math.random() * 4);
                String operatorSymbol = "";
                int answer = 0;
                try{
                switch (operator) {
                    case 0:
                        answer = num1 + num2;
                        operatorSymbol = "+";
                        break;
                    case 1:
                        answer = num1 - num2;
                        operatorSymbol = "-";
                        break;
                    case 2:
                        answer = num1 * num2;
                        operatorSymbol = "*";
                        break;
                    case 3:
                        answer = num1 / num2;
                        operatorSymbol = "/";
                        break;
                }
                }catch(Exception e)
                {
                	e.printStackTrace();
                }
                String mathProblem = num1 + " " + operatorSymbol + " " + num2 ;
               // out.print(mathProblem+".."+answer);
                request.getSession().setAttribute("answer", answer);
            %>
            <input type="text" id="txtstudentresult" name="txtstudentresult" value="<%=mathProblem%>" readonly>
        	<input type="hidden" name="calculationresult" value="<%=answer%>"/>
        </div>
        <div>
            <label for="answer">Answer:</label>
            <input type="number" id="answer" name="answer" required>
        </div>
        <div>
        <button type="button" class="btn btn-primary" onclick="fncLogin()">Log In</button>
      </form><br>
      
      
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
