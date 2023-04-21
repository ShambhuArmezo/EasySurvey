<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link href="../media/css/demo_page.css" rel="stylesheet" type="text/css">
	<link href="../media/css/demo_table_jui.css" rel="stylesheet" type="text/css">
	<link href="../media/css/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css">
	<link href="../media/css/TableTools_JUI.css" rel="stylesheet" type="text/css">
	<link href="../media/css/TableTools.css" rel="stylesheet" type="text/css">
	<link href="../media/css/ColVis.css" rel="stylesheet" type="text/css">
	 
	<script type="text/javascript" charset="utf-8" src="../media/js2/jquery.js"></script> 
	<script type="text/javascript" charset="utf-8" src="../media/js2/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf-8" src="../media/js/ZeroClipboard.js"></script>
	<script type="text/javascript" charset="utf-8" src="../media/js/TableTools.js"></script>
	<script type="text/javascript" charset="utf-8" src="../media/js/ColVis.js"></script>

	<script type="text/javascript" charset="utf-8">  
		        $(document).ready( function ()
				 {
	
							var oTable = $('#example').dataTable( {
								//"sDom": 'C<"clear">lfrtip',
								

			

								"bJQueryUI":true,
	 						    "sPaginationType": "full_numbers",
								"sDom": 'CTR<"clear"><"H"lfr>t<"F"ip<',
								//"sDom": 'R<"H"lfr>t<"F"ip<',
									"oTableTools": {
										"aButtons": [
										"copy",
										"print",
										{
										"sExtends":    "collection",
										"sButtonText": "Save",
										"aButtons":    [ "csv", "xls", "pdf" ]
										}
									]}
								
							} );
	
					} );
	</script>
</head>
<body>
</body>
</html>