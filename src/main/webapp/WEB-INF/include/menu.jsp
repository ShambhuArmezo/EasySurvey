
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en"> 
<head>

	<link rel="stylesheet" type="text/css" href="../include/slidemenucss/ddlevelsmenu-base.css" />
	<link rel="stylesheet" type="text/css" href="../include/slidemenucss/ddlevelsmenu-topbar.css" />
	<link rel="stylesheet" type="text/css" href="../include/slidemenucss/ddlevelsmenu-sidebar.css" />
	
	<script type="text/javascript" src="../include/slidemenucss/ddlevelsmenu.js"></script>
	<script type="text/javascript" src="../js/util.js"></script>
	
</head>
<body> 

	<div id="ddtopmenubar" class="mattblackmenu">
		<ul> 
			
			<li><a href="javascript:void(0)" rel="submenuMSG">USER MGMt</a></li>
			
			<li><a href="javascript:void(0)" rel="submenuEmployee">PARTICIPANTS&nbsp;&nbsp;</a></li>
			
			
			<li><a href="javascript:void(0)" rel="submenuReports">SURVEY REPORTS&nbsp;&nbsp;</a></li>
			<li><a href="changePass" >&nbsp;&nbsp;CHANGE PASSWORD&nbsp;&nbsp;</a></li>
			
			<li><a href="">LOGOUT</a></li> 
			
		</ul>    
	</div> 
	<table width="100%">
	   <tr><td class="txt-red" align="left"  valign="bottom">Signed in as ,&nbsp;&nbsp;</td></tr>
	   <tr><td>&nbsp;</td></tr> 
	</table> 

	<script type="text/javascript">
	ddlevelsmenu.setup("ddtopmenubar", "topbar") //ddlevelsmenu.setup("mainmenuid", "topbar|sidebar")
	</script>

	 <ul id="submenuMSG" class="ddsubmenustyle"> 
    	
    	
    	<li><a href="addUser">Add User</a></li>
    	<li><a href="userList">View User List</a></li>
    	
    </ul>

    <ul id="submenuEmployee" class="ddsubmenustyle"> 
    	<!-- <li><a href="addEmp">REGISTER PARTICIPANTS</a></li> -->
    	
    	<li><a href="uploadEmp">UPLOAD PARTICIPANTS VIA EXCEL</a></li>
    	
		<li><a href="empList">VIEW PARTICIPANTS</a></li> 
		
		
    	<li><a href="uploadParMan">UPLOAD PARTICIPANTS AND MANAGER MAPPING</a></li>
		<li><a href="uploadManPH1">UPLOAD Accesskey MAPPING</a></li>
		<li><a href="uploadPerformance">Upload Performance</a></li>
		<li><a href="jswUploadPerformance">JSW Upload Performance</a></li>
    	
		
    </ul>  
    
    <ul id="submenuMaster" class="ddsubmenustyle">
		
		<li><a href="addDiv">ADD DIVISION</a></li>
		 
	</ul>
    
    <ul id="submenuReports" class="ddsubmenustyle">
		
			<li><a href="questionWiseReport">QUESTION WISE REPORTS</a></li>
			<li><a href="responsesReport">EXCEL REPORTS</a></li>
			
		<li><a href="serveyEndDate">SURVEY END DATE</a></li>
	</ul> 
</body>  
</html>  


