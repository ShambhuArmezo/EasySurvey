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
  	
  function fncAddDimension()
  {
  	if(document.addDimension.surveyId.value=="")
  	{
  		alert("Please Select survey name");
  		return false;
  	}
  	
  	if(document.addDimension.dimension.value=="")
  	{
  		alert("Please add Dimension name");
  		return false;
  	}
  	
  	document.addDimension.method="post";
  	document.addDimension.action="/addDimenPro";
  	document.addDimension.submit();	 	
  }
  
  function fncViewDimension()
  {
	  var surveyId=document.addDimension.surveyId.value;
  	if(surveyId=="")
  	{
  		alert("Please Select survey name");
  		return false;
  	}
  	
  	document.addDimension.method="post";
  	document.addDimension.action="/showAllDimension?surveyId="+surveyId;
  	document.addDimension.submit();	 	
  }
  function fncEditDimension(dimensionid)
  {
	  document.addDimension.method="post";
	  document.addDimension.action="/editDimension?dimensionids="+dimensionid;
	  document.addDimension.submit();
  }
  	
  </script>
  
  </head>
  <body>
    <div class="container">
      <h2>ADD DIMENSION</h2>
     <form name="addDimension" method="post" enctype="multipart/form-data">
     <input type="hidden" name="dimensionid" value="${dimensionMaster.dimensionid}" />
        <div class="form-group">
          <label for="surveyName">Survey Name:</label>
          <select name="surveyId" id="surveyId" style="width:150px;">
					<option  value="">-- Select --</option>
					 <c:forEach var="sur" items="${surveys}" varStatus="status">
					<option value="${sur.surveyId}" ${sur.surveyId == survey ? 'selected' : ''}>${sur.surveyName}</option>
					</c:forEach>
					</select>
        </div>
        <div class="form-group">
          <label for="dimensionName">Dimension Name:</label>
          <input type="text" class="form-control" id="dimension" name="dimension" value="${dimensionMaster.dimension}">
        </div>
        <button type="button" class="btn btn-primary" onclick="fncAddDimension()">Add</button>
        <input type="button" class="btn btn-primary" value="View" onclick="fncViewDimension()" />
      </form><br>
      <table class="table">
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Survey Name</th>
            <th>Dimension Name</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
         <c:forEach items="${views}" var="view" varStatus="status">
          <tr>
            <td>${status.count}</td>
            <td>${view.surveyName}</td>
            <td>${view.clientName}</td>
            <td>${view.dimensionName}</td>
            <td><input type="button" class="btn btn-primary" value="Edit" onclick="fncEditDimension(${view.dimensionId})" /></td>
            <td><a href="/deleteDimension?dimensionId=${view.dimensionId}" ><span class="btn btn-danger">Delete</span></a></td>
          </tr>
          </c:forEach>
          
          <!-- Add more rows for additional dimensions -->
        </tbody>
      </table>
      
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
