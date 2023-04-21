<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
     <title> :: EASYSURVEY :: </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 	 <script type="text/javascript" src="./js/jquery-3.6.4.js"></script>
    <script type="text/javascript" src="./js/jquery.validate.js"></script>
    <script type="text/javascript" src="./js/validateForm.js"></script>
    <style>
  .filter-button {
    background: linear-gradient(to bottom, #004F6E, #2C95B6);
    color: #fff;
    border: none;
    border-radius: 20px;
    padding: 10px 20px;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  
  .filter-button:hover {
    background: linear-gradient(to bottom, #2C95B6, #64B6D9);
    color: #004F6E;
  }
  
  .filter-button:active {
    background: #004F6E;
    color: #fff;
  }
  
  .fa-filter {
    margin-right: 5px;
  }
  .error {
  				color: red;
				}
</style>
<!-- Jsp variable -->
<c:set var="dateFrom" value='${payload.fromDate}'></c:set>
<c:set var="dateTo" value='${payload.toDate}'></c:set>
<c:set var="surveyId" value='${payload.surveyId}'></c:set>

 <script type="text/javascript">
    function fncSearch1()
    {
    	var clientId=document.viewEmp.clientId.value;
    	document.viewEmp.method="post";
    	document.viewEmp.action="/viewSurvey2?clientId="+clientId;
    	document.viewEmp.submit();
    }
    </script>

</head>
<body>
<!-- Filter Page -->
  <div class="container">
<div class="filter-page" id="filter-page" >
    <div class="filter-modal">
      <h2>Filter Employees</h2>
      <form method="post" action="earchEmployee" name="viewEmp">
    <div class="form-row">
    	<div class="form-group col-md-6">
      		<label for="clientName">Client Name:</label>
  			<select id="clientId" name="clientId"  class="form-control" onchange="fncSearch1()">
    			<option value="">--Select--</option>
    			<c:forEach items="${clients}" var="client">
      				<option value="${client.id}" ${client.id == clientId ? 'selected' : ''}>${client.clientName}</option>
    			</c:forEach>
  			</select>
  		</div>								
  		<div class="form-group col-md-6">
  			<label for="surveyName" >Survey Name:</label>
  			<select id="surveyid" name="surveyid"  class="form-control">
    			<option value="">--Select--</option>
    			<c:forEach var="sur" items="${surveyList}" varStatus="status">
					<option value="${sur.surveyId}">${sur.surveyName}</option>
				</c:forEach>
  			</select>
  		</div>
      </div>
     <div class="form-row">
        <div class="form-group col-md-6">
          <label for="employeeId">Employee Id:</label>
          <input type="text" class="form-control" id="employeeId" name="employeeId" value="${payload.employeeId}">
        </div>
        <div class="form-group col-md-6">
          <label for="name">Name:</label>
          <input type="text" class="form-control" id="empName" name="empName"  value="${payload.name}">
        </div>
        </div>
        <div class="form-row">
        <div class="form-group col-md-6">
         <label for="accesskey">Access Key:</label>
          <input type="text" class="form-control" id="accesskey" name="accesskey" value="${payload.accesskey}">
        </div>
        <div class="form-group col-md-6">
        <label for="status">Status:</label>
  		<select id="status" name="status"  class="form-control">
    		<option value="">--Select--</option>
    		<option value="0" ${payload.status == '0' ? 'selected' : ''}>Not Started</option>
    		<option value="1" ${payload.status == '1' ? 'selected' : ''}>Incomplete</option>
    		<option value="2" ${payload.status == '2' ? 'selected' : ''}>Completed</option>
  		</select>
  		</div>
  		</div>
    <div class="form-row">
        <div class="form-group col-md-6">
          <label for="dateFrom">Reg Date From:</label>
          <input type="date" class="form-control" id="dateFrom" name="dateFrom" min="2020-01-01" max="<%= java.time.LocalDate.now() %>">
        </div>
        <div class="form-group col-md-6">
          <label for="dateTo">Reg Date To:</label>
          <input type="date" class="form-control" id="dateTo" name="dateTo" min="2020-01-01"  max="<%= java.time.LocalDate.now().plusYears(2) %>">
        </div>
     </div>
        <button type="submit" class="btn btn-primary" id="filter-submit">Search</button>
        <button type="reset" class="btn btn-secondary" >Reset</button>
      </form>
    </div>
  </div>

		<!-- <div class="filter-icon" onclick="showFilterPage()" id="filter-button">
    <button class="filter-button">
  <i class="fas fa-filter"></i> Filter
</button>
</div> -->
<div class="SurveyEndDate">
<p>Survey End Date : ${surveyEndDate} ${surveyStatus}</p>

</div>
	<div class="container" id="divTable">
  </div>
		<h1>Employee Table</h1>
		<div class="table-responsive">
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th scope="col">Sr.No.</th>
					<th scope="col">Employee ID</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Accesskey</th>
					<th scope="col">Reporting Manager Name</th>
					<th scope="col">Reporting Manager ID</th>
					<th scope="col">Survey Date</th>
					<th scope="col">Survey Status</th>
					<th scope="col">View Survey</th>
					<th scope="col">Edit</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="employee" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${employee.employeeId}</td>
						<td>${employee.name}</td>
						<td>${employee.email}</td>
						<td>${employee.accesskey}</td>
						<td>${employee.reportingManagerName}</td>
						<td>${employee.reportingManagerId}</td>
						<td>NA</td>
						<td>NA</td>
						<td><a href="#" ><span class="btn btn-info">View</span></a></td>
						<td><a href="/editEmployee?empId=${employee.id}" ><span class="btn btn-primary">Edit</span></a></td>
            			<td><a href="/deleteEmployee?empId=${employee.id}" ><span class="btn btn-danger">Delete</span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	<!-- Filter Page -->
  </div>
<!-- JavaScript code -->
<script>
  function showFilterPage() {
    var filterPage = document.getElementById("filter-page");
    filterPage.style.display = "block";
    //Hide Filter Button
    var filterDiv = document.getElementById("filter-button"); // get reference to the div element
    filterDiv.style.display = "none";
  }
  
  function hideFilterPage() {
    var filterPage = document.getElementById("filter-page");
    filterPage.style.display = "none";
    //Show Filter Button
    var filterDiv = document.getElementById("filter-button"); // get reference to the div element
    filterDiv.style.display = "block";
  }
  // Dynamic DateTo
  
</script>
<script>
	$(document).ready(function() {
		//Date Coming From Search Payload
		var dateFrom = `${dateFrom}`;
		var dateTo = `${dateTo}`;
		if(dateFrom.length >0){
			$('#dateFrom').val(dateFrom);
		}
		if(dateFrom.length >0){
			$('#dateTo').val(dateTo);
		}
		//Dynamic Min Date For DateTo field
		 $("#dateFrom").on("change", function() {
      var selectedDate = $(this).val();
      console.log(selectedDate);
      $("#dateTo").attr("min", selectedDate);
      if ($("#dateTo").val() < selectedDate) {
        $("#dateTo").val(selectedDate);
      }
    });

    $("#dateTo").on("change", function() {
      var selectedDate = $(this).val();
      var minDate = $("#dateFrom").val();
      if (selectedDate < minDate) {
        $(this).val(minDate);
      }
    });
		
    $('#clientName').change(function() {
        var clientId = $(this).val();
        $.ajax({
            type: 'GET',
            url: '/getSurveyByClientId',
            data: {clientId: clientId},
            success: function(surveys) {
            	var ids = "${payload.surveyId}";
            	var select = $('#surveyName');
            	$.each(surveys, function(index, survey) {
            	    var option = $('<option>').val(survey.surveyId).text(survey.surveyName);
            	    if (survey.surveyId === ids) {
            	        option.attr('selected', true);
            	    }
            	    select.append(option);
            	});
            }
        });
    });
});
	</script>
</body>
</html>