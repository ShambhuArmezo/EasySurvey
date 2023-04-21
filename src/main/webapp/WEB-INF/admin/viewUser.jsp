<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title> :: EASYSURVEY :: </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
  	 
  
  </head>
  <body>
    <div class="container">
      <h2>View Client</h2><br>
      <form name="editUser" method="post" enctype="multipart/form-data">
     <table class="table">
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Login Id</th>
            <th>Password</th>
            <th>Client Name</th>
            <th>Client Short Name</th>
            <th>Email Id</th>
            <th>Contact</th>
            <th>Logo</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${users}" var="user" varStatus="status">
          <tr>
            <td>${status.count}</td>
			<td>${user.userid}</td>
			<td>${user.password}</td>
			<td>${user.clientName}</td>
			<td>${user.cmpShortName}</td>
			<td>${user.emailId}</td>
			<td>${user.contactNo}</td>
			<td><img id="logo" src="/Image?name=${user.photoKey}.jpg" style="height: 60px;"></td>
			<td><button type="button" class="btn btn-primary" onclick="editUsers(${user.id})">Edit</button></td>
          </tr>
         </c:forEach>
         
          <!-- Add more rows for additional surveys -->
        </tbody>
      </table>
        
       <script language="javascript">
  		function editUsers(id)
		{
  			document.editUser.method="post";
		  	document.editUser.action="/viewUpdateUser?id="+id;
		  	document.editUser.submit();
		}
	</script>
      </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
