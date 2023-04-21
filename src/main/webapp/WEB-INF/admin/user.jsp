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
		
		function checkAddUser()
			{
			var un=document.forms[0].userid.value;
			  var company=document.forms[0].clientName.value;
			  var companyShortName=document.forms[0].cmpShortName.value;
			  var email=document.forms[0].emailId.value;
			  var contact=document.forms[0].contactNo.value;
			  var file = document.getElementById("imageFile");
			  
			  un = un.replace(/^\s*|\s*$/g,"");
			  company = company.replace(/^\s*|\s*$/g,"");
			  companyShortName = companyShortName.replace(/^\s*|\s*$/g,"");
			  email = email.replace(/^\s*|\s*$/g,"");
			  contact = contact.replace(/^\s*|\s*$/g,"");
			  
	          
	          if(un=="")
	          {
	              alert("Please enter Login ID:");
	              document.forms[0].userid.focus();
	              return false;
	          }
			  if(company=="")
	          {
	        	  alert("Please enter Client Name:");
	              document.forms[0].clientName.focus();
	              return false;
	          }
	          if(companyShortName=="")
	          {
	        	  alert("Please enter Client Short Name:");
	              document.forms[0].cmpShortName.focus();
	              return false;
	          }
	          
	          if(email=="")
	          {
	              alert("Please enter Email ID:");
	              document.forms[0].emailId.focus();
	              return false;
	          }
	          else
		      {
	      		var emailstr=document.forms[0].emailId.value;
	      		var fieldLength = emailstr.length; 
	      		var err01   = "email field can not be blank!"; 
	      		var err02   = "Invalid E-mail ID"; 
	      	 
	      		if ( fieldLength < 1 )
	      		{ 
	      			alert( err01 ); 
	      				document.forms[0].emailId.focus();
	      			return false;
	      		}
	      		else
	      		{ 
	      			if( /^[\w\.-]{1,}\@([\da-zA-Z-]{1,}\.){1,}[\da-zA-Z-]+$/.test( emailstr )) 
	      			{ 
	      				
	      			} 
	      			else
	      			{ 
	      				alert( err02 ); 
	      					document.forms[0].emailId.focus();
	      				return false;
	      			} 
	      		}
	           }
	          
	          if(contact=="")
	          {
	              alert("Please enter Contact Number:");
	              document.forms[0].contactNo.focus();
	              return false;
	          }
	          
	          if(file.files.length == 0 ){
	        	  alert("Please Upload Client Logo:");
	              document.forms[0].imageFile.focus();
	              return false;
	          }
			  
	          document.addUser.addbtn.value = "Please wait...";
	    	  document.addUser.addbtn.disabled = true;
	          document.forms[0].method="post";
	          document.forms[0].action="/addUserPro";
	          document.forms[0].submit();
			}
  </script> 
  
  </head>
  <body>
    <div class="container">
      <h1>:: Add New Client ::</h1>
     <form name="addUser" method="post" enctype="multipart/form-data">
    
        <div class="form-group">
          <label for="userid">Login ID:</label>
          <input type="text" class="form-control" id="userid" name="userid" size="20" style="width:200">
        </div>
        <div class="form-group">
          <label for="clientName">Client Name:</label>
          <input type="text" class="form-control" id="clientName" name="clientName" size="20" style="width:200">
        </div>
        <div class="form-group">
          <label for="cmpShortName">Client Short Name:</label>
          <input type="text" class="form-control" id="cmpShortName" name="cmpShortName" size="20" style="width:200">
        </div>
        <div class="form-group">
          <label for="emailId">Email:</label>
          <input type="email" class="form-control" id="emailId" name="emailId" size="20" style="width:200">
        </div>
        <div class="form-group">
          <label for="contactNo">Contact Number:</label>
          <input type="tel" class="form-control" id="contactNo" name="contactNo" size="20" style="width:200">
        </div> 
        <div class="form-group">
          <label for="logo">Upload Logo (PNG format only):</label>
          <input type="file" class="form-control-file" id="imageFile" name="imageFile" accept=".png">
        </div>
        <button type="button" class="btn btn-primary" name="addbtn" onclick="checkAddUser()">Add</button>
        <input class="btn btn-primary" name="reset" type="reset" value="Reset">
      </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
