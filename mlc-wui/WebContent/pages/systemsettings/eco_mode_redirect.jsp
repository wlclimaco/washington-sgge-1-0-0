<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../styles/base.css" ></link>	
		<link rel="stylesheet" type="text/css" href="../styles/yahoo_reset_fonts_grids.css" ></link>	
		<link rel="stylesheet" type="text/css" href="../styles/base.css"></link>
		<link rel="stylesheet" type="text/css" href="../thirdparty/jquery/ui/jquery-ui-1.8.20.custom.css"></link>
	</head>
	<body>
		<input type="hidden" id="messageError" value="${messageError}"/>
		<input type="hidden" id="messageSuccess" value="${messageSuccess}"/>
		<script src="../thirdparty/jquery/jquery-1.7.2.min.js" ></script>
		<script src="../thirdparty/jquery/jquery-ui-1.8.20.custom.min.js" ></script>
		<script src="../thirdparty/jquery/jquery.address-1.4.min.js" ></script>
		<script src="../scripts/util/namespace_init.js" ></script>
		<script src="../scripts/util/page.js" ></script>
		<script type="text/javascript">
			
		$(document).ready(function() {
				
				sensus.util.page.initProgressBar();
				
				var sMessageError  = $("#messageError").val();
				var sMessageSuccess= $("#messageSuccess").val();
			
				//Start Progress Bar
				$('.formError').remove();
				var oLoading = $('#loading');
				var oProgressbar = $("#progressbar");
									
				oLoading.dialog('open');
				oProgressbar.progressbar( {
					value : 1
				});
				(function progress() {
					var value = oProgressbar.progressbar('value');
					oProgressbar.progressbar('value', value + 1);
					if (value < 100) {
						setTimeout(progress, 100);
					}	
				}());
				
				if (sMessageError != '') {
					
					window.location = "../list.action#/systemsettings/ajax.list.action?messageError=" + escape(sMessageError);	
				
				} else {
				
					window.location = "../list.action#/systemsettings/ajax.list.action?messageSuccess=" + escape(sMessageSuccess);
				
				}

			});
			
		</script>
	</body>
</html>