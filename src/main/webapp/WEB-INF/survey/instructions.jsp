<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>:: EASYSURVEY ::</title>
	 
	
		<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
	  	<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
	  	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
		<script type="text/javascript" src="../js/util.js"></script>
		<script type="text/javascript" src="../js/survey.js">	</script>   
		<!-- Optional theme -->
	  	<link rel="stylesheet" href="../assets/css/bootstrap-theme.min.css">
	  	<link rel="stylesheet" href="../assets/css/style.css">
	    <!-- Fonts and Icons -->
         <link href="http://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
		 
	<script type="text/javascript">
		function fncGenProfile()
		{
			document.inst.method="post";
			document.inst.action="/onlineSurvey";
			document.inst.submit();	 	
		}
	</script>
		 
	<style type="text/css">
		
	.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 10px 24px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
    height: 40px;   
    border-radius: 0;
}

.button1 {
    background-color: #d44348; 
    color: black; 
    border: 2px solid #82181c;
}

.button1:hover {
       color: #d44348;
    background-color: #fff;
    border-style: solid;
    border-color: #82181c;
    text-decoration: none
}

.button2 {
    background-color: #e4292f; 
    color: #fff; 
    border: 2px solid #82181c;
	border-radius:30px;
}

.button2:hover {
    background-color: #fff;
    color: #e4292f;
}

.button3 {
    background-color: white; 
    color: black; 
    border: 2px solid #f44336;
}

.button3:hover {
    background-color: #f44336;
    color: white;
}

.button4 {
    background-color: white;
    color: black;
    border: 2px solid #e7e7e7;
}

.button4:hover {background-color: #e7e7e7;}

.button5 {
    background-color: white;
    color: black;
    border: 2px solid #555555;
}

.button5:hover {
    background-color: #555555;
    color: white;
}
	body {
 		 font-size: calc(0.75em + 1vmin);     
	}
	.user-name{padding:5px 0;    color: #e4292f;}
	</style>
		
</head>

<body>
	<form name="inst" method="post" enctype="multipart/form-data">
	<table width="80%" cellpadding="7" cellspacing="0" align="center" style="background-color: #ffffff" >
		<tr>
			<td>
				<p style="font-size:20px;">
					${ins}
				</p> 				
				<input type="button" Style="font-weight:bold;" class="button button2" value=" Proceed " onclick="fncGenProfile()">
			</td> 
		</tr>
	</table>


</form>
</body>
</html>