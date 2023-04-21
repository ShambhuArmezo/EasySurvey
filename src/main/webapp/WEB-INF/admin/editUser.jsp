<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Client</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script type="text/javascript" src="./js/jquery-3.6.4.js"></script>
    <script type="text/javascript" src="./js/jquery.validate.js"></script>
    <script type="text/javascript" src="./js/additional-methods.js"></script>
    <script type="text/javascript" src="./js/validateForm.js"></script>
</head>
<body>
<div class="container">
    <h1>Update Client</h1>
    <form method="POST" action="/updateUser" enctype="multipart/form-data">
     <input type="hidden" name="id" value="${user.id}" />
     <input type="hidden" name="photoKey" value="${user.photoKey}" />
     <div class="row mb-3">
    	 <div class="col-3">
    	<label for="userid">Login ID:</label>
        </div>
    	 <div class="col-6">
       	<input type="text" id="userid" readonly="readonly" name="userid" value="${user.userid}" class="form-control">
        </div>
        </div>
		<div class="row mb-3">
    	 <div class="col-3">
        <label for="clientName">Client Name:</label>
        </div>
    	 <div class="col-6">
        <input type="text" id="clientName" name="clientName"  value="${user.clientName}" class="form-control">
        </div>
        </div>
		<div class="row mb-3">
         <div class="col-3">
        <label for="cmpShortName">Client Short Name:</label>
        </div>
    	 <div class="col-6">
        <input type="text" id="cmpShortName" name="cmpShortName"  value="${user.cmpShortName}" class="form-control">
        </div>
        </div>
       <div class="row mb-3">
    	 <div class="col-3">
        <label for="emailId">Email ID:</label>
        </div>
    	 <div class="col-6">
        <input type="email" id="emailId" name="emailId"  value="${user.emailId}" class="form-control">
        </div>
        </div>
        <div class="row mb-3">
    	 <div class="col-3">
        <label for="mobile">Contact Number:</label>
        </div>
    	 <div class="col-6">
        <input type="number" id="contactNo" name="contactNo"  value="${user.contactNo}" class="form-control">
        </div>
        </div>
        <div class="row mb-3">
    	 <div class="col-3">
        <label for="photo">Photo:</label>
        </div>
        <img id="logo" src="/Image?name=4Q4NS.jpg" style="height: 60px;">
    	 <div class="col-6">
        <input type="file" id="photo" name="photo" accept=".jpg, .jpeg">
        </div></div>   
     <div class="row mb-3">
        <div class="col-9  text-center">
        <button type="submit" class="btn btn-primary btn-lg">Update</button>
        </div></div>
     
    </form>
    </div>
    </body>
    </html>